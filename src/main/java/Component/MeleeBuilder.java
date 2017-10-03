package Component;

import Entity.Entity;
import Entity.NinjaEntity;
import Entity.EntityType;
import Util.AudioPlayer;
import Util.SoundEffects;

/**
 * This class' primary sole function is to make melee weapons of any combination of properties.
 * This class saves the need of making multiple melee weapon classes, and instead puts it all
 * in one class. This class gives you unlimited flexibility to combining melee weapon properties
 * to make a unique melee weapon for each weapon.
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class MeleeBuilder {
    public static final int SWORD_MELEE_SPEED = 500;
    public static final int SPEAR_MELEE_SPEED = 30;
    public static final int LIGHTSABER_MELEE_SPEED = 7;

    public static final int SWORD_KNOCKBACK = 10;
    public static final int SPEAR_KNOCKBACK = 20;
    public static final int LIGHTSABER_KNOCKBACK =5;

    public static final int SWORD_DAMAGE = 1;
    public static final int SPEAR_DAMAGE = 200;
    public static final int LIGHTSABER_DAMAGE = 400;


    private NinjaEntity entity;
    private EntityType meleeType;
    private int x;
    private int y;
    private int width;
    private int height;
    private WeaponComponent.attackingDirection attackingDircetion;
    private int meleeSpeed;
    private AudioPlayer audioPlayer;

    public MeleeBuilder(Entity entity) {
        this.entity = (NinjaEntity) entity;
        this.width = 20;
        this.height = 20;
        attackingDircetion = this.entity.getAttackingDirection();
        calculateXYPosition();
    }

    private void calculateXYPosition() {
        switch (attackingDircetion) {
            case SHOOT_UP:
                x = entity.getX();
                y = entity.getY() - entity.getHeight();
                break;
            case SHOOT_DOWN:
                x = entity.getX();
                y = entity.getY() + entity.getHeight();
                break;
            case SHOOT_LEFT:
                x = entity.getX() - entity.getWidth();
                y = entity.getY();
                break;
            case SHOOT_RIGHT:
                x = entity.getX() + entity.getWidth();
                y = entity.getY();
                break;
        }
    }
    /**
     * @param meleeType - desired melee weapon type of this melee weapon
     */
    public void setMeleeType(EntityType meleeType) {
        this.meleeType = meleeType;
    }

    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param meleeSpeed - desired speed of melee weapon
     */
    public void setMeleeSPeed(int meleeSpeed) {
        this.meleeSpeed = meleeSpeed;
    }

    /**
     * Builds the melee weapon that this BulletBuilder created
     * @return - the created melee weapon
     */
    public MeleeWeapon buildMelee() {
        MeleeWeapon meleeWeapon;
        switch(meleeType) {
            case SWORD:
                audioPlayer = new AudioPlayer(SoundEffects.BSH.getValue());
                audioPlayer.play();
                meleeWeapon = new MeleeWeapon(x, y, width, height, EntityType.MELEE_WEAPON);
                meleeWeapon.setAttackingDirection(attackingDircetion);
                meleeWeapon.setMeleeSpeed(meleeSpeed);
                return meleeWeapon;

        }
        return null;
    }
}
