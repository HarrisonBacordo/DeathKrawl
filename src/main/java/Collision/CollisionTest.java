package Collision;

import Entity.*;
import org.junit.Test;
import ResourceLoader.*;

import java.util.ArrayList;
import java.util.List;

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


    //colliding bullets with enemies

    //colliding swords with enemies

    //colliding the player with enemies

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
