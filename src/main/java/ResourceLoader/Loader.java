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
        private static Font f;

        public static BufferedImage loadImage(String resourceName) {
            try{
                image = ImageIO.read(Loader.class.getResource(resourceName));
                return image;
            }
            catch (IOException e){ throw new Error(e); }
        }

     //   public static Font loadFont(String resourceName){
//            try {
//                f = Font.createFont(Font.TRUETYPE_FONT, new File(Loader.class.getResource(resourceName).getFile()));
//                f.deriveFont(Font.PLAIN, 30f);
//                return f;
//            } catch (FontFormatException e) {
//                throw new Error(e);
//            } catch (IOException e) {
//                throw new Error(e);
//            }
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            try{
//                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Loader.class.getResource(resourceName).getFile())));
//                System.out.println(ge.getAllFonts());
//                return null;
//            }catch(Exception e){
//                throw new Error(e);
//            }
      //  }
}

