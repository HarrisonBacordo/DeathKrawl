package LevelGenerator.Rooms;

import Entity.Entity;
import Entity.EntityType;
import Entity.EntityID;

import java.awt.*;

/**
 * Door object, is placed onto the level dynamically
 *
 * Created by krishna kapadia, 300358741 on 16/09/2017.
 */
public class Door extends Entity{
    private int x, y, width, height, row, col;
    private LOCATION location;

    public Door(int x, int y, int width, int height){
        super(x, y, width, height, EntityType.DOOR);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Door(int x, int y, int width, int height, int row, int col){
        super(x, y, width, height, EntityType.DOOR);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.row = row;
        this.col = col;
    }

    @Override
    public void tick() {

    }

    public void render(Graphics g){
//        g.setColor(Color.RED);
//        g.fillRect(x, y, width, height);
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
}

