package HUD;

import ResourceLoader.Resources;

import java.awt.*;

/**
 * This class represents and paints the currently equipped weapon
 * onto the canvas
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class WeaponHUD extends Canvas {
    public static Image image = Resources.getImage("DEFAULT-GUN");

    public void render(Graphics g) {
        super.paint(g);
        g.drawImage(image, 10, 40, 40, 40, null);
    }
}
