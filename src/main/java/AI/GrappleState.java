package AI;

import Entity.Entity;
import LevelGenerator.Rooms.Room;
import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by kumardyla on 18/09/17.
 */
public class GrappleState implements State{

    Entity entity;
    Room currentRoom;
    Entity opponent;
    long tStart;

    float speed = 10;
    int tileSize = 32;

    float endX;
    float endY;

    GrappleState(Entity entity, Room currentRoom, Entity opponent){
        this.entity = entity;
        this.currentRoom = currentRoom;
        this.opponent = opponent;
        tStart = System.currentTimeMillis();

        this.endX = entity.getX() + entity.getWidth()/2;
        this.endY = entity.getY() + entity.getHeight()/2;

    }



    @Override
    public void execute() {
        moveHookForward(getAngle());
    }

    public void moveHookForward(float angle) {
        endX += ((int)(speed * sin(angle)));
        endY += ((int)(speed * cos(angle)));


//        float entityCenterX = entity.getX() + entity.getWidth()/2;
//        float entityCenterY = entity.getY() + entity.getHeight()/2;
//
//        float desiredX;
//        float desiredY;
//
//        desiredX = opponent.getX() + opponent.getWidth()/2;
//        desiredY = opponent.getY() + opponent.getHeight()/2;
//
//
//        int speed = 10;
//
//        float distance = (float)(Math.sqrt(Math.pow(desiredX - entityCenterX, 2) + Math.pow(desiredY - entityCenterY, 2)));
//        float amountToMoveX = ((desiredX - entityCenterX) / distance) * speed;
//        float amountToMoveY = ((desiredY - entityCenterY) / distance) * speed;
//
//        endX += amountToMoveX;
//        endY += amountToMoveY;


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
        g2d.drawLine((int)entityCenterX, (int)entityCenterY, (int) endX, (int) endY);



        //g2d.drawLine((int)entityCenterX, (int)entityCenterY, (int) desiredX, (int) desiredY);
        g2d.fillOval((int) endX, (int) endY, 30, 30);
    }
}

