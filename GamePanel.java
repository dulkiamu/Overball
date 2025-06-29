import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
public class GamePanel extends JPanel implements Runnable
{
    //SCREEN Settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //16x16 bleibt aber sieht 48x48 auf dem screen aus
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 23; // blöcke vertical
    final int maxScreenRow = 12; // blöcke horizontal
    final int screenWidth = tileSize * maxScreenCol; // 1104pixel länge vom Screen
    final int screenHeight = tileSize * maxScreenRow; // 576pixel Höhe vom Screen
    
    //FPS
    int FPS = 60;
    
    
    //erstellt ein Thread und gleichzeitig startet die Uhr/Zeit. Programm wird laufen bis man es stoppt. Hilft das Prozzes zu wiederholen(FPS)    
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    //Spieler position am Anfang
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; 

    public GamePanel()
    {
        //erstellt ein Fenster von dieser Klasse
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        // Drawings werden in offscreen painting buffer gemacht
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyH);
        this.setFocusable(true); // this GamePanel ist focused um key Input zu bekommen
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    public void run()
    {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while(gameThread != null){ //wiederholt das Prozess
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if(keyH.upPressed == true){
            playerY = playerY - playerSpeed;
        }
        else if(keyH.downPressed == true ){
            playerY = playerY + playerSpeed;
        }
        else if(keyH.leftPressed == true ){
            playerX = playerX - playerSpeed;
        }
        else if(keyH.rightPressed == true ){
            playerX = playerX + playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // super = subclass von JPanel
        Graphics2D g2  = (Graphics2D)g; //Graphics2D erweitert class Graphics für mehr kontrolle über color managment
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize); // (x, y, weidth, height)
        g2.dispose();
    }
}
