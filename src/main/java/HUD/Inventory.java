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
    private final int ITEM_LIST_SIZE = 10;
    private final int INITAL_X_VALUE = 250;
    private final int ITEM_CELL_SIZE = 40;

    public static int inventoryIndex = 0;
    public static ArrayList<String> items;

    public int width;
    public int height;
    private Image image = Resources.getImage("PISTOL");

    public Inventory(int width, int height) {
        this.width = width;
        this.height = height;
        items = new ArrayList<>();
        items.add("default-gun");
    }

    /**
     * Renders the inventory onto the screen using the passed in graphics
     *
     * @param g - graphics to render with
     */
    public void render(Graphics2D g) {
        super.paint(g);

//        if inventoryIndex is past items list size, set inventoryIndex to last index in list
        if (inventoryIndex >= items.size()) {
            inventoryIndex = items.size() - 1;
        }
//        if inventoryIndex is below 0, set inventoryIndex to 0
        if (inventoryIndex < 0) {
            inventoryIndex = 0;
        }

        for (int i = 0; i <= ITEM_LIST_SIZE; i++) {
//            draw the inventory border and background
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(3));
            g.drawRect(INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE);
            g.setColor(new Color(30, 30, 30, 200));
            g.fillRect(INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE);

            if (i < items.size()) {  //check if index has gone through all the items
                if (inventoryIndex == i) {  //if i is at inventory index, fill the cell background with white
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
                    case "sword":
                        image = Resources.getImage("SWORD");
                        break;
                    case "assault-rifle":
                        image = Resources.getImage("ASSAULT_RIFLE");
                        break;
                }
                g.drawImage(image, INITAL_X_VALUE + (ITEM_CELL_SIZE * i), height - ITEM_CELL_SIZE, ITEM_CELL_SIZE, ITEM_CELL_SIZE, null);
            }
        }
    }
}
