package Item;

import Entity.Entity;
import Entity.EntityType;
import HUD.Inventory;
import ResourceLoader.Resources;

import java.awt.*;

public class AssaultRifle extends Entity {

    private boolean inInventory;

    public AssaultRifle(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        inInventory = false;
        image = Resources.getImage("ASSAULT_RIFLE");
        isColliadable = true;
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
            Inventory.items.add("assault-rifle");
        } else if(Inventory.items.contains("assault-rifle")) {
            Inventory.items.remove("assault-rifle");
        }
    }
}
