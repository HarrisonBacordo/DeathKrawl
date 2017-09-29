import Camera.Camera;
import Component.ComponentManager;
import GameStates.STATE;
import GameStates.StateManager;
import HUD.HeadsUpDisplay;
import Entity.KeyInput;
import LevelGenerator.*;
import LevelGenerator.Rooms.TYPE;
import ResourceLoader.Resources;
import Util.AudioPlayer;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    public static int WINDOW_WIDTH = 960;
    public static int WINDOW_HEIGHT = 565;
    private double scaleFactorX, scaleFactorY;
    private boolean isRunning;
    private Thread thread;
    private KeyInput inputHandler;
    private Camera camera;
    private Resources resourceManager;
    private Level level;

    private StateManager stateM;

//    --------------HUD-------------------
    private HeadsUpDisplay HUD;

    public Game(){

        Window w = new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "This is my game", this); // 960 x 540
        WINDOW_HEIGHT = (int)w.getHeight();
        WINDOW_WIDTH = (int)w.getWidth();
        //Calculate Scale factor
        scaleFactorX = (w.getWidth() / WINDOW_WIDTH);
        scaleFactorY = (w.getHeight() / WINDOW_HEIGHT);

        resourceManager = new Resources();
        inputHandler = new KeyInput();
        ComponentManager.setKeyHandler(inputHandler);
        addKeyListener(inputHandler);

        Random r = new Random();

        stateM = new StateManager(inputHandler, WINDOW_HEIGHT, WINDOW_WIDTH);
        //LEVEL INIT
        level = new Level(15, 960, 544);
        camera = new Camera(level.getCurrentRoom().getX(), level.getCurrentRoom().getY(), 960, 544);

//        -----------------HUD-----------------
        HUD = new HeadsUpDisplay(WINDOW_WIDTH, WINDOW_HEIGHT);

        isRunning = false;
        start();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60d;
        double ns = 1000000000D / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
//        Start background music
        AudioPlayer audioPlayer = new AudioPlayer("bg-wii.wav");
        audioPlayer.play();

        while (isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            try { //Slows down the frames
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(delta >= 1) {
                tick();
                delta--;
            }
            HUD.setRooms(level.getRooms());
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //system.out.println(frames);
                frames = 0;
            }

            do {
                Thread.yield();
            } while (System.nanoTime() - lastTime < 1e9/60);
        }

        stop();
    }

    /**
     * Updates everything in the game at each tick.
     */
    private void tick(){
        if(stateM.getState() == STATE.GAME) {
            if(inputHandler.isEscape()){
                stateM.setState(STATE.PAUSE);
                inputHandler.setEscape(false);
            }else{
                camera.tick(level.getCurrentRoom());
                //LEVEL TICK
                level.tick();
            }
        }else if(stateM.getState() == STATE.MENU){
            stateM.tickSelect('m');
        }else if(stateM.getState() == STATE.VICTORY){
            stateM.tickSelect('v');
        }else if(stateM.getState() == STATE.DEATH){
            stateM.tickSelect('d');
        }else if(stateM.getState() == STATE.PAUSE){
            stateM.tickSelect('p');
        }
    }

    /**
     * Renders everything in the game. Uses a buffered strategy with a buffer size of 3 to smooth out rendering.
     */
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        //Gets the buffers graphics image
        Graphics g = bs.getDrawGraphics();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//        g2d.scale(scaleFactorX, scaleFactorY);
        ///////////RENDER IN HERE////////////

        //Temp background

        if(stateM.getState() == STATE.GAME) {//Temp background
            g.setColor(new Color(66, 40, 53));
            g.fillRect(0, 0, getWidth(), getHeight());
            g2d.translate(-camera.getX(), -camera.getY());
            level.render(g);
            g2d.translate(camera.getX(), camera.getY());
            HUD.render(g2d);
        }else if(stateM.getState() == STATE.VICTORY) {
            stateM.renderSelect('v', g, g2d);
        }else if(stateM.getState() == STATE.PAUSE) {
            g2d.translate(-camera.getX(), -camera.getY());
            level.render(g);
            g2d.translate(camera.getX(), camera.getY());
            stateM.renderSelect('p', g, g2d);
        }else if(stateM.getState() == STATE.DEATH) {
            stateM.renderSelect('d', g, g2d);
        }else if(stateM.getState() == STATE.MENU) {
            stateM.renderSelect('m', g, g2d);
        }


        //Dispose the graphics objects, efficiency boost
        g2d.dispose();
        g.dispose();

        //Show buffer content
        bs.show();
    }


    /**
     * Starts the game thread
     */
    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops the game thread effectively used for pause
     */
    private void stop(){
        isRunning = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
