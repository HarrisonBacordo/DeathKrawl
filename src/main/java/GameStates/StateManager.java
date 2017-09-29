package GameStates;

import Entity.KeyInput;

import java.awt.*;

/**
 * Created by Sean on 16/09/17.
 */
public class StateManager {

    private MenuState ms;
    private PauseState ps;
    private DeathState ds;
    private VictoryState vs;
    private KeyInput keys;
    private STATE state;

    public StateManager(KeyInput keyInput){
        keys = keyInput;
        ms = new MenuState(keys, this);
        ps = new PauseState(keys, this);
        ds = new DeathState(keys, this);
        vs = new VictoryState(keys, this);
        state = STATE.MENU;
    }

    public void renderSelect(Character c, Graphics g, Graphics2D g2d){
        if (c.equals('v')){
            vs.render(g, g2d);
        }else if (c.equals('p')){
            ps.render(g, g2d);
        }else if (c.equals('d')){
            ds.render(g, g2d);
        }else if (c.equals('m')){
            ms.render(g, g2d);
        }
    }

    public void tickSelect(Character c){
        if (c.equals('v')){
            vs.tick();
        }else if (c.equals('p')){
            ps.tick();
        }else if (c.equals('d')){
            ds.tick();
        }else if (c.equals('m')){
            ms.tick();
        }
    }

    public void setState(STATE state){
        this.state = state;
    }

    public STATE getState(){
        return state;
    }

}
