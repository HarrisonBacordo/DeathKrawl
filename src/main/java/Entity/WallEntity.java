package Entity;

import ResourceLoader.Resources;
import Entity.Entity;
import Entity.EntityType;
import java.awt.*;

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
    }

    @Override
    public void tick() {
        xPos += xVelocity;
        yPos += yVelocity;

        components.executeAllComponents();
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.gray);
        g.drawImage(image, xPos, yPos, width, height, null);
//        g.fillRect(x, y, width, height);
    }
}
