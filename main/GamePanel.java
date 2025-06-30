package main;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import entity.Player;
import entity.Player2;

public class GamePanel extends JPanel implements Runnable
{
    //SCREEN Settings
    public final int originalTileSize = 16; //32x32 tile
    final int scale = 2; //32x32 bleibt aber sieht 64x64 auf dem screen aus
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 23; // blöcke vertical
    final int maxScreenRow = 12; // blöcke horizontal
    final int screenWidth = tileSize * maxScreenCol; // 1104pixel länge vom Screen
    final int screenHeight = tileSize * maxScreenRow; // 576pixel Höhe vom Screen
    
    //FPS
    int FPS = 60;
    
    
    //erstellt ein Thread und gleichzeitig startet die Uhr/Zeit. Programm wird laufen bis man es stoppt. Hilft das Prozzes zu wiederholen(FPS)    
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this,keyH);
    KeyHandler keyH2 = new KeyHandler();
    Player2 player2 = new Player2(this,keyH2);

    
    public GamePanel()
    {
        //erstellt ein Fenster von dieser Klasse
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.pink);
        // Drawings werden in offscreen painting buffer gemacht
        this.setDoubleBuffered(true); 
        this.addKeyListener(keyH);
        this.addKeyListener(keyH2);
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
                update2();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }
    
    public void update2() {
        player2.update();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // super = subclass von JPanel
        Graphics2D g2  = (Graphics2D)g; //Graphics2D erweitert class Graphics für mehr kontrolle über color managment
        player.draw(g2);
        g2.dispose();
    }
    
    public void paintComponent2(Graphics g) {
        super.paintComponent(g); // super = subclass von JPanel
        Graphics2D g2  = (Graphics2D)g; //Graphics2D erweitert class Graphics für mehr kontrolle über color managment
        player2.draw(g2);
        g2.dispose();
    }
}
