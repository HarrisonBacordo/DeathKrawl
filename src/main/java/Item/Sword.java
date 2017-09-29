package Item;

import Entity.Entity;
import Entity.EntityType;
import ResourceLoader.Resources;
import Component.WeaponComponent;
import java.awt.*;

public class Sword extends Entity{

    public WeaponComponent.attackingDirection direction = WeaponComponent.attackingDirection.NOT_SHOOTING;
    private boolean inInventory;
    private Image top,right,bottom,left;

    public Sword(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
        image = Resources.getImage("SWORD");
        this.top = Resources.getImage("SWIPET");
        this.right = Resources.getImage("SWIPER");
        this.bottom = Resources.getImage("SWIPEB");
        this.left = Resources.getImage("SWIPEL");
        isColliadable = true;
        inInventory = false;
    }

    @Override
    public void tick() {

    }

    public void attack(WeaponComponent.attackingDirection direction){

    }

    @Override
    public void render(Graphics g) {
        if(!inInventory) {
            g.drawImage(image, x, y, width, height, null);
        }
        else {
            switch (direction) {
                case SHOOT_UP:
                    System.out.println("up");
                    g.drawImage(top, x, y - 20, width, height, null);
                    break;

                case SHOOT_RIGHT:
                    System.out.println("right");
                    g.drawImage(right, x + 20, y, width, height, null);
                    break;

                case SHOOT_DOWN:
                    System.out.println("down");
                    g.drawImage(bottom, x, y + 20, width, height, null);
                    break;

                case SHOOT_LEFT:
                    System.out.println("left");
                    g.drawImage(left, x - 20, y, width, height, null);
                    break;

            }
        }


    }

    public boolean isInInventory() {
        return inInventory;
    }

    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }
}
