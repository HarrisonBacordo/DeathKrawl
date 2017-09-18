package ResourceLoader;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Sean on 16/09/17.
 */
public class Resources {

    private HashMap<String, BufferedImage> assets;
    private static final int width = 10, height = 10;
    private Loader imgLoader;

    public Resources(){
        assets = new HashMap<>();
        imgLoader = new Loader();
        //load the image, break down with sprite, extract here
        SpriteLoader sprites = new SpriteLoader(imgLoader.loadImage("/assets/Wheat.png"));
    }
}
