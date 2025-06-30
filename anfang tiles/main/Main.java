package main; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Main
{
    public static void main(String[] args)
    {
        //erstellt ein Fenster für Soft
        JFrame window = new JFrame();
        GamePanel gp = new GamePanel();
        
        //mach das Fenster zu
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //man kann nicht die Grösse ändern
        window.setResizable(false);
        //gibt den Spieltitel
        window.setTitle("Overball");
        
        window.add(gp);
        window.pack(); // passt die Grösse vom Fenster an
        
        //macht das Fenster in der Mitte auf dem Screen
        window.setLocationRelativeTo(null);
        //mach das Fenster sichtbar
        window.setVisible(true);
        
        gp.startGameThread();
    }
}
