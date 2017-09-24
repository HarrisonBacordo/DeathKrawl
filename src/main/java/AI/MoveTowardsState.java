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
    float oldAngle;
    float angle;

    MoveTowardsState(Entity entity, Room currentRoom, Entity opponent){
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;
        navGrid = new NavigationGrid<>(currentRoom.getCells());
        tStart = System.currentTimeMillis();
        moveTowardsEnemy();
        oldAngle = getAngle();
    }


    @Override
    public void execute() {

        //System.out.println("size: : " + pathToEnd);

        angle = getAngle();

//        System.out.println(oldAngle);
//        System.out.println(angle);

        //if(!pathToEnd.isEmpty()){
        if(Math.abs(angle - oldAngle) > 10){
            oldAngle = getAngle();
        }


        moveForward(oldAngle);
        //}



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

//        desiredX = currentRoom.getX() + pathToEnd.get(0).getX()*32 + tileSize/2;
//        desiredY = currentRoom.getY() + pathToEnd.get(0).getY()*32 + tileSize/2;

        desiredX = opponent.getX() + opponent.getWidth()/2;
        desiredY = opponent.getY() + opponent.getHeight()/2;


        float angle = (float) Math.toDegrees(Math.atan2((double)(desiredX - entityCenterX), desiredY - entityCenterY));

        angle += 90;

        //angle = (float) (angle + Math.ceil( -angle / 360 ) * 360);
        return angle;
    }

    public void moveForward(float angle) {
        entity.setX((int)(entity.getX() + speed * sin(angle)));
        entity.setY((int)(entity.getY() + speed * cos(angle)));
    }

    public void moveTowardsEnemy(){
        //creating finder
        //currentRoom.setCellsToWalkable();
        navGrid = new NavigationGrid<>(currentRoom.getCells());
        AStarGridFinder<GridCell> finder = new AStarGridFinder<GridCell>(GridCell.class);

//        System.out.println("YEEEEEEEEEEET " + (opponent.getX() - currentRoom.getX())/32);
//        System.out.println("SKJEET " + (opponent.getY() - currentRoom.getY())/32);
//
//        System.out.println("EKKS " + (entity.getX() - currentRoom.getX())/32);
//        System.out.println("LEEKS " + (entity.getX() - currentRoom.getX())/32);

        //pathToEnd = finder.findPath((opponent.getX() - currentRoom.getX())/tileSize, (opponent.getY() - currentRoom.getY())/tileSize, (entity.getX() - currentRoom.getX())/tileSize, (entity.getY() - currentRoom.getY())/tileSize, navGrid);

        pathToEnd = finder.findPath((entity.getX() - currentRoom.getX())/tileSize, (entity.getY() - currentRoom.getY())/tileSize, (opponent.getX() - currentRoom.getX())/tileSize, (opponent.getY() - currentRoom.getY())/tileSize, navGrid);

    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;

        double elapsedSeconds = tDelta / 1000.0;

        if(elapsedSeconds > 2){
            tStart = 0;
            moveTowardsEnemy();
            System.out.println("why hello there");
        }

        //navGrid = new NavigationGrid<>(currentRoom.getCells());
        //moveTowardsEnemy();

//        pathToEnd.stream().filter(GridCell::isWalkable).filter(g ->  {
//            g2d.setColor(Color.GREEN);
//            g2d.fillRect( currentRoom.getX() + g.getX() * 32, currentRoom.getY() + g.getY()* 32, width, height);
//            return true;
//        });

        if(!pathToEnd.isEmpty()){
            for(GridCell gridCell: pathToEnd){
                if(gridCell.isWalkable()){
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect( currentRoom.getX() + gridCell.getX() * 32, currentRoom.getY() + gridCell.getY()* 32, width, height);
                }
            }
        }

        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);


        //g2d.fillRect(x, y, width, height);

//        g2d.setColor(Color.RED);


//        float entityCenterX = entity.getX() + entity.getWidth()/2;
//        float entityCenterY = entity.getY() + entity.getHeight()/2;
//
//        //System.out.println("X:                              " + pathToEnd.get(0).getX());
//        float desiredX = currentRoom.getX() + pathToEnd.get(0).getX()*32 + tileSize/2;
//        float desiredY = currentRoom.getY() + pathToEnd.get(0).getY()*32 + tileSize/2;
//
//        float angle = (float) Math.toDegrees(Math.atan2((double)(desiredX - entityCenterX), desiredY - entityCenterY));
//
//        g2d.drawLine((int)entityCenterX, (int)entityCenterY, (int)desiredX, (int)desiredY);






    }

}