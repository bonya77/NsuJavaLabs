package ru.nsu.naboka.view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    private static BufferedImage playerSprite;
    private static BufferedImage machineSprite;
    private static BufferedImage backgroundTileSprite;
    private static BufferedImage treeSprite;
    private static BufferedImage stumpSprite;
    public static void loadresources(){
        try{
            playerSprite = ImageIO.read(ResourceManager.class.getResource("/sprites/player.png"));
            machineSprite = ImageIO.read(ResourceManager.class.getResource("/sprites/machine.png"));
            backgroundTileSprite = ImageIO.read(ResourceManager.class.getResource("/sprites/backgroundTile.png"));
            treeSprite = ImageIO.read(ResourceManager.class.getResource("/sprites/tree.png"));
            stumpSprite = ImageIO.read(ResourceManager.class.getResource("/sprites/stump.png"));
        }
        catch(IOException e){
            System.out.println("cannot find image, check path");
        }
    }

    public static BufferedImage getPlayerSprite(){
        return playerSprite;
    }
    public static BufferedImage getMachineSprite(){return machineSprite; }
    public static BufferedImage getBackgroundTileSpriteSprite(){return backgroundTileSprite; }
    public static BufferedImage getTreeSprite(){ return treeSprite; }
    public static BufferedImage getStumpSprite(){ return stumpSprite; }
}