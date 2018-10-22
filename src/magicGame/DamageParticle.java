package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DamageParticle extends Particle{
	static final Image[] damageNum = {(new Image("/images/damage_num/0d.png")),
	(new Image("/images/damage_num/1d.png")),
	(new Image("/images/damage_num/2d.png")),
	(new Image("/images/damage_num/3d.png")),
	(new Image("/images/damage_num/4d.png")),
	(new Image("/images/damage_num/5d.png")),
	(new Image("/images/damage_num/6d.png")),
	(new Image("/images/damage_num/7d.png")),
	(new Image("/images/damage_num/8d.png")),
	(new Image("/images/damage_num/9d.png")),
	};
	
	static final Image[] criticalNum = {(new Image("/images/damage_num/0c.png")),
			(new Image("/images/damage_num/1c.png")),
			(new Image("/images/damage_num/2c.png")),
			(new Image("/images/damage_num/3c.png")),
			(new Image("/images/damage_num/4c.png")),
			(new Image("/images/damage_num/5c.png")),
			(new Image("/images/damage_num/6c.png")),
			(new Image("/images/damage_num/7c.png")),
			(new Image("/images/damage_num/8c.png")),
			(new Image("/images/damage_num/9c.png")),
			};
	
	ArrayList<ImageView> imageViews;
	double extrax;
	
	public DamageParticle(double x, double y, int damage, int type) {
		imageViews = new ArrayList<ImageView>();
		int lastDigit;
		Image[] currentImage = damageNum;
		switch (type) {
		case 0:
			currentImage = damageNum;
			break;
		case 1:
			currentImage = criticalNum;
			break;
		}
		
		while (damage >= 10) {
			lastDigit = damage%10;
			imageViews.add(0, new ImageView(currentImage[lastDigit]));
			damage = damage / 10;
		}
		lastDigit = damage%10;
		imageViews.add(0, new ImageView(currentImage[lastDigit]));
		this.x = x;
		this.y = y - 20;
		nowScale = 0.6;
		state = 0;
		if (imageViews.size() % 2 == 0) {
			extrax = 10.5;
		}else {
			extrax = 0;
		}

		for (int i = 0; i < imageViews.size(); i ++) {
			imageViews.get(i).setScaleX(nowScale);
			imageViews.get(i).setScaleY(nowScale);
			imageViews.get(i).setOpacity(0.3);
			imageViews.get(i).relocate(this.x - ((imageViews.size() / 2 - i) * 21 * nowScale) + extrax * (nowScale), this.y);
			painter.pane.getChildren().add(imageViews.get(i));
		}
		
	}
	
	@Override
	public boolean update() {
		if (state == 0) {
			nowScale += 0.08;
			if (nowScale > 1.6) {
				state = 1;
			}
		}else if (state == 1) {
			nowScale -= 0.03;
			if (nowScale < 0) {
				state = 2;
			}
		}else {
			while (imageViews.size() > 0) {
				imageViews.get(0).setOpacity(0);
				painter.pane.getChildren().remove(imageViews.get(0));
				imageViews.remove(0);
				System.out.println("removed");
			}
			System.out.println("removed complet");
			painter.particles.remove(this);
			return false;
		}
		y -= 1;
		x += 0.3;
		
		for (int i = 0; i < imageViews.size(); i ++) {
			imageViews.get(i).setScaleX(nowScale);
			imageViews.get(i).setScaleY(nowScale);
			imageViews.get(i).setOpacity(0.7);
			imageViews.get(i).relocate(this.x - ((imageViews.size() / 2 - i) * 21 * nowScale) + extrax * (nowScale), this.y);
		}
		return true;
	}

}
