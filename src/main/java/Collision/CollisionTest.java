package Collision;

import Entity.*;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;
import com.badlogic.gdx.Game;
import org.junit.*;
import org.junit.Test;
import ResourceLoader.*;

import java.awt.*;
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

        NinjaEntity player = new NinjaEntity(40,40,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0,0,cellWidth,cellHeight);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , wall );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 40 );

    }


    //test that the player gets pushed the right amount and right direction when colliding into the wall from the right
    @Test
    public void wallTest02(){
        init();

        NinjaEntity player = new NinjaEntity(40,20,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0,0,cellWidth,cellHeight);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , wall );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 20 );

        player.setX(31);

        collision.checkCollisions(list);

        System.out.println("This should be 32 " + player.getX());

        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 32 && player.getY() == 20);

    }

    //test that the player gets pushed the right amount and right direction when colliding into the wall from the bottom
    @Test
    public void wallTest03(){
        init();

        NinjaEntity player = new NinjaEntity(20,40,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(0,0,cellWidth,cellHeight);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , wall );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 20 && player.getY() == 40 );

        player.setY(31);

        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the bottom edge of the wall", player.getX() == 20 && player.getY() == 32);

    }

    //test that the player gets pushed the right amount and right direction when colliding into the wall from the left
    @Test
    public void wallTest04(){
        init();

        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(40, 0 , cellWidth, cellHeight);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player, wall);

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        player.setX(41 - cellWidth);

        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the left edge of the wall", player.getX() == 40 - cellWidth && player.getY() == 0);

    }

    //test that the player gets pushed the right amount and right direction when colliding into the wall from the top
    @Test
    public void wallTest05(){
        init();

        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        WallEntity wall = new WallEntity(40, 0 , cellWidth, cellHeight);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player, wall);

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        player.setX(41 - cellWidth);

        collision.checkCollisions(list);

        assertTrue("Player should have been moved to the left edge of the wall", player.getX() == 40 - cellWidth && player.getY() == 0);

    }


    //Hazard tile tests

    //test that the player should not move
    @Test
    public void hazardTileTest01(){

        init();

        NinjaEntity player = new NinjaEntity(40,40,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(0,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , hazard );

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

        List<Entity> list = createListOfEntites(player , hazard );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 20 );

        player.setX(31);

        collision.checkCollisions(list);

        System.out.println("This should be 32 " + player.getX());

        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 32 && player.getY() == 20);

    }



    //test that the player should be moved off the hazard tile when pushed on to it from the bottom
    @Test
    public void hazardTileTest03(){

        init();

        NinjaEntity player = new NinjaEntity(20,40,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(0,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , hazard );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 40 && player.getY() == 20 );

        player.setY(31);

        collision.checkCollisions(list);

        System.out.println("This should be 32 " + player.getX());

        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 20 && player.getY() == 32);

    }

    //test that the player should be moved off the hazard tile when pushed on to it from the left
    @Test
    public void hazardTileTest04(){

        init();

        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(40,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , hazard );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        player.setX(41 - cellWidth);

        collision.checkCollisions(list);

        System.out.println("This should be 32 " + player.getX());

        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 40 - cellWidth && player.getY() == 0);

    }


    //test that the player should be moved off the hazard tile when pushed on to it from the top
    @Test
    public void hazardTileTest05(){

        init();

        NinjaEntity player = new NinjaEntity(0,0,cellWidth,cellHeight);
        SeaFloorEntity hazard = new SeaFloorEntity(40,0,cellWidth,cellHeight, EntityType.FLOOR_HAZARD);
        this.collision = new WallCollision(player);

        List<Entity> list = createListOfEntites(player , hazard );

        collision.checkCollisions(list);

        assertTrue( "Player should not have moved as no collision occured.", player.getX() == 0 && player.getY() == 0 );

        player.setX(41 - cellWidth);

        collision.checkCollisions(list);

        System.out.println("This should be 32 " + player.getX());

        assertTrue("Player should have been moved to the right edge of the wall", player.getX() == 40 - cellWidth && player.getY() == 0);

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
