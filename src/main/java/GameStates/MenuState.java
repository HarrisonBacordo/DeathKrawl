package GameStates;


import Animations.Animation;
import Entity.KeyInput;
import ResourceLoader.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * MenuState class, the state which will be shown on game start
 *
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
    private StateManager stateM;

    public MenuState(KeyInput keyInput, StateManager stateManager){
        this.keyInput = keyInput;
        this.status = 0;
        this.stateM = stateManager;

        //Initialising all the new animations
        playButton = new Animation(Resources.getPlayButton());
        infoButton = new Animation(Resources.getInfoButton());
        loadButton = new Animation(Resources.getLoadButton());
        quitButton = new Animation(Resources.getQuitButton());
        animations = new Animation[]{playButton, infoButton, loadButton, quitButton};
    }

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.black);
        g.fillRect(0, 0, StateManager.screenWidth, StateManager.screenHeight);
        g.drawImage(Resources.getImage("mainT"), StateManager.screenWidth/2-(int)((StateManager.screenWidth*0.7)/2), StateManager.screenHeight/10-30, (int)(StateManager.screenWidth*0.7), (int)(StateManager.screenHeight*0.2), null);
        g.drawImage(playButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90, 300, 90, null);
        g.drawImage(infoButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90+100, 300, 90, null);
        g.drawImage(loadButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90+200, 300, 90, null);
        g.drawImage(quitButton.getFrame(), StateManager.screenWidth/2-150, StateManager.screenHeight/2-90+300, 300, 90, null);
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
                stateM.setState(STATE.GAME);
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

    /**
     * The images are in an order, in an array, with the numbers corresponding to the position of the buttons.
     * Thus this method just selects which one to tick.
     * @param status current animation hovered
     */
    private void tickSelect(int status) {
        switch(status){
            case 0:
                playButton.tick();
                break;
            case 1:
                infoButton.tick();
                break;
            case 2:
                loadButton.tick();
                break;
            case 3:
                quitButton.tick();
                break;
        }
    }

    /**
     * Refreshs all the animations to only have selected one in red
     */
    private void refreshAll(){
        for(int i = 0; i < animations.length; i++){
            animations[i].refresh();
        }
    }
}c