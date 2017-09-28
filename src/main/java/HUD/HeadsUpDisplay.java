package HUD;

import LevelGenerator.Rooms.Room;
import ResourceLoader.Resources;

import java.awt.*;

/**
 * This class represents and paints the HUD onto the canvas
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class HeadsUpDisplay extends Canvas {
    public int width;
    public int height;
    public Room[][] rooms;
    HealthBar healthBar;
    MiniMap miniMap;
    Inventory inventory;
    WeaponHUD weapon;

    public HeadsUpDisplay(int width, int height) {
        this.width = width;
        this.height = height;
        healthBar = new HealthBar();
        inventory = new Inventory(width, height);
        weapon = new WeaponHUD();
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
        miniMap = new MiniMap(width, height, rooms);
    }

    public void render(Graphics2D g) {
        super.paint(g);
        healthBar.render(g);
        miniMap.render(g);
//        weapon.render(g);
        inventory.render(g);
    }
}
