package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile {
	int damageLow, damageRange, critical, timeCounter;
	double x, y, dx, dy, degree, speed, centerX, centerY;
	double [] velocity;
	ImageView imageView;
	ArrayList<ImageView> imageViews;
	CheckBox checkBox; 
	boolean fading;
	boolean[] solvedLink;
	SpellConstruct spellConstruct;
	
	static final int BASICBULLET = 0;
	
	public Projectile(SpellConstruct spellConstruct, double x, double y, double dx, double dy, double degree) {
		this.spellConstruct = spellConstruct;
		imageViews = new ArrayList<ImageView>();
		this.degree = degree;
		this.dx = dx;
		this.dy = dy;
		solvedLink = new boolean[]{false,false,false};
		fading = false;
		speed = 10;
		damageLow = 50;
		damageRange = 20;
		critical = 20;
		timeCounter = 0;
		
		switch(spellConstruct.effect) {
		case SpellConstruct.EEARTH:
			speed = 10;
			damageLow = 70;
			imageView = new ImageView(new Image("/images/bullet_round_earth.png"));
			break;
		case SpellConstruct.EFIRE:
			speed = 10;
			damageLow = 60;
			damageRange = 30;
			imageView = new ImageView(new Image("/images/bullet_round_fire.png"));
			break;
		case SpellConstruct.EDARK:
			speed = 10;
			imageView = new ImageView(new Image("/images/bullet_round_dark.png"));
			break;
		case SpellConstruct.ELIGHT:
			speed = 10;
			damageLow = 40;
			damageRange = 10;
			imageView = new ImageView(new Image("/images/bullet_round_light.png"));
			break;
		case SpellConstruct.EWATER:
			speed = 8;
			critical = 30;
			imageView = new ImageView(new Image("/images/bullet_round_water.png"));
			break;
		case SpellConstruct.EWIND:
			speed = 12;
			critical = 40;
			damageLow = 30;
			imageView = new ImageView(new Image("/images/bullet_round_wind.png"));
			break;
		}
		
		switch (spellConstruct.boot){
		case SpellConstruct.BBULLET:
			this.x = x - imageView.getImage().getWidth() / 2;
			this.y = y - imageView.getImage().getHeight() / 2;
			velocity = new double[]{dx * speed, dy * speed};			
			imageView.relocate(this.x, this.y);
			imageView.setRotate(degree);
			degree = 90 - degree;
			double diffX = (imageView.getImage().getWidth() / 2)*(Math.cos(degree*((2*Math.PI)/360)));
			double diffY = -(imageView.getImage().getHeight() / 2 )*(Math.sin(degree*((2*Math.PI)/360)));
			centerX = this.x + imageView.getImage().getWidth() / 2 + diffX;
			centerY = this.y + imageView.getImage().getHeight() / 2 + diffY;
			checkBox = new CheckBox(centerX, centerY, 13);
			painter.softCB.add(checkBox);
			break;
		default:
			break;
		}
	}
	
	public Projectile(int projectileID, double x, double y, double dx, double dy, double degree) {
		imageViews = new ArrayList<ImageView>();
		this.degree = degree;
		fading = false;
		
		
		switch (projectileID){
		case BASICBULLET:
			this.x = x - 14;
			this.y = y - 14;
			velocity = new double[]{dx * 10, dy * 10};
			imageView = new ImageView(new Image("/images/bullet_round_fire.png"));
			imageView.relocate(this.x, this.y);
			imageView.setRotate(degree);
			checkBox = new CheckBox(getCenterX(), getCenterY(), 13);
			painter.softCB.add(checkBox);
			break;
		default:
			break;
		}
		
		painter.pane.getChildren().add(imageView);
		imageViews.add(imageView);
		System.out.println(1);
		
	}
	
	public void changeOldView() {
		boolean needRemove = false;
		for (ImageView iv: imageViews) {
			iv.setScaleX(iv.getScaleX() - 0.1);
			iv.setScaleY(iv.getScaleY() - 0.1);
			iv.setOpacity(iv.getOpacity() - 0.1);
			if (iv.getScaleX() < 0.1) {
				needRemove = true;
				painter.pane.getChildren().remove(iv);
			}
		}
		if (needRemove) {
			imageViews.remove(0);
		}
		
	}
	
	public void startFading() {
		fading = true;
		painter.particles.add(x + 14 + velocity[0], y + 14 + velocity[1]);
	}
	
	public boolean fadingMove() {
		changeOldView();
		if (imageViews.size() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean getHit() {
		for (Enemy enemy: painter.enemys) {
			if(checkBox.isOverlap(enemy.checkBox, velocity)) {
				double criticCount = Math.random() * 100;
				int type = 0;
				if (criticCount < critical) {
					type = 1;
				}
				enemy.getHit((int)((Math.random() * damageRange + damageLow) * (type + 1)), type);
				startFading();
				return true;
			}
		}
		
		return false;
	}
	
	public double getCenterX() {
		double degree = 90 - this.degree;
		double diffX = (imageView.getImage().getWidth() / 2)*(Math.cos(degree*((2*Math.PI)/360)));
		return this.x + imageView.getImage().getWidth() / 2 + diffX;
		
	}
	
	public double getCenterY() {
		double degree = 90 - this.degree;
		double diffY = -(imageView.getImage().getHeight() / 2 )*(Math.sin(degree*((2*Math.PI)/360)));
		return this.y + imageView.getImage().getHeight() / 2 + diffY;
	}
	
	public double getRadianDegree() {
		double degree = 90 - this.degree;
		return - degree * (Math.PI/180);
	}
	
	public double getRadianDegree(double exDegree) {
		double degree = 90 - this.degree + exDegree;
		return - degree * (Math.PI/180);
	}
	
	public void setNewCoor(double[] tempVelocity) {
		changeOldView();
		x += tempVelocity[0];
		y += tempVelocity[1];
		switch(spellConstruct.effect) {
		case SpellConstruct.EEARTH:
			velocity[1] += 0.2;
			imageView = new ImageView(new Image("/images/bullet_round_earth.png"));
			break;
		case SpellConstruct.EFIRE:
			imageView = new ImageView(new Image("/images/bullet_round_fire.png"));
			break;
		case SpellConstruct.EDARK:
			imageView = new ImageView(new Image("/images/bullet_round_dark.png"));
			break;
		case SpellConstruct.ELIGHT:
			imageView = new ImageView(new Image("/images/bullet_round_light.png"));
			break;
		case SpellConstruct.EWATER:
			imageView = new ImageView(new Image("/images/bullet_round_water.png"));
			break;
		case SpellConstruct.EWIND:
			imageView = new ImageView(new Image("/images/bullet_round_wind.png"));
			break;
		}
		
		timeCounter ++;
		if(spellConstruct.links > 0) {
			for(int i = 0; i < spellConstruct.links; i+=2) {
				System.out.println("num" + i);
				switch(spellConstruct.link.get(i)) {
				case 32:
					if(timeCounter > 6 && !solvedLink[i/2]) {
						painter.projectiles.add(
							new SpellConstruct(spellConstruct.leftFrame.get(i/2)), 
							getCenterX(), getCenterY(), Math.cos(getRadianDegree(spellConstruct.link.get(i + 1))), Math.sin(getRadianDegree(spellConstruct.link.get(i + 1))), this.degree + spellConstruct.link.get(i + 1));
						solvedLink[i/2] = true;
					}
					break;
				case 33:
					if(timeCounter > 12 && !solvedLink[i/2]) {
						painter.projectiles.add(
							new SpellConstruct(spellConstruct.leftFrame.get(i/2)), 
							x, y, dx, dy, degree + spellConstruct.link.get(i + 1));
						solvedLink[i/2] = true;
					}
					break;
				case 34:
					if(timeCounter > 18 && !solvedLink[i/2]) {
						painter.projectiles.add(
							new SpellConstruct(spellConstruct.leftFrame.get(i/2)), 
							x, y, dx, dy, degree + spellConstruct.link.get(i + 1));
						solvedLink[i/2] = true;
					}
					break;
				case 35:
					if(timeCounter > 30 && !solvedLink[i/2]) {
						painter.projectiles.add(
							new SpellConstruct(spellConstruct.leftFrame.get(i/2)), 
							x, y, dx, dy, degree + spellConstruct.link.get(i + 1));
						solvedLink[i/2] = true;
					}
					break;
				}
			}
		}
		
		imageView.relocate(x, y);
		imageView.setRotate(degree);
		painter.pane.getChildren().add(imageView);
		imageViews.add(imageView);
	}
}
