package magicGame;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControlFrame extends Control{

	public ControlFrame(painter p) {
		super(p);
	}

	public void keyPressed(KeyEvent event) {
		//System.out.println(event.getCode());
		switch (event.getCode()) {
		case UP:
		case W:
			painter.cp.frameUp();
			break;
		case DOWN:
		case S:
			painter.cp.frameDown();
			break;
		case ENTER:
			painter.cp.setSpell();
			painter.cp.endIPS();
			p.control = new ControlConstruct(p);
			break;
		case P:
			painter.cp.closePanel();
			p.control = new ControlFight(p);
		default:
			break;
		}
	}
	public void keyReleased(KeyEvent event) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void updateTimer() {
		painter.cp.updateIPS();
	}
}
