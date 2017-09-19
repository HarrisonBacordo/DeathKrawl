package Component;

import Entity.KeyInput;

import java.util.ArrayList;
import java.util.List;

public class ComponentManager {
    private List<Component> components;
    public static KeyInput keyInput;

    public ComponentManager() {
        components = new ArrayList<>();
    }

    /**
     * Checks if this ComponentManger has the passed in component
     *
     * @param componentTypeSearchingFor - component to search for
     * @return - if this ComponentManager contains the passed in component
     */
    public boolean hasComponentOfType(ComponentType componentTypeSearchingFor) {
        for (Component component : components) {
            if (component.getComponentType() == componentTypeSearchingFor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the passed in component to this ComponentManager
     *
     * @param componentToAdd - component to add to this ComponentManager
     */
    public void addComponent(Component componentToAdd) {
        components.add(componentToAdd);
    }

    /**
     * Removes the passed in component from this ComponentManager
     *
     * @param componentToRemove - component to remove from this ComponentManager
     * @return - if the removal was successful
     */
    public boolean removeComponent(Component componentToRemove) {
        for (Component component : components) {
            if (component.getComponentType() == componentToRemove.getComponentType()) {
                return components.remove(component);
            }
        }
        return false;
    }

    /**
     * Removes the component at the given index
     *
     * @param indexOfComponent - index of component to remove
     * @return - the component that was removed
     */
    public Component removeComponent(int indexOfComponent) {
        return components.remove(indexOfComponent);
    }

    /**
     * Returns the component that matches the given ComponentType
     *
     * @param componentType - type of component to look for
     * @return - the component that matches the given ComponentType
     */
    public Component findComponentWithType(ComponentType componentType) {
        for (Component component : components) {
            if (component.getComponentType() == componentType) {
                return component;
            }
        }
        return null;
    }

    public Component getComponent(Component componentToReturn) {
        for (Component component : components) {
            if (component.getClass() == componentToReturn.getClass()) {
                return component;
            }
        }
        return null;
    }

    public void executeComponent(Component component) {
        if (getComponent(component) != null) {
            component.execute();
        }
    }

    public void executeAllComponents() {
        for (Component component : components) {
            component.execute();
        }
    }

    public static void setKeyHandler(KeyInput keyInput) {
        ComponentManager.keyInput = keyInput;
    }

}
