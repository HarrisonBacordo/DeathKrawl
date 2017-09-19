package Entity;

import Component.Component;
import Component.ComponentManager;

import java.awt.*;

/**
 * This class represents any physical object in the game. This includes walls, items, players, and enemies.
 */
public abstract class Entity {
    protected int xPos, yPos, width, height;
    protected float xVelocity, yVelocity = 0;
    protected EntityType entityType;
    protected long ID;    //unique ID for the entity
    protected ComponentManager components;    //Stores the list of components/features this entity has
    protected Image image;

    public Entity(int xPos, int yPos, int width, int height, EntityType entityType, long ID) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.entityType = entityType;
        this.ID = ID;
        components = new ComponentManager();
    }

    /**
     * Adds the passed in component to this entity's ComponentManager
     *
     * @param component - the component to add to this entity's ComponentManager
     * @return - if the component was successfully added
     */
    public boolean addComponent(Component component) {
        if (components.hasComponentOfType(component.getComponentType())) {
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
        return new Rectangle(xPos, yPos, width, height);
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
     * Returns the x position of this entity
     *
     * @return - the x position of this entity
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Sets the x position of this entity
     *
     * @param xPos - the desired x position of this entity
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Returns the y position of this entity
     *
     * @return - the y position of this entity
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Sets the y position of this entity
     *
     * @param yPos - the desired y position of this entity
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
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
    public float getxVelocity() {
        return xVelocity;
    }

    /**
     * Sets the x velocity of this entity
     *
     * @param xVelocity - the desired x velocity of this entity
     */
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * Returns the y velocity of this entity
     *
     * @return - the y velocity of this entity
     */
    public float getyVelocity() {
        return yVelocity;
    }

    /**
     * Sets the y velocity of this entity
     *
     * @param yVelocity - the desired y velocity of this entity
     */
    public void setyVelocity(int yVelocity) {
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
