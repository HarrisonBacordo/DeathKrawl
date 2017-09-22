package AI;

import Component.Component;
import Component.ComponentType;
import Entity.Entity;

import java.awt.*;

/**
 * Created by kumardyla on 18/09/17.
 * Checks whether another entity is in view of the current entity
 */
public class EntityDetectorComponent extends Component{

    //Cone of vision
    float dist;
    float fov;
    Entity searchingFor;

    EntityDetectorComponent(Entity entity, Entity searchingFor){
        super(entity, ComponentType.DETECTION);
        this.searchingFor = searchingFor;
    }

    @Override
    public void execute(){

    }

    public boolean CheckIfInView(){
        float centerX = entity.getX() + entity.getWidth()/2;
        float centerY = entity.getY() + entity.getHeight()/2;

        float detectionBoxWidth = 300;
        float detectionBoxHeight = 300;

        Rectangle detectionBox = new Rectangle((int)(centerX - detectionBoxWidth/2), (int)(centerY - detectionBoxHeight), (int)(detectionBoxWidth), (int)(detectionBoxHeight));

        if(detectionBox.contains(searchingFor.getBoundingBox())){
            return true;
        }else{
            return false;
        }
    }

}
