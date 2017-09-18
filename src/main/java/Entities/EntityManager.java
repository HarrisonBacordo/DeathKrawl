package Entities;

import java.awt.*;
import java.util.LinkedList;

/**
 * Entity Management system that handles all entities in the game.
 * Entities are split into normal and high priority entities,
 * with high priority entities being drawn after normal ones
 *
 * Created by krishna on 2/09/2017.
 */
public class EntityManager<T extends Entity> {
    private LinkedList<T> entities;
    private LinkedList<T> highPriority;

    public EntityManager(){
        entities      = new LinkedList<>();
        highPriority  = new LinkedList<>();
    }

    /**
     * Updates all stored entities
     */
    public void tick() {
        for (T entity : entities) entity.tick();
        for (T entity : highPriority) entity.tick();
    }

    /**
     * Renders all stored entities
     * @param g, graphics object to render entities with
     */
    public void render(Graphics g){
        for (T entity : entities) entity.render(g);
        for (T a : highPriority) a.render(g); //Always rendered players on top
    }

    /**
     *
     * Adds a new Entity to the manager
     * @param entity, Sub-type of Entity to be added
     */
    public void add(T entity){
        if (entity.getId().equals(ID.PLAYER)) highPriority.add(entity);
        else entities.add(entity);
    }

    /**
     * Removes the entity from the manager
     * @param entity, to be removed
     */
    public void remove(T entity){
        if(entities.contains(entity)) entities.remove(entity);
        else highPriority.remove(entity);
    }

    /**
     * Returns the list of game entities
     * @return entities list
     */
    public LinkedList<T> getEntities(){
        return entities;
    }

    /**
     * Finds and returns the player entity if it exists
     * @return player entity or null
     */
    public Entity getPlayer() {
        for(Entity entity : highPriority) if(entity.getId().equals(ID.PLAYER)) return entity;
        return null;
    }

}
