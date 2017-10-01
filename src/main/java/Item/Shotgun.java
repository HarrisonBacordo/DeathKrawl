package Item;

import Entity.Entity;
import Entity.EntityType;
import HUD.Inventory;
import ResourceLoader.Resources;

import java.awt.*;

public class Shotgun extends Entity {

    private boolean inInventory;

    public Shotgun(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        inInventory = false;
        image = Resources.getImage("SHOTGUN");
        isCollidable = true;
    }

    @Override
    public void tick() {

    }

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
        if (this.inInventory) {
            Inventory.items.add("shotgun");
        } else if (Inventory.items.contains("shotgun")) {
            Inventory.items.remove("shotgun");
        }
    }
}
