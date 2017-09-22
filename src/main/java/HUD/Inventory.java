package HUD;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents and paints the inventory onto the canvas
 */
public class Inventory extends Canvas{
    private final int DEFAULT_X_FOR_INVENTORY_OVER_40;
    private final int DEFAULT_X_FOR_INVENTORY_UNDER_40;
    private static final int INITIAL_Y = 15;

    public int width;
    public int height;
    public ArrayList<String> items;

    public Inventory(int width, int height) {
        DEFAULT_X_FOR_INVENTORY_OVER_40 = width - 180;
        DEFAULT_X_FOR_INVENTORY_UNDER_40 = width - 90;
        this.width = width;
        this.height = height;
        items = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            items.add("sword");
        }
    }

    public void render(Graphics g) {
        super.paint(g);
        int maxItemsPerRow;
        int currentPositionInRow = 0;
        int x;
        int defaultX;
        int y= INITIAL_Y;
        if(items.size() > 40) {
            maxItemsPerRow = 7;
            x = DEFAULT_X_FOR_INVENTORY_OVER_40;
            defaultX = DEFAULT_X_FOR_INVENTORY_OVER_40;
        } else {
            maxItemsPerRow = 3;
            x = DEFAULT_X_FOR_INVENTORY_UNDER_40;
            defaultX = DEFAULT_X_FOR_INVENTORY_UNDER_40;
        }
        for(String item : items) {
            switch(item) {
                case "sword":
                    g.setColor(Color.BLUE);
                    break;
                case "gun":
                    g.setColor(Color.GREEN);
                    break;
                case "speedBoost":
                    g.setColor(Color.CYAN);
                    break;
            }
            g.fillRect(x, y, 15, 15);

            currentPositionInRow++;
            x += 20;
            if(currentPositionInRow >= maxItemsPerRow) {
                currentPositionInRow = 0;
                y += INITIAL_Y + 3;
                x = defaultX;
            }
        }
    }

}
