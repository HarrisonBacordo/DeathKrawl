package HUD;

import java.awt.*;

/**
 * This class represents and paints the currently equipped weapon
 * onto the canvas
 */
public class WeaponHUD extends Canvas {

    public void render(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawRect(10, 40, 40, 40);
    }
}
