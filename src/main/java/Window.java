import javax.swing.*;
import java.awt.*;

/**
 * Creates a window
 *
 * Created by krishna on 2/09/2017.
 */
public class Window {
    private JFrame frame;

    /**
     * Creates a Window of the specified width and height
     * @param width, of the window
     * @param height, of the height
     * @param title, of the game
     * @param game, to display
     */
    public Window(int width, int height, String title, Game game){
        frame = new JFrame(title);

        frame.setMinimumSize(new Dimension(width, height));
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    /**
     * Creates a fullscreen window
     * @param title, Title of the game
     * @param game, Game to display
     */
    public Window(String title, Game game){
        System.out.println("um");
        frame = new JFrame(title);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.add(game);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    /**
     * Returns the width of the window
     * @return width
     */
    public double getWidth() {
        return frame.getWidth();
    }

    /**
     * Returns the height of the window
     * @return height
     */
    public double getHeight() {
        return frame.getHeight();
    }

}
