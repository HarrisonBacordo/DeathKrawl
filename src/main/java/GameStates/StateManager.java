package GameStates;

import Entity.KeyInput;

import java.awt.*;

/**
 * StateManager manages all of the states and their transitions.
 * Created by Sean on 16/09/17.
 */
public class StateManager {

    private MenuState menuState;
    private PauseState pauseState;
    private DeathState deathState;
    private VictoryState victoryState;
    private KeyInput keys;
    private STATE state;
    protected static int screenWidth;
    protected static int screenHeight;

    public StateManager(KeyInput keyInput, int windowHeight, int windowWidth){
        keys = keyInput;
        this.menuState = new MenuState(keys, this);
        this.pauseState = new PauseState(keys, this);
        this.deathState = new DeathState(keys, this);
        this.victoryState = new VictoryState(keys, this);
        this.screenWidth = windowWidth;
        this.screenHeight = windowHeight;
        state = STATE.MENU;
    }

    /**
     * Selects which state to render
     * @param c what state to render
     * @param g
     * @param g2d
     */
    public void renderSelect(Character c, Graphics g, Graphics2D g2d){
        if (c.equals('v')){
            victoryState.render(g, g2d);
        }else if (c.equals('p')){
            pauseState.render(g, g2d);
        }else if (c.equals('d')){
            deathState.render(g, g2d);
        }else if (c.equals('m')){
            menuState.render(g, g2d);
        }
    }

    /**
     * Selects which state should be ticking
     * @param c what state to tick
     */
    public void tickSelect(Character c){
        if (c.equals('v')){
            victoryState.tick();
        }else if (c.equals('p')){
            pauseState.tick();
        }else if (c.equals('d')){
            deathState.tick();
        }else if (c.equals('m')){
            menuState.tick();
        }
    }

    public void setState(STATE state){
        this.state = state;
    }

    public STATE getState(){
        return state;
    }

}
