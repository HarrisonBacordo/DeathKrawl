package Entity;

import LevelGenerator.Rooms.Door;
import LevelGenerator.Rooms.LOCATION;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the storage for all entities. It implements specific ways to
 * access the list of entities, such as finding entities by type.
 * <p>
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class EntityManager {
    private List<Entity> entities;
    private List<Entity> dynamicEntityList;
    private List<Entity> staticEntityList;
    private List<Entity> playerEntityList;
    private List<Entity> enemyEntityList;
    private List<Entity> wallEntityList;
    private List<Entity> floorEntityList;
    private List<Entity> doorEntityList;
    private List<Entity> bulletEntityList;
    private List<Entity> floorHazardEntityList;
    private List<Entity> itemEntityList;


    public EntityManager() {
        entities = new ArrayList<>();
        playerEntityList = new ArrayList<>();
        enemyEntityList = new ArrayList<>();
        wallEntityList = new ArrayList<>();
        floorEntityList = new ArrayList<>();
        doorEntityList = new ArrayList<>();
        bulletEntityList = new ArrayList<>();
        floorHazardEntityList = new ArrayList<>();
        itemEntityList = new ArrayList<>();
        dynamicEntityList = new ArrayList<>();
        staticEntityList = new ArrayList<>();
    }

    /**
     * Checks if there are any entities with the passed in ID
     *
     * @param ID - ID of the entity to look for
     * @return - if entity is present or not
     */
    public boolean containsEntity(long ID) {
        for (Entity entity : entities) {
            if (entity.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return - list of entities for this entity manager
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Adds the passed in entity to this EntityManager, and sorts
     * it into two other lists: its entityType list, as well as
     * either a staticEntity list (doesn't move/tick) or a
     * dynamicEntity list (does move/tick)
     *
     * @param entityToAdd - entity to add
     */
    public boolean addEntity(Entity entityToAdd) {
        if(!validateAdd(entityToAdd, entities)) { return false; } //attempt to add it to entities list
        switch (entityToAdd.getEntityType()) {
            case PLAYER:
                if (validateAdd(entityToAdd, playerEntityList)) {
                    return dynamicEntityList.add(entityToAdd);
                }
            case ENEMY:
                if (validateAdd(entityToAdd, enemyEntityList)) {
                    return dynamicEntityList.add(entityToAdd);
                }
            case WALL:
                if (validateAdd(entityToAdd, wallEntityList)) {
                    return staticEntityList.add(entityToAdd);
                }
            case DOOR:
                if (validateAdd(entityToAdd, doorEntityList)) {
                    return staticEntityList.add(entityToAdd);
                }
            case FLOOR:
                if (validateAdd(entityToAdd, floorEntityList)) {
                    return staticEntityList.add(entityToAdd);
                }
            case FLOOR_HAZARD:
                if (validateAdd(entityToAdd, floorHazardEntityList)) {
                    return staticEntityList.add(entityToAdd);
                }
//                All items
            case ITEM:
            case SHOTGUN:
            case ASSAULT_RIFLE:
            case SHIELD:
            case PISTOL:
            case SWORD:
            case SPEEDBOOST:
            case HEART:
                if (validateAdd(entityToAdd, itemEntityList)) {
                    return staticEntityList.add(entityToAdd);
                }
            case DEFAULT_BULLET:
            case FAST_BULLET:

            case SLOW_BULLET:

            case SHOTGUN_BULLET:
                if (validateAdd(entityToAdd, bulletEntityList)) {
                    return dynamicEntityList.add(entityToAdd);
                }

        }
        return false;
    }

    /**
     * Ensures that the passed-in entity is not already contained in the list
     * it is being added to. If it isn't then it adds the entity to the list
     * and returns true
     *
     * @param entityToAdd     - desired entity to put in list
     * @param listToAddEntity - desired list to put entity in
     * @return - if add was successful or not
     */
    private boolean validateAdd(Entity entityToAdd, List<Entity> listToAddEntity) {
        return !listToAddEntity.contains(entityToAdd) && listToAddEntity.add(entityToAdd);
    }

    /**
     * adds passed in list of entities to the this entity manager
     *
     * @param entitiesToAdd - list of entities to add
     */
    public Boolean addAllEntities(List<Entity> entitiesToAdd) {
//        Pass it through a for loop in order to check for duplicates
        for (Entity entity : entitiesToAdd) {
            if (!addEntity(entity)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of all entities that match a given EntityType
     *
     * @param typeToFind - type of entity to look for
     * @return - a list of all entities of the given type
     */
    public List<Entity> getEnemiesWithType(EntityType typeToFind) {
        switch (typeToFind) {
            case PLAYER:
                return playerEntityList;
            case ENEMY:
                return enemyEntityList;
            case WALL:
                return wallEntityList;
            case DOOR:
                return doorEntityList;
            case FLOOR:
                return floorEntityList;
            case FLOOR_HAZARD:
                return floorHazardEntityList;
//                All items
            case ITEM:
            case SHOTGUN:
            case ASSAULT_RIFLE:
            case SHIELD:
            case PISTOL:
            case SWORD:
            case SPEEDBOOST:
            case HEART:
                return itemEntityList;
            case DEFAULT_BULLET:
            case FAST_BULLET:
            case SLOW_BULLET:
            case SHOTGUN_BULLET:
                return bulletEntityList;

        }
        return null;
    }

    /**
     * Removes the passed in entity from this EntityManager and its
     * associated lists
     *
     * @param entityToRemove - entity to remove
     * @return - whether it was successfully removed or not
     */
    public boolean removeEntity(Entity entityToRemove) {
        if(!validRemove(entityToRemove, entities)) { return false; } //attempt to add it to entities list
        switch (entityToRemove.getEntityType()) {
            case PLAYER:
                if (validRemove(entityToRemove, playerEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }
            case ENEMY:
                if (validRemove(entityToRemove, enemyEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }
            case WALL:
                if (validRemove(entityToRemove, wallEntityList)) {
                    return staticEntityList.remove(entityToRemove);
                }
            case DOOR:
                if (validRemove(entityToRemove, doorEntityList)) {
                    return staticEntityList.remove(entityToRemove);
                }
            case FLOOR:
                if (validRemove(entityToRemove, floorEntityList)) {
                    return staticEntityList.remove(entityToRemove);
                }
            case FLOOR_HAZARD:
                if (validRemove(entityToRemove, floorHazardEntityList)) {
                    return staticEntityList.remove(entityToRemove);
                }
            case ITEM:
                if (validRemove(entityToRemove, itemEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }
            case DEFAULT_BULLET:
                if (validRemove(entityToRemove, bulletEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }
            case FAST_BULLET:
                if (validRemove(entityToRemove, bulletEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }

            case SLOW_BULLET:
                if (validRemove(entityToRemove, bulletEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }

            case SHOTGUN_BULLET:
                if (validRemove(entityToRemove, bulletEntityList)) {
                    return dynamicEntityList.remove(entityToRemove);
                }

        }
        return false;

    }

    /**
     * validates the remove by ensuring the entity exists
     *
     * @param entityToRemove     - entity to remove
     * @param listToRemoveEntity - list to remove entity from
     * @return - if it succeeded
     */
    private boolean validRemove(Entity entityToRemove, List<Entity> listToRemoveEntity) {
        return listToRemoveEntity.remove(entityToRemove);
    }

    /**
     * @return - the player entity
     */
    public Entity getPlayer() {
        return playerEntityList.get(0);
    }

    /**
     * Ticks all entities in this EntityManager
     */
    public void tickAllEntities() {
        for (Entity entity : dynamicEntityList) {
            entity.tick();
        }
    }

    /**
     * Renders all entities in this EntityManager
     *
     * @param g - graphics to render from
     */
    public void renderAllEntities(Graphics g) {
        /*rendering is done in a specific order as to
          ensure that the foreground entities don't get
          drawn behind any background entities*/

//        render non-moving "background" stuff first
        for (Entity entity : staticEntityList) {
            entity.render(g);
        }

//        then render doors on top of it
        for (Entity entity : doorEntityList) {
            entity.render(g);
        }

//        then render dynamic entities on top of it
        for (Entity entity : dynamicEntityList) {
            entity.render(g);
        }

//        finally, render the items
        for (Entity entity : itemEntityList) {
            entity.render(g);
        }
    }

    /**
     * Removes and returns the door with the corresponding enum
     *
     * @param location of the door
     * @return the door or null if not found
     */
    public Door removeDoor(LOCATION location) {
        Door toReturn = null;
        Iterator iterator = doorEntityList.iterator();

        while (iterator.hasNext()) {
            Door door = (Door) iterator.next();
            if (door.getLocation().equals(location)) {
                toReturn = door;
                iterator.remove();
                break;
            }
        }

        return toReturn;
    }

    /**
     * Checks if the entity manager is empty
     *
     * @return - if it is empty
     */
    public boolean isEmpty() {
        return entities.isEmpty();
    }

    /**
     * Returns the size of this entity manager
     *
     * @return - size of entity manager
     */
    public int size() {
        return entities.size();
    }
}
