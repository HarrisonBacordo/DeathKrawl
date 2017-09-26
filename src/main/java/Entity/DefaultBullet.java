package Entity;

import Component.ShootComponent;

import java.awt.*;

/**
 * Represents the default bullet that the player starts with
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class DefaultBullet extends Entity{
    private int bulletSpeed;

    private ShootComponent.ShootingDirection shootingDirection;
    private float xVelocity;
    private float yVelocity;

    public DefaultBullet(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
    }

    /**
     * sets the direction in which the bullet is travelling
     * @param shootingDirection - direction in which the bullet is travelling
     */
    public void setShootingDirection(ShootComponent.ShootingDirection shootingDirection) {
        this.shootingDirection = shootingDirection;
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
        switch (shootingDirection) {
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

