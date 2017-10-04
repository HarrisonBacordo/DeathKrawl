package ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;

/**
 * Main assets class that loads all the resources.
 * Created by Sean on 16/09/17.
 */
public class Resources {

    private static HashMap<String, BufferedImage> assets;
    private static BufferedImage[] playButton, quitButton, infoButton, loadButton, saveButton;
    private int variations = 6;

    public Resources(){
        loadAssets();
    }

    /**
     * Ran at the start of the game, this loads everything.
     * Eventually will split into many methods and have file reader.
     */
    public void loadAssets(){
        assets = new HashMap<>();
        //Creating all the animations
        playButton = new BufferedImage[12];
        quitButton = new BufferedImage[12];
        infoButton = new BufferedImage[12];
        loadButton = new BufferedImage[12];
        saveButton = new BufferedImage[12];
//        -------------------- MENU ANIMATION PNG --------------------
        for(int i = 0; i<12;i++){
            playButton[i] = Loader.loadImage("/Menu/playAni/playAni"+(i+1)+".png");
        }
        for(int i = 0; i<12;i++){
            quitButton[i] = Loader.loadImage("/Menu/quitAni/quitAni"+(i+1)+".png");
        }
        for(int i = 0; i<12;i++){
            infoButton[i] = Loader.loadImage("/Menu/infoAni/infoAni"+(i+1)+".png");
        }
        for(int i = 0; i<12;i++){
            loadButton[i] = Loader.loadImage("/Menu/loadAni/loadAni"+(i+1)+".png");
        }
        for(int i = 0; i<12;i++){
            saveButton[i] = Loader.loadImage("/Menu/saveAni/saveAni"+(i+1)+".png");
        }
        assets.put("mainT", Loader.loadImage("/Menu/titles/mainTitle.png"));
        assets.put("pauseT", Loader.loadImage("/Menu/titles/pauseTitle.png"));

//        -------------------- DOOR, BOSSROOM, PLAYER PNG --------------------
        assets.put("SpawnRoom", Loader.loadImage("/Rooms/SpawnRoomWDoor.png"));
        assets.put("BossRoom", Loader.loadImage("/Rooms/BossRoomSmall.png"));
        assets.put("Player", Loader.loadImage("/player.png"));

//        -------------------- ROOM VARIATION PNG --------------------
        for(int i = 0; i < variations; i++) {
            assets.put("Room" + (i + 1), Loader.loadImage("/Rooms/RoomVar" + (i + 1) + ".png"));
        }

//        -------------------- FLOOR PNG --------------------
        assets.put("FT", Loader.loadImage("/Enviroment/floorTile.png"));

//        -------------------- WALL PNG --------------------
        assets.put("WT", Loader.loadImage("/Enviroment/wallTile.png"));
        assets.put("WTL", Loader.loadImage("/Enviroment/wallTileLeft.png"));
        assets.put("WTR", Loader.loadImage("/Enviroment/wallTileRight.png"));
        assets.put("WTB", Loader.loadImage("/Enviroment/wallTileBottom.png"));

//        -------------------- FLOOR SHADOW PNG --------------------
        assets.put("FSL", Loader.loadImage("/Enviroment/floorShadowLeft.png"));
        assets.put("FSR", Loader.loadImage("/Enviroment/floorShadowRight.png"));
        assets.put("FS", Loader.loadImage("/Enviroment/floorShadow.png"));

//        -------------------- SEA TILE PNG --------------------
        assets.put("SEA", Loader.loadImage("/Enviroment/seaTile.png"));
        assets.put("SEAT", Loader.loadImage("/Enviroment/seaTileTop.png"));
        assets.put("SEAL", Loader.loadImage("/Enviroment/seaTileLeft.png"));
        assets.put("SEAR", Loader.loadImage("/Enviroment/seaTileRight.png"));
        assets.put("SEAB", Loader.loadImage("/Enviroment/seaTileBottom.png"));
        assets.put("SEABL", Loader.loadImage("/Enviroment/seaTileBottomLeft.png"));
        assets.put("SEABR", Loader.loadImage("/Enviroment/seaTileBottomRight.png"));
        assets.put("SEATL", Loader.loadImage("/Enviroment/seaTileTopLeftCorner.png"));
        assets.put("SEATR", Loader.loadImage("/Enviroment/seaTileTopRightCorner.png"));

//        -------------------- ITEMS PNG --------------------
        assets.put("PISTOL", Loader.loadImage("/HUD/defaultGun.png"));
        assets.put("SHOTGUN", Loader.loadImage("/HUD/shotgun.png"));
        assets.put("HEART", Loader.loadImage("/HUD/heart.png"));
        assets.put("SWORD", Loader.loadImage("/HUD/sword.png"));
        assets.put("SHIELD", Loader.loadImage("/Items/SHEILD.png"));
        assets.put("ASSAULT_RIFLE", Loader.loadImage("/Items/ASSAULT_RIFLE.png"));
        assets.put("SPEEDBOOST", Loader.loadImage("/Items/SPEEDBOOST 2.png"));

//        -------------------- SWORD SLASH PNG --------------------
        assets.put("SLASHT", Loader.loadImage("/Sword/slashTop.png"));
        assets.put("SLASHB", Loader.loadImage("/Sword/slashBottom.png"));
        assets.put("SLASHL", Loader.loadImage("/Sword/slashLeft.png"));
        assets.put("SLASHR", Loader.loadImage("/Sword/slashRight.png"));

//        -------------------- MUZZLE FLASH PNG --------------------
        assets.put("MUZZLE", Loader.loadImage("/MuzzleFlash/muzzleFlash.png"));
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
        return assets.get(s);
    }

    public static BufferedImage[] getPlayButton() {
        return playButton;
    }

    public static BufferedImage[] getQuitButton() {
        return quitButton;
    }

    public static BufferedImage[] getInfoButton() {
        return infoButton;
    }

    public static BufferedImage[] getLoadButton() {
        return loadButton;
    }

    public static BufferedImage[] getSaveButton() {
        return saveButton;
    }
}
