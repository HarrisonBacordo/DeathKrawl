package Camera;

import Entity.Entity;

/**
 * Camera that the player seeing the game though
 *
 * Created by krishna on 2/09/2017.
 */
public class Camera {
    private float x, y;
    private int width, height;

    public Camera(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Updates the camera's location
     * @param target, Entity to follow, if null camera is stationary
     */
    public void tick(Entity target){
        if(target != null) {
            x += ((target.getX() + 16 - x) - width / 2) * 0.05f;
            y += ((target.getY() + 16 - y) - height / 2) * 0.05f;
            //Clamp camera to level so it can't go outside it
//            if(x <= 0) x = 0;
//            if(x >= width - 400) x = width - 400;
//            if(y <= 0) y = 0;
//            if(y >= height - 150) y = height - 150;
        }

    }

    public void tick(int x, int y){
        // * 0.05f
        //((x - this.x) - width / 2);
        this.x = x;
        this.y = y;
//        this.x = ((x - this.x) - width / 2)*0.05f;
//        this.y = ((y - this.y) - height / 2)*0.05f;
    }


    //Getters and setters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
