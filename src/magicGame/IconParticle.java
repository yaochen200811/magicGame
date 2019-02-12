package magicGame;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class IconParticle extends Particle{
	static final double STARTX = 50, STARTY = 95;
	int nowX;
	double destX, destY, hue, veX, veY;
	boolean fading, huep;
	DropShadow ds;
	Circle circle;
	
	public IconParticle(int nowX, double degree, double hue) {
		this.nowX = nowX;
		this.degree = degree;
		this.state = 0;
		destX = 0;
		destY = 0;
		this.hue = hue;
		huep = true;
		fading = false;
		ds = new DropShadow();
		this.x = Math.cos(degree) * 35 + STARTX;
		this.y = Math.sin(degree) * 35 + STARTY  + nowX * 60;
		circle = new Circle();
		circle.setCenterX(x);
		circle.setCenterY(y);
		circle.setRadius(2.5);
		ds.setColor(Color.hsb(hue, 0, 0));
		circle.setFill(Color.hsb(hue, 0.8, 0.6));
		circle.setEffect(ds);
		painter.cp.view.getChildren().add(circle);
	}
	
	public boolean update() {
		switch (state) {
		case 0:
			if (huep) {
				hue += 0.7;
				if (hue > 180) {
					huep = false;
				}
			}else {
				hue -= 0.5;
				if (hue < 60) {
					huep = true;
				}
			}
			degree += Math.PI / 200;
			this.x = Math.cos(degree) * 35 + STARTX;
			this.y = Math.sin(degree) * 35 + STARTY + nowX * 60;
			circle.setCenterX(x);
			circle.setCenterY(y);
			circle.setFill(Color.hsb(hue, 0.8, 0.6));
			break;
		case 1:
			x += veX;
			y += veY;
			circle.setCenterX(x);
			circle.setCenterY(y);
			if ((Math.abs(destX - x) < Math.abs(veX)) && (Math.abs(destY - y) < Math.abs(veY))) {
//				circle.setCenterX(destX);
//				circle.setCenterY(destY);
				state = 2;
			}
			break;
		case 2:
			painter.cp.view.getChildren().remove(circle);
			painter.cp.ips.remove(this);
			return false;
		}
		return true;
	}
	
	public void startFade(double x, double y) {
		if (!fading) {
			state = 1;
			this.fading = true;
			this.destX = x;
			this.destY = y;
			double d = Math.atan((y - this.y)/ (x - this.x));
			veX = Math.cos(d) * 15;
			veY = Math.sin(d) * 15;
		}
	}
}
