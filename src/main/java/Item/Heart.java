package Item;

import Entity.Entity;
import Entity.EntityType;
import ResourceLoader.Resources;

import java.awt.*;

/**
 * Item that the player can pick up to gain health, so it doesn't do much
 */

public class Heart extends Entity {

    //check to see if the item is in the inventory, don't want it rendering if it is
    private boolean inInventory;

    public Heart(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        inInventory = false;
        image = Resources.getImage("HEART");
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

    //sets in inventory
    public void setInInventory(Boolean inInventory){
        this.inInventory = inInventory;
    }

}
