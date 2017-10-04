import Entity.KeyInput;
import GameStates.STATE;
import GameStates.StateManager;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sean on 26/09/17.
 */
public class GameStatesTests {

    @Test
    public void testMenu(){
        StateManager st = new StateManager(new KeyInput(), Game.WINDOW_HEIGHT, Game.WINDOW_WIDTH);
        st.setState(STATE.MENU);
        Assert.assertTrue(st.getState() == STATE.MENU);
    }

    @Test
    public void testPause(){
        StateManager st = new StateManager(new KeyInput(), Game.WINDOW_HEIGHT, Game.WINDOW_WIDTH);
        st.setState(STATE.PAUSE);
        Assert.assertTrue(st.getState() == STATE.PAUSE);
    }


    @Test
    public void testVictory(){
        StateManager st = new StateManager(new KeyInput(), Game.WINDOW_HEIGHT, Game.WINDOW_WIDTH);
        st.setState(STATE.VICTORY);
        Assert.assertTrue(st.getState() == STATE.VICTORY);
    }


    @Test
    public void testDeath(){
        StateManager st = new StateManager(new KeyInput(), Game.WINDOW_HEIGHT, Game.WINDOW_WIDTH);
        st.setState(STATE.DEATH);
        Assert.assertTrue(st.getState() == STATE.DEATH);
    }

}
