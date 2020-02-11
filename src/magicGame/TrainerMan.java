package magicGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrainerMan extends Enemy{
	int hitCounter;
	
	public TrainerMan() {
		super();
		x = 350;
		y = 250;
		hp = 10000;
		hitCounter = 0;
		fullHp = hp;
		image = new Image("/images/trainer_man.png");
		velocity = new double[]{0,0};
		imageView = new ImageView(image);
		imageView.relocate(x, y);
		painter.pane.getChildren().add(imageView);
		checkBox = new CheckBox(x + 10, y + 10, 30, 70);
	}
	
	@Override
	public void getHit(int damage, int type) {
		super.getHit(damage, type);
		if (hp <= 0){
			hp = 1;
		}
		showHpCounter = 180;
	}
	
	@Override
	public void aplayHit() {
		if (isHited) {
			if (changeRate < 0.13 && hitState == 0) {
				changeRate += 0.02;
			}else if (changeRate > 0.13 || hitState == 1){
				hitState = 1;
				if (changeRate < 0) {
					changeRate = 0;
					isHited = false;
					hitState = 0;
				}else {
					changeRate -= 0.02;
				}
			}
			imageView.setScaleX(1 - changeRate);
			hitCounter = 120;
		}else if(hitCounter <= 0) {
			hp = 10000;
		}else if(hitCounter <= 30 && hp < 10000) {
			hp += 1000/3;
			if (hp > 10000) {
				hp = 10000;
			}
		}else {
			hitCounter --;
		}
	}
}
