package Entities;

import ResourceLoader.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by krish on 17/09/2017.
 */
public class FloorEntity extends Entity {

    public FloorEntity(int x, int y, int width, int height) {
        super(x, y, width, height, ID.FLOOR);
        image = Resources.getImage("FT");

    }

    @Override
    public void tick() {
        //Does nothing
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
