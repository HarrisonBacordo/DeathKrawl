package Collision;

import Entity.*;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;

import java.util.ArrayList;

public class Collision {



    //room that we want to look at for collisions
    private Room room;

    private ArrayList<Entity> collisionGrid[][];

    private Entity player;


    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    public Collision(Room room, Entity player){
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
                    checkCollisions(i,j);
                }
            }
        }
    }


    /**
     //     * Look for collisions within the grid
     //     */
    public void checkCollisions(int row, int col){

        Long milliStart = System.currentTimeMillis();
        ArrayList<Entity> toCheck = collisionGrid[row][col];


        //Entity player = room.getEntities();

        //for all the entities in the grid tile

        //if their rectangles overlap
        for(Entity entity : toCheck) {
            if (entity.getBoundingBox().intersects(player.getBoundingBox())){
                    if(entity.getEntityType().equals(EntityType.WALL) && player.getEntityType().equals(EntityType.PLAYER)){
                        player.setXVelocity(0);
                        player.setYVelocity(0);

                        //sets the x and y pos to be the correct wall placment, will need to know where the wall is relative to the player to do this

                        int wallCenterYPos = (  entity.getY() + (entity.getHeight() / 2) );
                        int playerCenterYPos = (player.getY() + (player.getHeight() / 2));
                        int wallCenterXPos = ( entity.getX() + (entity.getWidth()/2));
                        int playerCenterXPos = (player.getX() + (player.getWidth()/2));

                        //uses Minkowski sum

                        float wy = (entity.getWidth() + player.getWidth()) * (wallCenterYPos - playerCenterYPos);
                        float hx = (entity.getHeight() + player.getHeight()) * (wallCenterXPos - playerCenterXPos);

                        //how much to push the player back off the wall
                        int buffer = 2;

                        //TODO ALLISTER gets stuck in the right hand bottom corner

                        if (wy > hx) {
                            if (wy > -hx) {
                                //bottom of player hitting wall
                                System.out.println("bottom");
                                //push the player off the wall so the collision ends
                                player.setY( entity.getY() - buffer - player.getHeight() );
                                return;

                            } else {
                                //left of wall
                                System.out.println("left");
                                //push the player off the wall so the collision ends
                                player.setX( entity.getX() + entity.getWidth() + buffer );
                                return;
                            }
                        }
                        else {
                            if (wy > -hx) {
                                //right of wall
                                System.out.println("right");
                                //push the player off the wall so the collision ends
                                player.setX( entity.getX() - buffer - player.getWidth() );
                                return;
                            }

                            else{
                                //top of player hitting wall
                                System.out.println("top");
                                //push the player off the wall so the collision ends
                                player.setY( entity.getY() + entity.getHeight() + buffer );
                                return;
                            }

                        }
                    }
                }
            }
        }

        Long endTime = System.currentTimeMillis();
        //collision times used to take around 30-60 when booting, then 6-13 when running around, mostly 6-9

        //pass the logic onto the entity itself
        //System.out.println("Time taken for collisions = " + (endTime - milliStart));

    }


//    /**
//     * Look for collisions within the grid
//     */
//    public void checkCollisions(int row, int col){
//
//        Long milliStart = System.currentTimeMillis();
////        Entity toCheck = grid[row][col];
//
//        //for all the entities in the grid tile
//
//        //if their rectangles overlap
//        for(Entity wall : room.getEntities()) {
//            for (Entity player : room.getEntities()) {
//                if (wall.getBoundingBox().intersects(player.getBoundingBox())){
//                    if(wall.getEntityType().equals(EntityType.WALL) && player.getEntityType().equals(EntityType.PLAYER)){
//                        player.setXVelocity(0);
//                        player.setYVelocity(0);
//
//                        //sets the x and y pos to be the correct wall placment, will need to know where the wall is relative to the player to do this
//
//                        int wallCenterYPos = (  wall.getY() + (wall.getHeight() / 2) );
//                        int playerCenterYPos = (player.getY() + (player.getHeight() / 2));
//                        int wallCenterXPos = ( wall.getX() + (wall.getWidth()/2));
//                        int playerCenterXPos = (player.getX() + (player.getWidth()/2));
//
//                        //uses Minkowski sum
//
//                        float wy = (wall.getWidth() + player.getWidth()) * (wallCenterYPos - playerCenterYPos);
//                        float hx = (wall.getHeight() + player.getHeight()) * (wallCenterXPos - playerCenterXPos);
//
//                        //how much to push the player back off the wall
//                        int buffer = 2;
//
//                        //TODO ALLISTER gets stuck in the right hand bottom corner
//
//                        if (wy > hx) {
//                            if (wy > -hx) {
//                                //bottom of player hitting wall
//                                System.out.println("bottom");
//                                //push the player off the wall so the collision ends
//                                player.setY( wall.getY() - buffer - player.getHeight() );
//                                return;
//
//                            } else {
//                                //left of wall
//                                System.out.println("left");
//                                //push the player off the wall so the collision ends
//                                player.setX( wall.getX() + wall.getWidth() + buffer );
//                                return;
//                            }
//                        }
//                        else {
//                            if (wy > -hx) {
//                                //right of wall
//                                System.out.println("right");
//                                //push the player off the wall so the collision ends
//                                player.setX( wall.getX() - buffer - player.getWidth() );
//                                return;
//                            }
//
//                            else{
//                                //top of player hitting wall
//                                System.out.println("top");
//                                //push the player off the wall so the collision ends
//                                player.setY( wall.getY() + wall.getHeight() + buffer );
//                                return;
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//
//        Long endTime = System.currentTimeMillis();
//        //collision times used to take around 30-60 when booting, then 6-13 when running around, mostly 6-9
//
//        //pass the logic onto the entity itself
//        //System.out.println("Time taken for collisions = " + (endTime - milliStart));
//
//    }



