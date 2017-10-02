import Entity.EntityType;
import Item.*;
import ResourceLoader.Resources;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;


public class ItemTests {

    private final int cellWidth = 32;
    private final int cellHeight = 32;

    public ItemTests(){
        init();
    }

    @Test
    //create an item, then check it's type and it's collision
    public void createSword(){
        Sword sword = new Sword(0,0, cellWidth , cellHeight, EntityType.SWORD);

        //check the type of the sword is sword
        assertTrue("Sword is of type sword.", sword.getEntityType().equals(EntityType.SWORD));

        //check the item can collide
        assertTrue("Item can be collided with.", sword.isCollidable);

    }

    @Test
    //create an item, then check it's type and it's collision
    public void createAssualRifle(){
        AssaultRifle rifle = new AssaultRifle(0,0, cellWidth , cellHeight, EntityType.ASSAULT_RIFLE);

        //check the type of the item is correct
        assertTrue("Item is the correct type.", rifle.getEntityType().equals(EntityType.ASSAULT_RIFLE));

        //check the item can collide
        assertTrue("Item can be collided with.", rifle.isCollidable);

    }

    @Test
    //create an item, then check it's type and it's collision
    public void createHeart(){
        Heart heart = new Heart(0,0, cellWidth , cellHeight, EntityType.HEART);

        //check the type of the item is correct
        assertTrue("Item is the correct type.", heart.getEntityType().equals(EntityType.HEART));

        //check the item can collide
        assertTrue("Item can be collided with.", heart.isCollidable);

    }

    @Test
    //create an item, then check it's type and it's collision
    public void createPistol(){
        Pistol pistol = new Pistol(0,0, cellWidth , cellHeight, EntityType.PISTOL);

        //check the type of the item is correct
        assertTrue("Item is the correct type.", pistol.getEntityType().equals(EntityType.PISTOL));

        //check the item can collide
        assertTrue("Item can be collided with.", pistol.isCollidable);

    }

    @Test
    //create an item, then check it's type and it's collision
    public void createShield(){
        Shield shield = new Shield(0,0, cellWidth , cellHeight, EntityType.SHIELD);

        //check the type of the item is correct
        assertTrue("Item is the correct type.", shield.getEntityType().equals(EntityType.SHIELD));

        //check the item can collide
        assertTrue("Item can be collided with.", shield.isCollidable);

    }

    @Test
    //create an item, then check it's type and it's collision
    public void createShotgun(){
        Shotgun shotgun = new Shotgun(0,0, cellWidth , cellHeight, EntityType.SHOTGUN);

        //check the type of the item is correct
        assertTrue("Item is the correct type.", shotgun.getEntityType().equals(EntityType.SHOTGUN));

        //check the item can collide
        assertTrue("Item can be collided with.", shotgun.isCollidable);

    }

    @Test
    //create an item, then check it's type and it's collision
    public void createSpeedboost(){
        SpeedBoost boost = new SpeedBoost(0,0, cellWidth , cellHeight, EntityType.SPEEDBOOST);

        //check the type of the item is correct
        assertTrue("Item is the correct type.", boost.getEntityType().equals(EntityType.SPEEDBOOST));

        //check the item can collide
        assertTrue("Item can be collided with.", boost.isCollidable);

    }

    private void init(){
        //need the images to load the items
        Resources resources = new Resources();
    }


}
