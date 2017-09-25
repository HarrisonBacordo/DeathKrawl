package GameStates;


import Entity.KeyInput;
import ResourceLoader.Resources;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sean on 17/09/17.
 */
public class PauseState{
    private KeyInput keyInput;

    private int bWidth = 250, bHeight = 50, bBuffer = 30;
    private int bX = 330, bY = 200;
    private int selectBuffer = 20, selectX = bX-(selectBuffer/2), selectY = bY-(selectBuffer/2);
    public Rectangle resume = new Rectangle(bX, bY, bWidth, bHeight);
    public Rectangle save = new Rectangle(bX, bY+bHeight+bBuffer, bWidth, bHeight);
    public Rectangle load = new Rectangle(bX, bY+(bHeight*2)+(bBuffer*2), bWidth, bHeight);
    public Rectangle exit = new Rectangle(bX, bY+(bHeight*3)+(bBuffer*3), bWidth, bHeight);
    public Rectangle select = new Rectangle(selectX, selectY, bWidth+selectBuffer, bHeight+selectBuffer);
    public Rectangle screen = new Rectangle(0, 0, 960, 565);

    public int status;
    public int[] loc;
    private StateManager st;

    public PauseState(KeyInput keyInput, StateManager stateManager){
        this.keyInput = keyInput;
        this.status = 0;
        this.loc = new int[]{bY-(selectBuffer/2),
                bY+bHeight+bBuffer-(selectBuffer/2),
                bY+(bHeight*2)+(bBuffer*2)-(selectBuffer/2),
                bY+(bHeight*3)+(bBuffer*3)-(selectBuffer/2)};
        this.st = stateManager;

    }

    public void render(Graphics g, Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        //g2d.setPaint(new Color(50, 50, 50, 180));
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fill(screen);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g.setColor(Color.black);
       // g.fillRect(0, 0, 960, 565);
        g.setColor(Color.red);

        g2d.fill(resume);
        g2d.fill(save);
        g2d.fill(exit);
        g2d.fill(load);

        g2d.setColor(Color.red);
        Font font = new Font("Serif", Font.PLAIN, 100);
        g2d.setFont(font);

        g.drawString("Pause", bX, 150);

        g2d.setColor(Color.yellow);
        font = new Font("Serif", Font.PLAIN, 40);
        g2d.setFont(font);

        g.drawString("Resume", bX+selectBuffer*4, bY+selectBuffer+15);
        g.drawString("Save", bX+selectBuffer*4, bY+bHeight+bBuffer+selectBuffer+15);
        g.drawString("Load", bX+selectBuffer*4, bY+(bHeight*2)+(bBuffer*2)+selectBuffer+15);
        g.drawString("Exit", bX+selectBuffer*4, bY+(bHeight*3)+(bBuffer*3)+selectBuffer+15);

        select.setLocation(selectX, selectY);
        g2d.draw(select);
    }

    public void tick() {
        if(keyInput.isMenuUp()) {
            status--;
            if(status < 0){
                status = 3;
            }
            selectY = loc[status];
            keyInput.setMenuUp(false);
        }
        if(keyInput.isMenuDown()) {
            status++;
            if(status > 3){
                status = 0;
            }
            selectY = loc[status];
            keyInput.setMenuDown(false);
        }
        if(keyInput.isEnter()){
            if(status == 0){
                st.setState(STATE.GAME);
            }
            else if(status == 1){
                System.out.println("save");
            }
            else if(status == 2){
                System.out.println("load");
            }
            else if(status == 3){
                st.setState(STATE.MENU);
            }
            keyInput.setEnter(false);
        }
    }
}