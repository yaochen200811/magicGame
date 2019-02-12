package magicGame;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControlConstruct extends Control{

	public ControlConstruct(painter p) {
		super(p);
	}

	public void keyPressed(KeyEvent event) {
		System.out.println(event.getCode());
		switch (event.getCode()) {
		case UP:
		case W:
			painter.cp.moveUp();
			break;
		case DOWN:
		case S:
			painter.cp.moveDown();
			break;
		case LEFT:
		case A:
			painter.cp.moveLeft();
			break;
		case RIGHT:
		case D:
			painter.cp.moveRight();
			break;
		case Q:
			painter.cp.GroupLeft();
			break;
		case E:
			painter.cp.GroupRight();
			break;
		case Z:
			painter.cp.switchToLeft();
			break;
		case C:
			painter.cp.switchToRight();
			break;
		case L:
			painter.cp.loadMagic();
			break;
		case K:
			painter.cp.saveMagic();
			break;
		case ENTER:
			painter.cp.setFrames();
			painter.cp.startIPS();
			p.control = new ControlFrame(p);
			break;
		case P:
			painter.cp.endEdit();
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
