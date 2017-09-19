package HUD;

import java.awt.*;

/**
 * This class represents and paints the mini-map onto the canvas
 */
public class MiniMap extends Canvas {
    int width, height;

    public MiniMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawRect(width - 180, height - 225, 150, 150);
    }
}
