package Component;

import Entity.Entity;
import Entity.EntityManager;
import Entity.NinjaEntity;
import Entity.DefaultBullet;

import java.awt.*;

/**
 * This class represents the component that allows an entity to shoot.
 * It handles the firing rate, building the appropriate bullet, and then
 * adding it to EntityManager, which serves to hold the bullets that are
 * still live and need to be drawn
 */
public class ShootComponent extends Component {
    private long firingRateInMS = 300;
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
    private void addBulletToEntity() {
        BulletBuilder bulletBuilder = new BulletBuilder(entity);
        bulletBuilder.setBulletSpeed(BulletBuilder.DEFAULT_BULLET_SPEED);
        DefaultBullet bullet = (DefaultBullet) bulletBuilder.buildBullet();
        bullets.addEntity(bullet);  //add the newly created bullet to the list of live bullets
        ninjaEntity.startKnockback(50, BulletBuilder.DEFAULT_BULLET_KNOCKBACK);
    }

    @Override
    public void execute() {
        /* Check if the entity is shooting. Also check if the time passed since the last bullet
        was shot is greater than the set firingRate. If it greater, we can fire the next bullet,
        as it conforms to the set firing rate. */
        if (((NinjaEntity) entity).shootingDirection != ShootingDirection.NOT_SHOOTING &&
                System.currentTimeMillis() - shootTime > firingRateInMS) {
            shootTime = System.currentTimeMillis(); //reset the shootTime to the current time
            addBulletToEntity();
        }
        if (!bullets.isEmpty()) {
            bullets.tickAllEntities();
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
