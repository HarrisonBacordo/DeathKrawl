package Component;

import Entity.Entity;
import Entity.NinjaEntity;
import Entity.DefaultBullet;
import Entity.EntityType;
import Entity.EntityID;

import java.awt.*;

public class BulletBuilder {
    private final EntityType DEFAULT_TYPE = EntityType.DEFAULT_BULLET;

    public static final int DEFAULT_BULLET_SPEED = 15;
    public static final int FAST_BULLET_SPEED = 30;
    public static final int SLOW_BULLET_SPEED = 7;

    public static final int DEFAULT_BULLET_KNOCKBACK = 10;
    public static final int FAST_BULLET_KNOCKBACK = 20;
    public static final int SLOW_BULLET_KNOCKBACK =5;


    private NinjaEntity entity;
    private EntityType bulletType;
    private int x;
    private int y;
    private int width;
    private int height;
    private float xVelocity;
    private float yVelocity;
    private ShootComponent.ShootingDirection shootingDirection;
    private int bulletSpeed;
    private Shape bulletShape;

    public BulletBuilder(Entity entity) {
        this.entity = (NinjaEntity) entity;
        this.bulletType = DEFAULT_TYPE;
        this.x = entity.getX() + entity.getWidth() / 4;
        this.y = entity.getY() + entity.getHeight() / 4;
        this.width = 20;
        this.height = 20;
        this.xVelocity = entity.getXVelocity();
        this.yVelocity = entity.getYVelocity();
        this.bulletSpeed = DEFAULT_BULLET_SPEED;
        shootingDirection = this.entity.getShootingDirection();
    }

    /**
     * @param bulletType - desired bullet type of this bullet
     */
    public void setBulletType(EntityType bulletType) {
        this.bulletType = bulletType;
    }

    /**
     * @param xVelocity - desired x velocity of bullet
     * @param yVelocity - desired y velocity of bullet
     */
    public void setXYVelocity(float xVelocity, float yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    /**
     * @param bulletSpeed - desired speed of bullet
     */
    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    /**
     * Builds the bullet that this BulletBuilder created
     * @return - the created bullet
     */
    public Entity buildBullet() {
        switch(bulletType) {
            case DEFAULT_BULLET:
                DefaultBullet bullet = new DefaultBullet(x, y, width, height, EntityType.DEFAULT_BULLET, EntityID.generateID());
                bullet.setShootingDirection(shootingDirection);
                bullet.setBulletSpeed(bulletSpeed);
                bullet.setVelocity(xVelocity, yVelocity);
                return bullet;
        }
        return null;
    }
}