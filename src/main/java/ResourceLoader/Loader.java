package ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

        public static Font loadFont(String resourceName){
//            try {
//                File file = new File(Loader.class.getResource(resourceName).getFile());
//                Font font = Font.createFont(Font.TRUETYPE_FONT, file);
//                return font.deriveFont(12f);
//            } catch (IOException|FontFormatException e) {
//                throw new Error(e);
//            }
            InputStream in = Loader.class.getResourceAsStream(resourceName);
            if ( in == null ) {
                System.out.println("wtf");
            }
            return null;
        }
}

