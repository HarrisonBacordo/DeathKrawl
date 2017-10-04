package AI;

import Entity.Entity;
import LevelGenerator.Rooms.Room;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;

import java.awt.*;
import java.util.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by kumardyla on 21/09/17.
 */
public class MoveTowardsState implements State {

    Entity entity;
    Room currentRoom;
    NavigationGrid<GridCell> navGrid;
    Entity opponent;
    java.util.List<GridCell> pathToEnd;
    long tStart;

    float speed = 2;
    int tileSize = 32;

    public MoveTowardsState(Entity entity, Room currentRoom, Entity opponent){
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;
        tStart = System.nanoTime();
    }


    @Override
    public void execute() {
        moveForward();
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
     * returns the center X coordinate of the opponents bounding box.
     * @return float
     */
    public float getOpponentCenterX(){
        return opponent.getX() + opponent.getWidth() / 2;
    }


    /**
     * returns the center y coordinate of the entity the opponents bounding.
     * @return float of center y coordinate
     */
    public float getOpponentCenterY(){
        return opponent.getY() + opponent.getHeight() / 2;
    }

    /**
     * Returns the entity of the state
     * @return entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Returns the entity of the opponent of the state
     * @return opponent
     */
    public Entity getOpponent() {
        return opponent;
    }
    /**
     * Gets angle between this entity (AI) and an opponent entity (player).
     * @return
     */
    public float getAngle(){
        float desiredX = getOpponentCenterX();
        float desiredY = getOpponentCenterY();


        float angle = (float) Math.toDegrees(Math.atan2((double)(desiredX - getCenterX()), desiredY - getCenterY()));

//        angle += 90;

        return angle;
    }


    /**
     * Moves the AI towards the opponent
     */
    public void moveForward() {
        if(getOpponentCenterX() > getCenterX()){
            entity.setX((int)(entity.getX() + speed)); //include size of player/entity(do it from the middle?)
        }else{
            entity.setX((int)(entity.getX() - speed));
        }

        if(getOpponentCenterY() > getCenterY()){
            entity.setY((int)(entity.getY() + speed));
        }else{
            entity.setY((int)(entity.getY() - speed));
        }

    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);
    }

}