package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Projectile {
	public static final Image earthB = new Image("/images/bullet_round_earth.png"),
			fireB = new Image("/images/bullet_round_fire.png"),
			darkB = new Image("/images/bullet_round_dark.png"),
			lightB = new Image("/images/bullet_round_light.png"),
			waterB = new Image("/images/bullet_round_water.png"),
			windB = new Image("/images/bullet_round_wind.png");
	
	int damageLow, damageRange, critical, timeCounter;
	double x, y, dx, dy, degree, speed, centerX, centerY;
	double[] velocity;
	Image image;
	ImageView imageView;
	ArrayList<ImageView> imageViews;
	CheckBox checkBox;
	boolean fading;
	boolean[] solvedLink;
	double scale;
	SpellConstruct spellConstruct;

	static final int BASICBULLET = 0;

	public Projectile(SpellConstruct spellConstruct, double x, double y, double dx, double dy, double degree) {
		this.spellConstruct = spellConstruct;
		imageViews = new ArrayList<ImageView>();
		this.degree = degree;
		this.dx = dx;
		this.dy = dy;
		solvedLink = new boolean[] { false, false, false };
		fading = false;
		speed = 10;
		scale = 1;
		damageLow = 50;
		damageRange = 20;
		critical = 20;
		timeCounter = 0;

		switch (spellConstruct.effect) {
		case SpellConstruct.EEARTH:
			damageLow = 70;
			image = earthB;
			imageView = new ImageView(image);
			break;
		case SpellConstruct.EFIRE:
			damageLow = 60;
			damageRange = 30;
			image = fireB;
			imageView = new ImageView(image);
			break;
		case SpellConstruct.EDARK:
			image = darkB;
			imageView = new ImageView(image);
			break;
		case SpellConstruct.ELIGHT:
			damageLow = 40;
			damageRange = 10;
			image = lightB;
			imageView = new ImageView(image);
			break;
		case SpellConstruct.EWATER:
			speed = 8;
			critical = 30;
			image = waterB;
			imageView = new ImageView(image);
			break;
		case SpellConstruct.EWIND:
			speed = 12;
			critical = 40;
			damageLow = 30;
			image = windB;
			imageView = new ImageView(image);
			break;
		}
		
		//System.out.println("end e");

		for (Integer c : spellConstruct.change) {
			switch (c) {
			case SpellConstruct.CSPREDED:
				scale *= 0.6;
				damageLow *= 0.2;
				break;
			case SpellConstruct.CGREATER:
				scale *= 1.2;
				damageLow *= 1.2;
				break;
			case SpellConstruct.CFASTER:
				speed *= 1.2;
				break;
			case SpellConstruct.CSLOWER:
				speed *= 0.8;
				break;
			case SpellConstruct.CHOMING:
				//System.out.println("start hom");
				double[] shortest = { dx * 1469, dy * 1469 };
				for (Enemy e : painter.enemys) {
					if (Math.sqrt(Math.pow(e.getCenterX() - x, 2) + Math.pow(e.getCenterY() - y, 2)) < 
							Math.sqrt(Math.pow(shortest[0], 2) + Math.pow(shortest[1], 2))) {
						shortest[0] = e.getCenterX() - x;
						shortest[1] = e.getCenterY() - y;
					}
				}
				//System.out.println("part 1");
				double degreeRad = Math.atan(shortest[1] / shortest[0]);
				dx = (Math.cos(degreeRad));
				dy = (Math.sin(degreeRad));
				this.dx = dx;
				this.dy = dy;
				degree = degreeRad * (180 / Math.PI);
				degree = -degree - 90;
				//System.out.println("part 2");
				if (shortest[0] < 0) {
					dx = -dx;
					dy = -dy;
					this.dx = -dx;
					this.dy = -dy;
					degree = -degree + 180;
				} else {
					degree = -degree;
				}
				this.degree = degree;

			}
		}
		//System.out.println("end c");
		
		switch (spellConstruct.boot) {
		case SpellConstruct.BSPRED:
			for (int i = 0; i < 4; i++) {
				double randDegree = Math.random() * 50 - 25;
				ArrayList<Integer> forSC = new ArrayList<Integer>();
				forSC.add(SpellConstruct.BBULLET);
				forSC.add(spellConstruct.effect);
				forSC.add(SpellConstruct.CSPREDED);
				for (Integer c : spellConstruct.change) {
					forSC.add(c);
				}
				painter.projectiles.add(new SpellConstruct(forSC.toArray(new Integer[forSC.size()])), x, y,
						Math.cos(getRadianDegree(randDegree)), Math.sin(getRadianDegree(randDegree)),
						this.degree + randDegree);
			}
		case SpellConstruct.BBULLET:
			this.x = x - imageView.getImage().getWidth() / 2;
			this.y = y - imageView.getImage().getHeight() / 2;
			velocity = new double[] { dx * speed, dy * speed };
			imageView.relocate(this.x, this.y);
			imageView.setRotate(degree);
			imageView.setScaleX(scale);
			imageView.setScaleY(scale);
			checkBox = new CheckBox(getCenterX(), getCenterY(), 13 * scale);
			painter.softCB.add(checkBox);
			break;
		default:
			break;
		}
		
		//System.out.println("end b");
		
		painter.pane.getChildren().add(imageView);
		imageViews.add(imageView);

	}

	public Projectile(int projectileID, double x, double y, double dx, double dy, double degree) {
		imageViews = new ArrayList<ImageView>();
		this.degree = degree;
		fading = false;

		switch (projectileID) {
		case BASICBULLET:
			this.x = x - 14;
			this.y = y - 14;
			velocity = new double[] { dx * 10, dy * 10 };
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
		// System.out.println(1);

	}

	public void changeOldView() {
		if (imageViews.size() > 6) {
			painter.pane.getChildren().remove(imageViews.remove(0));
		}
		for (ImageView iv : imageViews) {
			iv.setScaleX(iv.getScaleX() * 0.85);
			iv.setScaleY(iv.getScaleY() * 0.85);
			iv.setOpacity(iv.getOpacity() * 0.8);
		}
	}

	public void startFading(CheckBox cb) {
		if (spellConstruct.change.indexOf(SpellConstruct.CJUMP) != -1) {
			//System.out.println("jump");
			switch (checkBox.checkPosition(cb)) {
			case 0:
				degree = 180 - degree;
				dy = -dy;
				velocity[1] = -velocity[1];
				break;
			case 1:
				degree = -degree;
				dx = -dx;
				velocity[0] = -velocity[0];
				break;
			default:
				break;
			}
			spellConstruct.change.remove(spellConstruct.change.indexOf(SpellConstruct.CJUMP));
			setNewCoor();
		}else {
			fading = true;
			painter.particles.add(x + 14 + velocity[0], y + 14 + velocity[1]);
			
		}
	}

	public boolean fadingMove() {
		changeOldView();
		painter.pane.getChildren().remove(imageViews.remove(0));
		if (imageViews.size() <= 0) {
			return true;
		}
		return false;
	}

	public boolean getHit() {
		for (Enemy enemy : painter.enemys) {
			if (checkBox.isOverlap(enemy.checkBox, velocity)) {
				double criticCount = Math.random() * 100;
				int type = 0;
				if (criticCount < critical) {
					type = 1;
				}
				enemy.getHit((int) ((Math.random() * damageRange + damageLow) * (type + 1)), type);
				BloodParticle.generateParticles(x, y, degree,1);

				startFading(enemy.checkBox);
				return true;
			}
		}

		return false;
	}
	
	public boolean update() {
		if (fading) {
			if (fadingMove()) {
				painter.projectiles.remove(this);
				painter.softCB.remove(checkBox);
				checkBox = null;
				return true;
			}
		}else {
			if (getHit()) {
				
			}else if (!this.checkBox.isCollision(velocity)) {
				setNewCoor();
			}else {
				BloodParticle.generateParticles(x, y, degree,0);
				startFading(this.checkBox.getCollision(velocity));
			}
			
		}
		return false;
	}

	public double getCenterX() {
		double degree = 90 - this.degree;
		double diffX = (imageView.getImage().getWidth() / 2) * (Math.cos(degree * ((2 * Math.PI) / 360)));
		return this.x + imageView.getImage().getWidth() / 2 + diffX;

	}

	public double getCenterY() {
		double degree = 90 - this.degree;
		double diffY = -(imageView.getImage().getHeight() / 2) * (Math.sin(degree * ((2 * Math.PI) / 360)));
		return this.y + imageView.getImage().getHeight() / 2 + diffY;
	}

	public double getRadianDegree() {
		double degree = 90 - this.degree;
		return -degree * (Math.PI / 180);
	}

	public double getRadianDegree(double exDegree) {
		double degree = 90 - this.degree + exDegree;
		return -degree * (Math.PI / 180);
	}

	public void setNewCoor() {
		changeOldView();
		x += velocity[0];
		y += velocity[1];
		checkBox.move(velocity);
		switch (spellConstruct.effect) {
		case SpellConstruct.EEARTH:
			velocity[1] += 0.2;
			imageView = new ImageView(image);
		default :
			imageView = new ImageView(image);
			break;
		}

		timeCounter++;
		if (spellConstruct.links > 0) {
			for (int i = 0; i < spellConstruct.links; i += 2) {
				// System.out.println("num" + i);
				switch (spellConstruct.link.get(i)) {
				case 32:
					if (timeCounter > 6 && !solvedLink[i / 2]) {
						painter.projectiles.add(new SpellConstruct(spellConstruct.leftFrame.get(i / 2)), getCenterX(),
								getCenterY(), Math.cos(getRadianDegree(spellConstruct.link.get(i + 1))),
								Math.sin(getRadianDegree(spellConstruct.link.get(i + 1))),
								this.degree + spellConstruct.link.get(i + 1));
						solvedLink[i / 2] = true;
					}
					break;
				case 33:
					if (timeCounter > 12 && !solvedLink[i / 2]) {
						painter.projectiles.add(new SpellConstruct(spellConstruct.leftFrame.get(i / 2)), getCenterX(),
								getCenterY(), Math.cos(getRadianDegree(spellConstruct.link.get(i + 1))),
								Math.sin(getRadianDegree(spellConstruct.link.get(i + 1))),
								this.degree + spellConstruct.link.get(i + 1));
						solvedLink[i / 2] = true;
					}
					break;
				case 34:
					if (timeCounter > 18 && !solvedLink[i / 2]) {
						painter.projectiles.add(new SpellConstruct(spellConstruct.leftFrame.get(i / 2)), getCenterX(),
								getCenterY(), Math.cos(getRadianDegree(spellConstruct.link.get(i + 1))),
								Math.sin(getRadianDegree(spellConstruct.link.get(i + 1))),
								this.degree + spellConstruct.link.get(i + 1));
						solvedLink[i / 2] = true;
					}
					break;
				case 35:
					if (timeCounter > 30 && !solvedLink[i / 2]) {
						painter.projectiles.add(new SpellConstruct(spellConstruct.leftFrame.get(i / 2)), getCenterX(),
								getCenterY(), Math.cos(getRadianDegree(spellConstruct.link.get(i + 1))),
								Math.sin(getRadianDegree(spellConstruct.link.get(i + 1))),
								this.degree + spellConstruct.link.get(i + 1));
						solvedLink[i / 2] = true;
					}
					break;
				}
			}
		}

		imageView.relocate(x, y);
		imageView.setRotate(degree);
		imageView.setScaleX(scale);
		imageView.setScaleY(scale);
		painter.pane.getChildren().add(imageView);
		imageViews.add(imageView);
	}
}
