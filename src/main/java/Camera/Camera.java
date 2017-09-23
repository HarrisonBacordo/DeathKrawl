package Camera;

import Entity.Entity;
import LevelGenerator.Rooms.Room;

/**
 * Camera controls to
 *
 * Created by Krishna and Sean on 23/09/2017.
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
     * @param Room r, Room to lerp to when travelling to new room
     */
    public void tick(Room r){
            x += ((r.getX() - x)) * 0.05f;
            y += ((r.getY() - y)) * 0.05f;
    }

    /**
     * Updates the camera's location
     * @param Entity e, Entity to follow
     */
    public void tick(Entity e){
        x += ((e.getX() - x) - width / 2) * 0.05f;
        y += ((e.getY() - y) - height / 2) * 0.05f;
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
