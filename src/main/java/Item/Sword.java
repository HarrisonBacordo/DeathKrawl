package Item;

import Component.WeaponComponent;
import Entity.Entity;
import Entity.EntityType;
import HUD.Inventory;
import ResourceLoader.Resources;

import java.awt.*;

public class Sword extends Entity {

    public WeaponComponent.attackingDirection direction = WeaponComponent.attackingDirection.NOT_SHOOTING;
    //check to see if the item is in the inventory, don't want it rendering if it is
    private boolean inInventory;
    private Image top, right, bottom, left;

    public Sword(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        image = Resources.getImage("SWORD");
        this.top = Resources.getImage("SWIPET");
        this.right = Resources.getImage("SWIPER");
        this.bottom = Resources.getImage("SWIPEB");
        this.left = Resources.getImage("SWIPEL");
        isCollidable = true;
        inInventory = false;
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
        if (!inInventory) {
            g.drawImage(image, x, y, width, height, null);
        } else {

            //used for debugging at the moment to visualise the attack pattern
            switch (direction) {
                case SHOOT_UP:
                    g.drawImage(top, x, y - 20, width, height, null);
                    break;

                case SHOOT_RIGHT:
                    g.drawImage(right, x + 20, y, width, height, null);
                    break;

                case SHOOT_DOWN:
                    g.drawImage(bottom, x, y + 20, width, height, null);
                    break;

                case SHOOT_LEFT:
                    g.drawImage(left, x - 20, y, width, height, null);
                    break;

            }
        }


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
            Inventory.items.add("sword");
        } else if (Inventory.items.contains("sword")) {
            Inventory.items.remove("sword");
        }
    }
}
