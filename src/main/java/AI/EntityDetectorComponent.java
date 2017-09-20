package AI;

import Component.Component;
import Component.ComponentType;
import Entity.Entity;

/**
 * Created by kumardyla on 18/09/17.
 * Checks whether another entity is in view of the current entity
 */
public class EntityDetectorComponent extends Component{

    //Cone of vision
    float dist;
    float fov;

    EntityDetectorComponent(Entity entity){
        super(entity, ComponentType.DETECTION);
    }

    @Override
    public void execute(){

    }

    public boolean CheckIfInView(){
        return false;
    }

}
