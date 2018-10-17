package magicGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile {
	double x, y, degree;
	double [] velocity;
	ImageView imageView;
	CheckBox checkBox; 
	
	static final int BASICBULLET = 0;
	
	public Projectile(int projectileID, double x, double y, double dx, double dy, double degree) {
		this.x = x;
		this.y = y;
		this.degree = degree;
		
		
		switch (projectileID){
		case BASICBULLET:
			velocity = new double[]{dx * 10, dy * 10};
			imageView = new ImageView(new Image("/images/bullet.png"));
			imageView.relocate(this.x, this.y);
			imageView.setRotate(degree);
			degree = 90 - degree;
			double diffX = 14*(Math.cos(degree*((2*Math.PI)/360)));
			double diffY = -14*(Math.sin(degree*((2*Math.PI)/360)));
			double centerX = this.x + 14 + diffX;
			double centerY = this.y + 27.5 + diffY;
			checkBox = new CheckBox(centerX, centerY, 13);
			painter.softCB.add(checkBox);
			break;
		default:
			break;
		}
			
	}
	
	public void setNewCoor(double[] tempVelocity) {
		x += tempVelocity[0];
		y += tempVelocity[1];
		imageView.relocate(x, y);
		imageView.setRotate(degree);
	}
}
