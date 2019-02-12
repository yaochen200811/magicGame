package magicGame;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BloodParticle extends Particle{
	int life;
	Circle circle;
	double op;
	double[] velocity;

	public BloodParticle(double x, double y, double degree, double v, int type) {
		life = 20;
		circle = new Circle();
		switch(type) {
		case 0:
			circle.setFill(Color.CORNFLOWERBLUE);
			//circle.setEffect(dsc);
			break;
		case 1:
			circle.setFill(Color.RED);
			//circle.setEffect(dsr);
			break;
		}
		
		op = 1;
		this.x = x;
		this.y = y;
		circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setRadius(3);
		painter.pane.getChildren().add(circle);
		velocity = new double[2];
		this.degree = 90 - degree;
		this.degree = -this.degree * (Math.PI / 180);
		velocity[0] = -Math.cos(this.degree) * v;
		velocity[1] = -Math.sin(this.degree) * v;
	}
	
	public static void generateParticles(double x, double y, double degree, int type) {
		int num = (int)(Math.random() * 15) + 10;
		for (int i = 0; i < num; i++) {
			painter.particles.add(new BloodParticle(x,y,degree + Math.random() * 140 - 70, Math.random()*5+2, type));
		}
	}
	
	public boolean update() {
		if(life > 0) {
			life -= 1;
			if (velocity[1] < 7) {
				velocity[1] += 0.2;
			}
			x += velocity[0];
			y += velocity[1];
			circle.setCenterX(x);
			circle.setCenterY(y);
			return true;
		}else if(op > 0.2){
			op -= 0.1;
			if (velocity[1] < 7) {
				velocity[1] += 0.2;
			}
			x += velocity[0];
			y += velocity[1];
			circle.setCenterX(x);
			circle.setCenterY(y);
			circle.setOpacity(op);
			return true;
		}
		painter.pane.getChildren().remove(circle);
		painter.particles.remove(this);
		return false;
	}
}
