import Components.ComponentManager;
import Entities.*;
import LevelGenerator.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    private boolean isRunning;
    private Thread thread;
    private KeyInput inputHandler;
    private Camera camera;

    private Level level;

    public Game(){
        Window w = new Window(960, 565, "This is my game", this); // 960 x 540
        inputHandler = new KeyInput();
        ComponentManager.setKeyHandler(inputHandler);
        addKeyListener(inputHandler);

        Random r = new Random();

        //LEVEL INIT
        level = new Level(15, 960, 544);


        camera = new Camera(0, 0, 960, 565);

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

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames);
                frames = 0;
            }
        }

        stop();
    }

    /**
     * Updates everything in the game at each tick.
     */
    private void tick(){
        camera.tick(level.player);

        //LEVEL TICK
        level.tick();
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
        ///////////RENDER IN HERE////////////

        //Temp background
        g.setColor(new Color(66, 40, 53));
        g.fillRect(0, 0, getWidth(), getHeight());

        g2d.translate(-camera.getX(), -camera.getY());


        //LEVEL RENDER
        level.render(g);



        g2d.translate(camera.getX(), camera.getY());
        /////////////////////////////////////

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
