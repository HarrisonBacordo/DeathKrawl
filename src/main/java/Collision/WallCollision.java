package Collision;

import Entity.*;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;

import java.util.ArrayList;
import java.util.List;

public class WallCollision {



    private ArrayList<Entity> collisionGrid[][];

    private Entity player;


    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    public WallCollision(Room room, Entity player) {
        this.collisionGrid = room.getCollisionGrid();
        this.player = player;
    }

    public WallCollision(NinjaEntity player){
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
                   // checkCollisionsWithWalls(i, j);
                }
            }
        }
    }

    public void checkCollisions(List<Entity> listOfCloseObjects){

        for(Entity first : listOfCloseObjects){
            for(Entity second : listOfCloseObjects){
                if(first!=second) {
                    if(first.getEntityType().equals(EntityType.PLAYER)) {
//                        System.out.println("Player x goes from " + first.getX() + " to " + (first.getX() + first.getWidth()));
//                        System.out.println("Player y goes from " + first.getY() + " to " + (first.getY() + first.getHeight()));

                    }
                    if(second.getEntityType().equals(EntityType.WALL)) {
//                        System.out.println("Wall x goes from " + second.getX() + " to " + (second.getX() + second.getWidth()));
//                        System.out.println("Wall y goes from " + second.getY() + " to " + (second.getY() + second.getHeight()));
                    }
                    if (first.getBoundingBox().intersects(second.getBoundingBox())) {
                        if(first.getEntityType().equals(EntityType.WALL) && second.getEntityType().equals(EntityType.PLAYER)){
                           // System.out.println("wall");
                            intersectPlayerWithWall(first);
                        }//TODO GET WORKING WITH DOORS AGAIN
                        else if(first.getEntityType().equals(EntityType.FLOOR_HAZARD) && second.getEntityType().equals(EntityType.PLAYER)){
                            //System.out.println("hazard");
                            intersectPlayerWithWall(first);
                        }

                        else if(first.getEntityType().equals(EntityType.DEFAULT_BULLET) && second.getEntityType().equals(EntityType.WALL)){
                            System.out.println("bullet");
                        }
                    }
                }
            }
        }


    }



    public void checkCollisionsWithWalls(int row, int col) {

        Long milliStart = System.currentTimeMillis();
        ArrayList<Entity> toCheck = collisionGrid[row][col];

        for (Entity entity : toCheck) {
            if (entity.getBoundingBox().intersects(player.getBoundingBox())) {
                    //pass the logic onto the method
                    intersectPlayerWithWall(entity);
            }
        }


        Long endTime = System.currentTimeMillis();

        //System.out.println("Time taken for collisions = " + (endTime - milliStart));
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
                //push the player off the wall so the collision ends
                player.setY(entity.getY() - player.getHeight());
                return;

            } else {
                //left of wall
                //push the player off the wall so the collision ends
                player.setX(entity.getX() + entity.getWidth());
                return;
            }
        } else {
            if (wy > -hx) {
                //right of wall
                //push the player off the wall so the collision ends
                player.setX(entity.getX() - player.getWidth());
                return;
            } else {
                //top of player hitting wall
                //push the player off the wall so the collision ends
                player.setY(entity.getY() + entity.getHeight());
                return;
            }

        }
    }



}




