package AI;

import Entity.Entity;
import Entity.EntityType;
import java.awt.*;

/**
 * Created by kumardyla on 18/09/17.
 */
public class MoverAI extends Entity {

    State currentState;
    States state;

    public MoverAI(int x, int y, int width, int height, State currentState, States state, long ID) {
        super(x, y, width, height, EntityType.FLOOR, ID);
        this.currentState = currentState;
        this.state = state;
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
        currentState.draw();
        //g.drawRect(x, y, width, height);
    }

    @Override
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }

}
