package Entities;

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
        super(x, y, width, height, ID.WALL);
        image = Resources.getImage("WT");
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;

        processComponents();
    }

    @Override
    public void render(Graphics g) {
//        g.setColor(Color.gray);
        g.drawImage(image, x, y, width, height, null);
//        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
