package magicGame;

import javafx.scene.image.ImageView;

public class MahoJing extends Particle{
	private int counter;

	public MahoJing() {
		nowScale = 1;
		state = 0;
		counter = 0;
		degree = Math.random() * 360;
		imageView = new ImageView("/images/mahoJing.png");
		
		this.x = 430;
		this.y = 5;

		imageView.setOpacity(nowScale);
		imageView.setRotate(degree);
		imageView.relocate(this.x, this.y);
		painter.pane.getChildren().add(imageView);
	}
	
	public boolean update() {
		degree += 0.5;
		switch(state) {
		case 0:
			if(nowScale > 0.9) {
				nowScale -= 0.001;
				break;
			}
			state = 1;
			break;
		case 1:
			if (counter < 10) {
				counter += 1;
				break;
			}
			state = 2;
			counter = 0;
			break;
		case 2:
			if(nowScale < 1.0) {
				nowScale += 0.001;
				break;
			}
			state = 3;
			break;
		case 3:
			if (counter < 10) {
				counter += 1;
				break;
			}
			state = 0;
			counter = 0;
			break;
		}
		imageView.setRotate(degree);
		imageView.setScaleX(nowScale);
		imageView.setScaleY(nowScale);
		return true;
	}
}
