package HUD;

import java.awt.*;

/**
 * This class represents and paints the HUD onto the canvas
 */
public class HeadsUpDisplay extends Canvas {
    public int width;
    public int height;
    HealthBar healthBar;
    MiniMap miniMap;
    Inventory inventory;
    WeaponHUD weapon;

    public HeadsUpDisplay(int width, int height) {
        this.width = width;
        this.height = height;
        healthBar = new HealthBar();
        miniMap = new MiniMap(width, height);
        inventory = new Inventory(width, height);
        weapon = new WeaponHUD();
    }

    public void paint(Graphics g) {
        System.out.println("PAINTING HUD");
        super.paint(g);
        healthBar.paint(g);
        miniMap.paint(g);
        weapon.paint(g);
        inventory.paint(g);
    }
}
