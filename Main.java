
import javax.swing.JFrame;
public class Main
{
    public static void main(String[] args)
    {
        //erstellt ein Fenster für Soft
        JFrame window = new JFrame();
        //mach das Fenster zu
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //man kann nicht die Grösse ändern
        window.setResizable(false);
        //gibt den Spieltitel
        window.setTitle("Overball");
        //macht das Fenster in der Mitte auf dem Screen
        window.setLocationRelativeTo(null);
        //mach das Fenster sichtbar
        window.setVisible(true);
    }
}
