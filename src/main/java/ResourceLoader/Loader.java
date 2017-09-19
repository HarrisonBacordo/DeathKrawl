package ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Sean on 16/09/17.
*/

public class Loader {

        private static BufferedImage image;

        public static BufferedImage loadImage(String resourceName) {
            try{
                image = ImageIO.read(Loader.class.getResource(resourceName));
                return image;
            }
            catch (IOException e){ throw new Error(e); }
        }
}

