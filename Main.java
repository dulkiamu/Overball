
import javax.swing.JFrame;
public class Main
{
    public static void main()
    {
        //erstellt ein Fenster für Soft
        JFrame window = new JFrame();
        //mach das Fenster zu
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //man kann nicht die Grösse ändern
        window.setResizable(false);
        //gibt den Spieltitel
        window.setTitle("Overball");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // passt die Grösse vom Fenster an
        
        //macht das Fenster in der Mitte auf dem Screen
        window.setLocationRelativeTo(null);
        //mach das Fenster sichtbar
        window.setVisible(true);
    }
}
