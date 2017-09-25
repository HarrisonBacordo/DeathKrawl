package GameStates;


import Entity.KeyInput;
import ResourceLoader.Resources;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sean on 17/09/17.
 */
public class VictoryState{
    private KeyInput keyInput;

    private int bWidth = 250, bHeight = 50, bBuffer = 30;
    private int bX = 330, bY = 200;
    private int selectBuffer = 20, selectX = bX-(selectBuffer/2), selectY = bY-(selectBuffer/2);
    public Rectangle exit = new Rectangle(bX, bY+(bHeight*2)+(bBuffer*2), bWidth, bHeight);
    public Rectangle select = new Rectangle(selectX, selectY, bWidth+selectBuffer, bHeight+selectBuffer);

    public int status;
    public int[] loc;
    private StateManager st;

    public VictoryState(KeyInput keyInput, StateManager stateManager){
        this.keyInput = keyInput;
        this.status = 0;
        this.st = stateManager;
    }

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.red);
        g2d.fill(exit);

        g2d.setColor(Color.red);
        Font font = new Font("Serif", Font.PLAIN, 100);
        g2d.setFont(font);

        g.drawString("VICTORY", bX, 150);

        g2d.setColor(Color.yellow);
        font = new Font("Serif", Font.PLAIN, 40);
        g2d.setFont(font);

        g.drawString("Exit", bX+selectBuffer*4, bY+(bHeight*2)+(bBuffer*2)+selectBuffer+15);
        select.setLocation(selectX, selectY);
        g2d.draw(select);
    }

    public void tick() {
        if(keyInput.isEnter()){
            st.setState(STATE.MENU);
            keyInput.setEnter(false);
        }
    }
}