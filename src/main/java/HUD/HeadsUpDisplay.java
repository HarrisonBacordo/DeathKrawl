package HUD;

import Entity.NinjaEntity;
import LevelGenerator.Rooms.Room;
import ResourceLoader.Resources;

import java.awt.*;

/**
 * This class represents and paints the HUD onto the canvas
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class HeadsUpDisplay extends Canvas {
    protected static NinjaEntity PLAYER;

    public int width;
    public int height;
    public Room[][] rooms;
    private static HealthBar healthBar;
    private MiniMap miniMap;
    private Inventory inventory;

    public HeadsUpDisplay(int width, int height) {
        this.width = width;
        this.height = height;
        inventory = new Inventory(width, height);
    }

    /**
     * Sets the rooms fr the minimap display to draw
     * @param rooms - the rooms for the minimap to draw
     */
    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
        miniMap = new MiniMap(width, height, rooms);
    }

    public static void setPlayer(NinjaEntity ninjaEntity) {
        PLAYER = ninjaEntity;
        healthBar = new HealthBar();
    }

    /**
     * Renders the HUD onto the screen using the passed in graphics
     * @param g - graphics to render with
     */
    public void render(Graphics2D g) {
        super.paint(g);
        healthBar.render(g);
        miniMap.render(g);
        inventory.render(g);
    }
}
