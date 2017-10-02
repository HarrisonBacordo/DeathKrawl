package AI;

import Entity.Entity;
import Entity.EntityType;
import LevelGenerator.Rooms.Room;

import java.awt.*;
/**
 * Created by kumardyla on 18/09/17.
 */
public class MoverAI extends Entity {

    State currentState;
    States state;
    EntityDetectorComponent detection;
    Room currentRoom;
    Entity opponent;

    public MoverAI(int x, int y, int width, int height, States state, Entity player, Room currentRoom) {
        super(x, y, width, height, EntityType.ENEMY);
        this.isCollidable = true;
        this.currentState = new MoveTowardsState(this, currentRoom, player);

        //currentState = new ExplodeState(this, opponent);
        //this.currentState = new GrappleState(this, currentRoom, player);

        this.state = state;

        //components.addComponent(new EntityDetectorComponent(this, player));
        detection = new EntityDetectorComponent(this, player, 800);

        this.opponent = player;
        this.currentRoom = currentRoom;
    }

    public void setState(State state){
        this.currentState = state;
    }


    /**
     * Handles the different states of the MoverAI by executing the specific state the AI is on.
     */
    @Override
    public void tick(){

        assert currentState != null;

            currentState.execute();
            if(state == States.WANDER){
                if(detection.CheckIfInView(400)){
                    state = States.MOVETOWARDS;
                    this.currentState = new MoveTowardsState(this, currentRoom, opponent);
                }
            }else if(state == States.MOVETOWARDS){
                //if within a certain distance, change state to boom boom

                if(!detection.CheckIfInView(400)){
                    state = States.WANDER;
                    this.currentState = new DoNothingState();
                } else if(detection.CheckIfInView(100)){
                    state = States.EXPLODE;
                    currentState = new ExplodeState(this, opponent);
                }

            }else if(state == States.EXPLODE){
                if(((ExplodeState)(currentState)).isDead()){
                    //KILL ITSELF
                }
            }
    }

    @Override
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);

        currentState.draw(g2d, x, y, width, height);

        detection.draw(g2d, 100);
        //g2d.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }

    /**
     * Returns the state
     * @return state
     */
    public States getState() { return state; }

}
