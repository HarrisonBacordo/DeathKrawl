package HUD;

import ResourceLoader.Resources;

import java.awt.*;

/**
 * This class represents and paints the health bar onto the canvas
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class HealthBar extends Canvas {
    public static boolean HAS_SHIELD = false;
    private static final int HEALTH_SIZE = 3;
    private static final int SHIELD_SIZE = 3;
    private static final int CURRENT_HEALTH = 3;

    /**
     * Renders the healthbar onto the screen using the passed in graphics
     * @param g - graphics to render with
     */
    public void render(Graphics g) {
        super.paint(g);
        int x = 10;
        for(int i = 1; i < CURRENT_HEALTH + 1; i++) {
            g.setColor(Color.red);
            g.drawImage(Resources.getImage("HEART"), x, 10, 20, 20, null);
            x += 25;
        }
        if(HAS_SHIELD) {
            x = 10;
            for (int i = 1; i < SHIELD_SIZE + 1; i++) {
                g.setColor(Color.red);
                g.drawImage(Resources.getImage("SHIELD"), x, 10 + 25, 20, 20, null);
                x += 25;
            }
        }
    }
}