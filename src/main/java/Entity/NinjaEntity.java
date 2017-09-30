package Entity;

import Component.ComponentManager;
import Component.ComponentType;
import Component.InputComponent;
import Component.WeaponComponent;
import Component.KnockbackComponent;
import Component.HealthComponent;
import ResourceLoader.Resources;

import java.awt.*;
import java.io.Serializable;

/**
 * Entity that represents the player
 * <p>
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class NinjaEntity extends Entity implements Serializable {

    public WeaponComponent.attackingDirection shootingDirection;
    public WeaponComponent weaponComponent;
    private boolean isBoosted;
    private boolean isHit;
    private long boostStart;
    private long lengthOfBoost;

    /**
     * Creates a Ninja Player entity at the given location
     *
     * @param x, x position
     * @param y, y position
     */
    public NinjaEntity(int x, int y, int width, int height) {
        super(x, y, width, height, EntityType.PLAYER);

        addComponent(new InputComponent(this, ComponentManager.keyInput));
        addComponent(new KnockbackComponent(this));
        this.weaponComponent = new WeaponComponent(this);
        addComponent(weaponComponent);
        addComponent(new HealthComponent(this));
        shootingDirection = WeaponComponent.attackingDirection.NOT_SHOOTING;
        isCollidable = true;
        image = Resources.getImage("Player");
    }

    /**
     * start the boost for this ninja entity
     * @param duration - duration of the boost
     */
    public void startBoost(long duration) {
        isBoosted = true;
        boostStart = System.currentTimeMillis();
        lengthOfBoost = duration;
    }

    /**
     * switches to the previous gun
     */
    public void switchPreviousGun() {
        WeaponComponent shoot = (WeaponComponent) components.findComponentWithType(ComponentType.WEAPON);
        shoot.previousGun();

    }

    /**
     * switches to the next gun
     */
    public void switchNextGun() {
        WeaponComponent shoot = (WeaponComponent) components.findComponentWithType(ComponentType.WEAPON);
        shoot.nextGun();
    }

    /**
     * @return - if player hit is triggered
     */
    public boolean getIsHit() { return isHit; }

    /**
     * @param isHit - desired value for player hit
     */
    public void setIsHit(boolean isHit)  {
        this.isHit = isHit;
    }

    /**
     * @return - the shooting direction of this entity
     */
    public WeaponComponent.attackingDirection getAttackingDirection() {
        return shootingDirection;
    }

    @Override
    public void tick() {
        if (isBoosted) {
            if (System.currentTimeMillis() - boostStart < lengthOfBoost) {
                xVelocity *= 2;
                yVelocity *= 2;
            }
        }

        x += xVelocity;
        y += yVelocity;

        //Processes all components
        components.executeAllComponents();
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        WeaponComponent shoot = (WeaponComponent) components.findComponentWithType(ComponentType.WEAPON);
        shoot.renderBullets(g);
        shoot.renderMelee(g);
    }

}
