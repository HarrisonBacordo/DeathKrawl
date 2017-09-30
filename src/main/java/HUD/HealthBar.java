package HUD;

import Entity.NinjaEntity;
import ResourceLoader.Resources;

import java.awt.*;

/**
 * This class represents and paints the health bar onto the canvas
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class HealthBar extends Canvas {

    /**
     * Renders the health bar onto the screen using the passed in graphics
     * @param g - graphics to render with
     */
    public void render(Graphics g) {
        super.paint(g);
        int x = 10; //initial x value

//        draw health hearts
        for(int i = 1; i < NinjaEntity.CURRENT_HEALTH + 1; i++) {
            g.setColor(Color.red);
            g.drawImage(Resources.getImage("HEART"), x, 10, 25, 25, null);
            x += 30;
        }
//        if NinjaEntity has a shield, draw that as well
        if(NinjaEntity.HAS_SHIELD) {
            x = 10;
            for (int i = 1; i < NinjaEntity.SHIELD_SIZE + 1; i++) {
                g.setColor(Color.red);
                g.drawImage(Resources.getImage("SHIELD"), x, 10 + 30, 25, 25, null);
                x += 30;
            }
        }
    }
}