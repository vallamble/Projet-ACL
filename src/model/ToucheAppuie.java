package model;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ToucheAppuie implements KeyListener {
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyReleased(KeyEvent e) {	
	}
	
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		World world = (World) e.getSource();
		if (c == KeyEvent.VK_RIGHT) {
			world.setX(1);
		} else if (c == KeyEvent.VK_LEFT) {
			world.setX(-1);
		} 
	}
	
}
