package magicGame;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Projectiles extends ArrayList<Projectile>{
	private static final long serialVersionUID = 5224869620057894418L;

	public boolean add(int projectileID, int x, int y, double dx, double dy, double degree) {
		super.size();
		try {
			this.add(new Projectile(projectileID, x, y, dx, dy, degree));
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	
	
	
	public void update(CheckBoxs[] CBsList, Group pane) {
		int moveIndex = 0;
		while (moveIndex < this.size()) {
			if (!isCollision(CBsList, this.get(moveIndex))) {
				this.get(moveIndex).setNewCoor(this.get(moveIndex).velocity);
				this.get(moveIndex).checkBox.move(this.get(moveIndex).velocity);
				moveIndex ++;
			}else {
				pane.getChildren().remove(this.get(moveIndex).imageView);
				this.remove(moveIndex);
			}
		}
	}
	
	public boolean isCollision(CheckBoxs[] CBsList, Projectile projectile) {
		
		for (CheckBoxs CBs: CBsList) {
			for (CheckBox cb: CBs) {
					if(projectile.checkBox.isOverlap(cb, projectile.velocity)) {
						return true;
					}
			}
		}
		
		return false;
	}

}
