package magicGame;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Entity{
	static final int MOVESPEED = 3, JUMPFORCE = 22, STAFFCENTERX = 25, STAFFCENTERY = 64,
			FIRERATE = 10;
	private boolean isMovingLeft, isMovingRight, isJumping, isFiring, isFireDown,
	leftKeyDown, rightKeyDown , isTurning, faceRight, isFireLeft, isFireRight;
	private double lookAtX, lookAtY, turnCounter;
	private int fireCounter;
	ImageView playerView, staffView;
	
	public Player() {
		x = 615;
		y = 400;
		hp = 10;
		lookAtX = 1280;
		lookAtY = 400 + STAFFCENTERY;
		fireCounter = 0;
		turnCounter = 1;
		velocity = new double[]{0, 0};
		isMovingLeft = false;
		isMovingRight = false;
		isJumping = false;
		isOnGround = false;
		isFiring = false;
		isFireDown = false;
		isFireLeft = false;
		isFireRight = false;
		leftKeyDown = false;
		rightKeyDown = false;
		isTurning = false;
		faceRight = true;
		checkBox = new CheckBox(x, y, 50, 87);
//		image = new Image("/images/playerImage.png");
		
		playerView = new ImageView("/images/playerImage.png");
		playerView.relocate(getX(), getY());
		painter.pane.getChildren().add(playerView);
		
		staffView = new ImageView("/images/staff.png");
		staffView.relocate(getStaffX(), getStaffY());
		painter.pane.getChildren().add(staffView);
	}
	
	public void startMovingRight() {
		rightKeyDown = true;
	}
	
	public void startMovingLeft() {
		leftKeyDown = true;
	}
	
	public void stopMovingRight() {
		rightKeyDown = false;
	}
	
	public void stopMovingLeft() {
		leftKeyDown = false;
	}
	
	public void startJumping() {
		if (!(isJumping) && (isOnGround)) {
			isOnGround = false;
			velocity[1] -= JUMPFORCE;
			//isJumping = true;
		}
	}
	
	public int getStaffX() {
		return this.x+STAFFCENTERX - 14;
	}
	
	public int getStaffY() {
		return this.y+STAFFCENTERY - 31;
	}
	
	public double getStaffRotation() {
		double degree = 0;
		degree = Math.atan((lookAtY - (y + STAFFCENTERY)) / (lookAtX - (x + STAFFCENTERX)));
		if (lookAtX > x + STAFFCENTERX) {
			degree = 90 - (-1 * degree * (180/Math.PI));
		}else if (lookAtX < x + STAFFCENTERX){
			degree = 90 - (-1 * degree * (180/Math.PI)) + 180;
		}else if (lookAtY > (y + STAFFCENTERY)) {
			degree = 180;
		}
		return degree;
	}
	
	public void setLookAt(double x, double y) {
		lookAtX = x;
		lookAtY = y;
	}
	
	public double getFaceDir() {
		faceRight = (lookAtX > x + 25);
		if (faceRight && turnCounter < 1) {
			turnCounter += 0.4;
		}else if (!faceRight && turnCounter > -1) {
			turnCounter -= 0.4;
		}
		return turnCounter;
	}
	
	public void setFireDown(boolean state, int type) {
		switch(type) {
		case 1:
			isFireLeft = state;
			break;
		case 3:
			isFireRight = state;
			System.out.println("fight");
			break;
		}
	}
	
	public void repuireFire() {
		if ((!isFiring)&&(isFireLeft || isFireRight)) {
			isFiring = true;
			fireCounter = FIRERATE;
			double degree = Math.atan((lookAtY - (y + STAFFCENTERY)) / (lookAtX - (x + STAFFCENTERX)));
			double dx = (Math.cos(degree));
			double dy = (Math.sin(degree));
			if ((lookAtX < x + STAFFCENTERX)) {
				dx = -dx;
				dy = -dy;
			}
			if (isFireLeft) {
				painter.projectiles.add(new SpellConstruct(new Integer[] {0,14}), x + STAFFCENTERX, y + STAFFCENTERY, dx, dy, getStaffRotation());
			}else if(isFireRight) {
				
				painter.projectiles.add(new SpellConstruct(new Integer[] {0,15,32,30,32,-30,0,14,32,30,32,50,0,13,0,12,0,11,32,-30,0,10}), x + STAFFCENTERX, y + STAFFCENTERY, dx, dy, getStaffRotation());
				//System.out.println("fire");
			}
			//System.out.println(painter.projectiles.size());
		}
	}
	
	public void updateState() {
    	checkMove();
    	
		if (isFiring) {
			if (fireCounter > 0) {
				fireCounter -= 1;
			}else {
				isFiring = false;
			}
		}
		
		if ((!isMovingRight)&&(!isMovingLeft)) {
			if(leftKeyDown) {
				velocity[0] -= MOVESPEED;
				isMovingLeft = true;
			}else if(rightKeyDown) {
				velocity[0] += MOVESPEED;
				isMovingRight = true;
			} 
		}else if ((isMovingRight) && !rightKeyDown) {
			velocity[0] -= MOVESPEED;
			isMovingRight = false;
		}else if ((isMovingLeft) && !leftKeyDown) {
			velocity[0] += MOVESPEED;
			isMovingLeft = false;
		}
		
		
    	playerView.relocate(getX(),getY());
    	playerView.setScaleX(getFaceDir());
    	staffView.relocate(getStaffX(), getStaffY());
    	staffView.setRotate(getStaffRotation());
    	repuireFire();
	}
}
