package Components;

import Entities.Entity;

/**
 * Base of all Entity Components, examples of this are:
 * Controller component, allows user of keyboard to move the entity
 *
 *
 * Created by krishna on 2/09/2017.
 */
public abstract class Component {
    protected Entity entity;

    public Component(Entity entity){
        this.entity = entity;
    }

    public abstract void execute();

}
