package LevelGenerator.Rooms;

import Entity.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Creates a visual point light at the specified position
 * Created by Krishna Kapadia on 25/09/17.
 */
public class PointLight {
    private int x, y, width, height;
    private Point2D center;
    private float[] dist;
    private Color[] colors;
    private RadialGradientPaint radialPaint;

    public PointLight(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        center = new Point2D.Float(this.x, this.y);
        dist = new float[]{ 0.0f, 0.5f, 1.0f };
        colors = new Color[]{ Color.black, new Color(100, 100, 100), new Color(0f, 0f, 0f) };
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        radialPaint = new RadialGradientPaint(center, 200, dist, colors);
        g2d.setPaint(radialPaint);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2d.fillRect(x, y, width, height);
    }

    public void setPosition(int x, int y) {
        center.setLocation(x, y);
    }

// optionally set the focus to a side (instead of the center) to give direction
//focus.setLocation(Math.cos(angle) * 3 * radius / 4 + radius, Math.sin(angle) * 3 * radius / 4 + radius);

}
