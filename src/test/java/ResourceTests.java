import ResourceLoader.Loader;
import ResourceLoader.Resources;
import org.junit.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.Buffer;


/**
 * Created by Sean on 26/09/17.
 */
public class ResourceTests {

    @Test
    public void test1LoadImage(){
        Resources r = new Resources();
        BufferedImage bi = Resources.getImage("SpawnRoom");
        Assert.assertNotNull(bi);
    }

    @Test
    public void test2LoadImage(){
        Resources r = new Resources();
        BufferedImage bi = Resources.getImage("FT");
        Assert.assertNotNull(bi);
    }

    @Test
    public void testLoadImageNull(){
        Resources r = new Resources();
        BufferedImage bi = Resources.getImage("testNull");
        Assert.assertNull(bi);
    }

    @Test
    public void testLoader1(){
        BufferedImage bi = Loader.loadImage("/Enviroment/wallTile.png");
        Assert.assertNotNull(bi);
    }

    @Test
    public void testLoader2(){
        BufferedImage bi = Loader.loadImage("/Rooms/RoomVar1.png");
        Assert.assertNotNull(bi);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullLoader(){
        Assert.assertNull(Loader.loadImage("/Rooms/what.png"));
    }
}
