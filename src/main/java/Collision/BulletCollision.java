package Collision;

import Component.WeaponComponent;
import Entity.*;
import Item.DefaultBullet;
import LevelGenerator.Rooms.Room;

import java.util.ArrayList;


public class BulletCollision {

    //contains the walls and the enemies
    private ArrayList<Entity> collisionGrid[][];

    private WeaponComponent shoot;

    public BulletCollision(Room room, WeaponComponent shoot) {
        this.collisionGrid = room.getCollisionGrid();
        this.shoot = shoot;
    }

    public void checkWithinRadius(){
//        for(int i = 0; i < collisionGrid.length; i++){
//            for(int j = 0; j < collisionGrid[0].length; j++){
//                for(Entity e : collisionGrid[i][j]){
//                    for(Entity bullet : shoot.getBullets().getEntityManager()) {
//                        if (bullet.getBoundingBox().intersects(e.getBoundingBox())) {
//                            System.out.println("bullet hit something");
//                        }
//                    }
//                }
//            }
//        }
    }


    public boolean checkCollisions(Entity bullet){

        //first check if the bullet is within the radius of any entities

        long time = System.currentTimeMillis();

        for (int i = 0; i < collisionGrid.length; i++) {
            for (int j = 0; j < collisionGrid[0].length; j++) {
                if(collisionGrid[i][j].size() > 1) {
               //     return checkCollisionsWithWalls(i, j, bullet);
                }
            }
        }



        long time2 = System.currentTimeMillis();

       // System.out.println(time2 - time);
        return false;
    }

    public boolean checkCollisionsWithWalls(int row, int col, Entity bullet) {

        Long milliStart = System.currentTimeMillis();
        ArrayList<Entity> toCheck = collisionGrid[row][col];

        for (Entity entity : toCheck) {
            if (entity.getBoundingBox().intersects(bullet.getBoundingBox())) {
                System.out.println(bullet.getID());
                return true;
            }
        }


        Long endTime = System.currentTimeMillis();

        //System.out.println("Time taken for collisions = " + (endTime - milliStart));
        return false;
    }


}
