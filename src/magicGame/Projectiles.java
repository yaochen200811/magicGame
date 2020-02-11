package magicGame;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Projectiles extends ArrayList<Projectile>{
	private static final long serialVersionUID = 5224869620057894418L;
	CheckBox colict;

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
	
	
	
	
	public void update() {
		int moveIndex = 0;
//		System.out.print("µ¯: " + this.size());
		while(moveIndex < this.size()) {
			if(!this.get(moveIndex).update())
			moveIndex ++;
		}
	}

}
