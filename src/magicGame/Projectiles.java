package magicGame;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Projectiles extends ArrayList<Projectile>{
	private static final long serialVersionUID = 5224869620057894418L;

	public boolean add(int projectileID, int x, int y, double dx, double dy, double degree) {
		try {
			//System.out.println(4);
			this.add(0, new Projectile(projectileID, x, y, dx, dy, degree));
			//System.out.println(3);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean add(SpellConstruct sc, int x, int y, double dx, double dy, double degree) {
		try {
			//System.out.println(4);
			this.add(0, new Projectile(sc, x, y, dx, dy, degree));
			//System.out.println(3);
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public boolean add(SpellConstruct sc, double x, double y, double dx, double dy, double degree) {
		try {
			//System.out.println(4);
			this.add(0, new Projectile(sc, x, y, dx, dy, degree));
			//System.out.println(3);
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	
	
	public void update(CheckBoxs[] CBsList) {
		int moveIndex = 0;
		while(moveIndex < this.size()) {
			//System.out.println(3);
			if (this.get(moveIndex).fading) {
				//System.out.println(2);
				if (this.get(moveIndex).fadingMove()) {
					this.remove(moveIndex);
				}
			}else {
				//System.out.println(0);
				if (this.get(moveIndex).getHit()) {
				}else if (!isCollision(CBsList, this.get(moveIndex))) {
					//System.out.println(1);
					this.get(moveIndex).setNewCoor(this.get(moveIndex).velocity);
					this.get(moveIndex).checkBox.move(this.get(moveIndex).velocity);
					
				}else {
					this.get(moveIndex).startFading();
				}
			}
			moveIndex ++;
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
