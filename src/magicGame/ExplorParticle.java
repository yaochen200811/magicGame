package magicGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExplorParticle extends Particle{
	static final Image image = new Image("/images/explor.png");

	public ExplorParticle(double x, double y) {
		super();
		this.x = x - image.getHeight() / 2;
		this.y = y - image.getWidth() / 2;

		imageView = new ImageView(image);
		imageView.setScaleX(nowScale);
		imageView.setScaleY(nowScale);
		imageView.setOpacity(nowScale);
		imageView.setRotate(degree);
		imageView.relocate(this.x, this.y);
		painter.pane.getChildren().add(imageView);
	}
	
	@Override
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
