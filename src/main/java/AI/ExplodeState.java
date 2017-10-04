package AI;

import Entity.Entity;
import LevelGenerator.Rooms.Room;

import java.awt.*;

/**
 * Created by kumardyla on 29/09/17.
 */
public class ExplodeState implements State {

    private long ticks;
    private Entity entity;
    private Entity opponent;
    private double multiplier;
    private boolean dead;

    ExplodeState(Entity entity, Entity opponent){
        this.entity = entity;
        this.opponent = opponent;
        this.ticks = 0;
        this.multiplier = 0.1;
        this.dead = false;
    }

    @Override
    public void execute() {
        multiplier += 0.005;
        ticks++;
    }

    public boolean isDead(){
        return this.dead;
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {

        if(ticks < 60){
            if(ticks%4 ==0){
                g2d.setColor(Color.YELLOW);
            }else{
                g2d.setColor(Color.RED);
            }

            g2d.fillRect((int)(x - width*multiplier/2), (int)(y - height*multiplier/2), (int)(width + width*multiplier), (int)(height + height * multiplier));
        }else{
            dead = true;
        }
    }
}
