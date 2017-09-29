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
public class PushState implements State {

    Entity entity;
    Room currentRoom;
    NavigationGrid<GridCell> navGrid;
    Entity opponent;
    java.util.List<GridCell> pathToEnd;
    long tStart;

    float speed = 2;

    PushState(Entity entity, Room currentRoom, Entity opponent){
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;
//        navGrid = new NavigationGrid<>(currentRoom.getCells());
        tStart = System.nanoTime();
    }


    @Override
    public void execute() {
        moveForward();
    }

    /**
     * Gets angle between this entity (AI) and an opponent entity (player).
     * @return
     */
    public float getAngle(){

        float entityCenterX = entity.getX() + entity.getWidth()/2;
        float entityCenterY = entity.getY() + entity.getHeight()/2;


        float desiredX;
        float desiredY;

        desiredX = opponent.getX() + opponent.getWidth()/2;
        desiredY = opponent.getY() + opponent.getHeight()/2;


        float angle = (float) Math.toDegrees(Math.atan2((double)(desiredX - entityCenterX), desiredY - entityCenterY));

        angle += 90;

        return angle;
    }

    public void moveForward() {

        float entityCenterX = entity.getX() + entity.getWidth()/2;
        float entityCenterY = entity.getY() + entity.getHeight()/2;

        float opponentCenterX = opponent.getX() + opponent.getWidth()/2;
        float opponentCenterY = opponent.getY() + opponent.getHeight()/2;

        if(opponentCenterX > entityCenterX) entity.setX((int)(entity.getX() + speed)); //include size of player/entity(do it from the middle?)
        if(opponentCenterX< entityCenterX) entity.setX((int)(entity.getX() - speed));

        if(opponentCenterY > entityCenterY) entity.setY((int)(entity.getY() + speed));
        if(opponentCenterY < entityCenterY) entity.setY((int)(entity.getY() - speed));

    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);
    }

}