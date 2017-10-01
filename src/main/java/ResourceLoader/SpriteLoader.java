package ResourceLoader;

import java.awt.image.BufferedImage;


/**
 * If we potentially ever needed to load in sprites.
 * Created by Sean on 16/09/17.
 */
public class SpriteLoader {
    private BufferedImage sprites;

    public SpriteLoader(BufferedImage sprites){
        this.sprites = sprites;
    }

    public BufferedImage extract(int x, int y, int width, int height){
        return sprites.getSubimage(x, y, width, height);
    }
}
