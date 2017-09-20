package Entity;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles all the user input
 *
 * Created by krishna on 2/09/2017.
 */
public class KeyInput extends KeyAdapter {
    private boolean up, down, left, shootUp, shootDown,
            shootLeft, shootRight, right, space, pause;

    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) up = true;
        if(key == KeyEvent.VK_S) down = true;
        if(key == KeyEvent.VK_A) left = true;
        if(key == KeyEvent.VK_D) right = true;
        if(key == KeyEvent.VK_SPACE) space = true;
        if(key == KeyEvent.VK_ESCAPE) pause = !pause;
        if (key == KeyEvent.VK_I) shootUp = true;
        if (key == KeyEvent.VK_K) shootDown = true;
        if (key == KeyEvent.VK_J) shootLeft = true;
        if (key == KeyEvent.VK_L) shootRight = true;

    }

    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W) up = false;
        if(key == KeyEvent.VK_S) down = false;
        if(key == KeyEvent.VK_A) left = false;
        if(key == KeyEvent.VK_D) right = false;
        if(key == KeyEvent.VK_SPACE) space = false;
        if (key == KeyEvent.VK_I) shootUp = false;
        if (key == KeyEvent.VK_K) shootDown = false;
        if (key == KeyEvent.VK_J) shootLeft = false;
        if (key == KeyEvent.VK_L) shootRight = false;

    }


    public boolean isUp(){
        return up;
    }

    public boolean isDown(){
        return down;
    }

    public boolean isLeft(){
        return left;
    }

    public boolean isRight(){
        return right;
    }

    public boolean isSpace(){
        return space;
    }

    public boolean isShootUp() { return shootUp; }

    public boolean isShootDown() { return shootDown; }

    public boolean isShootLeft() { return shootLeft; }

    public boolean isShootRight() { return shootRight; }
}
