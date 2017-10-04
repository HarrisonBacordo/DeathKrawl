package LevelGenerator.Rooms;

import Entity.Entity;
import Entity.EntityType;

import java.awt.*;

/**
 * Door object, is placed onto the level dynamically
 *
 * Created by krishna kapadia, 300358741 on 16/09/2017.
 */
public class Door extends Entity{
    private int x, y, width, height, row, col;
    private LOCATION location;
    private boolean open;

    public Door(int x, int y, int width, int height, LOCATION location){
        super(x, y, width, height, EntityType.DOOR);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.open = true;
        this.location = location;
        isCollidable = true;
    }

    public Door(int x, int y, int width, int height, boolean open, LOCATION location){
        super(x, y, width, height, EntityType.DOOR);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.open = open;
        this.location = location;
        isCollidable = true;
    }

    @Override
    public void tick() {
        //doors dont tick
    }

    public void render(Graphics g){
        if(open) g.setColor(Color.BLUE);
        else g.setColor(Color.RED);

        g.fillRect(x, y, width, height);
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

    /**
     * Returns the row position of the door
     * @return row
     */
    public int getRow() { return row; }

    /**
     * Returns the col position of the door
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns if the door is open or not
     * @return open
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Sets the state of the door to be open or closed
     * @param open, new state of the door
     */
    public void setState(boolean open) { this.open = open; }

    /**
     * Returns the location of the door
     * @return enum value location
     */
    public LOCATION getLocation() { return location;}
}

