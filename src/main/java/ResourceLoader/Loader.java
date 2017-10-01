package ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Loads in images for game assets
 * Created by Sean on 16/09/17.
 */

public class Loader {

    public static BufferedImage loadImage(String resourceName) {
        try{
            return ImageIO.read(Loader.class.getResource(resourceName));
        }
        catch (IOException e){ throw new Error(e); }
    }
}

