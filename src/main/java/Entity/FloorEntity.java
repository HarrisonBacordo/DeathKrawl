package Entity;

import ResourceLoader.Resources;

import java.awt.*;

/**
 * Created by krish on 17/09/2017.
 */
public class FloorEntity extends Entity {

    public FloorEntity(int x, int y, int width, int height) {
        super(x, y, width, height, EntityType.FLOOR, EntityID.generateID());
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
}
