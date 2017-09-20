package HUD;

import java.awt.*;

/**
 * This class represents and paints the health bar onto the canvas
 */
public class HealthBar extends Canvas {
    private static final int HEALTH_SIZE = 3;

    /**
     * Paints this canvas.
     * <p>
     * Most applications that subclass <code>Canvas</code> should
     * override this method in order to perform some useful operation
     * (typically, custom painting of the canvas).
     * The default operation is simply to clear the canvas.
     * Applications that override this method need not call
     * super.paint(g).
     *
     * @param g the specified Graphics context
     * @see #update(Graphics)
     * @see Component#paint(Graphics)
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 10;
        for(int i = 1; i < HEALTH_SIZE + 1; i++) {
            g.setColor(Color.red);
            g.fillRect(x, 10, 20, 20);
            x += 30;
        }
    }
}