package GameStates;


import Animations.Animation;
import Entity.KeyInput;
import ResourceLoader.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Sean on 17/09/17.
 */
public class MenuState{
    private KeyInput keyInput;
    private Animation playButton, infoButton, loadButton, quitButton;
    private Animation[] animations;

    private int bWidth = 250, bHeight = 50, bBuffer = 30;
    private int bX = 330, bY = 200;
    private int selectBuffer = 20, selectX = bX-(selectBuffer/2), selectY = bY-(selectBuffer/2);

    public int status;
    public int[] loc;
    private StateManager st;

    public MenuState(KeyInput keyInput, StateManager stateManager){
        this.keyInput = keyInput;
        this.status = 0;
        this.loc = new int[]{bY-(selectBuffer/2),
                bY+bHeight+bBuffer-(selectBuffer/2),
                bY+(bHeight*2)+(bBuffer*2)-(selectBuffer/2),
                bY+(bHeight*3)+(bBuffer*3)-(selectBuffer/2)};
        this.st = stateManager;

        playButton = new Animation(Resources.getPlayButton());
        infoButton = new Animation(Resources.getInfoButton());
        loadButton = new Animation(Resources.getLoadButton());
        quitButton = new Animation(Resources.getQuitButton());
        animations = new Animation[]{playButton, infoButton, loadButton, quitButton};
    }

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.black);
        g.fillRect(0, 0, StateManager.screenWidth, StateManager.screenHeight);
        g.drawImage(Resources.getImage("mainT"), StateManager.screenWidth/2-400, StateManager.screenHeight/10-30, 800, 225, null);
        g.drawImage(playButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90, 300, 90, null);
        g.drawImage(infoButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90+100, 300, 90, null);
        g.drawImage(loadButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90+200, 300, 90, null);
        g.drawImage(quitButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90+300, 300, 90, null);
    }

    private BufferedImage getCurrentFrame(){
        return animations[status].getFrame();
    }

    public void tick() {
        animations[status].tick();
        if(keyInput.isMenuUp()) {
            status--;
            if(status < 0){
                animations[0].refresh();
                status = 3;
            }
            tickSelect(status);
            animations[status].refresh();
            if(status != 3){
                animations[status+1].refresh();
            }
            keyInput.setMenuUp(false);
        }
        if(keyInput.isMenuDown()) {
            status++;
            if(status > 3){
                animations[3].refresh();
                status = 0;
            }
            tickSelect(status);
            animations[status].refresh();
            if(status != 0){
                animations[status-1].refresh();
            }

            keyInput.setMenuDown(false);
        }
        if(keyInput.isEnter()){
            if(status == 0){
                st.setState(STATE.GAME);
            }
            else if(status == 1){
                System.out.println("info");
            }
            else if(status == 2){
                System.out.println("load");
            }
            else if(status == 3){
                System.exit(1);
            }
            refreshAll();
            keyInput.setEnter(false);
        }
    }

    private void tickSelect(int status) {
        if(status == 0){
            playButton.tick();
        }else if(status == 1){
            infoButton.tick();
        }else if(status == 2){
            loadButton.tick();
        }else if(status == 3){
            quitButton.tick();
        }
    }

    private void refreshAll(){
        for(int i = 0; i < animations.length; i++){
            animations[i].refresh();
        }
    }
}