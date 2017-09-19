package LevelGenerator.Rooms;

import Entities.Entity;
import Entities.ID;
import Entities.WallEntity;
import ResourceLoader.Resources;

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
    protected Map<LOCATION, Door> doors;
    protected TYPE type;

    public Room(int x, int y, int width, int height, int scale, TYPE type){
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.doors = new HashMap<>();
        this.grid = new Entity[30][17];
        this.type = type;

        create(scale);
    }

    /**
     * Creates the room and populates it. Selects a special type of room layout based on the type
     * of the room. TODO: EXTEND TO ACCOUNT FOR ALL ROOM TYPES
     * @param scale, the scale of the level, DEBUG MODE ONLY
     */
    private void create(int scale) {
        RoomLoader loader = new RoomLoader(3);

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
        for(Entity e : entities) if(!e.getId().equals(ID.PLAYER)) e.render(g);
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
        if(entity.getId().equals(ID.PLAYER)){
            entities.add(entity);
        }

        if(grid[x][y] == null) {
            grid[x][y] = entity;
            entities.add(entity);
            return true;
        }

//        if(!entities.contains(entity)) return entities.add(entity);
        return false;
    }

    /**
     * Adds a door to the room, will overwrite a door that already exists.
     * @param door, Door to be added
     * @param location, Location of the door in the room
     * @return successful or failure
     */
    public boolean addDoor(Door door, LOCATION location, int x, int y){
        grid[x][y] = door;
//        entities.add(door);
        if(this.doors.put(location, door) != null) return true;
        return false;
    }

    /**
     * Removes the door at the given location in the room
     * @param location, of the door
     * @return success or failure
     */
    public boolean removeDoor(LOCATION location) {
        Door target = doors.remove(location);
        if(target != null) {
            entities.add(new WallEntity(target.getX(), target.getY(), target.getWidth(), target.getHeight()));
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


}
