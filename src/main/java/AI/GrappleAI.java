package AI;

import Entity.Entity;
import Entity.EntityType;
import Component.Component;
import LevelGenerator.Rooms.Room;

import java.awt.*;
/**
 * Created by kumardyla on 18/09/17.
 */
public class GrappleAI extends Entity {

    State currentState;
    States state;
    FacingDirection facingDirection;
    EntityDetectorComponent detection;
    Room currentRoom;
    Entity opponent;

    public GrappleAI(int x, int y, int width, int height, States state, Entity player, Room currentRoom) {
        super(x, y, width, height, EntityType.ENEMY);


        this.isColliadable = true;

        this.currentState = new GrappleState(this, currentRoom, player);

        this.state = state;

        //components.addComponent(new EntityDetectorComponent(this, player));
        detection = new EntityDetectorComponent(this, player, 100);
        this.opponent = player;
        this.currentRoom = currentRoom;
        isColliadable = true;
    }

    public void setState(State state){
        this.currentState = state;
    }

    @Override
    public void tick(){

        if(currentState != null){

            currentState.execute();
            if(state == States.MOVETOWARDS){
                //if within a certain distance, change state to attack
                //if lost player, change state to searching
                if(!detection.CheckIfInView()){
                    state = States.GRAPPLE;
                    currentState = new GrappleState(this, currentRoom, opponent);
                }
            }else if(state == States.ATTACK){
                //explode
            }else if(state == States.GRAPPLE){
                if(detection.CheckIfInView()){
                    state = States.MOVETOWARDS;
                    currentState = new MoveTowardsState(this, currentRoom, opponent);
                }
            }
        }


    }

    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);

        currentState.draw(g2d, x, y, width, height);
//        detection.draw(g2d);
        //g2d.fillRect(x, y, width, height);
    }


}
