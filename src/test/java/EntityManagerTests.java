import Entity.Entity;
import Entity.EntityManager;
import Entity.EntityType;
import Entity.NinjaEntity;
import Component.Bullet;
import ResourceLoader.Resources;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EntityManagerTests {
    Resources resources = new Resources();

    private final int ENTITY_LIST_SIZE = 10;
    private EntityManager entityManager;

    @Test
    public void testValidEntityManagerInitialization() {
        entityManager = new EntityManager();
        assertTrue("Should return true for being empty", entityManager.isEmpty());
    }

    @Test
    public void testValidAddToEntityManager() {
        entityManager = new EntityManager();
        NinjaEntity entity = new NinjaEntity(10, 10, 10, 10);
        assertTrue("Should return true for adding entity", entityManager.addEntity(entity));
    }

    @Test
    public void testInvalidAddToEntityManager() {
//        -------------- TRIES TO ADD DUPLICATE ----------------
        entityManager = new EntityManager();
        NinjaEntity entity = new NinjaEntity(10, 10, 10, 10);
        entityManager.addEntity(entity);
        assertFalse("Should return false for adding  duplicate entity", entityManager.addEntity(entity));
    }


    @Test
    public void testValidAddAllEntitiesToManager() {
        entityManager = new EntityManager();
        List<Entity> entitiesToAdd = fillListWithEntities();
        assertTrue("Should return true for adding list of entities",
                entityManager.addAllEntities(entitiesToAdd));
    }

    @Test
    public void testValidEntityManagerContains() {
        entityManager = new EntityManager();
        NinjaEntity entity = new NinjaEntity(10, 10, 10, 10);
        entityManager.addEntity(entity);
        assertTrue("Should return true for containing entity",
                entityManager.containsEntity(entity.getID()));
    }

    @Test
    public void testInvalidEntityManagerContains() {
//        -------------- TRIES TO FIND Entity NOT IN EntityManager ----------------
        entityManager = new EntityManager();
        NinjaEntity entity1 = new NinjaEntity(10, 10, 10, 10);
        NinjaEntity entity2 = new NinjaEntity(10, 10, 10, 10);
        entityManager.addEntity(entity1);
        assertFalse("Should return false for not containing entity",
                entityManager.containsEntity(entity2.getID()));
    }

    @Test
    public void testValidEntityManagerSize() {
        populateEntityManager();
        assertEquals("Size of entityManager must be 10", ENTITY_LIST_SIZE, entityManager.size());
    }

    @Test
    public void testValidRemoveFromEntityManagerViaObject() {
        entityManager = new EntityManager();
        Entity entity = new NinjaEntity(10, 10, 10, 10);
        entityManager.addEntity(entity);
        int initialSize = entityManager.size();

        entityManager.removeEntity(entity);
        assertEquals("Should return 0 for size after removing entity",
                initialSize - 1, entityManager.size());
    }

    @Test
    public void testInvalidRemoveFromEntityManagerViaObject() {
//        -------------- TRIES TO REMOVE Entity NOT IN EntityManager ----------------
        entityManager = new EntityManager();
        Entity entity1 = new NinjaEntity(10, 10, 10, 10);
        Entity entity2 = new NinjaEntity(10, 10, 10, 10);
        entityManager.addEntity(entity1);
        int initialSize = entityManager.size();

        entityManager.removeEntity(entity2);
        assertEquals("Should return 0 for size after removing entity",
                initialSize, entityManager.size());
    }

    @Test
    public void testValidFindEntitiesWithTypeInEntityManager() {
        populateDiverseEntityManager();
        List<Entity> ninjaEntitiesList = entityManager.getEnemiesWithType(EntityType.PLAYER);
        assertEquals("Size should be 5 for ninja entities in EntityManager",
                5, ninjaEntitiesList.size());
        for (Entity entity : ninjaEntitiesList) {
            assertEquals("Should be list of ninja entities",
                    EntityType.PLAYER, entity.getEntityType());
        }
    }


    /**
     * Populates an EntityManager with 10 entities for testing purposes
     */
    private void populateEntityManager() {
        entityManager = new EntityManager();
        for (int i = 0; i < ENTITY_LIST_SIZE; i++) {
            entityManager.addEntity(new NinjaEntity(10, 10, 10, 10));
        }
    }

    /**
     * Populates an EntityManager with 10 diverse entities for testing purposes
     */
    private void populateDiverseEntityManager() {
        entityManager = new EntityManager();
        for (int i = 0; i < ENTITY_LIST_SIZE; i++) {
            if (i % 2 == 0) {
                entityManager.addEntity(new Bullet(10, 10, 10, 10, EntityType.DEFAULT_BULLET));
            } else {
                entityManager.addEntity(new NinjaEntity(10, 10, 10, 10));
            }
        }
    }

    /**
     * Fills a list with 10 entities for testing purposes
     *
     * @return - the filled list of entities
     */
    private List<Entity> fillListWithEntities() {
        List<Entity> entitiesToAdd = new ArrayList<Entity>();
        for (int i = 0; i < ENTITY_LIST_SIZE; i++) {
            entitiesToAdd.add(new NinjaEntity(10, 10, 10, 10));
        }
        return entitiesToAdd;
    }
}
