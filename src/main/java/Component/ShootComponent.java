package Component;

import Entity.Entity;
import Entity.EntityManager;
import Entity.NinjaEntity;
import Entity.DefaultBullet;

import java.awt.*;


public class ShootComponent extends Component {
    private long shootDelayInMS = 100;
    private long shootTime;
    private EntityManager bullets;
    NinjaEntity ninjaEntity;

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
        BulletBuilder bulletBuilder= new BulletBuilder(entity);
        bulletBuilder.setBulletSpeed(BulletBuilder.FAST_BULLET_SPEED);
        DefaultBullet bullet = (DefaultBullet)bulletBuilder.buildBullet();
        bullets.addEntity(bullet);
        ninjaEntity.startKnockback(50, BulletBuilder.FAST_BULLET_KNOCKBACK);
    }

    @Override
    public void execute() {
        if(((NinjaEntity) entity).shootingDirection != ShootingDirection.NOT_SHOOTING &&
                System.currentTimeMillis() - shootTime > shootDelayInMS) {
            shootTime = System.currentTimeMillis();
            addBulletToEntity();
        }
        if(!bullets.isEmpty()) {
            bullets.tickAllEntities();
        }
    }

    /**
     * Executes render of all bullets
     * @param g - graphics to render to
     */
    public void renderBullets(Graphics g) {
        bullets.renderAllEntities(g);
    }

    public enum ShootingDirection {
        NOT_SHOOTING,
        SHOOT_UP,
        SHOOT_DOWN,
        SHOOT_LEFT,
        SHOOT_RIGHT
    }
}
