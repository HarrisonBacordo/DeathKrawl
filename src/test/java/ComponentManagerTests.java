import Component.Component;
import Component.ComponentManager;
import Component.ComponentType;
import Component.ShootComponent;
import Component.InputComponent;
import Entity.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComponentManagerTests {
    private ComponentManager componentManager;
    private Entity entity = new NinjaEntity(10, 10, 10, 10);

    @Test
    public void testValidComponentManagerInitialization() {
        componentManager = new ComponentManager();
        assertTrue("Should return true for being empty", componentManager.isEmpty());
    }

    @Test
    public void testValidAddToComponentManager() {
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        assertTrue("Should return true for adding component", componentManager.addComponent(component));
    }

    @Test
    public void testInvalidAddToComponentManager() {
//        -------------- TRIES TO ADD DUPLICATE ----------------
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component);
        assertFalse("Should return false for adding duplicate component", componentManager.addComponent(component));
    }

    @Test
    public void testValidComponentManagerContains() {
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component);
        assertTrue("Should return true for containing component",
                componentManager.containsComponentOfType(component.getComponentType()));
    }

    @Test
    public void testInvalidComponentManagerContains() {
//        -------------- TRIES TO FIND Component NOT IN ComponentManager ----------------
        componentManager = new ComponentManager();
        Component component1 = new ShootComponent(entity, ComponentType.SHOOT);
        Component component2 = new InputComponent(entity, new KeyInput());
        componentManager.addComponent(component1);
        assertFalse("Should return false for not containing component",
                componentManager.containsComponentOfType(component2.getComponentType()));
    }

    @Test
    public void testValidRemoveFromComponentManagerViaIndex() {
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component);
        assertNotNull("Should return not null for removing in valid index",
                componentManager.removeComponent(0));
    }

    @Test
    public void testValidRemoveFromComponentManagerViaObject() {
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component);
        assertTrue("Should return true for removing valid component",
                componentManager.removeComponentOfType(ComponentType.SHOOT));
    }

    @Test
    public void testInvalidRemoveFromComponentManagerViaObject() {
//        -------------- TRIES TO REMOVE Component NOT IN ComponentManager ----------------
        componentManager = new ComponentManager();
        Component component1 = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component1);
        assertFalse("Should return false for trying to remove nonexistent component",
                componentManager.removeComponentOfType(ComponentType.INPUT));
    }

    @Test
    public void testValidFindComponentOfTypeInComponentManager() {
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component);
        assertNotNull("Should return not null for returning valid component in ComponentManager",
                componentManager.findComponentWithType(ComponentType.SHOOT));
    }

    @Test
    public void testInvalidFindComponentOfTypeInComponentManager() {
        componentManager = new ComponentManager();
        Component component = new ShootComponent(entity, ComponentType.SHOOT);
        componentManager.addComponent(component);
        assertNull("Should return null for returning valid component in ComponentManager",
                componentManager.findComponentWithType(ComponentType.INPUT));
    }
}
