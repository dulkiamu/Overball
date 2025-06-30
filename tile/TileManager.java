package tile;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import main.GamePanel;
import java.io.FileInputStream;

public class TileManager
{
    GamePanel gp;
    Tile[] tile;
    
    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        
        tile = new Tile[10];
        getTileImage();
        
        
    }
    public void getTileImage(){
        
        try{
             
             tile[0]=new Tile();
             tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
              
             tile[1]=new Tile();
             tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/waves.png"));
             
             tile[2]=new Tile();
             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
             
             tile[3]=new Tile();
             tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/clouds.png"));
             
             tile[4]=new Tile();
             tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/netz.png"));
            //System.out.println("Erfolg Bild");
        
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(tile[3].image,x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            
            if(col == gp.maxScreenCol)
            {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
