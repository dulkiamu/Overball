package main;   
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import entity.Player;
public class KeyHandler2 implements KeyListener  // KeyListener für Keyboard events
{
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    
    @Override 
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override 
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode(); // gibt den code von pressed keys zurück
        
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
    }
    
    @Override 
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }

    
}
