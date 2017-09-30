package Component;

import Entity.Entity;
import Entity.EntityType;
import Entity.EntityManager;
import Entity.NinjaEntity;
import HUD.Inventory;
import Item.MeleeWeapon;
import Item.Pistol;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the component that allows an entity to shoot.
 * It handles the firing rate, building the appropriate bullet, and then
 * adding it to EntityManager, which serves to hold the bullets that are
 * still live and need to be drawn.
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class WeaponComponent extends Component {
    private final int KNOCKBACK_DURATION = 50;
    private long firingRateInMS;
    private EntityType currentBulletType = EntityType.DEFAULT_BULLET;
    private long shootTime; //time that the most recent bullet was fired
    private EntityManager bullets;  //list of bullets that are still live
    private MeleeWeapon meleeWeapon;
    private KnockbackComponent knockbackComponent;
    NinjaEntity ninjaEntity;    //Used to access the methods unique to NinjaEntity
    private List<Entity> weapons;
    private boolean isMeleeAttacking = false;

    public WeaponComponent(Entity entity) {
        super(entity, ComponentType.SHOOT);
        ninjaEntity = (NinjaEntity) entity;
        bullets = new EntityManager();
        shootTime = System.currentTimeMillis();
        this.weapons = new ArrayList<Entity>();
        this.weapons.add(new Pistol(0, 0, 0, 0, EntityType.PISTOL));
        this.knockbackComponent = (KnockbackComponent) entity.getComponent(ComponentType.KNOCKBACK);
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
                break;
            case FAST_BULLET:
                bulletsToAdd = buildAssaultBullet(bulletBuilder);
                break;
        }
        bullets.addAllEntities(bulletsToAdd);  //add the newly created bullet to the list of live bullets
    }

    private void executeMeleeWeapon(EntityType meleeType) {
        MeleeBuilder meleeBuilder = new MeleeBuilder(entity);
        switch (meleeType) {
            case SWORD:
                meleeWeapon = buildSwordAttack(meleeBuilder);
                bullets.addEntity(meleeWeapon);
        }
    }

    @Override
    public void execute() {
        /* Check if the entity is shooting. Also check if the time passed since the last bullet
        was shot is greater than the set firingRate. If it greater, we can fire the next bullet,
        as it conforms to the set firing rate. */
        if (((NinjaEntity) entity).shootingDirection != WeaponComponent.attackingDirection.NOT_SHOOTING &&
                System.currentTimeMillis() - shootTime > firingRateInMS) {
            switch (weapons.get(Inventory.inventoryIndex).getEntityType()){
                case SHOTGUN:
                    shootTime = System.currentTimeMillis(); //reset the shootTime to the current time
                    addBulletToEntity(EntityType.SHOTGUN_BULLET);
                    break;

                case PISTOL:
                    shootTime = System.currentTimeMillis(); //reset the shootTime to the current time
                    addBulletToEntity(EntityType.DEFAULT_BULLET);
                    break;

                case ASSAULT_RIFLE:
                    shootTime = System.currentTimeMillis(); //reset the shootTime to the current time
                    addBulletToEntity(EntityType.FAST_BULLET);
                    break;

                case SWORD:
                    shootTime = System.currentTimeMillis();
                    isMeleeAttacking = true;
                    executeMeleeWeapon(EntityType.SWORD);
                    break;
            }
        }

        if (!bullets.isEmpty()) {
            bullets.tickAllEntities();
        }
    }

    private List<Entity> buildDefaultBullet(BulletBuilder builder) {
        builder.setBulletType(EntityType.DEFAULT_BULLET);
        builder.setBulletSpeed(BulletBuilder.DEFAULT_BULLET_SPEED);
        firingRateInMS = BulletBuilder.DEFAULT_BULLET_FIRING_RATE;
        knockbackComponent.startKnockback(KNOCKBACK_DURATION, BulletBuilder.DEFAULT_BULLET_KNOCKBACK);
        return builder.buildBullet();
    }

    private List<Entity> buildShotgunBullet(BulletBuilder builder) {
        builder.setBulletType(EntityType.SHOTGUN_BULLET);
        builder.setBulletSpeed(BulletBuilder.SHOTGUN_BULLET_SPEED);
        builder.setBulletDimensions(10, 10);
        firingRateInMS = BulletBuilder.SHOTGUN_BULLET_FIRING_RATE;
        knockbackComponent.startKnockback(KNOCKBACK_DURATION, BulletBuilder.SHOTGUN_BULLET_KNOCKBACK);
        return builder.buildBullet();
    }


    private List<Entity> buildAssaultBullet(BulletBuilder builder) {
        builder.setBulletType(EntityType.FAST_BULLET);
        builder.setBulletSpeed(BulletBuilder.DEFAULT_BULLET_SPEED);
        firingRateInMS = BulletBuilder.FAST_BULLET_FIRING_RATE;
        knockbackComponent.startKnockback(KNOCKBACK_DURATION, BulletBuilder.FAST_BULLET_KNOCKBACK);
        return builder.buildBullet();
    }

    private MeleeWeapon buildSwordAttack(MeleeBuilder builder) {
        builder.setMeleeType(EntityType.SWORD);
        builder.setMeleeSPeed(MeleeBuilder.SWORD_MELEE_SPEED);
        builder.setDimension(entity.getWidth(), entity.getHeight());
        firingRateInMS = MeleeBuilder.SWORD_MELEE_SPEED;
        return builder.buildMelee();
    }

    public void nextGun() {

        //if they have another weapon, let them switch
        if(weapons.size() > (Inventory.inventoryIndex + 1) ){
            Inventory.inventoryIndex++;
        }
        else if((Inventory.inventoryIndex + 1) == weapons.size()){
            Inventory.inventoryIndex = 0;
        }
    }

    public void previousGun() {

        //if they have another weapon, let them switch
        if(Inventory.inventoryIndex > 0 ){
            Inventory.inventoryIndex--;
        }
        else if(Inventory.inventoryIndex == 0 && weapons.size() > 1){
            Inventory.inventoryIndex = weapons.size() - 1;
        }
    }

    public void addWeapon(Entity e){
        this.weapons.add(e);
    }

    /**
     * Executes render of all bullets
     *
     * @param g - graphics to render to
     */
    public void renderBullets(Graphics g) {
        bullets.renderAllEntities(g);
    }

    public void renderMelee(Graphics g) {
        if (meleeWeapon != null && isMeleeAttacking) {
            meleeWeapon.render(g);
            isMeleeAttacking = false;
            bullets.removeEntity(meleeWeapon);
            meleeWeapon = null;
        }
    }

    /**
     * Returns all of the bullets created by this component
     * @return bullets
     */
    public EntityManager getBullets() { return this.bullets; }

    /**
     * Holds the possible shooting states of the entity
     */
    public enum attackingDirection {
        NOT_SHOOTING,
        SHOOT_UP,
        SHOOT_DOWN,
        SHOOT_LEFT,
        SHOOT_RIGHT
    }

    public List<Entity> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Entity> weapons) {
        this.weapons = weapons;
    }
}
