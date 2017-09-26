import Component.ComponentType;
import Component.ShootComponent;
import org.junit.Test;

import Entity.*;

import static org.junit.Assert.*;

public class EntityTests {
    private Entity entity;

    @Test
    public void testValidEntityInitialization() {
        entity = new NinjaEntity(10, 10, 10, 10);
        assertNotNull("Shouldn't be null, " +
                "as this is a valid initialization", entity);
    }

    @Test
    public void testValidEntityID() {
        EntityManager entityManager = new EntityManager();
        for(int i = 0; i < 50; i++) {
            entity = new NinjaEntity(10, 10, 10, 10);
            entityManager.addEntity(entity);
            assertEquals("Should each be unique", i, entity.getID());
        }
    }

    @Test
    public void testValidAddComponent() {
        entity = new NinjaEntity(10, 10, 10,10);
        entity.addComponent(new ShootComponent(entity, ComponentType.SHOOT));
        assertTrue("Entity should have component",
                entity.getComponents().containsComponentOfType(ComponentType.SHOOT));
    }

    @Test
    public void testInvalidAddComponent() {
        entity = new NinjaEntity(10, 10, 10,10);
        entity.addComponent(new ShootComponent(entity, ComponentType.SHOOT));
        assertFalse("Shouldn't be able to add duplicate components",
                entity.addComponent(new ShootComponent(entity, ComponentType.SHOOT)));
    }
}
