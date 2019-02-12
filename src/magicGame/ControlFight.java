package magicGame;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControlFight extends Control{
	public ControlFight(painter p) {
		super(p);
	}
	public void keyPressed(KeyEvent event) {
		//System.out.println("key pressed: " + event.getCode());
		switch (event.getCode()) {
		case D:
			p.player.startMovingRight();
			break;
		case A:
			p.player.startMovingLeft();
			break;
		case W:
		case SPACE:
			p.player.startJumping();
			break;
		case P:
			p.cp.showPanel();
			p.control = new ControlConstruct(p);
		default:
			break;
		}
	}
	
	
	public void keyReleased(KeyEvent event) {
		//System.out.println("key released: " + event.getCode());
		switch (event.getCode()) {
		case D:
			p.player.stopMovingRight();
			break;
		case A:
			p.player.stopMovingLeft();
			break;
		default:
			break;
		}
	}
	
	
	public void mouseMoved(MouseEvent e) {
		p.player.setLookAt(e.getSceneX(), e.getSceneY());
	}
	
	
	public void mouseDragged(MouseEvent e) {
		p.player.setLookAt(e.getSceneX(), e.getSceneY());
	}
	
	
	public void mousePressed(MouseEvent e) {
		p.player.setFireDown(true, e.getButton().ordinal());
	}
	
	
	public void mouseReleased(MouseEvent e) {
		p.player.setFireDown(false, e.getButton().ordinal());
	}
	
	public void updateTimer() {
		p.player.updateState();
		painter.projectiles.update();
		painter.particles.update();
		painter.enemys.update();
		p.showFPS();
	}
	
}
