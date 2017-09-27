package Entity;

import Component.Component;
import Component.ComponentManager;
import Component.ComponentType;


import java.awt.*;

/**
 * This class represents any physical object in the game. This includes walls, items, players, and enemies.
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public abstract class Entity {
    protected int x, y, width, height;
    protected float xVelocity, yVelocity = 0;
    protected EntityType entityType;
    protected long ID;    //unique ID for the entity
    protected ComponentManager components;    //Stores the list of components/features this entity has
    protected Image image;
    public boolean isColliadable = false;

    public Entity(int x, int y, int width, int height, EntityType entityType) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.entityType = entityType;
        this.ID = EntityID.generateID();
        components = new ComponentManager();
    }

    /**
     * Adds the passed in component to this entity's ComponentManager
     *
     * @param component - the component to add to this entity's ComponentManager
     * @return - if the component was successfully added
     */
    public boolean addComponent(Component component) {
        if (components.containsComponentOfType(component.getComponentType())) {
            System.out.println("This entity already has this component");
            return false;
        }
        components.addComponent(component);
        return true;
    }

    /**
     * Updates this entity
     */
    public abstract void tick();

    /**
     * Returns the bounding box for this entity
     *
     * @return - a rectangle representing this entity's bounding box
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * renders this entity onto the screen
     */
    public abstract void render(Graphics g);

    /**
     * returns the components of this entity
     *
     * @return - the components of this entity
     */
    public ComponentManager getComponents() {
        return components;
    }

    /**
     * Returns the corresponding component
     * @param type, type of the component you want returned
     * @return Component
     */
    public Component getComponent(ComponentType type){
        switch (type){
            case SHOOT:
                return components.findComponentWithType(ComponentType.SHOOT);
        }

        return null;
    }
    /**
     * Returns the x position of this entity
     *
     * @return - the x position of this entity
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x position of this entity
     *
     * @param x - the desired x position of this entity
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y position of this entity
     *
     * @return - the y position of this entity
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position of this entity
     *
     * @param y - the desired y position of this entity
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width of this entity
     *
     * @return - the width of this entity
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of this entity
     *
     * @param width - the desired width of this entity
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height of this entity
     *
     * @return - the height of this entity
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of this entity
     *
     * @param height - the desired height of this entity
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * returns the EntityType of this entity
     *
     * @return - the EntityType of this entity
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the EntityType of this entity
     *
     * @param entityType - the desired EntityType of this entity
     */
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    /**
     * Returns the unique ID of this entity
     *
     * @return - the unique ID of this entity
     */
    public long getID() {
        return ID;
    }

    /**
     * Returns the x velocity of this entity
     *
     * @return - the x velocity of this entity
     */
    public float getXVelocity() {
        return xVelocity;
    }

    /**
     * Sets the x velocity of this entity
     *
     * @param xVelocity - the desired x velocity of this entity
     */
    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * Returns the y velocity of this entity
     *
     * @return - the y velocity of this entity
     */
    public float getYVelocity() {
        return yVelocity;
    }

    /**
     * Sets the y velocity of this entity
     *
     * @param yVelocity - the desired y velocity of this entity
     */
    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * Sets the image of this entity
     *
     * @param image - the desired image of this entity
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
