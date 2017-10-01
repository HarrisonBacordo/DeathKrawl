package Item;

import Entity.Entity;
import Entity.EntityType;
import ResourceLoader.Resources;

import java.awt.*;

public class SpeedBoost extends Entity {

    //check to see if the item is in the inventory, don't want it rendering if it is
    private boolean inInventory;

    public SpeedBoost(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        inInventory = false;
        image = Resources.getImage("SPEEDBOOST");
        isCollidable = true;
    }

    @Override
    public void tick() {
        //does nothing
    }

    /**
     * Renders the item onto the screen only if it's on the ground
     */
    @Override
    public void render(Graphics g) {
        if (!inInventory)
            g.drawImage(image, x, y, width, height, null);
    }

    public boolean isInInventory() {
        return inInventory;
    }

    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }
}
