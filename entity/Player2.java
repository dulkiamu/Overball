package entity;
import main.GamePanel;
import main.KeyHandler2;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player2 extends Entity
{
    GamePanel gp;
    KeyHandler2 keyH;
    
    
    public Player2(GamePanel gp, KeyHandler2 keyH)
    {
        this.gp = gp;
        this.keyH = keyH; 
        int size = 128;
        
        setDefaultvalues();
        getPlayerImage();
    }
    
    public void setDefaultvalues()
    {
        x = 1200;
        y = 220;
        speed = 10;
        direction = "up";
    }
    
    public void getPlayerImage(){
        
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player2_up_1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player2_down_1.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player2_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player2_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player2_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player2_right_2.png"));
            //System.out.println("Erfolg Bild");
        
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update()
    {
        int newX = x;
        int newY = y;
        
        if(keyH.upPressed == true){
            direction = "up";
            newY = y - speed;
        }
        else if(keyH.downPressed == true ){
            direction = "down";
            newY = y + speed;
        }
        else if(keyH.leftPressed == true ){
            direction = "left";
            newX = x - speed;
        }
        else if(keyH.rightPressed == true ){
            direction = "right";
            newX = x + speed;
        }
        
        if (newX >= 0 && newX <= (gp.screenWidth-180) - gp.tileSize && newX >= 711){
            x = newX;
        }
        if (newY >= 0 && newY <= (gp.screenHeight-150) - gp.tileSize){
            y = newY;
        }
        
        playerCounter++;
        if(playerCounter > 16){
            if(playerNum == 1){
                playerNum = 2;
            }
            else if(playerNum == 2) {
                playerNum = 1;
            }
            playerCounter = 0;
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
                if(playerNum == 1){
                    image = left1;
                }
                if(playerNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(playerNum == 1){
                    image = right1;
                }
                if(playerNum == 2){
                    image = right2;
                }
                break;    
        }
        g2.drawImage(image, x, y, gp.tileSize*4, gp.tileSize*4, null);
    }
}