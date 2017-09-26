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
public class GrappleState implements State{

    Entity entity;
    Room currentRoom;
    Entity opponent;

    long tStart;
    long tStart2;

    float speed;
    int hookSize;

    float endX;
    float endY;

    float targetX;
    float targetY;
    boolean targeted;

    GrappleState(Entity entity, Room currentRoom, Entity opponent){
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;
        tStart = System.currentTimeMillis();
        tStart2= System.currentTimeMillis();
        this.hookSize = 12;
        this.speed = 1;
        this.endX = entity.getX() + entity.getWidth()/2;
        this.endY = entity.getY() + entity.getHeight()/2;
        tStart = System.nanoTime();
        targeted = false;
    }

    public void setTarget(){
        this.targetX = opponent.getX() + opponent.getWidth()/2;
        this.targetY = opponent.getY() + opponent.getHeight()/2;
        targeted = true;
    }



    @Override
    public void execute() {

        long tEnd = System.nanoTime();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000000000;

        long tDelta2 = tEnd - tStart2;
        double elapsedSeconds2 = tDelta / 1000000000;

        if(elapsedSeconds2 > 2){
            if(elapsedSeconds > 1) {

                if (!targeted) {
                    setTarget();
                }

                if(getHooksBoundingBox().intersects(opponent.getBoundingBox())){
                    reelHookIn();
                    tStart = System.nanoTime();
                }else{
                    moveHookForward();
                }
            }else {
                reelHookIn();
            }
        }


//            tStart = System.nanoTime();



            //TODO have multiple times for the hooks

        }





    public void moveHookForward() {
        if(targetX > endX) endX += speed;
        if(targetX < endX) endX -= speed;

        if(targetY > endY) endY += speed;
        if(targetY < endY) endY -= speed;
    }

    public void reelHookIn(){
        float entityCenterX = entity.getX() + entity.getWidth()/2;
        float entityCenterY = entity.getY() + entity.getHeight()/2;

        if(entityCenterX > endX) endX += speed;
        if(entityCenterX < endX) endX -= speed;

        if(entityCenterY > endY) endY += speed;
        if(entityCenterY < endY) endY -= speed;

        opponent.setX((int)endX);
        opponent.setY((int)endY);
    }



    public Rectangle getHooksBoundingBox(){
        return new Rectangle((int) endX - hookSize/2, (int) endY, hookSize, hookSize);
    }

    /**
     * Gets angle between this entity (AI) and an opponent entity (player).
     * @return
     */
    public float getAngle(){
        float entityCenterX = entity.getX() + entity.getWidth()/2;
        float entityCenterY = entity.getY() + entity.getHeight()/2;

        float desiredX;
        float desiredY;

        desiredX = opponent.getX() + opponent.getWidth()/2;
        desiredY = opponent.getY() + opponent.getHeight()/2;

        float angle = (float) Math.toDegrees(Math.atan2((double)(desiredX - entityCenterX), desiredY - entityCenterY));

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

        desiredX = opponent.getX() + opponent.getWidth()/2;
        desiredY = opponent.getY() + opponent.getHeight()/2;


        float entityCenterX = entity.getX() + entity.getWidth()/2;
        float entityCenterY = entity.getY() + entity.getHeight()/2;

        g2d.setColor(Color.GREEN);

        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine((int)entityCenterX, (int)entityCenterY, (int) desiredX, (int) desiredY);


        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine((int)entityCenterX, (int)entityCenterY, (int) endX, (int) endY);
        //hook end
        g2d.fillOval((int) endX - hookSize/2, (int) endY, hookSize, hookSize);
        //hook bounding box
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect((int) endX - hookSize/2, (int) endY, hookSize, hookSize);
    }
}

