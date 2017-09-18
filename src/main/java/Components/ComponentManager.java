package Components;

import Entities.KeyInput;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles any dependencies that components need, for example
 * Key input is stored here to be used by components as they cannot get direct access to it by any other means
 *
 * Created by krishna on 2/09/2017.
 */
public class ComponentManager<T extends Component> {
    private List<T> components;
    public static KeyInput keyInput;

    public ComponentManager(KeyInput keyInput){
        components = new ArrayList<>();
    }

    public static void setKeyHandler(KeyInput keyInput){
        ComponentManager.keyInput = keyInput;
    }

}
