package Entity;

import LevelGenerator.Rooms.LOCATION;
import ResourceLoader.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Entity that represents a wall
 * Created by krishna on 2/09/2017.
 */
public class WallEntity extends Entity {

    /**
     * Creates a Wall Entity at specific position
     * @param x, x position
     * @param y, y position
     */
    public WallEntity(int x, int y, int width, int height) {
        super(x, y, width, height, EntityType.WALL, EntityID.generateID());
        image = Resources.getImage("WT");
        isColliadable = true;
    }

    /**
     * Creates a Wall Entity at specific position, with a location specific image
     * @param x, x position
     * @param y, y position
     * @param width, width of wall
     * @param height, height of wall
     * @param location, location of the wall, used for image selection
     */
    public WallEntity(int x, int y, int width, int height, LOCATION location) {
        super(x, y, width, height, EntityType.WALL, EntityID.generateID());

        if(location.equals(LOCATION.TOP)) image = Resources.getImage("WT");
        else if(location.equals(LOCATION.BOTTOM)) image = Resources.getImage("WTB");
        else if(location.equals(LOCATION.LEFT)) image = Resources.getImage("WTL");
        else if(location.equals(LOCATION.RIGHT)) image = Resources.getImage("WTR");

    }

    @Override
    public void tick() {
        x += xVelocity;
        y += yVelocity;

        components.executeAllComponents();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
