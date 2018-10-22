package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Particle {

	
	ImageView imageView;
	double degree;
	double nowScale, x, y;
	int state;
	
	public Particle() {
		nowScale = 0;
		state = 0;
		degree = Math.random() * 360;
	}
	
	public boolean update() {
		switch (state){
		case 0:
			if (nowScale < 1) {
				nowScale += 0.1;
			}else {
				nowScale = 1;
				state = 1;
			}
			imageView.setScaleX(nowScale);
			imageView.setScaleY(nowScale);
			imageView.setOpacity((nowScale + 0.2) * 5 / 9);
			imageView.relocate(this.x, this.y);
			break;
		case 1:
			if(nowScale > 0){
				nowScale -= 0.1;
			}else {
				nowScale = 0;
				state = 2;
				
			}
			imageView.setScaleX(nowScale);
			imageView.setScaleY(nowScale);
			imageView.setOpacity((nowScale + 0.2) * 5 / 9);
			imageView.relocate(this.x, this.y);
			break;
		case 2:
			painter.pane.getChildren().remove(imageView);
			painter.particles.remove(this);
			return false;
		}
		return true;
			
	}
}
