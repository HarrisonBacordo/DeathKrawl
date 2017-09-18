import javax.swing.*;
import java.awt.*;

/**
 * Creates a window
 *
 * Created by krishna on 2/09/2017.
 */
public class Window {

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setMinimumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
