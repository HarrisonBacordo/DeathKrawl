import Entity.KeyInput;
import org.junit.*;
import org.junit.Assert;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sean on 26/09/17.
 */
public class KeyTests{

    @Test
    public void testKeyPressed1(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'W');
        c.getKeyListeners()[0].keyPressed(e);
        Assert.assertTrue(inputHandler.isUp());
    }

    @Test
    public void testKeyPressed2(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S');
        c.getKeyListeners()[0].keyPressed(e);
        Assert.assertTrue(!inputHandler.isUp());
    }

    @Test
    public void testKeyPressed3(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S');
        c.getKeyListeners()[0].keyPressed(e);
        e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
        c.getKeyListeners()[0].keyPressed(e);
        Assert.assertTrue(inputHandler.isDown());
        Assert.assertTrue(inputHandler.isLeft());
    }

    @Test
    public void testKeyReleased1(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'W');
        c.getKeyListeners()[0].keyReleased(e);
        Assert.assertFalse(inputHandler.isUp());
    }

    @Test
    public void testKeyReleased2(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'S');
        c.getKeyListeners()[0].keyReleased(e);
        Assert.assertFalse(inputHandler.isUp());
    }

    @Test
    public void testKeyReleased3() {
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        c.getKeyListeners()[0].keyReleased(e);
        e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        c.getKeyListeners()[0].keyReleased(e);
        Assert.assertFalse(inputHandler.isDown());
        Assert.assertFalse(inputHandler.isLeft());
    }

    @Test
    public void testKeyPressRelease1(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        c.getKeyListeners()[0].keyPressed(e);
        Assert.assertTrue(inputHandler.isRight());
        e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        c.getKeyListeners()[0].keyReleased(e);
        Assert.assertFalse(inputHandler.isRight());
    }

    @Test
    public void testKeyPressRelease2(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        c.getKeyListeners()[0].keyPressed(e);
        Assert.assertTrue(inputHandler.isLeft());
        e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        c.getKeyListeners()[0].keyReleased(e);
        Assert.assertFalse(inputHandler.isDown());
    }

    @Test
    public void testMassPress(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        c.getKeyListeners()[0].keyPressed(e);

        e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        c.getKeyListeners()[0].keyPressed(e);

        e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        c.getKeyListeners()[0].keyPressed(e);

        e = new KeyEvent(c, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        c.getKeyListeners()[0].keyPressed(e);
        Assert.assertTrue(inputHandler.isLeft());
        Assert.assertTrue(inputHandler.isUp());
        Assert.assertTrue(inputHandler.isDown());
        Assert.assertTrue(inputHandler.isRight());
    }

    @Test
    public void testMassRelease(){
        KeyInput inputHandler = new KeyInput();
        Component c = new Component() {};
        c.addKeyListener(inputHandler);
        KeyEvent e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A');
        c.getKeyListeners()[0].keyReleased(e);

        e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        c.getKeyListeners()[0].keyReleased(e);

        e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D');
        c.getKeyListeners()[0].keyReleased(e);

        e = new KeyEvent(c, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        c.getKeyListeners()[0].keyReleased(e);
        Assert.assertFalse(inputHandler.isLeft());
        Assert.assertFalse(inputHandler.isUp());
        Assert.assertFalse(inputHandler.isDown());
        Assert.assertFalse(inputHandler.isRight());
    }
}
