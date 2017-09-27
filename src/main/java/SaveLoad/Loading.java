package SaveLoad;

import LevelGenerator.Level;

import java.io.*;

/**
 * Created by Sean on 17/09/17.
 */
public class Loading {
    private Level l;
    public Loading(){
    }

    public void loadGame(String s){
        try {

            FileInputStream saveFile = new FileInputStream(s);
            ObjectInputStream restore = new ObjectInputStream(saveFile);
            Object o = (Level) restore.readObject();
            l = (Level) o;
            restore.close();
            saveFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("class");
            e.printStackTrace();
        }
    }

    public int getHeight(){
        return l.getRoomHeight();
    }

    public int getWidth(){
        return l.getRoomWidth();
    }

    public int getScale(){
        return l.getScale();
    }

}
