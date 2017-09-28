package Entity;

import Component.ComponentManager;
import Component.InputComponent;
import Component.ComponentType;
import Component.WeaponComponent;
import LevelGenerator.Rooms.PointLight;
import ResourceLoader.Resources;

import java.awt.*;
import java.io.Serializable;

/**
 * Entity that represents the player
 *
 * Created by krishna on 2/09/2017.
 */
public class NinjaEntity extends Entity implements Serializable{
    public boolean jumping;
    public boolean isKnockedBack;
    public long startKnockBack;
    public int knockBackStrength;
    public long knockBackDuration;
    public WeaponComponent.ShootingDirection shootDirectionOnKnockBack;
    public WeaponComponent.ShootingDirection shootingDirection;
    public WeaponComponent weaponComponent;

    /**
     * Creates a Ninja Player entity at the given location
     * @param x, x position
     * @param y, y position
     */
    public NinjaEntity(int x, int y, int width, int height) {
        super(x, y, width, height, EntityType.PLAYER);
        addComponent(new InputComponent(this, ComponentManager.keyInput));
        this.weaponComponent = new WeaponComponent(this, ComponentType.SHOOT);
        addComponent(weaponComponent);
        shootingDirection = WeaponComponent.ShootingDirection.NOT_SHOOTING;
        jumping = false;
        isKnockedBack = false;
        isColliadable = true;
        image = Resources.getImage("Player");
    }

    /**
     * Initializes the knockback of this entity in the opposite direciton
     * @param duration - duration of knockback
     */
    public void startKnockback(long duration, int knockBackStrength) {
        isKnockedBack = true;
        startKnockBack = System.currentTimeMillis();
        knockBackDuration = duration;
        shootDirectionOnKnockBack = shootingDirection;
        this.knockBackStrength = knockBackStrength;
    }

    public void switchPreviousGun() {
        WeaponComponent shoot = (WeaponComponent) components.findComponentWithType(ComponentType.SHOOT);
        shoot.previousGun();
    }

    public void switchNextGun() {
        WeaponComponent shoot = (WeaponComponent) components.findComponentWithType(ComponentType.SHOOT);
        shoot.nextGun();
    }
    /**
     * @return - the shooting direction of this entity
     */
    public WeaponComponent.ShootingDirection getShootingDirection() { return shootingDirection; }

    @Override
    public void tick() {
        if(isKnockedBack) {
            if(System.currentTimeMillis() - startKnockBack < knockBackDuration) {
                switch (shootDirectionOnKnockBack) {
                    case SHOOT_UP:
                        y += knockBackStrength;
                        break;
                    case SHOOT_DOWN:
                        y += -knockBackStrength;
                        break;
                    case SHOOT_LEFT:
                        x += knockBackStrength;
                        break;
                    case SHOOT_RIGHT:
                        x += -knockBackStrength;
                        break;

                }
                return;
            } else {
                isKnockedBack = false;
            }
        }
        x += xVelocity;
        y += yVelocity;
        //Processes all components
        components.executeAllComponents();
    }


    @Override
    public void render(Graphics g) {
//        g.setColor(Color.CYAN);
//        g.fillRect(x, y, width, height);
        g.drawImage(image, x, y, width, height, null);
        WeaponComponent shoot = (WeaponComponent) components.findComponentWithType(ComponentType.SHOOT);
        shoot.renderBullets(g);
    }

}
