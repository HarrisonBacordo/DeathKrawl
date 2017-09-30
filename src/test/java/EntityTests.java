import Component.ComponentType;
import Component.WeaponComponent;
import ResourceLoader.Resources;
import org.junit.Test;

import Entity.*;

import static org.junit.Assert.*;

public class EntityTests {
    Resources resources = new Resources();
    private Entity entity;

    @Test
    public void testValidEntityInitialization() {
        entity = new NinjaEntity(10, 10, 10, 10);
        assertNotNull("Shouldn't be null, " +
                "as this is a valid initialization", entity);
    }

    @Test
    public void testValidEntityID() {
        for(int i = 2; i < 52; i++) {
            entity = new WallEntity(10, 10, 10, 10);
            assertEquals("Should each be unique", i, entity.getID());
        }
    }

    @Test
    public void testValidAddComponent() {
        entity = new NinjaEntity(10, 10, 10,10);
        assertTrue("Entity should have component",
                entity.getComponents().containsComponentOfType(ComponentType.WEAPON));
    }

    @Test
    public void testInvalidAddComponent() {
        entity = new NinjaEntity(10, 10, 10,10);
        assertFalse("Shouldn't be able to add duplicate components",
                entity.addComponent(new WeaponComponent(entity)));
    }
}
