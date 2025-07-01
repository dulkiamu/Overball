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
    
    public double velocityY = 0.0;      // Vertikale Geschwindigkeit
    public double gravity = 0.5;        // Gravitationsstärke
    public double jumpPower = -12.0;    // Sprungkraft (negativ = nach oben)
    public double maxFallSpeed = 8.0;   // Maximale Fallgeschwindigkeit
    public boolean onGround = false;    // Ist der Spieler auf dem Boden?
    public boolean canJump = true;      // Kann der Spieler springen?
    
    public int groundLevel = 0;         
    
    public void applyGravity(int screenHeight, int spriteHeight) {
        // Bodenhöhe
        int ground = screenHeight - 220;
        
        // Gravitation anwenden (nur wenn nicht auf dem Boden)
        if (y < ground) {
            velocityY += gravity;
            
            // Maximale Fallgeschwindigkeit begrenzen
            if (velocityY > maxFallSpeed) {
                velocityY = maxFallSpeed;
            }
            
            onGround = false;
        }
        
        // Neue Y-Position berechnen
        int newY = (int) (y + velocityY); // int wegen convert von double zu int
        
        // Boden-Kollision prüfen
        if (newY >= ground) {
            newY = ground;
            velocityY = 0; // Fallgeschwindigkeit stoppen
            onGround = true;
            canJump = true; // Spieler kann wieder springen
        }
        
        y = newY;
    }
    
    
    public void jump() {
        if (onGround && canJump) {
            velocityY = jumpPower; // Neg. Geschwindigkeit = nach oben
            onGround = false;
            canJump = false; // Verhindert Doppelsprung bzw. undendlich springen
        }
    }
}

