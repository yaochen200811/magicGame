package magicGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity {
	static final int GRAVITY = 1;
	int x, y, hp;
	double [] velocity;
	boolean isOnGround;
	Image image;
	ImageView imageView;
	CheckBox checkBox; 
	
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void checkMove() {
		
		CheckBoxs[] CBsList = new CheckBoxs[]{painter.hardCB, painter.fieldCB};
		double[] tempVelocity = velocity.clone();
		boolean downXCheck = false, downYCheck = false;
		
		tempVelocity = checkGravity(tempVelocity);
		for (CheckBoxs CBs: CBsList) {
			for (CheckBox cb: CBs) {
				while (!downXCheck) {
					if(this.checkBox.isOverlap(cb, new double[] {tempVelocity[0], 0})) {
						if (tempVelocity[0] > 0) {
							tempVelocity[0] -= 1;
						}else if (tempVelocity[0] < 0) {
							tempVelocity[0] += 1;
						}else {
							downXCheck = true;
						}
					}else {
						downXCheck = true;
					}
				}
				downXCheck = false;
				
				while (!downYCheck) {
					if(this.checkBox.isOverlap(cb, new double[] { 0, tempVelocity[1]})) {
						if (tempVelocity[1] > 0) {
							tempVelocity[1] -= 1;
							
						}else if (tempVelocity[1] < 0) {
							tempVelocity[1] += 1;
							velocity[1] += 1;
						}else {
							downYCheck = true;
						}
					}else {
						downYCheck = true;
					}
				}
				downYCheck = false;
				
				if (!isOnGround) {
					isOnGround = this.checkBox.isOnGround(cb, tempVelocity);
					
				}
			}
		}
		
		x += tempVelocity[0];
		y += tempVelocity[1];
		checkBox.move(tempVelocity);
	}
	
	public double[] checkGravity(double[] tempVelocity) {
		if (isOnGround) {
			if (velocity[1] > 0) {
					velocity[1] = 0;
					tempVelocity[1] = 0;
					isOnGround = false;
					return tempVelocity;
				}
		}
		velocity[1] += GRAVITY;
		tempVelocity[1] += GRAVITY;
		return tempVelocity;
	}
	
	public CheckBox getCheckBox() {
		return checkBox;
	}
}
