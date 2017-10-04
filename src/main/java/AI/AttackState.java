package AI;

import Entity.Entity;

import java.awt.*;

/**
 * Created by kumardyla on 18/09/17.
 */
public class AttackState implements State{

    @Override
    public void execute() {

    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.fillRect(x, y, width, height);
    }


}
