import Entity.*;
import ResourceLoader.Resources;
import org.junit.Test;

import java.awt.*;
import Component.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ComponentTests {

    Resources r = new Resources();
    @Test
    public void testValid() {
        assertEquals(1, 1);
    }

    //Sean's Tests//

    @Test(expected = ClassCastException.class)
    public void testInValidHealthCreation(){
       HealthComponent hc = new HealthComponent(new SeaFloorEntity(1,1,1,1, EntityType.FLOOR));
    }

    @Test
    public void testValidHealthCreation(){
        HealthComponent hc = new HealthComponent(new NinjaEntity(1,1,1,1));
        assertNotNull(hc);
    }

    @Test
    public void testValidInputCreation(){
        InputComponent ic = new InputComponent(new NinjaEntity(1,1,1,1), new KeyInput());
        assertNotNull(ic);
    }

    @Test(expected = ClassCastException.class)
    public void testInValidKnockbackCreation(){
        KnockbackComponent kc = new KnockbackComponent(new WallEntity(1,1,1,1));

    }

    @Test
    public void testValidKnockbackCreation(){
        KnockbackComponent kc = new KnockbackComponent(new NinjaEntity(1,1,1,1));
        assertNotNull(kc);

    }

    @Test(expected = ClassCastException.class)
    public void testInValidWeaponCreation(){
        WeaponComponent wc = new WeaponComponent(new SeaFloorEntity(1,1,1,1,EntityType.FLOOR));
        assertNotNull(wc);
    }

    @Test
    public void testValidWeaponCreation(){
        WeaponComponent wc = new WeaponComponent(new NinjaEntity(1,1,1,1));
        assertNotNull(wc);
    }

    @Test
    public void testMeleeBuilder(){
        MeleeBuilder mb = new MeleeBuilder(new NinjaEntity(1,1,1,1));
        assertNotNull(mb);
    }

    @Test
    public void testBulletBuilder(){
        BulletBuilder bb = new BulletBuilder(new NinjaEntity(1,1,1,1));
        assertNotNull(bb);
    }
}
