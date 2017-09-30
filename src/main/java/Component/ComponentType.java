package Component;

/**
 * This enum functions as a way of storing all types of components that an entity may possess (i.e. health,
 * collision, shoot...).
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordahrr)
 */
public enum ComponentType {
    MOVEMENT,
    SHOOT,
    COLLISION,
    HEALTH,
    INPUT,
    DETECTION,
    WEAPON,
    KNOCKBACK;
}
