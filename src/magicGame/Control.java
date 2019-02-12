package magicGame;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public abstract class Control {
	protected painter p;
	
	public Control(painter p) {
		this.p = p;
	}
	
	public void keyPressed(KeyEvent event) {}
	public void keyReleased(KeyEvent event) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void updateTimer() {}

}
