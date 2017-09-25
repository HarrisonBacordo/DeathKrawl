package GameStates;


import Entity.KeyInput;
import ResourceLoader.Resources;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sean on 17/09/17.
 */
public class DeathState{
    private KeyInput keyInput;

    private int bWidth = 250, bHeight = 50, bBuffer = 30;
    private int bX = 330, bY = 200;
    private int selectBuffer = 20, selectX = bX-(selectBuffer/2), selectY = bY-(selectBuffer/2);
    public Rectangle load = new Rectangle(bX, bY+(bHeight*2)+(bBuffer*2), bWidth, bHeight);
    public Rectangle quit = new Rectangle(bX, bY+(bHeight*3)+(bBuffer*3), bWidth, bHeight);
    public Rectangle select = new Rectangle(selectX, selectY, bWidth+selectBuffer, bHeight+selectBuffer);

    public int status;
    public int[] loc;
    private StateManager st;

    public DeathState(KeyInput keyInput, StateManager stateManager){
        this.keyInput = keyInput;
        this.status = 0;
        this.loc = new int[]{
                bY+(bHeight*2)+(bBuffer*2)-(selectBuffer/2),
                bY+(bHeight*3)+(bBuffer*3)-(selectBuffer/2)};
        this.st = stateManager;

    }

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 960, 565);
        g.setColor(Color.red);
        g2d.fill(quit);
        g2d.fill(load);

        g2d.setColor(Color.red);
        Font font = new Font("Serif", Font.PLAIN, 100);
        g2d.setFont(font);

        g.drawString("DEFEAT", 220, 150);

        g2d.setColor(Color.yellow);
        font = new Font("Serif", Font.PLAIN, 40);
        g2d.setFont(font);

        g.drawString("Load", bX+selectBuffer*4, bY+(bHeight*2)+(bBuffer*2)+selectBuffer+15);
        g.drawString("Quit", bX+selectBuffer*4, bY+(bHeight*3)+(bBuffer*3)+selectBuffer+15);

        select.setLocation(selectX, selectY);
        g2d.draw(select);
    }

    public void tick() {
        if(keyInput.isMenuUp()) {
            status--;
            if(status < 0){
                status = 1;
            }
            selectY = loc[status];
            keyInput.setMenuUp(false);
        }
        if(keyInput.isMenuDown()) {
            status++;
            if(status > 1){
                status = 0;
            }
            selectY = loc[status];
            keyInput.setMenuDown(false);
        }
        if(keyInput.isEnter()){
            if(status == 0){
                System.out.println("load");
            }
            else if(status == 1){
                st.setState(STATE.MENU);
            }
            keyInput.setEnter(false);
        }
    }
}