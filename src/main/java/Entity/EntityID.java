package Entity;

/**
 * This class functions as a generator of unique ID numbers for entities. Unique ID numbers make it easy
 * to keep track of all generated entities.
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class EntityID {
    private static long ID = 0; //Increment ID every time one is requested, in order to maintain uniqueness

    /**
     * Generates a unique ID
     *
     * @return - the unique ID
     */
    public static long generateID() {
        return ID++;
    }
}
