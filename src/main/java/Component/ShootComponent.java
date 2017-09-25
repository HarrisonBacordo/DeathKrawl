package Component;

import Entity.Entity;
import Entity.EntityType;
import Entity.EntityManager;
import Entity.NinjaEntity;

import java.awt.*;
import java.util.List;

/**
 * This class represents the component that allows an entity to shoot.
 * It handles the firing rate, building the appropriate bullet, and then
 * adding it to EntityManager, which serves to hold the bullets that are
 * still live and need to be drawn
 */
public class ShootComponent extends Component {
    private final int KNOCKBACK_DURATION = 50;
    private long firingRateInMS;
    private EntityType currentBulletType = EntityType.DEFAULT_BULLET;
    private long shootTime; //time that the most recent bullet was fired
    private EntityManager bullets;  //list of bullets that are still live
    NinjaEntity ninjaEntity;    //Used to access the methods unique to NinjaEntity

    public ShootComponent(Entity entity, ComponentType componentType) {
        super(entity, componentType);
        ninjaEntity = (NinjaEntity) entity;
        bullets = new EntityManager();
        shootTime = System.currentTimeMillis();

    }

    /**
     * Adds a fired bullet to this entity
     */
    private void addBulletToEntity(EntityType bulletType) {
        List<Entity> bulletsToAdd = null;
        BulletBuilder bulletBuilder = new BulletBuilder(entity);
        switch (bulletType) {
            case DEFAULT_BULLET:
                bulletsToAdd = buildDefaultBullet(bulletBuilder);
                break;
            case SHOTGUN_BULLET:
                bulletsToAdd = buildShotgunBullet(bulletBuilder);
        }
        bullets.getEntities().addAll(bulletsToAdd);  //add the newly created bullet to the list of live bullets
    }

    @Override
    public void execute() {
        /* Check if the entity is shooting. Also check if the time passed since the last bullet
        was shot is greater than the set firingRate. If it greater, we can fire the next bullet,
        as it conforms to the set firing rate. */
        if (((NinjaEntity) entity).shootingDirection != ShootingDirection.NOT_SHOOTING &&
                System.currentTimeMillis() - shootTime > firingRateInMS) {
            shootTime = System.currentTimeMillis(); //reset the shootTime to the current time
            addBulletToEntity(currentBulletType);
        }
        if (!bullets.isEmpty()) {
            bullets.tickAllEntities();
        }
    }

    private List<Entity> buildDefaultBullet(BulletBuilder builder) {
        builder.setBulletType(EntityType.DEFAULT_BULLET);
        builder.setBulletSpeed(BulletBuilder.DEFAULT_BULLET_SPEED);
        firingRateInMS = BulletBuilder.DEFAULT_BULLET_FIRING_RATE;
        ninjaEntity.startKnockback(KNOCKBACK_DURATION, BulletBuilder.DEFAULT_BULLET_KNOCKBACK);
        return builder.buildBullet();
    }

    private List<Entity> buildShotgunBullet(BulletBuilder builder) {
        builder.setBulletType(EntityType.SHOTGUN_BULLET);
        builder.setBulletSpeed(BulletBuilder.SHOTGUN_BULLET_SPEED);
        firingRateInMS = BulletBuilder.SHOTGUN_BULLET_FIRING_RATE;
        ninjaEntity.startKnockback(KNOCKBACK_DURATION, BulletBuilder.SHOTGUN_BULLET_KNOCKBACK);
        return builder.buildBullet();
    }

    public void nextGun() {
        if(currentBulletType.equals(EntityType.DEFAULT_BULLET)) {
            currentBulletType = EntityType.SHOTGUN_BULLET;
        } else {
            currentBulletType = EntityType.DEFAULT_BULLET;
        }
    }

    /**
     * Executes render of all bullets
     *
     * @param g - graphics to render to
     */
    public void renderBullets(Graphics g) {
        bullets.renderAllEntities(g);
    }

    /**
     * Holds the possible shooting states of the entity
     */
    public enum ShootingDirection {
        NOT_SHOOTING,
        SHOOT_UP,
        SHOOT_DOWN,
        SHOOT_LEFT,
        SHOOT_RIGHT
    }
}
