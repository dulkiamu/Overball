package entity;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class Entity // Klass für variablen in Player class
{
    public int x, y;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int playerCounter = 0;
    public int playerNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}

