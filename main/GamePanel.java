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
import main.KeyHandler2;
import tile.TileManager;
import entity.Ball;
import entity.Scoreboard;
import entity.ScoreList;
import java.util.List;
public class GamePanel extends JPanel implements Runnable
{
    //SCREEN Settings
    public final int originalTileSize = 16; // 16x16 tile-Größe
    final int scale = 4; //16x4 = 64 Pixel
    public final int tileSize = originalTileSize * scale; // 64 Pixel pro Tile
    public final int maxScreenCol = 23; // blöcke horizontal
    public final int maxScreenRow = 12; // blöcke vertical
    public final int screenWidth = tileSize * maxScreenCol; // 1472 pixel länge vom Screen
    public final int screenHeight = tileSize * maxScreenRow; // 768 pixel Höhe vom Screen

    //FPS
    int FPS = 60;

    BufferedImage backgroundImage;

    TileManager tileM = new TileManager(this);
    //erstellt ein Thread und gleichzeitig startet die Uhr/Zeit. Programm wird laufen bis man es stoppt. Hilft das Prozzes zu wiederholen(FPS)    
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this,keyH);

    KeyHandler2 keyH2 = new KeyHandler2();
    Player2 player2 = new Player2(this,keyH2);

    ScoreList scoreList = new ScoreList();
    Scoreboard scoreboard = new Scoreboard(scoreList);

    Ball ball = new Ball(this, scoreboard);

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

        loadBackgroundImage();
    }

    public void loadBackgroundImage()
    {
        try{
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background/background.image.png"));
            System.out.println("Erfolg Hinter_Bild");
        }
        catch(IOException e){
            System.out.println("Kein Erfolg Hinter_Bild");
            e.printStackTrace();
        }
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    public void run()
    {

        double drawInterval = 1000000000/FPS; // 0.01666 seconds, Zeit zwischen Frames
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){ //wiederholt das Prozess

            currentTime = System.nanoTime();

            delta = delta + (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update(); // Spieler 1 aktualisieren
        player2.update(); // Spieler 2 aktualisieren
        ball.update(); // Ball aktualisieren

        ball.checkPlayerCollision(player); // Kollision prüfen
        ball.checkPlayerCollision(player2); // Kollision prüfen
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // super = subclass von JPanel
        Graphics2D g2  = (Graphics2D)g; //Graphics2D erweitert class Graphics für mehr kontrolle über color managment
        if (backgroundImage != null){
            g2.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
        }
        tileM.draw(g2); // netz zeichnen
        player.draw(g2); // Spieler1 zeichnen
        player2.draw(g2); // Spieler2 zeichnen
        ball.draw(g2); // Ball zeichnen
        g2.dispose(); // Grafik-Ressourcen freigeben
    }

}