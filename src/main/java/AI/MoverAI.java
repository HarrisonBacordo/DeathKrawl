package AI;

import Entity.Entity;
import Entity.EntityType;
import Component.Component;

import java.awt.*;

/**
 * Created by kumardyla on 18/09/17.
 */
public class MoverAI extends Entity {

    State currentState;
    States state;
    FacingDirection facingDirection;
    EntityDetectorComponent detection;

    public MoverAI(int x, int y, int width, int height, State currentState, States state, FacingDirection fd, Entity player) {
        super(x, y, width, height, EntityType.FLOOR);
        this.currentState = currentState;
        this.state = state;
        detection = new EntityDetectorComponent(this, player);
        this.facingDirection = fd;
    }

    public void setState(State state){
        this.currentState = state;
    }

    @Override
    public void tick(){
        if(currentState != null){
            currentState.execute();
            if(state == States.WANDER){
                //if can detect player, change state to move towards player
                if(detection.CheckIfInView()){
                    state = States.MOVETOWARDS;
                    currentState = new MoveTowardsState();
                }
            }else if(state == States.MOVETOWARDS){
                //if within a certain distance, change state to attack
                //if lost player, change state to searching
            }else if(state == States.ATTACK){

            }else if(state == States.RUNAWAY){
                //if far away from the enemy enough, resume Wander (or regroup?)
            }else if(state == States.SEARCHINGSTATE){
                //search for X seconds then go to Wander state
            }
        }


    }

    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        currentState.draw(g2d);
        //g.drawRect(x, y, width, height);
    }

    @Override
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }

}
