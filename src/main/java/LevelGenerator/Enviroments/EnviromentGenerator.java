package LevelGenerator.Enviroments;

import Entity.Entity;
import Entity.EntityType;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.RoomLoader;
import ResourceLoader.Resources;

import java.util.List;

/**
 * Goes through the level and changes the assets. Used for changing the look
 *
 * Created by krishna kapadia 300358741 on 17/09/2017.
 */
public class EnviromentGenerator {

    /**
     * Go through each room, iterating over its stored entities changing their visual style.
     * @param level
     */
    public EnviromentGenerator(Level level) {
        Room[][] rooms = level.getRooms();
        RoomLoader loader = new RoomLoader(3);

        for (int yy = 0; yy < rooms[0].length; yy++) {
            for (int xx = 0; xx < rooms.length; xx++) {
                if(rooms[xx][yy] == null) continue;

                Room currentRoom = rooms[xx][yy];
                Entity[][] grid = currentRoom.getGrid();
                List<Entity> entities = currentRoom.getEntityManager().getEntities();

                for (int y = 16; y > 0; y--) {
                    for (int x = 0; x < 30; x++) {
                        if(grid[x][y] == null) continue;
                        Entity e = grid[x][y];
                        checkWalls(e, grid, x, y);
                        checkFloor(e, grid, x, y);
                        checkHazards(e, grid, x, y);
                    }
                }

            }
        }

    }

    /**
     * Completes the wall checks and sets the appropriate images of the wall entities
     * @param x, row location
     * @param y, col location
     *
     */
    private void checkWalls(Entity e, Entity[][] grid, int x, int y) {
        //Walls
        if(e.getEntityType().equals(EntityType.WALL)) {

            //Walls
            if(x == 0) e.setImage(Resources.getImage("WTL"));
            if(x == 29) e.setImage(Resources.getImage("WTR"));
            if(y == 16) e.setImage(Resources.getImage("WTB"));

            //Sea
            if(y > 0) {
                if (grid[x][y - 1] != null) {
                    //BOTTOM
                    if (grid[x][y - 1].getEntityType().equals(EntityType.FLOOR_HAZARD)) {
                        grid[x][y - 1].setImage(Resources.getImage("SEAT"));
                    }
                }
            }


            if(x < 28){
                if(grid[x + 1][y] != null) {
                    //LEFT
                    if (grid[x + 1][y].getEntityType().equals(EntityType.FLOOR_HAZARD)) {
                        grid[x + 1][y].setImage(Resources.getImage("SEAL"));
                    }
                }
            }

        }
    }

    /**
     * Completes the wall checks and sets the appropriate images of the floor entities
     * @param e, Entity to check
     * @param grid, grid of entities in the room
     * @param x, row location
     * @param y, col location
     */
    private void checkFloor(Entity e, Entity[][] grid, int x, int y) {
        if(e.getEntityType().equals(EntityType.FLOOR)){
            //Shadows
            if(x == 1) e.setImage(Resources.getImage("FSL"));
            if(x == 28) e.setImage(Resources.getImage("FSR"));
            if(y == 1) e.setImage(Resources.getImage("FS"));

        }
    }

    /**
     * Completes the hazard checks and sets the appropriate image of the hazard entity
     * @param x, row location
     * @param y, col location
     */
    private void checkHazards(Entity e, Entity[][] grid, int x, int y) {
        if(e.getEntityType().equals(EntityType.FLOOR_HAZARD)){
            if(y > 0) {
                if(grid[x][y - 1] != null) {
                    //TOP
                    if (grid[x][y - 1].getEntityType().equals(EntityType.WALL)) {
                        grid[x][y].setImage(Resources.getImage("SEAT"));
                    }
                }
            }

            if(x < 29){
                if(grid[x + 1][y] != null) {
                    //RIGHT
                    if (grid[x + 1][y].getEntityType().equals(EntityType.WALL)) {
                        grid[x][y].setImage(Resources.getImage("SEAR"));
                    }
                }
            }


        }
    }

}
