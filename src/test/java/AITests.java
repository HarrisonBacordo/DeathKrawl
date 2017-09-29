import AI.*;
import Entity.EntityID;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * Created by kumardyla on 27/09/17.
 */

public class AITests {

    /**
     * Tests whether movingAI goes to the location of the stationary player
     */
    @Test
    public void testMovement1(){

        MoverAI stationaryPlayerToFollow = new MoverAI(50, 50, 32, 32, null,  null, null);

        MoverAI movingAI = new MoverAI(500, 300, 32, 32, States.MOVETOWARDS,  stationaryPlayerToFollow, null);

        int i = 0;
        while(i < 300){
            movingAI.tick();
            i++;
        }

        Assert.assertEquals(movingAI.getBoundingBox().intersects(stationaryPlayerToFollow.getBoundingBox()), true);
    }

    /**
     * Tests whether movingAI goes to the location of the stationary player
     */
    @Test
    public void testMovement2(){

        MoverAI stationaryPlayerToFollow = new MoverAI(100, 250, 32, 32, null,  null, null);

        MoverAI movingAI = new MoverAI(500, 300, 32, 32, States.MOVETOWARDS,  stationaryPlayerToFollow, null);

        int i = 0;
        while(i < 300){
            movingAI.tick();
            i++;
        }

        Assert.assertEquals(movingAI.getBoundingBox().intersects(stationaryPlayerToFollow.getBoundingBox()), true);
    }

    /**
     * Tests whether movingAI goes to the location of the stationary player
     */
    @Test
    public void testMovement3(){

        MoverAI stationaryPlayerToFollow = new MoverAI(100, 250, 32, 32, null, null, null);

        MoverAI movingAI = new MoverAI(0, 30, 32, 32, States.MOVETOWARDS, stationaryPlayerToFollow, null);

        int i = 0;
        while(i < 300){
            movingAI.tick();
            i++;
        }

        Assert.assertEquals(movingAI.getBoundingBox().intersects(stationaryPlayerToFollow.getBoundingBox()), true);
    }


    /**
     * Tests whether the player is moved to the location of the grappleAI
     */
    @Test
    public void testGrapple1(){

        MoverAI player = new MoverAI(500, 250, 32, 32, null,  null, null);

        GrappleAI grappleAI = new GrappleAI(100, 200, 32, 32, States.GRAPPLE,  player, null);

        int i = 0;
        while(i < 1500){
            grappleAI.tick();
            i++;
        }

        Assert.assertEquals(grappleAI.getBoundingBox().intersects(player.getBoundingBox()), true);
    }


    /**
     * Tests whether the player is moved to the location of the grappleAI
     */
    @Test
    public void testGrapple2(){

        MoverAI player = new MoverAI(200, 270, 32, 32, null,  null, null);

        GrappleAI grappleAI = new GrappleAI(300, 100, 32, 32, States.GRAPPLE, player, null);

        int i = 0;
        while(i < 1500){
            grappleAI.tick();
            i++;
        }

        Assert.assertEquals(grappleAI.getBoundingBox().intersects(player.getBoundingBox()), true);
    }


    /**
     * Tests whether the player is moved to the location of the grappleAI
     */
    @Test
    public void testGrapple3(){

        MoverAI player = new MoverAI(0, 0, 32, 32, null, null, null);

        GrappleAI grappleAI = new GrappleAI(-100, -100, 32, 32, States.GRAPPLE, player, null);

        int i = 0;
        while(i < 1500){
            grappleAI.tick();
            i++;
        }

        Assert.assertEquals(grappleAI.getBoundingBox().intersects(player.getBoundingBox()), true);
    }



}
