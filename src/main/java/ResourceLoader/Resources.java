package ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Sean on 16/09/17.
 */
public class Resources {

    private static HashMap<String, BufferedImage> assets;
    private static final int width = 10, height = 10;
    private Loader imgLoader;
    private Font f;
    private int variations = 5;

    public Resources(){
        //SpriteLoader sprites = new SpriteLoader(imgLoader.loadImage("/assets/Wheat.png"));
        loadAssets();
    }

    public void loadAssets(){
        assets = new HashMap<>();

        f = Loader.loadFont("/Font/Crucifixion-Regular.tff");


        //load the image, break down with sprite, extract here
        assets.put("SpawnRoom", Loader.loadImage("/Rooms/SpawnRoomWDoor.png"));

        //Adds all variations
        for(int i = 0; i < variations; i++) {
            assets.put("Room" + (i + 1), Loader.loadImage("/Rooms/RoomVar" + (i + 1) + ".png"));
        }
        assets.put("FT", Loader.loadImage("/Enviroment/floorTile.png"));

        assets.put("WT", Loader.loadImage("/Enviroment/wallTile.png"));
        assets.put("WTL", Loader.loadImage("/Enviroment/wallTileLeft.png"));
        assets.put("WTR", Loader.loadImage("/Enviroment/wallTileRight.png"));
        assets.put("WTB", Loader.loadImage("/Enviroment/wallTileBottom.png"));

        assets.put("FSL", Loader.loadImage("/Enviroment/floorShadowLeft.png"));
        assets.put("FSR", Loader.loadImage("/Enviroment/floorShadowRight.png"));
        assets.put("FS", Loader.loadImage("/Enviroment/floorShadow.png"));
        assets.put("SEA", Loader.loadImage("/Enviroment/seaTile.png"));
        assets.put("SEAT", Loader.loadImage("/Enviroment/seaTileTop.png"));
        assets.put("SEAL", Loader.loadImage("/Enviroment/seaTileLeft.png"));
        assets.put("SEATL", Loader.loadImage("/Enviroment/seaTileTopLeftCorner.png"));
        assets.put("SEATR", Loader.loadImage("/Enviroment/seaTileTopRightCorner.png"));
    }

    public static HashMap<String, BufferedImage> getAssets() {
        return assets;
    }

    /**
     * getImage: get image from map of resource images
     * @param s name of image to retrieve
     * @return BufferedImage
     */
    public static BufferedImage getImage(String s){
        BufferedImage b = assets.get(s);
        return b;
    }

    public Font getF() {
        return f;
    }
}
