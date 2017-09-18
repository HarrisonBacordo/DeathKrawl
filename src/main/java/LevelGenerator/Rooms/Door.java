package LevelGenerator.Rooms;

import Entities.Entity;
import Entities.ID;

import java.awt.*;

/**
 * Door object, is placed onto the level dynamically
 *
 * Created by krishna kapadia, 300358741 on 16/09/2017.
 */
public class Door extends Entity{
    private int x, y, width, height;
    private LOCATION location;

    public Door(int x, int y, int width, int height){
        super(x, y, width, height, ID.DOOR);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public void tick() {

    }

    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    /**
     * Returns the width of the door
     * @return width
     */
    public int getWidth() { return width; }

    /**
     * Returns the height of the door
     * @return height
     */
    public int getHeight() { return height; }

}
