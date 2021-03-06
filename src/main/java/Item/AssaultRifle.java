package Item;

import Entity.Entity;
import Entity.EntityType;
import HUD.Inventory;
import ResourceLoader.Resources;

import java.awt.*;

public class AssaultRifle extends Entity {

    //check to see if the item is in the inventory, don't want it rendering if it is
    private boolean inInventory;

    public AssaultRifle(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        inInventory = false;
        image = Resources.getImage("ASSAULT_RIFLE");
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

    /**
     * When the player runs over the item this is called, it adds the item into the
     * list of the players items, or takes it out
     * @param inInventory boolean for whether it goes in the inventory or out
     */
    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
        if (this.inInventory) {
            Inventory.items.add("assault-rifle");
        } else if (Inventory.items.contains("assault-rifle")) {
            Inventory.items.remove("assault-rifle");
        }
    }
}
