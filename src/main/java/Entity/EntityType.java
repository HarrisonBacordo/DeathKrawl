package Entity;

/**
 * This enum functions as a way of storing all types of entities (i.e. player,
 * enemy, wall...).
 */
public enum EntityType {
    PLAYER(0),
    ENEMY(1),
    BOSS(2),
    WALL(3),
    ITEM(4),
    FLOOR(5),
    DOOR(6),
    DEFAULT_BULLET(7),
    FAST_BULLET(8),
    FLOOR_HAZARD(9);

    private int value;

    EntityType(int value) {
        this.value = value;
    }

    /**
     * returns the value of this EntityType
     *
     * @return - value of this EntityType
     */
    public int getValue() {
        return value;
    }

}
