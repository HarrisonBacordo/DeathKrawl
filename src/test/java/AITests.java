import AI.*;
import Entity.EntityID;
import LevelGenerator.Level;
import ResourceLoader.Resources;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        assertEquals(movingAI.getBoundingBox().intersects(stationaryPlayerToFollow.getBoundingBox()), true);
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

        assertEquals(movingAI.getBoundingBox().intersects(stationaryPlayerToFollow.getBoundingBox()), true);
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

        assertEquals(movingAI.getBoundingBox().intersects(stationaryPlayerToFollow.getBoundingBox()), true);
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

        assertEquals(grappleAI.getBoundingBox().intersects(player.getBoundingBox()), true);
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

        assertEquals(grappleAI.getBoundingBox().intersects(player.getBoundingBox()), true);
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

        assertEquals(grappleAI.getBoundingBox().intersects(player.getBoundingBox()), true);
    }





    //EXTERNAL TESTS BY Krishna Kapadia 300358741

    /**
     * Ensures that 2 ai cannot pass through each other
     */
    @Test
    public void testAIColliding(){
        MoverAI player = new MoverAI(100, 100, 32, 32, null,  null, null);
        MoverAI movingAIleft = new MoverAI(50, 100, 32, 32, States.MOVETOWARDS,  player, null);
        MoverAI movingAIRight = new MoverAI(150, 100, 32, 32, States.MOVETOWARDS, player, null);
        int i = 0;
        while(i < 600){
            movingAIleft.tick();
            movingAIRight.tick();
            i++;
        }

        //Ensure that they both intersect with the player but that they do not intersect with each other
        assertEquals(movingAIleft.getBoundingBox().intersects(player.getBoundingBox()), true);
        assertEquals(movingAIRight.getBoundingBox().intersects(player.getBoundingBox()), true);
        assertFalse(movingAIleft.getBoundingBox().intersects(movingAIRight.getBoundingBox()));
    }

    /**
     * Ensure that a entity within the vision on the mover AI is detected
     */
    @Test
    public void testDetection_1() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(300, 100, 32, 32, States.MOVETOWARDS, player, null);

        EntityDetectorComponent detectorComponent = new EntityDetectorComponent(moverAI, player, 300);

        assertTrue("Player should be detected", detectorComponent.CheckIfInView());
    }

    /**
     * Ensure that an entity within the vision on the mover AI is detected via a custom vision box
     */
    @Test
    public void testDetection_2() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(300, 100, 32, 32, States.MOVETOWARDS, player, null);

        EntityDetectorComponent detectorComponent = new EntityDetectorComponent(moverAI, player, 0);
        assertTrue("Player should be detected", detectorComponent.CheckIfInView(300));
    }

    /**
     * Ensure that an entity outside of the vision of the AI is not detected.
     */
    @Test
    public void testDetectionFail_1() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(600, 100, 32, 32, States.MOVETOWARDS, player, null);

        EntityDetectorComponent detectorComponent = new EntityDetectorComponent(moverAI, player, 300);
        assertFalse("Player should not be detected", detectorComponent.CheckIfInView());
    }

    /**
     * Ensure that an entity outside of the vision of the AI is not detected using a Custom vision box size
     */
    @Test
    public void testDetectionFail_2() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(600, 100, 32, 32, States.MOVETOWARDS, player, null);

        EntityDetectorComponent detectorComponent = new EntityDetectorComponent(moverAI, player, 0);
        assertFalse("Player should not be detected", detectorComponent.CheckIfInView(300));
    }

    /**
     * Ensures that the correct angle is calculated between a MoverAI and a player entity
     */
    @Test
    public void testAngle_1() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(200, 200, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        assertTrue("Angle should be 0", moveTowardsState.getAngle() == 0f);
    }

    /**
     * Ensures that the correct angle is calculated between a MoverAI and a player entity
     */
    @Test
    public void testAngle_2() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(300, 100, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        assertTrue("Angle should be " + moveTowardsState.getAngle(), moveTowardsState.getAngle() == 90f);
    }

    /**
     * Ensures that the correct angle is calculated between a MoverAI and a player entity in the anti-clockwise rotation
     */
    @Test
    public void testAngle_3() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(100, 100, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        assertTrue("Angle should be " + moveTowardsState.getAngle(), moveTowardsState.getAngle() == -90f);
    }

    /**
     * Ensures that the correct angle is calculated between 2 AI directly on top of each other
     */
    @Test
    public void testAngle_4() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(200, 100, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        assertTrue("Angle should be " + moveTowardsState.getAngle(), moveTowardsState.getAngle() == 0f);
    }

    /**
     * Ensure that the entity moves in the correct way
     */
    @Test
    public void correctMovementDirection_1() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(100, 100, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        moveTowardsState.moveForward();

        //Check that the entity moved to the right
        assertTrue("Mover AI moved in the wrong direction" , moveTowardsState.getEntity().getX() > 100);
    }

    /**
     * Ensure that the entity moves in the correct way
     */
    @Test
    public void correctMovementDirection_2() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(100, 50, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        moveTowardsState.execute();

        //Check that the entity moved to the right and down
        assertTrue("Mover AI moved in the wrong direction" , moveTowardsState.getEntity().getX() > 100);
        assertTrue("Mover AI moved in the wrong direction", moveTowardsState.getEntity().getY() > 50);
    }

    /**
     * Ensure that the entity moves in the correct way
     */
    @Test
    public void correctMovementDirection_3() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(300, 100, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        moveTowardsState.execute();

        //Check that the entity moved to the left
        assertTrue("Mover AI moved in the wrong direction" , moveTowardsState.getEntity().getX() < 300);
    }

    /**
     * Ensure that the entity moves in the correct way
     */
    @Test
    public void correctMovementDirection_4() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(300, 200, 32, 32, States.MOVETOWARDS, player, null);

        MoveTowardsState moveTowardsState = new MoveTowardsState(player, null, moverAI);
        moveTowardsState.execute();

        //Check that the entity moved to the left
        assertTrue("Mover AI moved in the wrong direction" , moveTowardsState.getEntity().getX() < 300);
        assertTrue("Mover AI moved in the wrong direction", moveTowardsState.getEntity().getY() < 200);
    }

    /**
     * Ensures that the correct angle is calculated between a MoverAI and a player entity
     */
    @Test
    public void testGrappleAngle_1() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(200, 200, 32, 32, States.MOVETOWARDS, player, null);

        GrappleState moveTowardsState = new GrappleState(player, null, moverAI);
        assertTrue("Angle should be 0", moveTowardsState.getAngle() == 0f);
    }

    /**
     * Ensures that the correct angle is calculated between a MoverAI and a player entity
     */
    @Test
    public void testGrappleAngle_2() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(300, 100, 32, 32, States.MOVETOWARDS, player, null);

        GrappleState moveTowardsState = new GrappleState(player, null, moverAI);
        assertTrue("Angle should be " + moveTowardsState.getAngle(), moveTowardsState.getAngle() == 90f);
    }

    /**
     * Ensures that the correct angle is calculated between a MoverAI and a player entity in the anti-clockwise rotation
     */
    @Test
    public void testGrappleAngle_3() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(100, 100, 32, 32, States.MOVETOWARDS, player, null);

        GrappleState moveTowardsState = new GrappleState(player, null, moverAI);
        assertTrue("Angle should be " + moveTowardsState.getAngle(), moveTowardsState.getAngle() == -90f);
    }

    /**
     * Ensures that the correct angle is calculated between 2 AI directly on top of each other
     */
    @Test
    public void testGrappleAngle_4() {
        MoverAI player = new MoverAI(200, 100, 32, 32, null,  null, null);
        MoverAI moverAI = new MoverAI(200, 100, 32, 32, States.MOVETOWARDS, player, null);

        GrappleState moveTowardsState = new GrappleState(player, null, moverAI);
        assertTrue("Angle should be " + moveTowardsState.getAngle(), moveTowardsState.getAngle() == 0f);
    }

    /**
     * Ensures that the correct state change is made
     */
    @Test
    public void testStateChange_1() {
        MoverAI player = new MoverAI(100, 200, 32, 32, null, null, null);
        MoverAI ai = new MoverAI(200, 200, 32, 32, States.WANDER, player, null);
        //Initial check
        assertTrue("Initial state should be WANDER", ai.getState().equals(States.WANDER));
        ai.tick();
        //Recheck
        assertTrue("State should be MOVETOWARDS", ai.getState().equals(States.MOVETOWARDS));
    }

    /**
     * Ensures that the correct state change is made
     */
    @Test
    public void testStateChange_2() {
        MoverAI player = new MoverAI(100, 200, 32, 32, null, null, null);
        GrappleAI ai = new GrappleAI(200, 200, 32, 32, States.MOVETOWARDS, player, null);
        //Initial check
        assertTrue("Initial state should be MOVETOWARDS", ai.getState().equals(States.MOVETOWARDS));
        ai.tick();
        //Recheck
        assertTrue("State should be GRAPPLE", ai.getState().equals(States.GRAPPLE));
}

    /**
     * Ensures that the correct state change is made
     */
    @Test
    public void testStateChange_3() {
        MoverAI player = new MoverAI(100, 200, 32, 32, null, null, null);
        GrappleAI ai = new GrappleAI(200, 200, 32, 32, States.MOVETOWARDS, player, null);
        //Initial check
        assertTrue("Initial state should be MOVETOWARDS", ai.getState().equals(States.MOVETOWARDS));

        //Change states
        ai.setState(States.WANDER);
        assertTrue("State should now be WANDER", ai.getState().equals(States.WANDER));
    }

    /**
     * Ensures that the correct state change is made
     */
    @Test
    public void testStateChange_4() {
        MoverAI player = new MoverAI(300, 200, 32, 32, null, null, null);
        GrappleAI ai = new GrappleAI(200, 200, 32, 32, States.WANDER, player, null);
        //Initial check
        assertTrue("Initial state should be WANDER", ai.getState().equals(States.WANDER));
    }

    /**
     * Ensures that the correct state change is made
     */
    @Test
    public void testStateChange_5() {
        MoverAI player = new MoverAI(100, 200, 32, 32, null, null, null);
        GrappleAI ai = new GrappleAI(200, 200, 32, 32, States.MOVETOWARDS, player, null);
        //Initial check
        assertTrue("Initial state should be MOVETOWARDS", ai.getState().equals(States.MOVETOWARDS));

        //Change states
        ai.setState(States.WANDER);
        assertTrue("State should now be WANDER", ai.getState().equals(States.WANDER));
    }

}
