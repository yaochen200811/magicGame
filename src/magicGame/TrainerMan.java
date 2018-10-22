package magicGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrainerMan extends Enemy{

	public TrainerMan() {
		super();
		x = 350;
		y = 250;
		hp = 10000;
		fullHp = hp;
		velocity = new double[]{0,0};
		image = new Image("/images/trainer_man.png");
		imageView = new ImageView(image);
		imageView.relocate(x, y);
		painter.pane.getChildren().add(imageView);
		checkBox = new CheckBox(x + 10, y + 10, 30, 74);
	}
}
