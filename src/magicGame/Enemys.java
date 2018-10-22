package magicGame;

import java.util.ArrayList;

public class Enemys extends ArrayList<Enemy>{

	private static final long serialVersionUID = 1L;
	
	public void update() {
		for (Enemy enemy: this) {
			enemy.update();
		}
	}

}
