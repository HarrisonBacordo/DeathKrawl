import AI.GrappleAI;
import AI.MoveTowardsState;
import AI.MoverAI;
import Collision.WallCollision;
import Component.WeaponComponent;
import Entity.*;
import HUD.HeadsUpDisplay;
import HUD.HealthBar;
import HUD.Inventory;
import Item.*;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;
import ResourceLoader.Resources;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class CollisionTest {

    private final int cellWidth = 32;
    private final int cellHeight = 32;

    private WallCollision collision;

    public CollisionTest(){
        init();
    }



    /**
     * Wall tests, checking if a player running into a wall behave correctly.
     */

    //test that the player does not move when it doesn't run into a wall
    @Test
    public void wallTest01(){
        init();

        //make a new player and wall
        NinjaEntity player = new NinjaEntity(40,40,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0,0,cellWidth,cellHeight);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , wall );

        //and use the collision method to check a collision between them
        collision.checkCollisions(list);

        //make sure nothing happened
        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 40 );

    }


    //test that the player gets pushed the right amount and right direction when colliding into the wall from the right
    @Test
    public void wallTest02(){
        init();

        //make a new player and wall
        NinjaEntity player = new NinjaEntity(40,20,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0,0,cellWidth,cellHeight);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , wall );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 20 );

        //cause a collision
        player.setX(31);

        //check it and fix it
        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 32 && player.getY() == 20);

    }

    //test that the player gets pushed the right amount and right direction when colliding into the wall from the bottom
    @Test
    public void wallTest03(){
        init();

        //make a new player and wall
        NinjaEntity player = new NinjaEntity(20,40,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0,0,cellWidth,cellHeight);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , wall );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 20 && player.getY() == 40 );

        //cause a collision
        player.setY(31);

        //check it and fix it
        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the bottom edge of the wall", player.getX() == 20 && player.getY() == 32);

    }

    //test that the player gets pushed the right amount and right direction when colliding into the wall from the left
    @Test
    public void wallTest04(){
        init();

        //make a new player and wall
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(40, 0 , cellWidth, cellHeight);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player, wall);

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        //cause a collision
        player.setX(41 - cellWidth);

        //check it and fix it
        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the left edge of the wall", player.getX() == 40 - cellWidth && player.getY() == 0);

    }

    //test that the player gets pushed the right amount and right direction when colliding into the wall from the top
    @Test
    public void wallTest05(){
        init();

        //make a new player and wall
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0, 40 , cellWidth, cellHeight);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player, wall);

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        //cause a collision
        player.setY(41 - cellHeight);

        //check it and fix it
        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the top edge of the wall", player.getX() == 0 && player.getY() == 40 - cellHeight);

    }


    //Hazard tile tests

    //test that the player should not move
    @Test
    public void hazardTileTest01(){

        init();

        NinjaEntity player = new NinjaEntity(40,40,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(0,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , hazard );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 40 );

    }

    //test that the player should be moved off the hazard tile when pushed on to it from the right
    @Test
    public void hazardTileTest02(){

        init();

        NinjaEntity player = new NinjaEntity(40,20,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(0,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , hazard );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 20 );

        //cause a collision
        player.setX(31);

        //check it and fix it
        collision.checkCollisions(list);


        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 32 && player.getY() == 20);

    }



    //test that the player should be moved off the hazard tile when pushed on to it from the bottom
    @Test
    public void hazardTileTest03(){

        init();

        NinjaEntity player = new NinjaEntity(20,40,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(0,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , hazard );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 20 && player.getY() == 40 );

        //cause a collision
        player.setY(31);

        //check it and fix it
        collision.checkCollisions(list);


        assertTrue("Player should have been moved to the bottom edge of the hazard", player.getX() == 20 && player.getY() == 32);

    }

    //test that the player should be moved off the hazard tile when pushed on to it from the left
    @Test
    public void hazardTileTest04(){

        init();

        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(40,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , hazard );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        //cause a collision
        player.setX(41 - cellWidth);

        //check it and fix it
        collision.checkCollisions(list);


        assertTrue("Player should have been moved to the left edge of the hazard", player.getX() == 40 - cellWidth && player.getY() == 0);

    }


    //test that the player should be moved off the hazard tile when pushed on to it from the top
    @Test
    public void hazardTileTest05(){

        init();

        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(0,40,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , hazard );

        //use the collision method to check a collision between them
        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        //cause a collision
        player.setY(41 - cellHeight);

        //check it and fix it
        collision.checkCollisions(list);


        assertTrue("Player should have been moved to the top edge of the hazard", player.getX() == 0 && player.getY() == 40 - cellHeight);

    }

    //colliding bullets with walls
    @Test
    public void bulletWithWallTest(){

        init();

        //make a new wall, bullet and room
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0, 0 , cellWidth, cellHeight);
        Bullet bullet = new Bullet(50,50,10,10, EntityType.DEFAULT_BULLET);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );

        //add the bullet to an entity manager in the room like the game
        room.add(bullet , 0, 0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(bullet , wall );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Bullet should still be in the entity manager as it hasn't collided with the wall yet.", room.getEntityManager().getDynamicEntityList().contains(bullet));

        //push the bullet into the wall
        bullet.setX(10);
        bullet.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertFalse( "Bullet should no longer be in the entity manager as it has collided with the wall.",  room.getEntityManager().getDynamicEntityList().contains(bullet));

    }

    //colliding bullets with enemies

    @Test
    public void bulletWithMoverAI(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        Bullet bullet = new Bullet(50,50,10,10, EntityType.DEFAULT_BULLET);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        MoverAI enemy = new MoverAI(0, 0 , cellWidth, cellHeight, null , null , room);

        //add the bullet to an entity manager in the room like the game
        room.add(bullet , 0, 0);
        room.add(enemy,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(bullet , enemy);

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Bullet should still be in the entity manager as it hasn't collided with the enemy yet.", room.getEntityManager().getDynamicEntityList().contains(bullet));
        assertTrue( "Enemy should still be in the entity manager as it hasn't collided with the bullet yet.", room.getEntityManager().getDynamicEntityList().contains(enemy));


        //push the bullet into the wall
        bullet.setX(10);
        bullet.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertFalse( "Enemy should no longer be in the entity manager as it has collided with the bullet.",  room.getEntityManager().getDynamicEntityList().contains(enemy));
        assertFalse( "Bullet should no longer be in the entity manager as it has collided with the enemy.",  room.getEntityManager().getDynamicEntityList().contains(bullet));

    }

    @Test
    public void bulletWithGrappleAI(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        Bullet bullet = new Bullet(50,50,10,10, EntityType.DEFAULT_BULLET);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        GrappleAI enemy = new GrappleAI(0, 0 , cellWidth, cellHeight, null , null , room);

        //add the bullet to an entity manager in the room like the game
        room.add(bullet , 0, 0);
        room.add(enemy,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(bullet , enemy );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Bullet should still be in the entity manager as it hasn't collided with the enemy yet.", room.getEntityManager().getDynamicEntityList().contains(bullet));
        assertTrue( "Enemy should still be in the entity manager as it hasn't collided with the bullet yet.", room.getEntityManager().getDynamicEntityList().contains(enemy));


        //push the bullet into the wall
        bullet.setX(10);
        bullet.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertFalse( "Enemy should no longer be in the entity manager as it has collided with the bullet.",  room.getEntityManager().getDynamicEntityList().contains(enemy));
        assertFalse( "Bullet should no longer be in the entity manager as it has collided with the enemy.",  room.getEntityManager().getDynamicEntityList().contains(bullet));

    }

    //colliding swords with enemies

    @Test
    public void swordWithMoverAI(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        MeleeWeapon sword = new MeleeWeapon(50,50,10,10, EntityType.MELEE_WEAPON);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        MoverAI enemy = new MoverAI(0, 0 , cellWidth, cellHeight, null , null , room);

        //add the bullet to an entity manager in the room like the game
        room.add(sword , 0, 0);
        room.add(enemy,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(sword , enemy );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Enemy should still be in the entity manager as it hasn't collided with the Sword yet.", room.getEntityManager().getDynamicEntityList().contains(enemy));


        //push the bullet into the wall
        sword.setX(10);
        sword.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertFalse( "Enemy should no longer be in the entity manager as it has collided with the Sword.",  room.getEntityManager().getDynamicEntityList().contains(enemy));

    }

    @Test
    public void swordWithGrappleAI(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        MeleeWeapon sword = new MeleeWeapon(50,50,10,10, EntityType.MELEE_WEAPON);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        GrappleAI enemy = new GrappleAI(0, 0 , cellWidth, cellHeight, null , null , room);

        //add the bullet to an entity manager in the room like the game
        room.add(sword , 0, 0);
        room.add(enemy,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(sword , enemy );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Enemy should still be in the entity manager as it hasn't collided with the Sword yet.", room.getEntityManager().getDynamicEntityList().contains(enemy));


        //push the bullet into the wall
        sword.setX(10);
        sword.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertFalse( "Enemy should no longer be in the entity manager as it has collided with the Sword.",  room.getEntityManager().getDynamicEntityList().contains(enemy));

    }


    //colliding the player with enemies

    @Test
    public void playerWithMoverAI(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        MoverAI enemy = new MoverAI(0, 0 , cellWidth, cellHeight, null , null , room);


        //add the bullet to an entity manager in the room like the game
        room.add(player , 0, 0);
        room.add(enemy,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , enemy );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Player's health should be full.", player.healthComponent.getCurrentHealth() == 5);


        //push the player into the enemy
        player.setX(10);
        player.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        //should take off health as the entity is now 'hit'
        player.healthComponent.tryDecrementHealth();

        assertTrue( "Player should have lost some health from the enemy",  player.healthComponent.getCurrentHealth() == 4);

    }

    @Test
    public void playerWithGrappleAI(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        GrappleAI enemy = new GrappleAI(0, 0 , cellWidth, cellHeight, null , null , room);



        //add the bullet to an entity manager in the room like the game
        room.add(player , 0, 0);
        room.add(enemy,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , enemy );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Player's health should be full.", player.healthComponent.getCurrentHealth() == 5);

        //push the player into the enemy
        player.setX(10);
        player.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        //should take off health as the entity is now 'hit'
        player.healthComponent.tryDecrementHealth();

        assertTrue( "Player should have lost some health from the enemy",  player.healthComponent.getCurrentHealth() == 4);


    }


    //colliding player with items

    @Test
    public void playerWithSwordItem(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        Sword sword = new Sword(0, 0 , cellWidth, cellHeight, EntityType.SWORD);
        //so that the player can access it's inventory, needs and instance of HUD
        HeadsUpDisplay hud = new HeadsUpDisplay(0,0);


        room.add(player , 0, 0);
        room.add(sword,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , sword );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Player should not have any sword in their inventory", player.weaponComponent.getWeapons().size() == 1);


        //push the player into the item
        player.setX(10);
        player.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertTrue( "Player should now have the sword in their inventory",  player.weaponComponent.getWeapons().size() == 2 &&
                player.weaponComponent.getWeapons().get(1).getEntityType().equals(EntityType.SWORD) );

    }

    @Test
    public void playerWithShotgunItem(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        Shotgun shotgun = new Shotgun(0, 0 , cellWidth, cellHeight, EntityType.SHOTGUN);
        //so that the player can access it's inventory, needs and instance of HUD
        HeadsUpDisplay hud = new HeadsUpDisplay(0,0);


        room.add(player , 0, 0);
        room.add(shotgun,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , shotgun );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Player should not have any shotgun in their inventory", player.weaponComponent.getWeapons().size() == 1);


        //push the player into the item
        player.setX(10);
        player.setY(10);

        //check for the new collisionb  n
        collision.checkCollisions(list);

        assertTrue( "Player should now have the shotgun in their inventory",  player.weaponComponent.getWeapons().size() == 2 &&
                player.weaponComponent.getWeapons().get(1).getEntityType().equals(EntityType.SHOTGUN) );

    }

    @Test
    public void playerWithAssaultRifleItem(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        AssaultRifle rifle = new AssaultRifle(0, 0 , cellWidth, cellHeight, EntityType.ASSAULT_RIFLE);
        //so that the player can access it's inventory, needs and instance of HUD
        HeadsUpDisplay hud = new HeadsUpDisplay(0,0);


        room.add(player , 0, 0);
        room.add(rifle,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , rifle );

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        assertTrue( "Player should not have any rifle in their inventory", player.weaponComponent.getWeapons().size() == 1);


        //push the player into the item
        player.setX(10);
        player.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        assertTrue( "Player should now have the rifle in their inventory",  player.weaponComponent.getWeapons().size() == 2 &&
                player.weaponComponent.getWeapons().get(1).getEntityType().equals(EntityType.ASSAULT_RIFLE) );

    }

    @Test
    public void playerWithSpeedboostItem(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        SpeedBoost boost = new SpeedBoost(0, 0 , cellWidth, cellHeight, EntityType.SPEEDBOOST);



        room.add(player, 0, 0);
        room.add(boost,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , boost);

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);

        //set the player to move
        player.setXVelocity(5);
        player.setYVelocity(5);

        assertTrue( "Player should not have any modified speed", player.getXVelocity() == 5);
        assertTrue( "Player should not have any modified speed", player.getYVelocity() == 5);

        //push the player into the item
        player.setX(10);
        player.setY(10);

        //check for the new collision
        collision.checkCollisions(list);

        //need to tick the player so that it will recognize the speed boost
        player.boostSpeed();


        assertTrue( "Player should now be be faster", player.getXVelocity() == 10);
        assertTrue( "Player should now be be faster", player.getYVelocity() == 10);

    }

    @Test
    public void playerWithHeartItem(){

        init();

        //make a new enemy, bullet and room
        NinjaEntity player = new NinjaEntity(50,50,cellWidth,cellHeight);
        Room room = new Room(0,0,1000,1000, 1 , TYPE.SPAWN );
        Heart heart = new Heart(0, 0 , cellWidth, cellHeight, EntityType.HEART);



        room.add(player, 0, 0);
        room.add(heart,0,0);

        //create a list of those entities
        List<Entity> list = createListOfEntites(player , heart);

        //use the collision method to check a collision between them (should be none right now)
        this.collision = new WallCollision(room,player);
        collision.checkCollisions(list);



        assertTrue( "Player's health should be 5", player.healthComponent.getCurrentHealth() == 5);


        //remove a health from the player
        player.healthComponent.tryDecrementHealth();


        assertTrue( "Player's health should be 4", player.healthComponent.getCurrentHealth() == 4);

        //push the player into the item
        player.setX(10);
        player.setY(10);

        //check for the new collision
        collision.checkCollisions(list);


        assertTrue( "Player's health should be 5", player.healthComponent.getCurrentHealth() == 5);

    }




    //check picking up 3 items in a row?
    //colliding the player with chests?


    private void init(){
        new Resources();
    }

    private List<Entity> createListOfEntites(Entity e, Entity e2){
        List<Entity> list = new ArrayList<Entity>();
        list.add(e);
        list.add(e2);
        return list;
    }


}
