package Camera;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by Sean on 26/09/17.
 */
public class CameraTests {

    @Test
    public void testCreate1(){
        //Generate mock input
        String input = "create A 0" + "\npass";
        //Generate input stream
        InputStream in = new ByteArrayInputStream(input.getBytes());
        //Make program read from mock string
        System.setIn(in);
        //Set game to true so testing functions works correctly

    }
}
