package AI;

import java.awt.*;

public class DoNothingState implements State{

    @Override
    public void execute() {

    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {
        g2d.setColor(Color.PINK);
        g2d.fillRect(x, y, width, height);
    }


}
