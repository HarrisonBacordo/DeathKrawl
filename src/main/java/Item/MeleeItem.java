package Item;

import Entity.Entity;
import Entity.EntityType;

import java.awt.*;

public class MeleeItem extends Entity{

    private int damage;
    private int swingSpeed;
    private int hitBack;

    public MeleeItem(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
