package GameStates;


import java.awt.*;

/**
 * Created by Sean on 17/09/17.
 */
public class MenuState{

    public int xPos = 960/2-110;
    public int yBase = 130, buffer = 10, y1 = 70, y2 = 190, y3 = 310, width = 220, height = 50;
    public Rectangle play = new Rectangle(xPos, y1+yBase, width, height);
    public Rectangle info = new Rectangle(xPos, y2+yBase, width, height);
    public Rectangle quit = new Rectangle(xPos, y3+yBase, width, height);
    public Rectangle select = new Rectangle(xPos-buffer, 310+yBase-buffer, 220+buffer*2, 50+buffer*2);

    public void render(Graphics g, Graphics2D g2d) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 960, 565);

        g2d.setColor(Color.red);
        g2d.fill(info);
        g2d.fill(quit);
        g2d.fill(play);
        g2d.draw(select);
    }

    public void tick() {

    }
}