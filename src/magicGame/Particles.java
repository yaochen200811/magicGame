package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Particles  extends ArrayList<Particle>{
	private static final long serialVersionUID = 1L;
	
	public boolean add(double x, double y) {
		try {
			this.add(new ExplorParticle(x, y));
		}catch(Exception e) {
			System.out.println("f");
			return false;
		}
		return true;
	}
	
	public void update() {
		int moveIndex = 0;
		while(moveIndex < this.size()) {
			if (this.get(moveIndex).update()) {
				moveIndex ++;
			}
		}
	}

}
