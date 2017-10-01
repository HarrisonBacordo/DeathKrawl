package Camera;

import Entity.Entity;
import LevelGenerator.Rooms.Room;

/**
 * Camera class which is what all the level translates based on. Tick methods controlled by Room or Player.
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
     * @param r , Room to lerp to when travelling to new room
     */
    public void tick(Room r){
        //*0.05f to get smooth tweening when transitioning between rooms.
        x += ((r.getX() - x)) * 0.05f;
        y += ((r.getY() - y)) * 0.05f;
    }

    /**
     * Updates the camera's location
     * @param e, Entity to follow
     */
    public void tick(Entity e){
        //*0.05f to get smooth tweening when player is moving, so camera has a "lag" effect
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
