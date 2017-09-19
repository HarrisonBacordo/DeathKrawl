package LevelGenerator.Rooms;

import Entity.FloorEntity;
import Entity.NinjaEntity;
import Entity.WallEntity;
import ResourceLoader.Resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Loads rooms from an image. This allows us to easily add new rooms to the game
 * to be selected at random to add to a level.
 *
 * Created by Krishna Kapadia, 300258741 on 2/09/2017.
 */
public class RoomLoader {
    private BufferedImage image;
    private int variations;
    public Map<String, BufferedImage> roomsImages;

    public RoomLoader(int variations){
        this.variations = variations;
        roomsImages = new HashMap<>();

        //Load all rooms
        //loadAll();
    }

//    /**
//     * Loads an image, given its path locations
//     * @param path where image is stored
//     * @return BufferedImage
//     */
//    public BufferedImage loadImage(String path){
//        try {
//            System.out.println(path);
//            image = ImageIO.read(getClass().getResource(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return image;
//    }

//    /**
//     * Loads all rooms from the asset folder
//     */
//    public void loadAll(){
//        //Spawn Room
//        roomsImages.put("SpawnRoom", loadImage("/Rooms/SpawnRoomWDoor.png"));
//
//        //Adds all variations
//        for(int i = 0; i < variations; i++) {
//            roomsImages.put("Room" + (i + 1), loadImage("/Rooms/RoomVar" + (i + 1) + ".png"));
//        }
//
//    }

    /**
     * Given a room object and a scale, it selects a random room blueprint and populates the current room.
     * This allows for more diverse levels as rooms can have different feels to them.
     * @param room, Room object to populate
     * @param scale, scale to scale the room by
     */
    public void loadRandomRoom(Room room, int scale) {
        Random rand = new Random();
        int num = rand.nextInt(variations) + 1; //This ensures that the spawn room wil never be chosen as it has already been placed

        int i = 0;
//        for(Map.Entry<String, BufferedImage> entry : roomsImages.entrySet()){
//            if(i++ == num) {
//                loadRoom(entry.getValue(), room, scale);
//            }
//        }

        loadRoom(Resources.getImage("Room" + num), room, scale);
    }

    /**
     * Creates a room based of a given image.
     *
     * @param image, Level represented by image
     */
    public void loadRoom(BufferedImage image, Room room, int scale){
        int cellWidth = 32 / scale;
        int cellHeight = 32 / scale;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                //Place at positions past room's x and rooms y
                if(red == 0 && green == 0 && blue == 0) {
                    room.add(new WallEntity(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth, cellHeight), x, y);
                }

                //Doors
                else if(green == 220 || green == 230 || green == 240 || green == 250){
                    switch (green){
                        case 220: //TOP DOOR
                            room.addDoor(new Door(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth * 2, cellHeight), LOCATION.TOP, x++, y);
                            break;

                        case 230: //RIGHT DOOR
                            room.addDoor(new Door(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth, cellHeight * 2), LOCATION.RIGHT, x++, y);
                            break;

                        case 240: //BOTTOM DOOR
                            room.addDoor(new Door(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth * 2, cellHeight), LOCATION.BOTTOM, x, y);
                            break;

                        case 250: //LEFT DOOR
                            room.addDoor(new Door(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth, cellHeight * 2), LOCATION.LEFT, x, y);
                    }
                }

                //Floor
                else if(red == 255 && green == 255 && blue == 255){
                    room.add(new FloorEntity(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth, cellHeight), x, y);
                }


                else if(red == 0 && green == 0 && blue == 255) {
                    room.add(new FloorEntity(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth, cellHeight), x, y);
                    room.add(new NinjaEntity(room.getX() + (x * cellWidth), room.getY() + (y * cellHeight), cellWidth, cellHeight), x, y);
                }


            }
        }
    }

//    /**
//     * Returns the buffered image associated with the name
//     *
//     * @param roomName, name of the room
//     * @return BufferedImage corresponding to the roomName.
//     */
//    public BufferedImage getImage(String roomName) {
//        return roomsImages.get(roomName);
//    }

}
