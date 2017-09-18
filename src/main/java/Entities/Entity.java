package Entities;

import Components.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Base of all Game entities
 * Created by krishna on 2/09/2017.
 */
public abstract class Entity {
    protected int x, y, width, height;
    protected float xVel = 0, yVel = 0;
    protected ID id;
    protected BufferedImage image;
    protected ArrayList<Component> components;

    public Entity(int x, int y, int width, int height, ID id){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.components = new ArrayList<>();
    }

    /**
     * Updates the entity
     */
    public abstract void tick();

    /**
     * Renders the entity
     * @param g, graphics object with which to draw the entity
     */
    public abstract void render(Graphics g);

    /**
     * Returns the bounds of the entity in a Rectangle approximation
     * Used for collision detection
     *
     * @return Rectangle type bounding box
     */
    public abstract Rectangle getBounds();

    /**
     * Adds a new component to the entity
     * @param component, that needs to be added to the entity
     */
    public void addComponent(Component component){
        components.add(component);
    }

    /**
     * Executes all attached components
     */
    public void processComponents(){
        for (Component c : components) c.execute();
    }



    //Getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    public ID getId() {
        return id;
    }

    public void setImage(BufferedImage image) { this.image = image; }
}
