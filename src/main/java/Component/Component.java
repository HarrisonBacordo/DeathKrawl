package Component;

import Entity.Entity;

/**
 * This class represents a range of features or properties that an entity can possess. Components include health,
 * movement, and collision.
 *
 * PRIMARY AUTHOUR: Harrison Bacordo (bacordharr)
 */
public abstract class Component {
    protected Entity entity;  //which entity this component belongs to
    protected ComponentType componentType;

    public Component(Entity entity, ComponentType componentType) {
        this.entity = entity;
        this.componentType = componentType;
    }

    /**
     * Returns the entity that this component belongs to
     *
     * @return - the entity that this component belongs to
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * returns the ComponentType for this component
     *
     * @return - the ComponentType for this component
     */
    public ComponentType getComponentType() {
        return componentType;
    }

    public void execute() {

    }
}
