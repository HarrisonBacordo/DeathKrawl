package HUD;

import ResourceLoader.Resources;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents and paints the inventory onto the canvas
 * <p>
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public class Inventory extends Canvas {
    private final int DEFAULT_X_FOR_INVENTORY_OVER_40;
    private final int DEFAULT_X_FOR_INVENTORY_UNDER_40;
    private final int INITAL_X_VALUE = 250;
    private final int ITEM_CELL_SIZE = 40;
    private static final int INITIAL_Y = 15;

    public static int inventoryIndex = 0;

    public int width;
    public int height;
    public static ArrayList<String> items;
    private Image image = Resources.getImage("PISTOL");

    public Inventory(int width, int height) {
        DEFAULT_X_FOR_INVENTORY_OVER_40 = width - 180;
        DEFAULT_X_FOR_INVENTORY_UNDER_40 = width - 90;
        this.width = width;
        this.height = height;
        items = new ArrayList<>();
        items.add("default-gun");
        items.add("shotgun");
    }

    public void render(Graphics2D g) {
        if(inventoryIndex >= items.size()) { inventoryIndex = items.size() - 1; }
        if(inventoryIndex <= 0) { inventoryIndex = 0; }

        super.paint(g);
        for (int i = 0; i <= 10; i++) {
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3));
            g.drawRect(INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE);
            g.setColor(new Color(30, 30, 30, 200));
            g.fillRect(INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE);
//            check if current index equals selected weapon index
            if(i < items.size()) {
                if (inventoryIndex == i) {
                    g.setColor(Color.WHITE);
                    g.fillRect(INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE);
                }
                switch (items.get(i)) {
                    case "default-gun":
                        image = Resources.getImage("PISTOL");
                        break;
                    case "shotgun":
                        image = Resources.getImage("SHOTGUN");
                        break;

                }
            }
            if(i < items.size()) {
                g.drawImage(image, INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE, null);
            }
        }
//        for(String item : items) {
//            switch(item) {
//                case "sword":
//                    g.setColor(Color.BLUE);
//                    break;
//                case "gun":
//                    g.setColor(Color.GREEN);
//                    break;
//                case "speedBoost":
//                    g.setColor(Color.CYAN);
//                    break;
//            }
//            g.fillRect(x, y, 15, 15);
    }
//        int maxItemsPerRow;
//        int currentPositionInRow = 0;
//        int x;
//        int defaultX;
//        int y= INITIAL_Y;
//        if(items.size() > 40) {
//            maxItemsPerRow = 7;
//            x = DEFAULT_X_FOR_INVENTORY_OVER_40;
//            defaultX = DEFAULT_X_FOR_INVENTORY_OVER_40;
//        } else {
//            maxItemsPerRow = 3;
//            x = DEFAULT_X_FOR_INVENTORY_UNDER_40;
//            defaultX = DEFAULT_X_FOR_INVENTORY_UNDER_40;
//        }
//        for(String item : items) {
//            switch(item) {
//                case "sword":
//                    g.setColor(Color.BLUE);
//                    break;
//                case "gun":
//                    g.setColor(Color.GREEN);
//                    break;
//                case "speedBoost":
//                    g.setColor(Color.CYAN);
//                    break;
//            }
//            g.fillRect(x, y, 15, 15);
//
//            currentPositionInRow++;
//            x += 20;
//            if(currentPositionInRow >= maxItemsPerRow) {
//                currentPositionInRow = 0;
//                y += INITIAL_Y + 3;
//                x = defaultX;
//            }
//        }

}
