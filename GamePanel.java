
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
    //erstellt ein Thread und gleichzeitig startet die Uhr/Zeit. Programm wird laufen bis man es stoppt. Hilft das Prozzes zu wiederholen(FPS)    
    Thread gameThread;

    public GamePanel()
    {
        //erstellt ein Fenster von dieser Klasse
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        // Drawings werden in offscreen painting buffer gemacht
        this.setDoubleBuffered(true); 
    }
    
    public void startGameThread()
    {
        
    }
    
    @Override 
    public void run()
    {
        
    }

}
