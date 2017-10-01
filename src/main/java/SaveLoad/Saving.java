package SaveLoad;

import Entity.NinjaEntity;
import LevelGenerator.Level;

import java.io.*;

/**
 * Saving for the game
 * Created by Sean on 17/09/17.
 */
public class Saving {

    public Saving(){
    }

    public void saveGame(Object o){
        try {
            FileOutputStream saveFile = new FileOutputStream("saveFile.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(o);
            save.close();
            saveFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
