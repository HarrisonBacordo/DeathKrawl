package Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A group of static methods to load in resources
 *
 * Created by Admin on 15/09/17.
 */
public class ResourceLoader {
//    public static Map<String, BufferedImage> resources = new HashMap<>();

    /**
     * Loads an image, given its path locations
     * @param path where image is stored
     * @return BufferedImage
     */
    public BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
