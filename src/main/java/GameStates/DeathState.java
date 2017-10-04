package GameStates;


import Entity.KeyInput;

import java.awt.*;

/**
 * DeathState class, the state which will be shown upon player death
 *
 * Created by Sean on 17/09/17.
 */
public class DeathState{
    private KeyInput keyInput;

    private int bWidth = 250, bHeight = 50, bBuffer = 30;
    private int bX = 330, bY = 200;
    private int selectBuffer = 20, selectX = bX-(selectBuffer/2), selectY = bY-(selectBuffer/2);
    private int status;
    private StateManager stateM;

    public DeathState(KeyInput keyInput, StateManager stateManager){
        this.keyInput = keyInput;
        this.status = 0;
        this.stateM = stateManager;

    }

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 960, 565);
        g.setColor(Color.red);

        g2d.setColor(Color.red);
        Font font = new Font("Serif", Font.PLAIN, 100);
        g2d.setFont(font);

        g.drawString("DEFEAT", 220, 150);

        g2d.setColor(Color.yellow);
        font = new Font("Serif", Font.PLAIN, 40);
        g2d.setFont(font);

        g.drawString("Load", bX+selectBuffer*4, bY+(bHeight*2)+(bBuffer*2)+selectBuffer+15);
        g.drawString("Quit", bX+selectBuffer*4, bY+(bHeight*3)+(bBuffer*3)+selectBuffer+15);

    }

    public void tick() {
        //If you want to cycle up the menu
        if(keyInput.isMenuUp()) {

            status--;
            //If you reach threshold, reset
            if(status < 0){
                status = 1;
            }
            // selectY = selected[status];
            keyInput.setMenuUp(false);
        }
        //Cycling down
        if(keyInput.isMenuDown()) {
            status++;
            //If you reach threshold, reset
            if(status > 1){
                status = 0;
            }
            // selectY = selected[status];
            keyInput.setMenuDown(false);
        }
        if(keyInput.isEnter()){
            //Checks current selected option to execute
            if(status == 0){
                System.out.println("load");
            }
            else if(status == 1){
                stateM.setState(STATE.MENU);
            }
            keyInput.setEnter(false);
        }
    }
}