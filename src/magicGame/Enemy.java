package magicGame;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class Enemy extends Entity{
	boolean isHited, clearedCheckBox;
	int hitState, showHpCounter, fullHp;
	double changeRate;
	ImageView hpBar, hpBarBack;
	
	public Enemy() {
		isOnGround = false;
		isHited = false;
		clearedCheckBox = false;
		changeRate = 0;
		hitState = 0;
		hpBar = new ImageView("/images/hp_bar.png");
		hpBar.setOpacity(0);
		hpBarBack = new ImageView("/images/hp_bar_back.png");
		hpBarBack.setOpacity(0);
		painter.pane.getChildren().add(hpBarBack);
		painter.pane.getChildren().add(hpBar);
	}
	
	public void update() {
		//System.out.println("l");
		if (isAlive()) {
			checkMove();
			aplayHit();
			this.imageView.relocate(x, y);
			showHpBar();
		}else if(aplayDead()){
			painter.pane.getChildren().remove(imageView);
			painter.pane.getChildren().remove(hpBarBack);
			painter.pane.getChildren().remove(hpBar);
				painter.enemys.remove(this);
				return;
		}else if(!clearedCheckBox) {
			clearedCheckBox = true;
			checkBox = new CheckBox(0,0,0,0);
		}
	}
	
	public boolean isAlive() {
		return hp > 0;
	}
	
	public void showHpBar() {
		if (showHpCounter > 0) {
			hpBar.setScaleX((double)hp / fullHp);
			hpBar.relocate(x - 1 - (1 - (double)hp / fullHp) * 26.5, y - 13);
			hpBar.setOpacity(0.8);
			hpBarBack.relocate(x - 2, y - 15);
			hpBarBack.setOpacity(0.8);
			showHpCounter --;
		}else {
			hpBar.setOpacity(0);
			hpBarBack.setOpacity(0);
		}
	}
	
	public boolean aplayDead() {
		changeRate += 0.05;
		imageView.setScaleX(1 - changeRate);
		showHpBar();
		return changeRate > 1;
	}
	
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
			
		}
	}
	
	public void getHit(int damage, int type) {
		painter.particles.add(new DamageParticle(x + checkBox.width / 2,y + checkBox.height / 2,damage, type));
		isHited = true;
		hp -= damage;
		showHpCounter = 90;
	}

}
