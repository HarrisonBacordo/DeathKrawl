package LevelGenerator.Rooms;

import Entity.Entity;
import Entity.EntityType;
import Entity.WallEntity;
import ResourceLoader.Resources;
import com.rits.cloning.Cloner;
import org.xguzm.pathfinding.grid.GridCell;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a room, the room may contain a series of entities.
 * Currently needs a image path to base the room design off.
 *
 * Created by Admin on 13/09/17.
 */
public class Room {
    protected List<Entity> entities;
    protected int x, y;
    protected int width, height;
    protected Entity grid[][];
    protected ArrayList<Entity> collisionGrid[][];
    protected Map<LOCATION, Door> doors;
    protected TYPE type;
    GridCell[][] cells;

    public Room(int x, int y, int width, int height, int scale, TYPE type){
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.doors = new HashMap<>();
        this.grid = new Entity[30][17];

        this.collisionGrid = new ArrayList[6][5];
        for (int i = 0; i < collisionGrid[0].length; i++) {
            for (int j = 0; j < collisionGrid.length; j++) {
                collisionGrid[j][i] = new ArrayList<>();
            }
        }
        this.type = type;

        create(scale);
    }

    public GridCell[][] getCells() {
        //GridCell[][] gridClone = new G
        Cloner cloner=new Cloner();
        GridCell[][] clone = cloner.deepClone(cells);
        return clone;
    }

    /**
     * Creates the room and populates it. Selects a special type of room layout based on the type
     * of the room. TODO: EXTEND TO ACCOUNT FOR ALL ROOM TYPES
     * @param scale, the scale of the level, DEBUG MODE ONLY
     */
    private void create(int scale) {
        RoomLoader loader = new RoomLoader(5);

        switch (type) {
            case SPAWN:
                loader.loadRoom(Resources.getImage("SpawnRoom"), this, scale);
                break;

            case ENEMY:
                loader.loadRandomRoom(this, scale);
                break;

        }

    }

    /**
     * Renders all entities in the room
     * @param g, graphics object to draw with
     */
    public void render(Graphics g){
//        Test floor
//        g.setColor(Color.blue);
//        g.fillRect(getX(), getY(), width, height);
        for(Entity e : entities) e.render(g);
        for(Door d : doors.values()) d.render(g);

//        for (int y = 0; y < grid[0].length; y++) {
//            for (int x = 0; x < grid.length; x++) {
//                if(grid[x][y] != null) grid[x][y].render(g);
//            }
//        }
    }

    /**
     * Updates all entities in the room
     */
    public void tick() {
        if(!entities.isEmpty()) for(Entity e : entities) e.tick();
    }

    /**
     * Adds an entity to the room, will not add if the entity already exists in the room
     * @param entity, the entity to add to the room
     * @return successful or failure
     */
    public boolean add(Entity entity, int x, int y){
        if(entity.getEntityType().equals(EntityType.PLAYER)){
            entities.add(entity);
        }
        else if(grid[x][y] == null) {
            //Create the collision grid optimisations, TODO ensure that array divisions are correct
            int xx = Math.round(x / 5);
            int yy = Math.round(y / 4);
            if(entity.isColliadable)
                collisionGrid[xx][yy].add(entity);

            grid[x][y] = entity;
            entities.add(entity);
            return true;
        }

        return false;
    }

    /**
     * Removes the given entity from the room
     * @param entity to remove
     * @return successful or failure
     */
    public boolean removeEntity(Entity entity){
        return entities.remove(entity);
    }

    /**
     * Adds a door to the room, will overwrite a door that already exists.
     * @param door, Door to be added
     * @param location, Location of the door in the room
     * @return successful or failure
     */
    public boolean addDoor(Door door, LOCATION location, int x, int y){
        grid[x][y] = door;
        return this.doors.put(location, door) != null;
    }

    /**
     * Removes the door at the given location in the room
     * @param location, of the door
     * @return success or failure
     */
    public boolean removeDoor(LOCATION location) {
        Door target = doors.remove(location);

        if(target != null) {

            if(location.equals(LOCATION.TOP) || location.equals(LOCATION.BOTTOM)){
                WallEntity w1 = (location.equals(LOCATION.TOP))
                        ? new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.TOP) :
                        new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.BOTTOM);

                entities.add(w1);
                //COLLISION GRID ADDITIONS
                int xx = Math.round(target.getCol() / 5);
                int yy = Math.round(target.getRow() / 4);
                collisionGrid[xx][yy].add(w1);
                grid[target.getCol()][target.getRow()] = w1;

                WallEntity w2 = (location.equals(LOCATION.TOP))
                        ? new WallEntity(target.getX() + 32, target.getY(), 32, 32, LOCATION.TOP) :
                        new WallEntity(target.getX() + 32, target.getY(), 32, 32, LOCATION.BOTTOM);

                entities.add(w2);
                //COLLISION GRID ADDITIONS
                int xxx = Math.round(target.getCol() / 5);
                int yyy = Math.round(target.getRow() / 4);
                collisionGrid[xxx][yyy].add(w2);

                grid[target.getCol() + 1][target.getRow()] = w2;
            }

            if(location.equals(LOCATION.LEFT) || location.equals(LOCATION.RIGHT)) {
                WallEntity w1 = (location.equals(LOCATION.LEFT))
                        ? new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.LEFT) :
                        new WallEntity(target.getX(), target.getY(), 32, 32, LOCATION.RIGHT);

                entities.add(w1);
                //COLLISION GRID ADDITIONS
                int xx = Math.round(target.getCol() / 5);
                int yy = Math.round(target.getRow() / 4);
                collisionGrid[xx][yy].add(w1);
                grid[target.getCol()][target.getRow()] = w1;

                WallEntity w2 = (location.equals(LOCATION.LEFT))
                        ? new WallEntity(target.getX(), target.getY() + 32, 32, 32, LOCATION.LEFT) :
                        new WallEntity(target.getX(), target.getY() + 32, 32, 32, LOCATION.RIGHT);

                entities.add(w2);
                //COLLISION GRID ADDITIONS
                int xxx = Math.round(target.getCol() / 5);
                int yyy = Math.round(target.getRow() / 4);
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
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Sets the list of entities to the one provided
     * @param entities, entities
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
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


}
