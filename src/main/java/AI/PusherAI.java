package AI;

import Entity.Entity;
import Entity.EntityType;
import Component.Component;
import LevelGenerator.Rooms.Room;

import java.awt.*;
/**
 * Created by kumardyla on 18/09/17.
 */
public class PusherAI extends Entity {

    State currentState;
    States state;
    EntityDetectorComponent detection;
    Room currentRoom;
    Entity opponent;

    public PusherAI(int x, int y, int width, int height, States state, Entity player, Room currentRoom) {
        super(x, y, width, height, EntityType.ENEMY);

        this.isColliadable = true;

        this.currentState = new PushState(this, currentRoom, player);

        this.state = state; //TODO uncomment

        detection = new EntityDetectorComponent(this, player, 800);

        this.opponent = player;
        this.currentRoom = currentRoom;
    }

    public void setState(State state){
        this.currentState = state;
    }

    @Override
    public void tick(){
        if(currentState != null){
            currentState.execute();
        }

    }

    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        currentState.draw(g2d, x, y, width, height);
        detection.draw(g2d, 100);
    }

    @Override
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }

}
