package AI;

import java.awt.*;

/**
 * Created by kumardyla on 18/09/17.
 */
public interface State {

    public void execute();
    public void draw(Graphics2D g2d);
}
