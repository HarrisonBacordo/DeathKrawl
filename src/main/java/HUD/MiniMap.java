package HUD;

import Entity.Entity;
import Entity.EntityType;
import LevelGenerator.Rooms.Room;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents and paints the mini-map onto the canvas
 * <p>
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

    public void render(Graphics2D g) {
        super.paint(g);
        Color alphaBlack = new Color(30, 30, 30, 150);
        Color alphaWhite = new Color(255, 255, 255, 200);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(3));
        g.drawRect(x, y, width, height);
        g.setStroke(new BasicStroke(1));
        g.setColor(alphaBlack);
        g.fillRect(x, y, width, height);
        getMidPoint();
        int translateX = miniMapMidX - midX;
        int translateY = miniMapMidY - midY;

        g.setColor(alphaWhite);
        for (int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                if (rooms[x][y] != null) {
                    int roomWidth = rooms[x][y].getWidth() / scaleWidth;
                    int roomHeight = rooms[x][y].getHeight() / scaleHeight;
                    int roomX = (roomWidth * x) + translateX;
                    int roomY = (roomHeight * y) + translateY;
                    for (Entity e : rooms[x][y].getEntityManager().getEntities()) {
                        if (e.getEntityType().equals(EntityType.PLAYER)) {
                            roomsVisited.add(rooms[x][y]);
                            g.fillRect(roomX, roomY, roomWidth, roomHeight);
                        }
                    }
                    if (roomsVisited.contains(rooms[x][y])) {
                        g.setColor(alphaWhite);
                        g.drawRect(roomX, roomY, roomWidth, roomHeight);
                    }


                }
            }
        }

    }

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
