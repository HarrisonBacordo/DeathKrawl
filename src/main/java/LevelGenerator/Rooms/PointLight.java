package LevelGenerator.Rooms;

import Entity.Entity;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Creates a visual point light at the specified position
 * Created by Krishna Kapadia on 25/09/17.
 */
public class PointLight {
    private int x, y, radius;
    private Point2D center;
    private float[] dist;
    private Color[] colors;
    private RadialGradientPaint radialPaint;

    public PointLight(int x, int y, int radius){
        this.x = x - (radius / 2);
        this.y = y - (radius / 2);
        this.radius = radius;
        Point2D center = new Point2D.Float(this.x, this.y);
        dist = new float[]{ 0.0f, 1.0f };
        Color[] colors = { new Color(0f, 0f, 0f), Color.black };
        radialPaint = new RadialGradientPaint(center, radius, dist, colors);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(radialPaint);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        g2d.fillOval(x, y, 1000, 1000);
    }

    public void setPosition(int x, int y) {
        this.x = x - (radius / 2);
        this.y = y - (radius / 2);
    }

// optionally set the focus to a side (instead of the center) to give direction
//focus.setLocation(Math.cos(angle) * 3 * radius / 4 + radius, Math.sin(angle) * 3 * radius / 4 + radius);

}
