package GameStates;


import Animations.Animation;
import Entity.KeyInput;
import ResourceLoader.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Created by Sean on 17/09/17.
 */
public class MenuState{
    private KeyInput keyInput;
    private Animation playButton, infoButton;
    private Animation[] animations;

    private int bWidth = 250, bHeight = 50, bBuffer = 30;
    private int bX = 330, bY = 200;
    private int selectBuffer = 20, selectX = bX-(selectBuffer/2), selectY = bY-(selectBuffer/2);
    public Rectangle play = new Rectangle(bX, bY, bWidth, bHeight);
    public Rectangle info = new Rectangle(bX, bY+bHeight+bBuffer, bWidth, bHeight);
    public Rectangle load = new Rectangle(bX, bY+(bHeight*2)+(bBuffer*2), bWidth, bHeight);
    public Rectangle quit = new Rectangle(bX, bY+(bHeight*3)+(bBuffer*3), bWidth, bHeight);
    //public Rectangle select = new Rectangle(selectX, selectY, bWidth+selectBuffer, bHeight+selectBuffer);

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

        playButton = new Animation(100, Resources.getAnimations());
        infoButton = new Animation(100, Resources.getAnimations());
        animations = new Animation[]{playButton, infoButton};
    }

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 960, 565);
        g.setColor(Color.red);
        g2d.fill(info);
        g2d.fill(quit);
        g2d.fill(play);
        g2d.fill(load);

        g2d.setColor(Color.red);
        Font font = new Font("Serif", Font.PLAIN, 100);
        g2d.setFont(font);

        g.drawString("DeathKrawl", 220, 150);

        g2d.setColor(Color.yellow);
        font = new Font("Serif", Font.PLAIN, 40);
        g2d.setFont(font);


        g.drawImage(getCurrentFrame(), 330, 200, 275, 75, null);
        // g.drawString("Play", bX+selectBuffer*4, bY+selectBuffer+15);
        g.drawString("Info", bX+selectBuffer*4, bY+bHeight+bBuffer+selectBuffer+15);
        g.drawString("Load", bX+selectBuffer*4, bY+(bHeight*2)+(bBuffer*2)+selectBuffer+15);
        g.drawString("Quit", bX+selectBuffer*4, bY+(bHeight*3)+(bBuffer*3)+selectBuffer+15);

        // select.setLocation(selectX, selectY);
        //g2d.draw(select);
    }

    private BufferedImage getCurrentFrame(){
        return playButton.getFrame();
    }

    public void tick() {
        animations[status].tick();
        if(keyInput.isMenuUp()) {
            status--;
            if(status < 0){
                status = 3;
            }
            selectY = loc[status];
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
                status = 0;
            }
            selectY = loc[status];
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
            keyInput.setEnter(false);
        }
    }

    private void tickSelect(int status) {
        if(status == 0){
            playButton.tick();
        }else if(status == 1){
            return;
        }else if(status == 2){
            return;
        }else if(status == 3){
            return;
        }
    }
}