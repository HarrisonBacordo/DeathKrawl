package Collision;

import Entity.*;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Door;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;

import java.util.ArrayList;

public class WallCollision {

    //room that we want to look at for collisions
    private Room room;

    private ArrayList<Entity> collisionGrid[][];

    private Entity player;


    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    public WallCollision(Room room, Entity player) {
        this.room = room;
        this.collisionGrid = room.getCollisionGrid();
        this.player = player;
    }


    /**
     * Checks to see if there is more than one entity within a grid square, and thus is collision calculations are necessary
     */
    public void gridCheck() {
        for (int i = 0; i < collisionGrid.length; i++) {
            for (int j = 0; j < collisionGrid[0].length; j++) {
                //if the grid contains more than one collidable entity, then check collisions
                if (collisionGrid[i][j].size() > 1) {
                    checkCollisionsWithWalls(i, j);
                }
            }
        }
    }

    /**
     * Checks to see if a player is intersecting with a door, if so return the door otherwise return null.
     * Note: Does NOT use collision grid method as only 4 doors. TODO: ALLISTER I ADDED THIS
     * @return Door in collision or null if no door has been collided with.
     */
    public Door checkCollisionsWithDoors() {
        for(Door d : room.getDoors().values()){
            if(d.getBoundingBox().intersects(player.getBoundingBox())){
                return d;
            }
        }
        return null;
    }

    public void checkCollisionsWithWalls(int row, int col) {

        Long milliStart = System.currentTimeMillis();
        ArrayList<Entity> toCheck = collisionGrid[row][col];

        for (Entity entity : toCheck) {
            if (entity.getBoundingBox().intersects(player.getBoundingBox())) {
                if ((entity.getEntityType().equals(EntityType.WALL) || entity.getEntityType().equals(EntityType.FLOOR_HAZARD))&& player.getEntityType().equals(EntityType.PLAYER)) {
                    //pass the logic onto the method
                    intersectPlayerWithWall(entity);
                }else if(entity.getEntityType().equals(EntityType.DOOR)){
                    //ONLY HANDLES WHEN DOOR IS CLOSED TODO: ALLISTER I ADDED THIS
                    if(!((Door) entity).isOpen()){
                        //Door closed
                        intersectPlayerWithWall(entity);
                    }
                }
            }
        }


        Long endTime = System.currentTimeMillis();
        //collision times used to take around 30-60 when booting, then 6-13 when running around, mostly 6-9

        //=System.out.println("Time taken for collisions = " + (endTime - milliStart));
    }


    private void intersectPlayerWithWall(Entity entity){
        player.setXVelocity(0);
        player.setYVelocity(0);

        //sets the x and y pos to be the correct wall placment, will need to know where the wall is relative to the player to do this

        int wallCenterYPos = (entity.getY() + (entity.getHeight() / 2));
        int playerCenterYPos = (player.getY() + (player.getHeight() / 2));
        int wallCenterXPos = (entity.getX() + (entity.getWidth() / 2));
        int playerCenterXPos = (player.getX() + (player.getWidth() / 2));

        //uses Minkowski sum

        float wy = (entity.getWidth() + player.getWidth()) * (wallCenterYPos - playerCenterYPos);
        float hx = (entity.getHeight() + player.getHeight()) * (wallCenterXPos - playerCenterXPos);

        if (wy > hx) {
            if (wy > -hx) {
                //bottom of player hitting wall
                //System.out.println("bottom");
                //push the player off the wall so the collision ends
                player.setY(entity.getY() - player.getHeight());
                return;

            } else {
                //left of wall
                // System.out.println("left");
                //push the player off the wall so the collision ends
                player.setX(entity.getX() + entity.getWidth());
                return;
            }
        } else {
            if (wy > -hx) {
                //right of wall
                //System.out.println("right");
                //push the player off the wall so the collision ends
                player.setX(entity.getX() - player.getWidth());
                return;
            } else {
                //top of player hitting wall
                // System.out.println("top");
                //push the player off the wall so the collision ends
                player.setY(entity.getY() + entity.getHeight());
                return;
            }

        }
    }



}




