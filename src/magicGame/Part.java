package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Part {
	private int x, y;
	private Image image;
	private CheckBox cb;
	
	
	public Part(int x, int y, Image image, CheckBox cb) {
		this.setX(x);
		this.setY(y);
		this.image = image;
		this.setCb(cb);
	}
	
	public Image getImage() {
		return image;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CheckBox getCb() {
		return cb;
	}

	public void setCb(CheckBox cb) {
		this.cb = cb;
	}
	
	
}
