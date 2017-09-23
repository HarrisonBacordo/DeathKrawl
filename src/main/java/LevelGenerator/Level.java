package LevelGenerator;

import Collision.Collision;
import Entity.Entity;
import Entity.EntityType;
import LevelGenerator.Enviroments.EnviromentGenerator;
import LevelGenerator.Rooms.LOCATION;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;
import java.awt.*;
import java.util.Random;

/**
 * Represents a level/floor. The level is comprised of n rooms that
 * are randomly placed in the map of rooms. Each level consists of:
 *      - 1 player initial spawn room
 *      - 1 boss room
 *      - Currently the rest will be enemy rooms
 *
 * TODO: OPTIMIZATION, Shrink the room grid to hug its contents, currently lots of wasted space as it is a N x N grid
 * TODO: Randomize the rooms to give each a different feel, both in terms of structure and assets
 *
 * Created by Krishna Kapadia 300358741 on 13/09/17.
 */
public class Level {
    private Room[][] rooms;
    private int numOfRooms, roomWidth, roomHeight, scale;
    private Room currentRoom;
    private Entity player;
    private Collision collision;

    /**
     * Creates a level of n number of rooms and with each room being of a certain width and height
     * @param numOfRooms, number of rooms that the level must have
     * @param roomWidth, width of the room
     * @param roomHeight, height of the room
     */
    public Level(Integer numOfRooms, int roomWidth, int roomHeight){
        rooms = new Room[numOfRooms][numOfRooms];
        this.numOfRooms = numOfRooms;
        this.roomWidth  = roomWidth;
        this.roomHeight = roomHeight;
        this.scale = 1;
        this.generate();
        this.collision = new Collision(this.getCurrentRoom(), player);
    }

    /**
     * Creates a level of n number of rooms and with each room being of a certain width and height
     * @param numOfRooms, number of rooms that the level must have
     * @param roomWidth, width of the room
     * @param roomHeight, height of the room
     * @param scale, Scales down the level by that amount
     */
    public Level(Integer numOfRooms, int roomWidth, int roomHeight, int scale){
        rooms = new Room[numOfRooms][numOfRooms];
        this.numOfRooms = numOfRooms;

        //Temp scaling
        this.scale      = scale;
        this.roomWidth  = roomWidth / scale;
        this.roomHeight = roomHeight / scale;

        this.generate();
    }

    /**
     * Generates a level. Starts from a seed node and places rooms until the max number has been reached.
     */
    public void generate(){
        Random random = new Random();

        //First randomly place the spawn room, cannot be placed on an edge as spawn room will have 4 doors.
        int row = random.nextInt(numOfRooms - 2) + 1;
        int col = random.nextInt(numOfRooms - 2) + 1;
        currentRoom = rooms[col][row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.SPAWN);

        //Gets the player
        for(Entity e : currentRoom.getEntities()){
            if(e.getEntityType().equals(EntityType.PLAYER)) {
                this.player = e;
                break;
            }
        }

        placeRooms(col, row);

        createDoors();

        //Adds the environment
        EnviromentGenerator eg = new EnviromentGenerator(this);

        //DEBUG CHECK
        printToConsole();
    }

    /**
     * Places the rooms in the level. Ensures that we don't place a room on a place in where a room already exists.
     */
    private void placeRooms(int currentCol, int currentRow) {
        //Add other random rooms
        Random random = new Random();
        int col = currentCol;
        int row = currentRow;
        int placed = 0;

        System.out.println(numOfRooms - 2);

        while(placed < numOfRooms - 3){
            int dir = random.nextInt(5) + 1;

            switch (dir){
                case 1: //Up
                    if(row > 0) {
                        if(rooms[col][row - 1] == null) {
                            rooms[col][--row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.ENEMY);
                            placed++;
                        }
                    }
                    break;

                case 2: //Down
                    if(row < numOfRooms - 2) {
                        if(rooms[col][row + 1] == null) {
                            rooms[col][++row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.ENEMY);
                            placed++;
                        }
                    }
                    break;

                case 3: //Left
                    if(col > 0) {
                        if(rooms[col - 1][row] == null) {
                            rooms[--col][row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.ENEMY);
                            placed++;
                        }
                    }
                    break;

                case 4: //Right
                    if(col < numOfRooms - 2) {
                        if(rooms[col + 1][row] == null) {
                            rooms[++col][row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.ENEMY);
                            placed++;
                        }
                    }
                    break;
            }
        }

    }

    /**
     * Goes through all the rooms and enables the appropriate doors, replaces un-needed doors with walls.
     * if the current room has neighbours then it must have a door in that direction
     */
    private void createDoors() {
        for(int y = 0; y < rooms[0].length; y++) {
            for(int x = 0; x < rooms.length; x++) {
                Room current = rooms[x][y];

                //if the room exists then check its neighbours
                if(current != null){

                    //TOP
                    if(y == 0 || y > 0 && rooms[x][y - 1] == null) current.removeDoor(LOCATION.TOP);

                    //BOTTOM
                    if(y== (rooms[0].length - 2) || y < (rooms[0].length - 2) && rooms[x][y + 1] == null) current.removeDoor(LOCATION.BOTTOM);

                    //LEFT
                    if(x == 0 || x > 0 && rooms[x - 1][y] == null) current.removeDoor(LOCATION.LEFT);

                    //RIGHT
                    if(x == (rooms[0].length - 2) || x < (rooms[0].length - 2) && rooms[x + 1][y] == null) current.removeDoor(LOCATION.RIGHT);

                }
            }
        }
    }

    /**
     * Renders all visited rooms,
     * TODO make it render all visited rooms
     * @param g, graphics object to draw with
     */
    public void render(Graphics g) {
        for (int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if(rooms[x][y] != null) rooms[x][y].render(g);
                //currentRoom.render(g);
            }
        }

        //Should render player last, therefore on-top of everything
        player.render(g);
    }

    /**
     * Updates everything inside the current room at each state,
     * also handles the panning currently and add and removes the player into and out of the current room
     * TODO remove from here and add to door collision method
     */
    public void tick() {
        int newRoomCol =  currentRoom.getX() / roomWidth;
        int newRoomRow =  currentRoom.getY() / roomHeight;

        currentRoom.removeEntity(player);

        if(player.getX() < currentRoom.getX()){
            currentRoom = rooms[newRoomCol - 1][newRoomRow];
            this.collision = new Collision(currentRoom,player);
        }
        else if(player.getX() > currentRoom.getX() + roomWidth){
            currentRoom = rooms[newRoomCol + 1][newRoomRow];
            this.collision = new Collision(currentRoom, player);
        }
        else if(player.getY() < currentRoom.getY()){
            currentRoom = rooms[newRoomCol][newRoomRow - 1];
            this.collision = new Collision(currentRoom, player );
        }
        else if(player.getY() > currentRoom.getY() + roomHeight) {
            currentRoom = rooms[newRoomCol][newRoomRow + 1];
            this.collision = new Collision(currentRoom, player);
        }

        currentRoom.add(player, player.getX(), player.getY());

//        player.tick();
        currentRoom.tick();
        collision.gridCheck();
    }

    /**
     * DEBUG METHOD:
     * Prints the level to the console, X signifies a room
     */
    public void printToConsole(){
        //Prints the map to the console
        for(int y = 0; y < rooms[0].length; y++){
            for (int x = 0; x < rooms.length; x++) {
                System.out.print("|");
                if(rooms[x][y] != null) System.out.print("X");
                else System.out.print(" ");
            }
            System.out.print("|\n");
        }
    }

    /**
     * Returns the current room of the player
     * @return currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Returns the grid rooms
     * @return rooms
     */
    public Room[][] getRooms() { return rooms; }

}
