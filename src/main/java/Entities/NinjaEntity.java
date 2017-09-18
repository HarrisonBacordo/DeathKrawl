package Entities;

import Components.ComponentManager;
import Components.InputComponent;

import java.awt.*;
import java.util.Vector;

/**
 * Entity that represents the player
 *
 * Created by krishna on 2/09/2017.
 */
public class NinjaEntity extends Entity {
    public boolean jumping = false;

    /**
     * Creates a Ninja Player entity at the given location
     * @param x, x position
     * @param y, y position
     */
    public NinjaEntity(int x, int y, int width, int height) {
        super(x, y, width, height, ID.PLAYER);
        addComponent(new InputComponent(this, ComponentManager.keyInput));
    }

    @Override
    public void tick() {
        x += xVel;
        y += yVel;

        //Processes all components
        processComponents();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
