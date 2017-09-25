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
    public boolean containsComponentOfType(ComponentType componentTypeSearchingFor) {
        for (Component component : components) {
            if (component.getComponentType() == componentTypeSearchingFor) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    /**
     * Adds the passed in component to this ComponentManager
     *
     * @param componentToAdd - component to add to this ComponentManager
     */
    public boolean addComponent(Component componentToAdd) {
        for(Component component : components) {
            if (component.getComponentType().equals(componentToAdd.getComponentType())) {
                return false;
            }
        }
        return components.add(componentToAdd);
    }

    /**
     * Removes the passed in component type from this ComponentManager
     *
     * @param componentToRemove - component type to remove from this ComponentManager
     * @return - if the removal was successful
     */
    public boolean removeComponentOfType(ComponentType componentToRemove) {
        for (Component component : components) {
            if (component.getComponentType() == componentToRemove) {
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

    public void executeComponent(Component component) {
        if (findComponentWithType(component.componentType) != null) {
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
