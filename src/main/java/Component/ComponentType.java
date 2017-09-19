package Component;

/**
 * This enum functions as a way of storing all types of components that an entity may possess (i.e. health,
 * collision, shoot...).
 */
public enum ComponentType {
    MOVEMENT(0),
    SHOOT(1),
    COLLISION(2),
    HEALTH(3),
    INPUT(4),;

    private int value;

    ComponentType(int value) {
        this.value = value;
    }

    /**
     * returns the value of this ComponentType
     *
     * @return - value of this ComponentType
     */
    public int getValue() {
        return value;
    }
}
