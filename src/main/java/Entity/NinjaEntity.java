package Entity;

import Component.ComponentManager;
import Component.InputComponent;
import Component.ComponentType;
import Component.ShootComponent;
import LevelGenerator.Rooms.PointLight;
import ResourceLoader.Resources;

import java.awt.*;

/**
 * Entity that represents the player
 *
 * Created by krishna on 2/09/2017.
 */
public class NinjaEntity extends Entity {
    public boolean jumping;
    public boolean isKnockedBack;
    public long startKnockBack;
    public int knockBackStrength;
    public long knockBackDuration;
    public ShootComponent.ShootingDirection shootDirectionOnKnockBack;
    public ShootComponent.ShootingDirection shootingDirection;

    /**
     * Creates a Ninja Player entity at the given location
     * @param x, x position
     * @param y, y position
     */
    public NinjaEntity(int x, int y, int width, int height) {
        super(x, y, width, height, EntityType.PLAYER, EntityID.generateID());
        addComponent(new InputComponent(this, ComponentManager.keyInput));
        addComponent(new ShootComponent(this, ComponentType.SHOOT));
        shootingDirection = ShootComponent.ShootingDirection.NOT_SHOOTING;
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

    /**
     * @return - the shooting direction of this entity
     */
    public ShootComponent.ShootingDirection getShootingDirection() { return shootingDirection; }

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
        ShootComponent shoot = (ShootComponent) components.findComponentWithType(ComponentType.SHOOT);
        shoot.renderBullets(g);
    }
}
