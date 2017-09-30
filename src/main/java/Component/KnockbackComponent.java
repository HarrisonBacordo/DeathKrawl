package Component;

import Entity.Entity;
import Entity.NinjaEntity;

public class KnockbackComponent extends Component{

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
                return;
            } else {
                isKnockedBack = false;
            }
        }
    }


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
