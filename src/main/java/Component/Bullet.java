package Component;

import Entity.*;

import java.awt.*;
import java.awt.Component;

import Component.WeaponComponent;

/**
 * Represents the default bullet that the player starts with
 */
public class Bullet extends Entity {
    private int bulletSpeed;

    private WeaponComponent.attackingDirection attackingDirection;
    private float xVelocity;
    private float yVelocity;


    public Bullet(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
    }

    /**
     * sets the direction in which the bullet is travelling
     * @param attackingDircetion - direction in which the bullet is travelling
     */
    public void setAttackingDircetion(WeaponComponent.attackingDirection attackingDircetion) {
        this.attackingDirection = attackingDircetion;
    }

    public void setVelocity(float xVelocity, float yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    /**
     * Updates this entity
     */
    @Override
    public void tick() {
        switch (attackingDirection) {
            case SHOOT_UP:
                y += -bulletSpeed;
                x += xVelocity;
                break;
            case SHOOT_DOWN:
                y += bulletSpeed;
                x += xVelocity;
                break;
            case SHOOT_LEFT:
                x += -bulletSpeed;
                y += yVelocity;
                break;
            case SHOOT_RIGHT:
                x += bulletSpeed;
                y += yVelocity;
                break;
        }
}

    /**
     * renders this entity onto the screen
     *
     * @param g - graphics to render from
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

}

