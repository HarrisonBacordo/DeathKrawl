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

    Entity searchingFor;
    float detectionBoxSize;

    EntityDetectorComponent(Entity entity, Entity searchingFor, float detectionBoxSize){
        super(entity, ComponentType.DETECTION);
        this.searchingFor = searchingFor;
        this.detectionBoxSize = detectionBoxSize;
    }

    @Override
    public void execute(){
        //doesn't execute anything
    }


    /**
     * returns the center X coordinate of the entity the detection bounding box is from.
     * @return float
     */
    public float getCenterX(){
        return entity.getX() + entity.getWidth()/2;
    }


    /**
     * returns the center y coordinate of the entity the detection bounding box is from.
     * @return float of center y coordinate
     */
    public float getCenterY(){
        return entity.getY() + entity.getHeight()/2;
    }


    /**
     * Checks if the entities bounding box contains another entities bounding box
     * @param size, the custom size of the detection box
     * @return boolean whether detected or not
     */
    public boolean CheckIfInView(float size){
        Rectangle detectionBox = new Rectangle((int)(getCenterX() - size/2), (int)(getCenterY() - size/2), (int)(size), (int)(size));
        return (detectionBox.contains(searchingFor.getBoundingBox()));
    }

    /**
     * Checks if the entities bounding box contains another entities bounding box
     * @return boolean whether detected or not
     */
    public boolean CheckIfInView(){
        Rectangle detectionBox = new Rectangle((int)(getCenterX() - detectionBoxSize/2), (int)(getCenterY() - detectionBoxSize/2), (int)(detectionBoxSize), (int)(detectionBoxSize));
        return(detectionBox.contains(searchingFor.getBoundingBox()));
    }

    public void draw(Graphics2D g2d){
        g2d.drawRect((int)(getCenterX() - detectionBoxSize/2), (int)(getCenterY() - detectionBoxSize/2), (int)(detectionBoxSize), (int)(detectionBoxSize));
    }

    public void draw(Graphics2D g2d, float size){
        float centerX = entity.getX() + entity.getWidth()/2;
        float centerY = entity.getY() + entity.getHeight()/2;

        g2d.drawRect((int)(centerX - size/2), (int)(centerY - size/2), (int)(size), (int)(size));
    }

}
