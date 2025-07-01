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
        
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // macht das Fenster zu schließen möglich
        
        window.setResizable(false); // man kann nicht die Grösse ändern
        window.setTitle("Overball"); // gibt den Fenstertitel
        
        window.add(gp); // Gamepanel zum Fenster hinzufügen
        window.pack(); // passt die Grösse vom Fenster an
        
        
        window.setLocationRelativeTo(null); // zentriert das Fenster 
        window.setVisible(true); // macht das Fenster sichtbar
        
        gp.startGameThread(); // Spiel-Loop starten
        
    }
}
