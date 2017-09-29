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
    float detectionBoxSize;

    EntityDetectorComponent(Entity entity, Entity searchingFor, float detectionBoxSize){
        super(entity, ComponentType.DETECTION);
        this.searchingFor = searchingFor;
        this.detectionBoxSize = detectionBoxSize;
    }

    @Override
    public void execute(){

    }


    public boolean CheckIfInView(float size){
        float centerX = entity.getX() + entity.getWidth()/2;
        float centerY = entity.getY() + entity.getHeight()/2;

        Rectangle detectionBox = new Rectangle((int)(centerX - size/2), (int)(centerY - size/2), (int)(size), (int)(size));

        if(detectionBox.contains(searchingFor.getBoundingBox())){
            return true;
        }else{
            return false;
        }
    }

    public boolean CheckIfInView(){
        float centerX = entity.getX() + entity.getWidth()/2;
        float centerY = entity.getY() + entity.getHeight()/2;

        float detectionBoxWidth = detectionBoxSize;
        float detectionBoxHeight = detectionBoxSize;

        Rectangle detectionBox = new Rectangle((int)(centerX - detectionBoxWidth/2), (int)(centerY - detectionBoxHeight/2), (int)(detectionBoxWidth), (int)(detectionBoxHeight));

        if(detectionBox.contains(searchingFor.getBoundingBox())){
            return true;
        }else{
            return false;
        }
    }

    public void draw(Graphics2D g2d){
        float centerX = entity.getX() + entity.getWidth()/2;
        float centerY = entity.getY() + entity.getHeight()/2;

        float detectionBoxWidth = detectionBoxSize;
        float detectionBoxHeight = detectionBoxSize;
        g2d.drawRect((int)(centerX - detectionBoxWidth/2), (int)(centerY - detectionBoxHeight/2), (int)(detectionBoxWidth), (int)(detectionBoxHeight));
    }

    public void draw(Graphics2D g2d, float size){
        float centerX = entity.getX() + entity.getWidth()/2;
        float centerY = entity.getY() + entity.getHeight()/2;

        g2d.drawRect((int)(centerX - size/2), (int)(centerY - size/2), (int)(size), (int)(size));
    }

}
