package Animations;

import java.awt.image.BufferedImage;

/**
 * Class to handle all the animations, simply iterates over an array of images.
 * Created by Sean on 28/09/17.
 */
public class Animation {
    private int index, timer;
    private BufferedImage[] images;

    public Animation(BufferedImage[] toAnima){
        this.images = toAnima;
        this.index = 0;
        this.timer = 0;
    }


    /**
     * As we already have a tick method in game, this works off that one to only draw when it looks correct.
     */
    public void tick(){
        timer++;
        if(timer %7==0) {
            index++;
            if (index >= images.length) {
                index = images.length - 1;
            }
        }
    }

    public BufferedImage getFrame(){
        return images[index];
    }

    /**
     * If an animation needs to refresh, set the image retrieved to 0.
     */
    public void refresh(){
        this.index = 0;
    }
}
