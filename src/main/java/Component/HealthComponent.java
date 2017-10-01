package Component;

import Entity.Entity;
import Entity.NinjaEntity;

public class HealthComponent extends Component{
    private NinjaEntity entity;
    private boolean hasShield = false;
    private int shieldSize = 3;
    private int currentHealth = 5;
    private long hitTime;

    public HealthComponent(Entity entity) {
        super(entity, ComponentType.HEALTH);
        this.entity = (NinjaEntity) entity;
    }

    public void incrementCurrentHealth() {
        currentHealth++;
    }

    /**
     * @return - if the entity with this component has a shield
     */
    public boolean isHasShield() {
        return hasShield;
    }

    /**
     * @param hasShield  - desired value for if this entity has a shield or not
     */
    public void setHasShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    /**
     * @return - the shield size of this entity
     */
    public int getShieldSize() {
        return shieldSize;
    }

    /**
     * @param shieldSize - the desired size of the shield
     */
    public void setShieldSize(int shieldSize) {
        this.shieldSize = shieldSize;
    }

    /**
     * @return - current health of this entity
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * @param currentHealth - desired current health for this entity
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * Attempts to decrement this ninjaEntity's health.
     */
    public void tryDecrementHealth() {
        long hitDelay = 1000;
//        check if hitDelay has been passed
        if (System.currentTimeMillis() - hitTime < hitDelay) {
            return;
        }
//        if it has shield. decrement that instead
        if (hasShield) {
            hitTime = System.currentTimeMillis();
            if (--shieldSize == 0) {
                hasShield = false;
            }
//        otherwise, decrement the health if it isn't already 0
        } else {
            hitTime = System.currentTimeMillis();
            if (currentHealth > 0) {
                currentHealth--;
            }
        }
    }

    @Override
    public void execute() {
        if(entity.getIsHit()) {
            tryDecrementHealth();
            entity.setIsHit(false); //reset hit to false to prevent continuous health decay
        }
    }
}
