package Item;

import Entity.Entity;
import Entity.EntityType;
import ResourceLoader.Resources;

import java.awt.*;

/**
 * Default item for the game, so the code is minimal, doesn't need ticking or rendering
 */

public class Pistol extends Entity{


    public Pistol(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        image = Resources.getImage("PISTOL");
        isCollidable = true;
    }

    @Override
    public void tick() {
        //does nothing
    }

    /**
     * No rendering required as it's the default item
     */
    @Override
    public void render(Graphics g) {
        //does nothing
    }

}
