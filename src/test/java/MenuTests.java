import AI.MoverAI;
import AI.States;
import Entity.KeyInput;
import GameStates.MenuState;
import GameStates.STATE;
import GameStates.StateManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

/**
 * Created by kumardyla on 3/10/17.
 */
public class MenuTests {

    /**
     * Tests whether the menu runs without throwing exceptions
     */
    @Test
    public void testMenuRunning() throws Throwable {
        final Throwable[] thrown = {null};
        Game game = new Game(){
            @Override
            public void run() {
                //if exception is not caught in game, it is caught here instead.
                Thread.setDefaultUncaughtExceptionHandler((ex,ex2)->{
                    thrown[0] = ex2;
                });
                super.run();
            }
        };
        try{
            Thread.sleep(3000);
        }catch (Exception e){

        }
        if (thrown[0] != null) throw thrown[0];
    }

    /**
     * Tests whether the pause menu runs without throwing exceptions
     */
    @Test
    public void testPauseRunning() throws Throwable{
        final Throwable[] thrown = {null};
        Game game = new Game(){
            @Override
            public void run() {
                //if exception is not caught in game, it is caught here instead.
                Thread.setDefaultUncaughtExceptionHandler((ex,ex2)->{
                    thrown[0] = ex2;
                });
                super.run();
            }

        };

        game.getInputHandler().setEnter(true);
        game.getInputHandler().setEscape(true);

        try{
            Thread.sleep(3000);
        }catch (Exception ex){

        }
        if (thrown[0] != null) throw thrown[0];
    }


    /**
     * Tests whether the info menu runs without throwing exceptions
     */
    @Test
    public void testInfoRunning() throws Throwable{
        final Throwable[] thrown = {null};
        Game game = new Game(){
            @Override
            public void run() {
                //if exception is not caught in game, it is caught here instead.
                Thread.setDefaultUncaughtExceptionHandler((ex,ex2)->{
                    thrown[0] = ex2;
                });
                super.run();
            }

        };

        game.getInputHandler().setMenuDown(true);
        game.getInputHandler().setEnter(true);

        try{
            Thread.sleep(3000);
        }catch (Exception ex){

        }
        if (thrown[0] != null) throw thrown[0];
    }



    /**
     * Tests whether the load menu runs without throwing exceptions
     */
    @Test
    public void testLoadRunning() throws Throwable{
        final Throwable[] thrown = {null};
        Game game = new Game(){
            @Override
            public void run() {
                //if exception is not caught in game, it is caught here instead.
                Thread.setDefaultUncaughtExceptionHandler((ex,ex2)->{
                    thrown[0] = ex2;
                });
                super.run();
            }

        };

        try{
            Thread.sleep(50);
        }catch (Exception ex){

        }

        game.getInputHandler().setMenuDown(true);
        try{
            Thread.sleep(50);
        }catch (Exception ex){

        }
        game.getInputHandler().setMenuDown(true);
        game.getInputHandler().setEnter(true);

        try{
            Thread.sleep(3000);
        }catch (Exception ex){

        }
        if (thrown[0] != null) throw thrown[0];
    }


    /**
     * Tests whether the exit menu runs without throwing exceptions
     */
    @Test
    public void testExitRunning() throws Throwable{
        final Throwable[] thrown = {null};
        Game game = new Game(){
            @Override
            public void run() {
                //if exception is not caught in game, it is caught here instead.
                Thread.setDefaultUncaughtExceptionHandler((ex,ex2)->{
                    thrown[0] = ex2;
                });
                super.run();
            }

        };
        try{
            Thread.sleep(50);
        }catch (Exception ex){

        }

        game.getInputHandler().setMenuDown(true);
        try{
            Thread.sleep(50);
        }catch (Exception ex){

        }
        game.getInputHandler().setMenuDown(true);
        try{
            Thread.sleep(50);
        }catch (Exception ex){

        }
        game.getInputHandler().setMenuDown(true);

        try{
            Thread.sleep(3000);
        }catch (Exception ex){

        }

        if (thrown[0] != null) throw thrown[0];
    }


}

