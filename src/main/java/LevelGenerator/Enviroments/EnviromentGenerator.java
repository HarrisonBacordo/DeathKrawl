package LevelGenerator.Enviroments;

import Entities.Entity;
import Entities.ID;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.RoomLoader;

import java.util.List;

/**
 * TODO CHANGE ALL IMAGE LOADS TO BE LOADED ONCE FROM A STATIC ASSET MANAGER CLASS
 * Goes through the level and changes the assets.
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
                List<Entity> entities = currentRoom.getEntities();

                for (int y = 16; y > 0; y--) {
                    for (int x = 0; x < 30; x++) {
                        if(grid[x][y] == null) continue;

                        Entity e = grid[x][y];


                        //Walls
                        if(e.getId().equals(ID.WALL)) {
                            //Walls
//                            e.setImage(loader.loadImage("/res/Enviroment/wallTile.png"));
                            if(x == 0) e.setImage(loader.loadImage("/Enviroment/wallTileLeft.png"));

                            if(x == 29) e.setImage(loader.loadImage("/Enviroment/wallTileRight.png"));

                            if(y == 16) e.setImage(loader.loadImage("/Enviroment/wallTileBottom.png"));

                        }else if(e.getId().equals(ID.FLOOR)){
                            //Shadows
                            if(x == 1) e.setImage(loader.loadImage("/Enviroment/floorShadowLeft.png"));

                            if(x == 28) e.setImage(loader.loadImage("/Enviroment/floorShadowRight.png"));

                            if(y == 1) e.setImage(loader.loadImage("/Enviroment/floorShadow.png"));

                        }








                    }
                }

//                for (Entity e : entities) {
//                    if(e.getId().equals(ID.WALL)){
//
//                        e.setImage(loader.loadImage("/res/Enviroment/wallTile.png"));
//                    }
//                }

            }
        }

    }



}
