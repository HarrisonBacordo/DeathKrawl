package Collision;

import Component.HealthComponent;
import Entity.*;
import Item.*;
import LevelGenerator.Rooms.Room;
import Component.WeaponComponent;
import Component.ComponentType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WallCollision {

    //player of the game
    private NinjaEntity player;
    //room the collisions are occuring within
    private Room room;

    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    public WallCollision(Room room, Entity player) {
        this.room = room;
        this.player = (NinjaEntity) player;
    }

    //second constructor for making tests faster
    public WallCollision(NinjaEntity player){
        this.player = player;
    }


    /**
     * This method is passed in a list of entites, and checks for collisions between them
     * using the bounding boxes provided by each of them. It then checks the types of the
     * entities if they collide to make sure that both entites behave correctly from the
     * collision.
     *
     * @param listOfCloseObjects List of entites that are near each other in the room.
     */
    public void checkCollisions(List<Entity> listOfCloseObjects){

        for(Entity first : listOfCloseObjects){
            for(Entity second : listOfCloseObjects){
                if(first!=second) {

                    String typeOfCollision = "";

                    //check for a collision between the bounding boxes
                    if (first.getBoundingBox().intersects(second.getBoundingBox())) {
                        if(first.getEntityType().equals(EntityType.WALL) && second.getEntityType().equals(EntityType.PLAYER)) {
                            typeOfCollision = "playerWithWall";
                        }
                        else if(first.getEntityType().equals(EntityType.FLOOR_HAZARD) && second.getEntityType().equals(EntityType.PLAYER)){
                            typeOfCollision = "playerWithHazard";
                        }
                        else if(first.getEntityType().equals(EntityType.MELEE_WEAPON) && second.getEntityType().equals(EntityType.ENEMY)){
                            typeOfCollision = "swordWithEnemy";
                        }
                        else if(first instanceof Bullet && second.getEntityType().equals(EntityType.WALL)){
                            typeOfCollision = "bulletWithWall";
                        }
                        else if(first.getEntityType().equals(EntityType.ENEMY) && second.getEntityType().equals(EntityType.WALL)){
                            typeOfCollision = "enemyWithWall";
                        }
                        else if((first.getEntityType().equals(EntityType.DEFAULT_BULLET) || first.getEntityType().equals(EntityType.SHOTGUN_BULLET) || first.getEntityType().equals(EntityType.FAST_BULLET)) && second.getEntityType().equals(EntityType.ENEMY)){
                            typeOfCollision = "enemyWithBullet";
                        }
                        else if(first.getEntityType().equals(EntityType.ENEMY) && second.getEntityType().equals(EntityType.ENEMY)){
                            typeOfCollision = "enemyWithEnemy";
                        }
                        else if(first.getEntityType().equals(EntityType.ENEMY) && second.getEntityType().equals(EntityType.PLAYER)){
                            typeOfCollision = "enemyWithPlayer";
                        }
                        else if((first.getEntityType().equals(EntityType.SWORD) || first.getEntityType().equals(EntityType.SHOTGUN) || first.getEntityType().equals(EntityType.PISTOL) || first.getEntityType().equals(EntityType.ASSAULT_RIFLE) ||
                                 first.getEntityType().equals(EntityType.SHIELD) || first.getEntityType().equals(EntityType.SPEEDBOOST) || first.getEntityType().equals(EntityType.HEART))
                                && second.getEntityType().equals(EntityType.PLAYER)){
                            typeOfCollision = "itemWithPlayer";
                        }

                        if(!typeOfCollision.isEmpty())
                          collide(typeOfCollision, first, second);
                    }
                }
            }
        }

    }

    /**
     * This method is a helper used by the main collision method to seperate
     * the logic to make it easier to understand. The type of collision is
     * passed in and the method will call the method for that collision
     * logic.
     *
     * @param typeOfCollision String describing the collision
     * @param first Entity that has collided
     * @param second Entity that has collided
     */

    private void collide(String typeOfCollision, Entity first, Entity second) {
        switch (typeOfCollision) {

            case "playerWithWall":
                intersectPlayerWithWall(first);
                break;

            case "playerWithHazard":
                intersectPlayerWithWall(first);
                break;

            case "bulletWithWall":
                room.getEntityManager().removeEntity(first);
                ((WeaponComponent) player.getComponent(ComponentType.WEAPON)).getBullets().removeEntity(first);
                break;

            case "enemyWithWall":
                intersectEnemyWithWall(first, second);
                break;

            case "enemyWithBullet":
                intersectBulletWithEnemy(first, second);
                break;

            case "enemyWithEnemy":
                intersectEnemyWithWall(first, second);
                break;

            case "enemyWithPlayer":
                NinjaEntity ninja = (NinjaEntity) second;
                ninja.setIsHit(true);
                break;

            case "itemWithPlayer":
                itemIntersectsPlayer(first);
                break;

            case "swordWithEnemy":
                intersectSwordWithEnemy(first, second);
                break;
        }
    }

    /**
     * Collision logic for an enemy colliding with a wall
     *
     * @param enemy
     * @param wall
     */

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

    /**
     * Collision logic for a player hitting a wall.
     *
     * @param entity
     */

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


    /**
     * Collision logic for a bullet hitting an enemy.
     *
     * @param bullet
     * @param enemy
     */

    private void intersectBulletWithEnemy(Entity bullet, Entity enemy){

        //TODO implement health/damage system for enemies
        room.removeEntity(enemy);
        room.getEntityManager().removeEntity(enemy);

        //delete the bullet
        room.getEntityManager().removeEntity(bullet);
        ((WeaponComponent) player.getComponent(ComponentType.WEAPON)).getBullets().removeEntity(bullet);

    }

    /**
     * Collision logic for a sword hitting an enemy.
     *
     * @param sword
     * @param enemy
     */
    private void intersectSwordWithEnemy(Entity sword, Entity enemy){

        //TODO implement health/damage system for enemies
        room.removeEntity(enemy);
        room.getEntityManager().removeEntity(enemy);
        ((WeaponComponent) player.getComponent(ComponentType.WEAPON)).getBullets().removeEntity(sword);


    }

    /**
     * Collision logic for a player picking an item up.
     *
     * @param item
     */

    private void itemIntersectsPlayer(Entity item){
        WeaponComponent weaponComponent = player.weaponComponent;
        HealthComponent healthComponent = (HealthComponent) player.getComponent(ComponentType.HEALTH);

        EntityType type = item.getEntityType();


        switch (type){

            case ASSAULT_RIFLE:
                AssaultRifle rifle = (AssaultRifle) item;
                rifle.setInInventory(true);
                weaponComponent.addWeapon(item);
                room.getEntityManager().removeEntity(rifle);
                break;

            case SHOTGUN:
                Shotgun shotgun = (Shotgun) item;
                shotgun.setInInventory(true);
                weaponComponent.addWeapon(item);
                room.getEntityManager().removeEntity(shotgun);
                break;

            case SWORD:
                Sword sword = (Sword) item;
                sword.setInInventory(true);
                weaponComponent.addWeapon(item);
                room.getEntityManager().removeEntity(sword);
                break;


            case SHIELD:
                Shield shield = (Shield) item;
                shield.setInInventory(true);
                healthComponent.setHasShield(true);
                room.getEntityManager().removeEntity(shield);
                break;

            case SPEEDBOOST:
                SpeedBoost speedBoost = (SpeedBoost) item;
                speedBoost.setInInventory(true);
                player.startBoost(10000);
                room.getEntityManager().removeEntity(speedBoost);
                break;

            case HEART:
                Heart heart = (Heart) item;
                heart.setInInventory(true);
                healthComponent.incrementCurrentHealth();
                //do player logic here
                room.getEntityManager().removeEntity(heart);
                break;


        }


    }


    public List<Entity> mergeWalls(List<Entity> wallsToMerge){

        List<Entity> mergedWalls = new ArrayList<Entity>();
        int size = wallsToMerge.size();

        List<Entity> toRemove = new ArrayList<Entity>();

        boolean removed = false;

        for(int i = 0; i < size; i++) {
            for(Entity e : wallsToMerge){
                for(Entity j : wallsToMerge){
                    if (e != j) {

                        //top and bottom lines
                        if (e.getY() == j.getY() && ((e.getX() + e.getWidth()) == j.getX())) {
                           // System.out.println("hit");
                            WallEntity newWall = new WallEntity(e.getX(), e.getY(), e.getWidth() + j.getWidth(), e.getHeight());
                            mergedWalls.add(newWall);
                            toRemove.add(e);
                            toRemove.add(j);
                            break;
                        }
                        //left and right lines
                        else if (e.getX() == j.getX() && ((e.getY() + e.getHeight() == j.getY()))) {
                          //  System.out.println("hit2");
                            WallEntity newWall = new WallEntity(e.getX(), e.getY(), e.getWidth(), e.getHeight() + j.getHeight());
                            mergedWalls.add(newWall);
                            toRemove.add(e);
                            toRemove.add(j);
                            break;
                        }


                    }
                }

            }

            wallsToMerge.removeAll(toRemove);
            mergedWalls.removeAll(toRemove);
            wallsToMerge.addAll(mergedWalls);
        }



        return mergedWalls;
    }


}




