package Collision;

import Entity.*;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;
import Component.ShootComponent;
import Component.ComponentType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WallCollision {
    private ArrayList<Entity> collisionGrid[][];
    private Entity player;
    private Room room;


    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    public WallCollision(Room room, Entity player) {
        this.room = room;
        this.collisionGrid = room.getCollisionGrid();
        this.player = player;
    }

    public WallCollision(NinjaEntity player){
        this.player = player;
    }


    public void checkCollisions(List<Entity> listOfCloseObjects){

        for(Entity first : listOfCloseObjects){
            for(Entity second : listOfCloseObjects){
                if(first!=second) {

                    String typeOfCollision = "";

                    if (first.getBoundingBox().intersects(second.getBoundingBox())) {
                        if(first.getEntityType().equals(EntityType.WALL) && second.getEntityType().equals(EntityType.PLAYER)) {
                            typeOfCollision = "playerWithWall";
                        }
                        else if(first.getEntityType().equals(EntityType.FLOOR_HAZARD) && second.getEntityType().equals(EntityType.PLAYER)){
                            typeOfCollision = "playerWithHazard";
                        }
                        else if((first.getEntityType().equals(EntityType.DEFAULT_BULLET) || first.getEntityType().equals(EntityType.SHOTGUN_BULLET)) && second.getEntityType().equals(EntityType.WALL)){
                            typeOfCollision = "bulletWithWall";
                        }
                        else if(first.getEntityType().equals(EntityType.ENEMY) && second.getEntityType().equals(EntityType.WALL)){
                            typeOfCollision = "enemyWithWall";
                        }
                        else if((first.getEntityType().equals(EntityType.DEFAULT_BULLET) || first.getEntityType().equals(EntityType.SHOTGUN_BULLET)) && second.getEntityType().equals(EntityType.ENEMY)){
                            typeOfCollision = "enemyWithBullet";
                        }

                        switch (typeOfCollision){

                            case "playerWithWall":
                                intersectPlayerWithWall(first);
                                break;

                            case "playerWithHazard":
                                intersectPlayerWithWall(first);
                                break;

                            case "bulletWithWall":
                                room.getEntities().remove(first);
                                ((ShootComponent) player.getComponent(ComponentType.SHOOT)).getBullets().remove(first);
                                break;

                            case "enemyWithWall":
                                intersectEnemyWithWall(first, second);
                                break;

                            case "enemyWithBullet":
                                intersectBulletWithEnemy(first,second);
                                break;

                        }
                    }
                }
            }
        }


    }


    private void intersectEnemyWithWall(Entity enemy, Entity wall){
        enemy.setXVelocity(0);
        enemy.setYVelocity(0);

        //sets the x and y pos to be the correct wall placment, will need to know where the wall is relative to the player to do this

        int wallCenterYPos = (wall.getY() + (wall.getHeight() / 2));
        int playerCenterYPos = (enemy.getY() + (enemy.getHeight() / 2));
        int wallCenterXPos = (wall.getX() + (wall.getWidth() / 2));
        int playerCenterXPos = (enemy.getX() + (enemy.getWidth() / 2));

        //uses Minkowski sum

        float wy = (wall.getWidth() + enemy.getWidth()) * (wallCenterYPos - playerCenterYPos);
        float hx = (wall.getHeight() + enemy.getHeight()) * (wallCenterXPos - playerCenterXPos);

        if (wy > hx) {
            if (wy > -hx) {
                //bottom of player hitting wall
                //push the player off the wall so the collision ends
                enemy.setY(wall.getY() - enemy.getHeight());
                return;

            } else {
                //left of wall
                //push the player off the wall so the collision ends
                enemy.setX(wall.getX() + wall.getWidth());
                return;
            }
        } else {
            if (wy > -hx) {
                //right of wall
                //push the player off the wall so the collision ends
                enemy.setX(wall.getX() - enemy.getWidth());
                return;
            } else {
                //top of player hitting wall
                //push the player off the wall so the collision ends
                enemy.setY(wall.getY() + wall.getHeight());
                return;
            }

        }
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


    private void intersectBulletWithEnemy(Entity bullet, Entity enemy){

        //TODO implement health/damage system for enemies 

        room.removeEntity(enemy);
        room.enemyManager.removeEntity(enemy);

        //delete the bullet
        room.getEntities().remove(bullet);
        ((ShootComponent) player.getComponent(ComponentType.SHOOT)).getBullets().remove(bullet);

    }


}




