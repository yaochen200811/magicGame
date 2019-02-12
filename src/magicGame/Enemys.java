package magicGame;

import java.util.ArrayList;

public class Enemys extends ArrayList<Enemy>{

	private static final long serialVersionUID = 1L;
	
	public void update() {
		int i = 0;
		while (i < this.size()) {
			this.get(i).update();
			i ++;
		}
	}

}
