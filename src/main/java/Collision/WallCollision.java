package Collision;

import Component.HealthComponent;
import Entity.*;
import Item.*;
import LevelGenerator.Rooms.Room;
import Component.WeaponComponent;
import Component.ComponentType;

import java.util.ArrayList;
import java.util.List;

public class WallCollision {
    private ArrayList<Entity> collisionGrid[][];
    private NinjaEntity player;
    private Room room;


    /**
     * Constructor that sets the currentRoom and grid given a level
     */
    public WallCollision(Room room, Entity player) {
        this.room = room;
        this.collisionGrid = room.getCollisionGrid();
        this.player = (NinjaEntity) player;
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
                        else if(first.getEntityType().equals(EntityType.MELEE_WEAPON) && second.getEntityType().equals(EntityType.ENEMY)){
                            typeOfCollision = "swordWithEnemy";
                        }
                        else if((first.getEntityType().equals(EntityType.DEFAULT_BULLET) || first.getEntityType().equals(EntityType.SHOTGUN_BULLET)) && second.getEntityType().equals(EntityType.WALL)){
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

                        if(!typeOfCollision.equals("")) {
                            switch (typeOfCollision) {

                                case "playerWithWall":
                                    intersectPlayerWithWall(first);
                                    break;

                                case "playerWithHazard":
                                    intersectPlayerWithWall(first);
                                    break;

                                case "bulletWithWall":
                                    room.getEntityManager().removeEntity(first);
                                    ((WeaponComponent) player.getComponent(ComponentType.SHOOT)).getBullets().removeEntity(first);
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
        room.getEntityManager().removeEntity(enemy);

        //delete the bullet
        room.getEntityManager().removeEntity(bullet);
        ((WeaponComponent) player.getComponent(ComponentType.SHOOT)).getBullets().removeEntity(bullet);

    }

    private void intersectSwordWithEnemy(Entity sword, Entity enemy){

        //TODO implement health/damage system for enemies
        room.removeEntity(enemy);
        room.getEntityManager().removeEntity(enemy);
        ((WeaponComponent) player.getComponent(ComponentType.SHOOT)).getBullets().removeEntity(sword);


    }

    private void itemIntersectsPlayer(Entity item){
        WeaponComponent weaponComponent = player.weaponComponent;
        HealthComponent healthComponent = (HealthComponent) player.getComponent(ComponentType.HEALTH);

        String type = item.getClass().toString();


        switch (type){

            case("class Item.AssaultRifle"):
                AssaultRifle rifle = (AssaultRifle) item;
                rifle.setInInventory(true);
                weaponComponent.addWeapon(item);
                room.getEntityManager().removeEntity(rifle);
                break;

            case("class Item.Shotgun"):
                Shotgun shotgun = (Shotgun) item;
                shotgun.setInInventory(true);
                weaponComponent.addWeapon(item);
                room.getEntityManager().removeEntity(shotgun);
                break;

            case("class Item.Sword"):
                Sword sword = (Sword) item;
                sword.setInInventory(true);
                weaponComponent.addWeapon(item);
                room.getEntityManager().removeEntity(sword);
                break;


            case("class Item.Shield"):
                Shield shield = (Shield) item;
                shield.setInInventory(true);
                healthComponent.setHasShield(true);
                room.getEntityManager().removeEntity(shield);
                break;

            case("class Item.SpeedBoost"):
                SpeedBoost speedBoost = (SpeedBoost) item;
                speedBoost.setInInventory(true);
                player.startBoost(10000);
                room.getEntityManager().removeEntity(speedBoost);
                break;

            case("class Item.Heart"):
                Heart heart = (Heart) item;
                heart.setInInventory(true);
                healthComponent.incrementCurrentHealth();
                //do player logic here
                room.getEntityManager().removeEntity(heart);
                break;


        }


    }


}




