package Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the storage for all entities. It implements specific ways to
 * access the list of entities, such as finding entities by type.
 * <p>
 * PRIMARY AUTHOR: Harrison Bacordo (bacordoharr)
 */
public class EntityManager {
    private List<Entity> entities;
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

    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Adds the passed in entity to this EntityManager
     *
     * @param entityToAdd - entity to add
     */
    public boolean addEntity(Entity entityToAdd) {
//        switch (entityToAdd.getEntityType()) {
//            case PLAYER:
//                return validateAdd(entityToAdd, playerEntityList);
//            case ENEMY:
//                return validateAdd(entityToAdd, enemyEntityList);
//            case WALL:
//                return validateAdd(entityToAdd, wallEntityList);
//            case DOOR:
//                return validateAdd(entityToAdd, doorEntityList);
//            case FLOOR:
//                return validateAdd(entityToAdd, floorEntityList);
//            case FLOOR_HAZARD:
//                return validateAdd(entityToAdd, floorHazardEntityList);
//            case ITEM:
//                return validateAdd(entityToAdd, itemEntityList);
//            case DEFAULT_BULLET:
//            case FAST_BULLET:
//            case SLOW_BULLET:
//            case SHOTGUN_BULLET:
//                return validateAdd(entityToAdd, bulletEntityList);
//        }
        return validateAdd(entityToAdd, entities);
//        return false;
    }

    private boolean validateAdd(Entity entityToAdd, List<Entity> listToAddEntity) {
        for (Entity entity : listToAddEntity) {
            if (entity.getID() == entityToAdd.getID()) {
                return false;
            }
        }
        listToAddEntity.add(entityToAdd);
//        System.out.println(listToAddEntity.get(0).getEntityType() + ": " + listToAddEntity.size());
        return true;
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
     * @param entityType - type of entity to look for
     * @return - a list of all entities of the given type
     */
    public List<Entity> findEntitiesWithType(EntityType entityType) {
        ArrayList<Entity> entitiesWithRequestedType = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getEntityType() == entityType) {
                entitiesWithRequestedType.add(entity);
            }
        }
        return entitiesWithRequestedType;
    }

    /**
     * Removes the passed in entity from this EntityManager
     *
     * @param entityToRemove - entity to remove
     * @return - whether it was successfully removed or not
     */
    public boolean removeEntity(Entity entityToRemove) {
        for (Entity entity : entities) {
            if (entity.getID() == entityToRemove.getID()) {
                return entities.remove(entity);
            }
        }
        return false;
    }

    /**
     * Removes the entity at the given index
     *
     * @param indexOfEntity - index of entity to remove
     * @return - the entity that has been removed
     */
    public Entity removeEntity(int indexOfEntity) {
        return entities.remove(indexOfEntity);
    }

    public void executeAllComponents() {
        for (Entity entity : entities) {
            entity.getComponents().executeAllComponents();
        }
    }

    /**
     * Ticks all entities in this EntityManager
     */
    public void tickAllEntities() {
        for (Entity entity : entities) {
            entity.tick();
        }
    }

    /**
     * Renders all entities in this EntityManager
     *
     * @param g - graphics to render from
     */
    public void renderAllEntities(Graphics g) {
        for (Entity entity : entities) {
            entity.render(g);
        }
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
