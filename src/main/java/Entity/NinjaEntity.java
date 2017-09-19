package Entity;

import Component.ComponentManager;
import Component.InputComponent;
import Entity.Entity;
import Entity.EntityID;

import java.awt.*;

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
        super(x, y, width, height, EntityType.PLAYER, EntityID.generateID());
        addComponent(new InputComponent(this, ComponentManager.keyInput));
    }

    @Override
    public void tick() {
        xPos += xVelocity;
        yPos += yVelocity;

        //Processes all components
        components.executeAllComponents();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(xPos, yPos, width, height);
    }
}
