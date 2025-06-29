package main;   
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import entity.Player;
public class KeyHandler implements KeyListener  // KeyListener für Keyboard events
{
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    @Override 
    public void keyTyped(KeyEvent e){
        
    }
    @Override 
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode(); // gibt die nummer von pressed keys zurück
        
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }
    @Override 
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }

    
}
