package LevelGenerator;

import Collision.WallCollision;
import Component.ComponentType;
import Component.ShootComponent;
import Entity.Entity;

import Collision.CollisionQuadTree;
import Entity.EntityType;
import LevelGenerator.Enviroments.EnviromentGenerator;
import LevelGenerator.Rooms.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.List;

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
public class Level implements Serializable{
    private Room[][] rooms;
    private int numOfRooms, roomWidth, roomHeight, scale;
    private Room currentRoom, bossRoom;
    public Entity player;
    private static final long serialVersionUID = 1L;
    public WallCollision collision;
    protected PointLight light;
    private CollisionQuadTree tree;

    /**
     * Creates a level of n number of rooms and with each room being of a certain width and height
     * @param numOfRooms, number of rooms that the level must have
     * @param roomWidth, width of the room
     * @param roomHeight, height of the room
     */
    public Level(int numOfRooms, int roomWidth, int roomHeight) throws IllegalArgumentException{
        if(numOfRooms < 1 || roomWidth < 1 || roomHeight < 1) {
            throw new IllegalArgumentException("Parameters invalid");
        }else {
            this.numOfRooms = numOfRooms;
            this.roomWidth = roomWidth;
            this.roomHeight = roomHeight;
            this.scale = 1;
            this.rooms = new Room[numOfRooms][numOfRooms];
            this.generate();
            this.collision = new WallCollision(this.getCurrentRoom(), player);
            this.light = new PointLight(bossRoom.getX(), bossRoom.getY(), roomWidth, roomHeight);
            this.tree = new CollisionQuadTree(0, new Rectangle(0,0,960,565));
        }
    }

    /**
     * Creates a level of n number of rooms and with each room being of a certain width and height
     * @param numOfRooms, number of rooms that the level must have
     * @param roomWidth, width of the room
     * @param roomHeight, height of the room
     * @param scale, Scales down the level by that amount
     */
    public Level(Integer numOfRooms, int roomWidth, int roomHeight, int scale) throws IllegalArgumentException{
        if(numOfRooms < 1 || roomWidth < 1 || roomHeight < 1 || scale < 1) {
            throw new IllegalArgumentException("Parameters invalid");
        }else {
            this.numOfRooms = Objects.requireNonNull(numOfRooms);
            this.roomWidth = Objects.requireNonNull(roomWidth);
            this.roomHeight = Objects.requireNonNull(roomHeight);
            this.scale = Objects.requireNonNull(scale);

            //Temp scaling
            this.scale = scale;
            this.roomWidth = roomWidth / scale;
            this.roomHeight = roomHeight / scale;
            this.rooms = new Room[numOfRooms][numOfRooms];

            this.generate();
            this.light = new PointLight(player.getX(), player.getY(), roomWidth, roomHeight);
            this.collision = new WallCollision(this.getCurrentRoom(), player);
            this.tree = new CollisionQuadTree(0, new Rectangle(0,0,960,565));
        }
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
        this.player = currentRoom.getEntityManager().getPlayer();

        //Place all other rooms and doors
        placeRooms(col, row);
        createDoors();

        //Alters the base environment
        EnviromentGenerator eg = new EnviromentGenerator(this);

        //DEBUG CHECK
        printToConsole();
    }

    /**
     * Places the rooms in the level. Ensures that we don't place a room on a place in where a room already exists.
     * Takes into account a timeout for the special case where the rooms are placed infinitely. If the elapsed time
     * is has been longer than a pre-determined level, then no more rooms will need to be placed and all level conditions
     * will remain satisfied.
     *
     * @param currentCol, column location of the seed room
     * @param currentRow , row location of the seed room
     */
    private void placeRooms(int currentCol, int currentRow) {
        //Add other random rooms
        Random random = new Random();
        int col = currentCol;
        int row = currentRow;
        int placed = 0;

        //Handles timeout to ensure breakage of infinite looping
        long prevTime = System.currentTimeMillis();
        long currentTime = prevTime;
        long timeout = 100;

        while(placed < numOfRooms - 2 && (currentTime - prevTime) < timeout){
            int dir = random.nextInt(4) + 1;

            switch (dir){
                case 1: //Up
                    if(row > 1) {
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
                    if(col > 1) {
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

            currentTime = System.currentTimeMillis();
        }

        //Placing Boss Room
        if(col < numOfRooms - 2 && rooms[col + 1][row] == null) bossRoom = rooms[++col][row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.BOSS);
        else if(col > 0 && rooms[col - 1][row] == null) bossRoom = rooms[--col][row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.BOSS);
        else if(row < numOfRooms - 2 && rooms[col][row + 1] == null) bossRoom = rooms[col][++row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.BOSS);
        else if(row > 0 && rooms[col][row - 1] == null) bossRoom = rooms[col][--row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.BOSS);
        else bossRoom = rooms[col][row] = new Room(col * roomWidth, row * roomHeight, roomWidth, roomHeight, scale, TYPE.BOSS);
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
                    if((y > 0) && rooms[x][y - 1] == null) current.removeDoor(LOCATION.TOP);

                    //BOTTOM
                    if((y == (rooms[0].length - 2) || y < (rooms[0].length - 2)) && rooms[x][y + 1] == null) current.removeDoor(LOCATION.BOTTOM);

                    //LEFT
                    if((x > 0) && rooms[x - 1][y] == null) current.removeDoor(LOCATION.LEFT);

                    //RIGHT
                    if((x == (rooms[0].length - 2) || x < (rooms[0].length - 2)) && rooms[x + 1][y] == null) current.removeDoor(LOCATION.RIGHT);

                }
            }
        }
    }

    /**
     * Renders the current room as well as those that are directly adjacent to it
     * @param g, graphics object to draw with
     */
    public void render(Graphics g) {
        currentRoom.render(g);
        renderAdjacentRooms(g);

        //Should render player last, therefore on-top of everything
        player.render(g);

        //Only render the light if the player is in the boss room
        if(currentRoom.getType().equals(TYPE.BOSS)) light.render(g);
    }

    /**
     * Finds the adjacent rooms and renders them.
     * @param g, Graphic object to draw with
     */
    private void renderAdjacentRooms(Graphics g) {
        int currentCol =  currentRoom.getCol();
        int currentRow =  currentRoom.getRow();

        //Left neighbour
        if(currentCol > 0){
            if(rooms[currentCol - 1][currentRow] != null) rooms[currentCol - 1][currentRow].render(g);
        }
        //Right neighbour
        if(currentCol < rooms[0].length - 1){
            if(rooms[currentCol + 1][currentRow] != null) rooms[currentCol + 1][currentRow].render(g);
        }
        //Top neighbour
        if(currentRow > 0){
            if(rooms[currentCol][currentRow - 1] != null) rooms[currentCol][currentRow - 1].render(g);
        }
        //Bottom neighbour
        if(currentRow < rooms.length - 1){
            if(rooms[currentCol][currentRow + 1] != null) rooms[currentCol][currentRow + 1].render(g);
        }

    }

    /**
     * Updates everything inside the current room at each state,
     * also handles the panning currently and add and removes the player into and out of the current room
     */
    public void tick() {

//        for(Door d : currentRoom.getDoors().values()) d.setState(true);
        //Checks for collisions with doors
//        Door collided = collision.checkCollisionsWithDoors();

//        if(collided != null){
//            if(collided.isOpen()){
                calculateCurrentRoom();
//                for(Door d : currentRoom.getDoors().values()) d.setState(true);
//            }
//        }


//        player.tick();
        currentRoom.tick();
        //old collision
        //collision.gridCheck();

        tree.clear();

        List<Entity> entities = currentRoom.getEntityManager().getEntities();
        ArrayList<Entity> collidableEntites = new ArrayList<>();


        //add all the entities back in
        for (int i = 0; i < currentRoom.getEntityManager().size(); i++) {
            if(entities.get(i).isColliadable) {
               // tree.insert(entities.get(i));
                collidableEntites.add(entities.get(i));
            }
        }

        //Adds the bullets to the list of entities, allows for collision computation
        List<Entity> bullets = ((ShootComponent) player.getComponent(ComponentType.SHOOT)).getBullets().getEntities();
        collidableEntites.addAll(bullets);

      //  ArrayList<Entity> returnObjects = new ArrayList<Entity>();
       // int size = collidableEntites.size();
       // for (int i = 0; i < size; i++) {

          //  returnObjects.clear();
            //returnObjects = tree.retrieve(returnObjects, collidableEntites.get(i).getBoundingBox());
            //System.out.println("size = " + size + " return objexts = " + returnObjects.size());

            //only want to call once for efficiency sake

            collision.checkCollisions(collidableEntites);
       // }

        //Point light
        if(currentRoom.getType().equals(TYPE.BOSS)) {
            light.setPosition(player.getX() + (player.getWidth() / 2), player.getY() + (player.getHeight() / 2));
        }

    }

    /**
     * Calculates the current room, used for when a player goes through an open door.
     */
    private void calculateCurrentRoom() {
        int newRoomCol =  currentRoom.getX() / roomWidth;
        int newRoomRow =  currentRoom.getY() / roomHeight;

        if(player.getX() < currentRoom.getX()){
            currentRoom.removeEntity(player);
            currentRoom = rooms[newRoomCol - 1][newRoomRow];
            currentRoom.add(player, player.getX(), player.getY());
            this.collision = new WallCollision(currentRoom, player);
        }
        else if(player.getX() > currentRoom.getX() + roomWidth){
            currentRoom.removeEntity(player);
            currentRoom = rooms[newRoomCol + 1][newRoomRow];
            currentRoom.add(player, player.getX(), player.getY());
            this.collision = new WallCollision(currentRoom, player);
        }
        else if(player.getY() < currentRoom.getY()){
            currentRoom.removeEntity(player);
            currentRoom = rooms[newRoomCol][newRoomRow - 1];
            currentRoom.add(player, player.getX(), player.getY());
            this.collision = new WallCollision(currentRoom, player);
        }
        else if(player.getY() > currentRoom.getY() + roomHeight) {
            currentRoom.removeEntity(player);
            currentRoom = rooms[newRoomCol][newRoomRow + 1];
            currentRoom.add(player, player.getX(), player.getY());
            this.collision = new WallCollision(currentRoom, player);
        }

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
                if(rooms[x][y] != null) {
                    if(rooms[x][y].getType().equals(TYPE.SPAWN)) System.out.print("S");
                    else if (rooms[x][y].getType().equals(TYPE.BOSS)) System.out.print("B");
                    else System.out.print("X");
                }
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
     * Sets the current room to the one passed in
     * @param room, too to become the current room
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Returns the grid rooms
     * @return rooms
     */
    public Room[][] getRooms() { return rooms; }

    public int getRoomWidth() {
        return roomWidth;
    }

    public int getRoomHeight() {
        return roomHeight;
    }

    public int getScale() {
        return scale;
    }

    public Entity getPlayer() {
        return player;
    }

    public Level(){
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public void setRoomWidth(int roomWidth) {
        this.roomWidth = roomWidth;
    }

    public void setRoomHeight(int roomHeight) {
        this.roomHeight = roomHeight;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

}
