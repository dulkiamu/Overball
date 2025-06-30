package entity;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player extends Entity
{
    GamePanel gp;
    KeyHandler keyH;
    
    
    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH; 
        int size = gp.tileSize;
        
        setDefaultvalues();
        getPlayerImage();
    }
    
    public void setDefaultvalues()
    {
        x = 220;
        y = 220;
        speed = 9;
        direction = "up";
    }
    
    public void getPlayerImage(){
        
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2-1.png.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
            System.out.println("Erfolg Bild");
        
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update()
    {
        if(keyH.upPressed == true){
            direction = "up";
            y = y - speed;
        }
        else if(keyH.downPressed == true ){
            direction = "down";
            y = y + speed;
        }
        else if(keyH.leftPressed == true ){
            direction = "left";
            x = x - speed;
        }
        else if(keyH.rightPressed == true ){
            direction = "right";
            x = x + speed;
        }
    }
    
    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize); // (x, y, weidth, height)
        BufferedImage image = null;
        switch(direction)
        {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;    
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

