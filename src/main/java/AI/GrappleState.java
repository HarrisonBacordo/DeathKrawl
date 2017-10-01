package AI;

import Entity.Entity;
import LevelGenerator.Rooms.Room;

import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.incrementExact;
import static java.lang.Math.sin;

/**
 * Created by kumardyla on 18/09/17.
 *
 *
 * BUG LIST: If two enemies grapple onto player and you kill one, you magically teleport to the other grapple hook
 *
 */

public class GrappleState implements State {

    Entity entity;
    Room currentRoom;
    Entity opponent;

    long ticks;

    float speed;
    float reelSpeed;
    float reelSpeedWithPlayer;
    int hookSize;

    float endX, endY;

    float targetX, targetY;

    boolean targeted;
    boolean reel;
    boolean withPlayer;

    GrappleState(Entity entity, Room currentRoom, Entity opponent) {
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;

        this.hookSize = 12;
        this.speed = 13;
        this.reelSpeed = 7;
        this.reelSpeedWithPlayer = 1;
        this.endX = entity.getX() + entity.getWidth() / 2;
        this.endY = entity.getY() + entity.getHeight() / 2;

        ticks = 0;

        targeted = false;
        reel = false;
        withPlayer = false;
    }


    /**
     * Sets target to current opponents position
     */
    public void setTarget() {
        this.targetX = opponent.getX() + opponent.getWidth() / 2;
        this.targetY = opponent.getY() + opponent.getHeight() / 2;
        targeted = true;
    }

    public float getEndX() {
        return endX;
    }


    /**
     * Waits 1 second, then sends a hook towards the opponents position.
     * If it fails to hook, re-target, wait a second and repeat.
     */
    @Override
    public void execute() {

        ticks++;
        double elapsedSeconds = ticks / 60;

        if (elapsedSeconds > 1) {
            if (reel) {
                reelHookIn(withPlayer);
                //if the hook returns
                if (getHooksBoundingBox().intersects(entity.getBoundingBox())) {
                    reel = false;
                    targeted = false;
                    withPlayer = false;
                }
            } else if (!targeted) {
                ticks = 0;
            } else if (getHooksBoundingBox().intersects(opponent.getBoundingBox())) {
                reel = true;
                withPlayer = true;
            } else {
                //if the hook doesn't touch the player but it's reached the target bounding box, retract
                if (getHooksBoundingBox().intersects(getTargetBoundingBox())) {
                    reel = true;
                    targeted = false;
                    withPlayer = false;
                }
                moveHookForward();
            }
        } else {
            setTarget();
        }


    }


    /**
     * Moves hook forward towards target
     */
    public void moveHookForward() {
        if (targetX > endX){
            endX += speed;
        }else{
            endX -= speed;
        }
        if (targetY > endY){
            endY += speed;
        }else{
            endY -= speed;
        }

    }


    /**
     * Reel hook back to AI
     * @param withPlayer, if the AI is reeling with the player or not
     */
    public void reelHookIn(boolean withPlayer) {
        if (withPlayer) {
            reel(reelSpeedWithPlayer);
            opponent.setX((int) endX);
            opponent.setY((int) endY);
        }else{
            reel(reelSpeed);
        }
    }


    /**
     * returns the center X coordinate of the entity the detection bounding box is from.
     * @return float
     */
    public float getCenterX(){
        return entity.getX() + entity.getWidth()/2;
    }


    /**
     * returns the center y coordinate of the entity the detection bounding box is from.
     * @return float of center y coordinate
     */
    public float getCenterY(){
        return entity.getY() + entity.getHeight()/2;
    }


    /**
     * returns the center X coordinate of the opponents bounding box.
     * @return float
     */
    public float getOpponentCenterX(){
        return opponent.getX() + opponent.getWidth() / 2;
    }


    /**
     * returns the center y coordinate of the entity the opponents bounding.
     * @return float of center y coordinate
     */
    public float getOpponentCenterY(){
        return opponent.getY() + opponent.getHeight() / 2;
    }



    /**
     * Reels the hook back towards the AI
     * @param reelSpeed, speed to reel back by
     */
    public void reel(float reelSpeed){
        if (getCenterX() > endX) endX += reelSpeed;
        if (getCenterX() < endX) endX -= reelSpeed;
        if (getCenterY() > endY) endY += reelSpeed;
        if (getCenterY() < endY) endY -= reelSpeed;
    }


    /**
     *
     * @return the bounding box of the target
     */
    public Rectangle getTargetBoundingBox() {
        return new Rectangle((int) (targetX - opponent.getWidth() / 2), (int) (targetY - opponent.getWidth() / 2), opponent.getWidth(), opponent.getHeight());
    }

    public Rectangle getHooksBoundingBox() {
        return new Rectangle((int) endX - hookSize / 2, (int) endY, hookSize, hookSize);
    }




    /**
     * Gets angle between this entity (AI) and an opponent entity (player).
     *
     * @return
     */
    public float getAngle() {
        float desiredX = getOpponentCenterX();
        float desiredY = getOpponentCenterY();

        float angle = (float) Math.toDegrees(Math.atan2((double) (desiredX - getCenterX()), desiredY - getCenterY()));
        angle += 90;
        return angle;
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {

        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);

        g2d.setColor(Color.GREEN);

        g2d.setStroke(new BasicStroke(1));

        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine((int) getCenterX(), (int) getCenterY(), (int) endX, (int) endY);
        //hook end
        g2d.fillOval((int) endX - hookSize / 2, (int) endY, hookSize, hookSize);
        //hook bounding box
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect((int) endX - hookSize / 2, (int) endY, hookSize, hookSize);

    }
}

