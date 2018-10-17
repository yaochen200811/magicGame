package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class CheckBoxs extends ArrayList<CheckBox>{
	private static final long serialVersionUID = -2660135037441086500L;

	CheckBoxs() {}
	
	public boolean add(Parts parts) {
		for (Part part: parts) {
			try {
				this.add(part);
			}catch(Exception e) {
				return false;
			}
		}
		return true;
	}
	
	public boolean add(Part part) {
		try {
			this.add(part.getCb());
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public boolean add(Player player) {
		try {
			this.add(player.getCheckBox());
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
