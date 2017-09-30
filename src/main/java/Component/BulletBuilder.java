package Component;

import Entity.Entity;
import Entity.NinjaEntity;
import Entity.EntityType;
import Item.Bullet;
import Util.AudioPlayer;
import Util.SoundEffects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class' primary sole function is to make bullets of any combination of properties.
 * This class saves the need of making multiple bullet classes, and instead puts it all
 * in one class. This class gives you unlimited flexibility to combining bullet properties
 * to make a unique bullet for each weapon.
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class BulletBuilder {
    private final EntityType DEFAULT_TYPE = EntityType.DEFAULT_BULLET;

    public static final int DEFAULT_BULLET_SPEED = 15;
    public static final int FAST_BULLET_SPEED = 40;
    public static final int SLOW_BULLET_SPEED = 7;
    public static final int SHOTGUN_BULLET_SPEED = 30;

    public static final int DEFAULT_BULLET_FIRING_RATE = 300;
    public static final int FAST_BULLET_FIRING_RATE = 120;
    public static final int SLOW_BULLET_FIRING_RATE = 400;
    public static final int SHOTGUN_BULLET_FIRING_RATE = 800;


    private NinjaEntity entity;
    private EntityType bulletType;
    private int x;
    private int y;
    private int width;
    private int height;
    private float xVelocity;
    private float yVelocity;
    private WeaponComponent.attackingDirection attackingDircetion;
    private int bulletSpeed;
    private Shape bulletShape;
    private AudioPlayer audioPlayer;

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
        attackingDircetion = this.entity.getAttackingDirection();
    }

    /**
     * @param bulletType - desired bullet type of this bullet
     */
    public void setBulletType(EntityType bulletType) {
        this.bulletType = bulletType;
    }

    public void setBulletDimensions(int width, int height) {
        this.width = width;
        this.height = height;
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
    public List<Entity> buildBullet() {
        List<Entity> bulletsToAdd = new ArrayList<>();
        Bullet bullet;
        switch(bulletType) {
            case DEFAULT_BULLET:
                audioPlayer = new AudioPlayer(SoundEffects.BAP.getValue());
                audioPlayer.play();
                bullet = new Bullet(x, y, width, height, EntityType.DEFAULT_BULLET);
                bullet.setAttackingDircetion(attackingDircetion);
                bullet.setBulletSpeed(bulletSpeed);
                bullet.setVelocity(xVelocity, yVelocity);
                bulletsToAdd.add(bullet);
                return bulletsToAdd;

            case SHOTGUN_BULLET:
                audioPlayer = new AudioPlayer(SoundEffects.BOOM.getValue());
                audioPlayer.play();

                float tempXVelocity = xVelocity -32;
                float tempYVelocity = yVelocity - 32;
                for(int i = 0; i < 5; i++) {
                    bullet = new Bullet(x, y, width, height, EntityType.SHOTGUN_BULLET);
                    bullet.setAttackingDircetion(attackingDircetion);
                    bullet.setBulletSpeed(bulletSpeed);
                    bullet.setVelocity(tempXVelocity, tempYVelocity);
                    bulletsToAdd.add(bullet);
                    tempXVelocity += 16;
                    tempYVelocity += 16;
                }
                return bulletsToAdd;

            case FAST_BULLET:
                audioPlayer = new AudioPlayer(SoundEffects.SKRRRA.getValue());
                audioPlayer.play();
                bullet = new Bullet(x, y, width, height, EntityType.FAST_BULLET);
                bullet.setAttackingDircetion(attackingDircetion);
                bullet.setBulletSpeed(bulletSpeed);
                bullet.setVelocity(xVelocity, yVelocity);
                bulletsToAdd.add(bullet);
                return bulletsToAdd;

        }
        return null;
    }
}
