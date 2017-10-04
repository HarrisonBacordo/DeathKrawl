package Entity;

import ResourceLoader.Resources;

import java.awt.*;

/**
 *  Represents a sea floor in the game, not walkable but also not damaging
 *
 * Created by Admin on 21/09/17.
 */
public class SeaFloorEntity extends Entity {

    public SeaFloorEntity(int x, int y, int width, int height, EntityType entityType) {
        super(x, y, width, height, entityType);
        image = Resources.getImage("SEA");
        isCollidable = true;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
