package LevelGenerator.Rooms;

import Entity.Entity;
import Entity.EntityType;
import Entity.WallEntity;
import Entity.EntityManager;
import Item.Sword;
import ResourceLoader.Resources;
import Component.WeaponComponent;
import com.rits.cloning.Cloner;
import org.xguzm.pathfinding.grid.GridCell;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Represents a room, the room may contain a series of entities.
 * Currently needs a image path to base the room design off.
 *
 * Created by Krishna Kapadia 300358741 on 13/09/17.
 */
public class Room {
    protected EntityManager entities;
    protected int x, y, xDivider, yDivider;
    protected int width, height, col, row;
    protected Entity grid[][];
    protected ArrayList<Entity> collisionGrid[][];
    protected TYPE type;

    public Room(int x, int y, int width, int height, int scale, TYPE type){
        this.collisionGrid = new ArrayList[6][5];
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
        this.type = type;
        this.entities = new EntityManager();
        this.col =  x / width;
        this.row =  y / height;
        //GRID SIZE CHANGES AS BOSS ROOM x2
//        if(type.equals(TYPE.BOSS)) this.grid = new Entity[60][34];
//        else
        this.grid = new Entity[30][17];
        this.type = type;


        //Collision grid size changes on room size as boss room is twice as large as a normal room
        xDivider = 5; //(this.type.equals(TYPE.BOSS)) ? 10 : 5;
        yDivider = 4; //(this.type.equals(TYPE.BOSS)) ? 8 : 4;

        this.collisionGrid = new ArrayList[6][5];
        for (int i = 0; i < collisionGrid[0].length; i++) {
            for (int j = 0; j < collisionGrid.length; j++) {
                collisionGrid[j][i] = new ArrayList<>();
            }
        }

        create(scale);
    }


    /**
     * Creates the room and populates it. Selects a special type of room layout based on the type
     * of the room.
     * @param scale, the scale of the level, DEBUG MODE ONLY
     */
    private void create(int scale) {
        RoomLoader loader = new RoomLoader(6);

        switch (type) {
            case SPAWN:
                loader.loadRoom(Resources.getImage("SpawnRoom"), this, scale);
                break;

            case ENEMY:
                loader.loadRandomRoom(this, scale);
                break;

            case BOSS:
                loader.loadBossRoom(this, scale);
                break;
        }

    }

    /**
     * Renders all entities in the room
     * @param g, graphics object to draw with
     */
    public void render(Graphics g){
        entities.renderAllEntities(g);
    }

    /**
     * Updates all entities in the room
     */
    public void tick() {
        entities.tickAllEntities();
    }

    /**
     * Adds an entity to the room, will not add if the entity already exists in the room
     * @param entity, the entity to add to the room
     * @return successful or failure
     */
    public boolean add(Entity entity, int x, int y){

        if(entity.getEntityType().equals(EntityType.PLAYER) || entity.getEntityType().equals(EntityType.ENEMY)){
            return entities.addEntity(entity);

        }

        if(entity.getEntityType().equals(EntityType.SWORD) || entity.getEntityType().equals(EntityType.SHOTGUN) || entity.getEntityType().equals(EntityType.ASSAULT_RIFLE) || entity.getEntityType().equals(EntityType.SHIELD)
                || entity.getEntityType().equals(EntityType.SPEEDBOOST) || entity.getEntityType().equals(EntityType.HEART)){
            return  entities.addEntity(entity);
        }

        else if(grid[x][y] == null) {
            //Create the collision grid optimisations, takes into account the scale of the room

            int xx = Math.round(x / xDivider); // BOSS ROOM 10, NORMALLY 5
            int yy = Math.round(y / yDivider); // BOSS ROOM 8, NORMALLY 4

            if(entity.getEntityType().equals(EntityType.WALL)) collisionGrid[xx][yy].add(entity);

            grid[x][y] = entity;
            entities.addEntity(entity);
            return true;
        }

        return false;
    }

    /**
     * Removes the given entity from the room
     * @param entity to remove
     * @return successful or failure
     */
    public boolean removeEntity(Entity entity){ return entities.removeEntity(entity); }

    /**
     * Adds a door to the room, will overwrite a door that already exists.
     * @param door, Door to be added
     * @param location, Location of the door in the room
     * @return successful or failure
     */
    public boolean addDoor(Door door, LOCATION location, int x, int y){
        //Calculates collision grid location and adds the door
        int xx = Math.round(x / xDivider);
        int yy = Math.round(y / yDivider);
        collisionGrid[xx][yy].add(door);

        grid[x][y] = door;
        return entities.addEntity(door);
    }

    /**
     * Removes the door at the given location in the room
     * @param location, of the door
     * @return success or failure
     */
    public boolean removeDoor(LOCATION location) {
        Door target = entities.removeDoor(location);

        if(target != null) {

            if(location.equals(LOCATION.TOP) || location.equals(LOCATION.BOTTOM)){
                WallEntity w1 = (location.equals(LOCATION.TOP))
                        ? new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.TOP) :
                        new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.BOTTOM);

                entities.addEntity(w1);

                //COLLISION GRID ADDITIONS
                int xx = Math.round(target.getCol() / xDivider);
                int yy = Math.round(target.getRow() / yDivider);
                collisionGrid[xx][yy].add(w1);
                grid[target.getCol()][target.getRow()] = w1;

                WallEntity w2 = (location.equals(LOCATION.TOP))
                        ? new WallEntity(target.getX() + 32, target.getY(), 32, 32, LOCATION.TOP) :
                        new WallEntity(target.getX() + 32, target.getY(), 32, 32, LOCATION.BOTTOM);

                entities.addEntity(w2);

                //COLLISION GRID ADDITIONS
                int xxx = Math.round(target.getCol() / xDivider);
                int yyy = Math.round(target.getRow() / yDivider);
                collisionGrid[xxx][yyy].add(w2);

                grid[target.getCol() + 1][target.getRow()] = w2;
            }

            if(location.equals(LOCATION.LEFT) || location.equals(LOCATION.RIGHT)) {
                WallEntity w1 = (location.equals(LOCATION.LEFT))
                        ? new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.LEFT) :
                        new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.RIGHT);

                entities.addEntity(w1);
                //COLLISION GRID ADDITIONS
                int xx = Math.round(target.getCol() / xDivider);
                int yy = Math.round(target.getRow() / yDivider);
                collisionGrid[xx][yy].add(w1);
                grid[target.getCol()][target.getRow()] = w1;

                WallEntity w2 = (location.equals(LOCATION.LEFT))
                        ? new WallEntity(target.getX(), target.getY() + 32, 32, 32, LOCATION.LEFT) :
                        new WallEntity(target.getX(), target.getY() + 32, 32, 32, LOCATION.RIGHT);

                entities.addEntity(w2);
                //COLLISION GRID ADDITIONS
                int xxx = Math.round(target.getCol() / xDivider);
                int yyy = Math.round(target.getRow() / yDivider);
                collisionGrid[xxx][yyy].add(w2);
                grid[target.getCol()][target.getRow()] = w2;
            }

            return true;
        }
        return false;
    }


    //GETTERS AND SETTERS

    /**
     * Returns the list of entities inside the room
     * @return List of entities
     */
    public EntityManager getEntityManager() {
        return entities;
    }

    /**
     * Sets the list of entities to the one provided
     * @param entityManager, entities
     */
    public void setEntities(EntityManager entityManager) {
        this.entities = entities;
    }

    /**
     * Returns the map of doors to their locations
     * @return Map of Location -> Door;
     */
    public Map<LOCATION, Door> getDoors() {
        HashMap<LOCATION, Door> doors = new HashMap<>();

        for (Entity entity : entities.getEnemiesWithType(EntityType.DOOR)) {
            Door d = (Door) entity;
            doors.put(d.getLocation(), d);
        }

        return doors;
    }

    /**
     * Returns the x position of the room
     * @return x position
     */
    public int getX() {
        return x;
    }

    /**
     * Set x location
     * @param x, new Location
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y position of the room
     * @return, y position
     */
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of the room
     * @return width of room
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the room
     * @param width, new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of the room
     * @return height of room
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the room
     * @param height, new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the grid of entities inside the room
     * @return grid of entities.
     */
    public Entity[][] getGrid() {
        return grid;
    }

    /**
     * Sets the grid of entities to a new one
     * @param grid, new grid of entities.
     */
    public void setGrid(Entity[][] grid) {
        this.grid = grid;
    }

    /**
     * Returns the type of the room
     * @return TYPE, type of room
     */
    public TYPE getType() {
        return type;
    }

    /**
     * Sets the type of the room
     * @param type, type of room
     */
    public void setType(TYPE type) {
        this.type = type;
    }

    /**
     * Returns the collision grid of the room
     * @return List<Entity>[][]
     */
    public ArrayList<Entity>[][] getCollisionGrid() {
        return this.collisionGrid;
    }

    /**
     * Returns the col position of this room
     * @return col
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Returns the row position of this room
     * @return row
     */
    public int getRow() {
        return this.row;
    }
}
