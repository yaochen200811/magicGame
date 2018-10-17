package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Parts extends ArrayList<Part>{
	
	private static final long serialVersionUID = 1L;
	
	Parts() {}
	
	public boolean add(int x, int y, Image image, int bx, int by) {
		try {
			this.add(new Part(x, y, image, new CheckBox(x, y , bx, by)));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
