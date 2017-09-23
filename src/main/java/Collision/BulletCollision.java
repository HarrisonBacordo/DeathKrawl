package Collision;

import Entity.*;
import LevelGenerator.Rooms.Room;

import java.util.ArrayList;


public class BulletCollision {

    private Room room;

    //contains the walls and the enemies
    private ArrayList<Entity> collisionGrid[][];

    private EntityManager bullets;

    public BulletCollision(Room room, EntityManager bullets) {
        this.room = room;
        this.bullets = bullets;
        this.collisionGrid = room.getCollisionGrid();
    }

    public void checkWithinRadius(){
        for(int i = 0; i < collisionGrid.length; i++){
            for(int j = 0; j < collisionGrid[0].length; j++){
                for(Entity e : collisionGrid[i][j]){
                    for(Entity bullets : bullets.getEntities()){
                        if(bullets.getBoundingBox().intersects(e.getBoundingBox())){
                            System.out.println("bullet hit something");
                        }
                    }
                }
            }
        }
    }



}
