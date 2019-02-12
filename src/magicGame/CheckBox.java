package magicGame;

public class CheckBox {
	double width, height, r;
	private double x, y;
	private String type;

	public CheckBox(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		type = "rectangle";
	}

	public CheckBox(double x, double y, double d) {
		this.x = x;
		this.y = y;
		this.r = d;
		type = "circle";
	}

	public int checkPosition(CheckBox cb) {
		if ((cb.y > this.y + this.height) ||(cb.y + cb.height < this.y)) {
			return 0;
		}
		return 1;
	}

	public boolean isOverlap(CheckBox cb, double[] velocity) {
		if (type == "circle") {
			double DeltaX = x - Math.max(cb.x, Math.min(x, cb.x + cb.width));
			double DeltaY = y - Math.max(cb.y, Math.min(y, cb.y + cb.height));
			return (DeltaX * DeltaX + DeltaY * DeltaY) < (r * r);
		}
		return (!(this.x + this.width + velocity[0] <= cb.x || this.x + velocity[0] >= cb.x + cb.width
				|| this.y + this.height + velocity[1] <= cb.y || this.y + velocity[1] >= cb.y + cb.height));
	}

	public boolean isCollision(double[] velocity) {
		for (CheckBox cb : painter.hardCB) {
			if (this.isOverlap(cb, velocity)) {
				return true;
			}
		}
		for (CheckBox cb : painter.fieldCB) {
			if (this.isOverlap(cb, velocity)) {
				return true;
			}
		}
		return false;
	}
	
	public CheckBox getCollision(double[] velocity) {
		for (CheckBox cb : painter.hardCB) {
			if (this.isOverlap(cb, velocity)) {
				return cb;
			}
		}
		for (CheckBox cb : painter.fieldCB) {
			if (this.isOverlap(cb, velocity)) {
				return cb;
			}
		}
		return new CheckBox(0,0,0);
	}

	public boolean isOnGround(CheckBox cb, double[] velocity) {
		return (((this.x + this.width + velocity[0] > cb.x && this.x + this.width + velocity[0] < cb.x + cb.width)
				|| (this.x + velocity[0] > cb.x && this.x + velocity[0] < cb.x + cb.width))
				&& (this.y + this.height + velocity[1] == cb.y));
	}

	public void move(double[] velocity) {
		this.x += velocity[0];
		this.y += velocity[1];
	}
}
