package Component;

import Entity.Entity;
import Entity.NinjaEntity;

public class KnockbackComponent extends Component{
    public static final int DEFAULT_KNOCKBACK_DURATION = 100;
    public static final int FAST_BULLET_KNOCKBACK_DURATION = 75;

    public static final int DEFAULT_BULLET_KNOCKBACK_RATE = 5;
    public static final int FAST_BULLET_KNOCKBACK_RATE = 10;
    public static final int SLOW_BULLET_KNOCKBACK_RATE =5;
    public static final int SHOTGUN_BULLET_KNOCKBACK_RATE = 20;
    public static final int SWORD_KNOCKBACK_RATE = 15;

    private NinjaEntity entity;
    public WeaponComponent.attackingDirection shootingDirection;
    public WeaponComponent.attackingDirection shootDirectionOnKnockBack;
    public boolean isKnockedBack;
    public long startKnockBack;
    public int knockBackStrength;
    public long knockBackDuration;

    public KnockbackComponent(Entity entity) {
        super(entity, ComponentType.KNOCKBACK);
        this.entity = (NinjaEntity) entity;
    }

    @Override
    public void execute() {
        if (isKnockedBack) {
            if (System.currentTimeMillis() - startKnockBack < knockBackDuration) {
                switch (shootDirectionOnKnockBack) {
                    case SHOOT_UP:
                        entity.setY(entity.getY() + knockBackStrength);
                        break;
                    case SHOOT_DOWN:
                        entity.setY(entity.getY() + -knockBackStrength);
                        break;
                    case SHOOT_LEFT:
                        entity.setX(entity.getX() + knockBackStrength);
                        break;
                    case SHOOT_RIGHT:
                        entity.setX(entity.getX() + -knockBackStrength);
                        break;

                }
            } else {
                isKnockedBack = false;
            }
        }
    }

    public long getKnockBackDuration() { return knockBackDuration; }

    public void setKnockBackDuration(long knockBackDuration) { this.knockBackDuration = knockBackDuration; }



    /**
     * Initializes the knockback of this entity in the opposite direction
     *
     * @param duration - duration of knockback
     */
    public void startKnockback(long duration, int knockBackStrength) {
        isKnockedBack = true;
        startKnockBack = System.currentTimeMillis();
        knockBackDuration = duration;
        shootDirectionOnKnockBack = entity.shootingDirection;
        this.knockBackStrength = knockBackStrength;
    }


}
