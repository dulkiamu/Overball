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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TileManager
{
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[] [];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        //loadMap("/map/map01.txt");

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
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/netzbottom.png"));
            tile[4].collision = true;
            
            tile[5]=new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/netztop.png"));
            tile[5].collision = true;
            // System.out.println("Erfolg Bild");

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            

        }
    }*/

    public void draw(Graphics2D g2)
    {
        g2.drawImage(tile[4].image, 704, 576, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[4].image, 704, 640, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[4].image, 704, 704, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[4].image, 704, 512, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[5].image, 704, 448, gp.tileSize, gp.tileSize, null);
    }
}
