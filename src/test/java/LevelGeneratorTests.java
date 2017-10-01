import Entity.NinjaEntity;
import Entity.WallEntity;
import LevelGenerator.Level;
import LevelGenerator.Rooms.Door;
import LevelGenerator.Rooms.LOCATION;
import LevelGenerator.Rooms.Room;
import LevelGenerator.Rooms.TYPE;
import ResourceLoader.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


/**
 * Tests for the level generator library
 *
 * Created by Krishna Kapadia 300358741 on 25/09/17.
 */
public class LevelGeneratorTests {

    /**
     * Generating a normal level with n number of rooms
     */
    @Test
    public void validNumOfRoomsGenerated_1(){
        int numberOfRooms = 4;

        //Create new level and resource loader
        Resources resources = new Resources();
        Level level = new Level(numberOfRooms, 30, 17, 4, 2);

        //Check that the number of rooms in the level is equal to the number of rooms set above
        Room[][] rooms = level.getRooms();
        int roomCount = 0;

        //Go through the generated rooms and count them
        for(int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if(rooms[x][y] != null) roomCount++;
            }
        }

        //Assertion
        assertEquals("Incorrect number of rooms", numberOfRooms, roomCount);
    }

    /**
     * Test for creating a level with 0 rooms should be invalid
     */
    @Test (expected = IllegalArgumentException.class)
    public void invalidNumOfRoomsGenerated_1() {
        int numberOfRooms = 0;

        //Create new level
        Level level = new Level(numberOfRooms, 30, 17, 4, 2);
    }

    /**
     * Test to see if the current room when spawned in, is the spawn room
     */
    @Test
    public void validCurrentRoom_1() {
        //Create new level and resource loader
        Resources resources = new Resources();
        Level level = new Level(8, 30, 17, 4, 2);
        Room currentRoom = level.getCurrentRoom();
        //Ensure that the current room is of type SpawnRoom
        assertEquals("Current room should be of type SpawnRoom", currentRoom.getType(), TYPE.SPAWN);
    }

    /**
     * Test to see if the current room when moved is no longer the spawn room
     */
    @Test
    public void validCurrentRoom_2() {
        Resources resources = new Resources();
        Level level = new Level(8, 30, 17, 4, 2);
        Room[][] rooms = level.getRooms();
        Room currentRoom = level.getCurrentRoom();
        Point oldLocation = new Point(currentRoom.getX(), currentRoom.getY());

        //Move the current room
        Room next = null;
        //Go through the generated rooms and find the first available one that is not the spawn room
        for(int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if(rooms[x][y] != null && rooms[x][y] != currentRoom) next = rooms[x][y];
            }
        }

        if(next != null) level.setCurrentRoom(next);
        Point newLocation = new Point(level.getCurrentRoom().getX(), level.getCurrentRoom().getY());

        //Ensure that the current room is not of type SpawnRoom
        assertNotEquals("Current room should not be of type spawn room", level.getCurrentRoom().getType(), TYPE.SPAWN);

        //Ensure that it has changed location
        assertNotEquals("Current room position should have changed", oldLocation, newLocation);

    }

    /**
     * Ensures that the right doors are created between levels
     */
    @Test
    public void validDoorCheck_1() {
        Resources resources = new Resources();
        Level level = new Level(8, 30, 17, 4, 2);
        Room[][] rooms = level.getRooms();

        //Go through the generated rooms , checking to ensure that doors exist in that location
        for(int y = 1; y < rooms[0].length - 1; y++) {
            for (int x = 1; x < rooms.length - 1; x++) {
                Room current = rooms[x][y];
                if(current != null) {
                    //Right of current room
                    if(rooms[x + 1][y] != null) {
                        assertNotNull(current.getDoors().get(LOCATION.RIGHT));
                        assertNotNull(rooms[x + 1][y].getDoors().get(LOCATION.LEFT));
                    }

                    //Left of current room
                    if(rooms[x - 1][y] != null) {
                        assertNotNull(current.getDoors().get(LOCATION.LEFT));
                        assertNotNull(rooms[x - 1][y].getDoors().get(LOCATION.RIGHT));
                    }

                    //Above current room
                    if(rooms[x][y - 1] != null) {
                        assertNotNull(current.getDoors().get(LOCATION.TOP));
                        assertNotNull(rooms[x][y - 1].getDoors().get(LOCATION.BOTTOM));
                    }

                    //Below current room
                    if(rooms[x][y + 1] != null) {
                        assertNotNull(current.getDoors().get(LOCATION.BOTTOM));
                        assertNotNull(rooms[x][y + 1].getDoors().get(LOCATION.TOP));
                    }
                }
            }
        }

    }

    /**
     * Ensures door has initial state of open and then can be set to closed
     */
    @Test
    public void validDoorCheck_2() {
        Door d = new Door(0, 0, 32, 32, true, LOCATION.TOP);
        assertTrue("Door must have initial state of open to be true", d.isOpen());

        //close the door
        d.setState(false);
        assertFalse("Door must have state closed", d.isOpen());
    }

    /**
     * Test to see if the door has the correct location
     */

    @Test
    public void validDoorCheck_3() {
        Resources resources = new Resources();
        Level level = new Level(8, 30, 17, 4, 2);
        Room[][] rooms = level.getRooms();
        int col = 0, row = 0;

        //Find the current room's row and cols
        for(int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if (rooms[x][y] != null && rooms[x][y].equals(level.getCurrentRoom())) {
                    col = y;
                    row = x;
                    break;
                }
            }
        }

        //Check for valid door locations
        if(rooms[col + 1][row] != null){
            assertNotNull(level.getCurrentRoom().getDoors().get(LOCATION.RIGHT));
        }

        if(rooms[col - 1][row] != null) {
            assertNotNull(level.getCurrentRoom().getDoors().get(LOCATION.LEFT));
        }

        if(rooms[col][row + 1] != null) {
            assertNotNull(level.getCurrentRoom().getDoors().get(LOCATION.BOTTOM));
        }

        if(rooms[col][row - 1] != null) {
            assertNotNull(level.getCurrentRoom().getDoors().get(LOCATION.TOP));
        }
    }

    /**
     * Adding an entity to a room
     */
    @Test
    public void validRoomAdd_1() {
        for(int i = 0; i < 5; i++){
            Room room = new Room(0, 0, 30, 17, 1, TYPE.SPAWN);
            NinjaEntity e = new NinjaEntity(i, i, 10, 10);
            //Add entity to room and check if successful
            assertTrue(room.add(e, i, i));
        }
    }

    /**
     * Removing an entity from a room
     */
    @Test
    public void validRoomRemove_1() {
        Room room = new Room(0, 0, 30, 17, 1, TYPE.SPAWN);
        NinjaEntity e = new NinjaEntity(10, 10, 10, 10);
        room.add(e, 10, 10);
        //Check if removal of ninja entity was successful
        assertNotNull(room.removeEntity(e));
    }

}
