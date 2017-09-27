package AI;

import Entity.Entity;
import LevelGenerator.Rooms.Room;

import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.incrementExact;
import static java.lang.Math.sin;

/**
 * Created by kumardyla on 18/09/17.
 */
public class GrappleState implements State {

    Entity entity;
    Room currentRoom;
    Entity opponent;

    long ticks;

    float speed;
    float reelSpeed;
    int hookSize;

    float endX;
    float endY;

    float targetX;
    float targetY;

    boolean targeted;
    boolean reel;
    boolean withPlayer;

    GrappleState(Entity entity, Room currentRoom, Entity opponent) {
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;

        this.hookSize = 12;
        this.speed = 10;
        this.reelSpeed = 3;
        this.endX = entity.getX() + entity.getWidth() / 2;
        this.endY = entity.getY() + entity.getHeight() / 2;

        ticks = 0;

        targeted = false;
        reel = false;
        withPlayer = false;
    }

    public void setTarget() {
        this.targetX = opponent.getX() + opponent.getWidth() / 2;
        this.targetY = opponent.getY() + opponent.getHeight() / 2;
        targeted = true;
    }

    public float getEndX() {
        return endX;
    }

    @Override
    public void execute() {

        ticks++;
//        System.out.println(ticks);
        double elapsedSeconds = ticks / 60;

        if (elapsedSeconds > 1) {
            System.out.println("x: " + opponent.getX() + " y: " + opponent.getY());
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
                //setTarget();
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


    public void moveHookForward() {
        if (targetX > endX) endX += speed;
        if (targetX < endX) endX -= speed;

        if (targetY > endY) endY += speed;
        if (targetY < endY) endY -= speed;
    }

    public void reelHookIn(boolean withPlayer) {
        float entityCenterX = entity.getX() + entity.getWidth() / 2;
        float entityCenterY = entity.getY() + entity.getHeight() / 2;

        if (entityCenterX > endX) endX += reelSpeed;
        if (entityCenterX < endX) endX -= reelSpeed;

        if (entityCenterY > endY) endY += reelSpeed;
        if (entityCenterY < endY) endY -= reelSpeed;

        if (withPlayer) {
            opponent.setX((int) endX);
            opponent.setY((int) endY);
        }

    }


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
        float entityCenterX = entity.getX() + entity.getWidth() / 2;
        float entityCenterY = entity.getY() + entity.getHeight() / 2;

        float desiredX;
        float desiredY;

        desiredX = opponent.getX() + opponent.getWidth() / 2;
        desiredY = opponent.getY() + opponent.getHeight() / 2;

        float angle = (float) Math.toDegrees(Math.atan2((double) (desiredX - entityCenterX), desiredY - entityCenterY));

        angle += 90;

        return angle;
    }


    @Override
    public void draw(Graphics2D g2d, int x, int y, int width, int height) {


        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, width, height);

        //TODO refactor out duplicate variables and stuff

        float desiredX;
        float desiredY;

        desiredX = opponent.getX() + opponent.getWidth() / 2;
        desiredY = opponent.getY() + opponent.getHeight() / 2;


        float entityCenterX = entity.getX() + entity.getWidth() / 2;
        float entityCenterY = entity.getY() + entity.getHeight() / 2;

        g2d.setColor(Color.GREEN);

        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine((int) entityCenterX, (int) entityCenterY, (int) desiredX, (int) desiredY);


        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine((int) entityCenterX, (int) entityCenterY, (int) endX, (int) endY);
        //hook end
        g2d.fillOval((int) endX - hookSize / 2, (int) endY, hookSize, hookSize);
        //hook bounding box
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect((int) endX - hookSize / 2, (int) endY, hookSize, hookSize);
    }
}

