package Animations;

import java.awt.image.BufferedImage;

/**
 * Created by Sean on 28/09/17.
 */
public class Animation {
    private int speed, index;
    private long timer, lastTime;
    private BufferedImage[] images;

    public Animation(BufferedImage[] toAnima){
        this.speed = speed;
        this.images = toAnima;
        this.index = 0;
        this.timer = 0;
    }


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

    public void refresh(){
        this.index = 0;
    }
}
