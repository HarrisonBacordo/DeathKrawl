package Item;

import Entity.*;

import java.awt.*;

import Component.WeaponComponent;
import ResourceLoader.Resources;

/**
 * Represents the default bullet that the player starts with
 */
public class MeleeWeapon extends Entity {
    private int meleeSpeed;

    private WeaponComponent.attackingDirection attackingDirection;


    public MeleeWeapon(int xPos, int yPos, int width, int height, EntityType entityType) {
        super(xPos, yPos, width, height, entityType);
    }

    /**
     * sets the direction in which the bullet is travelling
     * @param attackingDirection - direction in which the bullet is travelling
     */
    public void setAttackingDirection(WeaponComponent.attackingDirection attackingDirection) {
        this.attackingDirection = attackingDirection;
    }

    public void setMeleeSpeed(int meleeSpeed) {
        this.meleeSpeed = meleeSpeed;
    }

    /**
     * Updates this entity
     */
    @Override
    public void tick() {
    }

    /**
     * renders this entity onto the screen
     *
     * @param g - graphics to render from
     */
    @Override
    public void render(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
    }

}

