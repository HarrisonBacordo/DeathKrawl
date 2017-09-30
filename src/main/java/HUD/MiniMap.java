package HUD;

import LevelGenerator.Level;
import LevelGenerator.Rooms.Room;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents and paints the mini-map onto the canvas
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class MiniMap extends Canvas {
    private int x, y;
    private int midX, midY;
    private int miniMapMidX, miniMapMidY;
    private int width, height, scaleWidth, scaleHeight;
    private Room[][] rooms;
    private static Set<Room> roomsVisited = new HashSet<>();

    public MiniMap(int width, int height, Room[][] rooms) {
        this.width = 200;
        this.height = 150;
        this.x = width - 200;
        this.y = height - 150;
        this.scaleWidth = 42;
        this.scaleHeight = 32;
        miniMapMidX = (x + (x + this.width)) / 2;
        miniMapMidY = (y + (y + this.height)) / 2;
        this.rooms = rooms;
    }

    /**
     * Renders the minimap onto the screen using the passed in graphics
     *
     * @param g - graphics to render with
     */
    public void render(Graphics2D g) {
        super.paint(g);
//        set colors to be used for minimap
        Color alphaBlack = new Color(30, 30, 30, 150);
        Color alphaWhite = new Color(255, 255, 255, 200);
//        draw border and background of minimap
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawRect(x, y, width, height);
        g.setStroke(new BasicStroke(1));
        g.setColor(alphaBlack);
        g.fillRect(x, y, width, height);

        getMidPoint();  //get XY midpoint of the rooms layout

//        set translation needed for minimap to be centered on the minimap window
        int translateX = miniMapMidX - midX;
        int translateY = miniMapMidY - midY;

        g.setColor(alphaWhite);
        for (int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if (rooms[x][y] != null) {
//                    scale down room dimensions and XY position
                    int roomWidth = rooms[x][y].getWidth() / scaleWidth;
                    int roomHeight = rooms[x][y].getHeight() / scaleHeight;
                    int roomX = (roomWidth * x) + translateX;
                    int roomY = (roomHeight * y) + translateY;

                    if(rooms[x][y] == Level.currentRoom) {  //check if this room is the current room the player is in
//                        fill this room with white to indicate the current room
                        roomsVisited.add(rooms[x][y]);
                        g.fillRect(roomX, roomY, roomWidth, roomHeight);
                    }
//                    if this room has been visited, outline it. otherwise, don't draw it
                    if (roomsVisited.contains(rooms[x][y])) { g.drawRect(roomX, roomY, roomWidth, roomHeight); }
                }
            }
        }

    }

    /**
     * calculates and sets the midpoint of the room layout for this level
     */
    private void getMidPoint() {
        int leftMostX = Integer.MAX_VALUE;
        int upperMostY = Integer.MAX_VALUE;
        int rightMostX = Integer.MIN_VALUE;
        int lowerMostY = Integer.MIN_VALUE;
        for (int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if (rooms[x][y] != null) {
                    if (rooms[x][y].getX() < leftMostX) {
                        leftMostX = rooms[x][y].getX();
                    }
                    if (rooms[x][y].getX() > rightMostX) {
                        rightMostX = rooms[x][y].getX();
                    }

                    if (rooms[x][y].getY() < upperMostY) {
                        upperMostY = rooms[x][y].getY();
                    }
                    if (rooms[x][y].getY() > lowerMostY) {
                        lowerMostY = rooms[x][y].getY();
                    }


                }
                midX = ((leftMostX + rightMostX) / 2) / scaleWidth;
                midY = ((upperMostY + lowerMostY) / 2) / scaleHeight;
            }
        }
    }
}
