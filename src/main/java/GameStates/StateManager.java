package GameStates;

import java.awt.*;

/**
 * Created by Sean on 16/09/17.
 */
public class StateManager {

    private MenuState ms;
    private PauseState ps;
    private DeathState ds;
    private VictoryState vs;

    public StateManager(){
        ms = new MenuState();
        ps = new PauseState();
        ds = new DeathState();
        vs = new VictoryState();
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

}
