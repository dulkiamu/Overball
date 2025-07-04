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
        int size = 128;

        setDefaultvalues();
        getPlayerImage();
    }

    public void setDefaultvalues()
    {
        x = 220;
        y = 220;
        speed = 10;
        direction = "up";
        gravity = 0.6; // Gravitation
        jumpPower = -15.0; // Sprungkraft
        maxFallSpeed = 10.0; // maximale Fallgeschwindigkeit
        velocityY = 0.0;
        onGround = false;
    }

    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
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
        int newX = x;
        int newY = y;

        // Horizontal
        if(keyH.leftPressed == true ){
            direction = "left";
            newX = x - speed;
        }
        else if(keyH.rightPressed == true ){
            direction = "right";
            newX = x + speed;
        }

        if(keyH.upPressed == true){
            jump(); 
        }

        if (newX >= -50 && newX <= 500){
            x = newX;
        }
        
        applyGravity(gp.screenHeight, gp.tileSize*4);

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
        if (!onGround) {
            // In der Luft 
            if (velocityY < 0) {
                // Springt nach oben
                image = up1;
            } else {
                // Fällt nach unten
                image = down1;
            }
        }
        else{
            switch(direction)
            {
                case "up":
                    image = up1;
                    break;
                case "down":
                    if(playerNum == 1){
                        image = down1;
                    }
                    if(playerNum == 2){
                        image = down2;
                    }
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
        }

        g2.drawImage(image, x, y, gp.tileSize*4, gp.tileSize*4, null);
    }
}

