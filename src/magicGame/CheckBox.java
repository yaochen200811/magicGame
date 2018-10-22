package magicGame;

public class CheckBox {
	int width, height, r;
	private double x, y;
	private String type;
	
	public CheckBox(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		type = "rectangle";
	}
	
	public CheckBox(double x, double y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		type = "circle";
	}
	
	public boolean isOverlap(CheckBox cb, double[] velocity) {
		if (type == "circle") {
			double DeltaX = x - Math.max(cb.x, Math.min(x, cb.x + cb.width));
			double DeltaY = y - Math.max(cb.y, Math.min(y, cb.y + cb.height));
			return (DeltaX * DeltaX + DeltaY * DeltaY) < (r * r);
		}
		return (!(this.x+this.width+velocity[0] <= cb.x ||
				this.x+velocity[0] >= cb.x+cb.width ||
				this.y+this.height+velocity[1] <= cb.y ||
				this.y+velocity[1] >= cb.y+cb.height));
		
//		boolean changed = true;
//		while ((!(this.x+this.width+velocity[0] < cb.x ||
//				this.x+velocity[0] > cb.x+cb.width ||
//				this.y+this.height+velocity[1] < cb.y ||
//				this.y+velocity[1] > cb.y+cb.height)) 
//				&& changed) {
//			changed = false;
//			if (!(this.x+this.width+velocity[0] < cb.x ||
//					this.x+velocity[0] > cb.x+cb.width)
//					&& velocity[0] != 0) {
//				if (velocity[0] > 0) {
//					velocity[0] -= 1;
//					changed = true;
//				}else if (velocity[0] < 0) {
//					velocity[0] += 1;
//					changed = true;
//				}	
//			}else if (!(this.y+this.height+velocity[1] < cb.y ||
//					this.y+velocity[1] > cb.y+cb.height)
//					&& velocity[1] != 0) {
//				if (velocity[1] > 0) {
//					velocity[1] -= 1;
//					changed = true;
//				}else if (velocity[1] < 0) {
//					velocity[1] += 1;
//					changed = true;
//				}
//			}
//		}	
	}
	
	public boolean isOnGround(CheckBox cb, double[] velocity) {
		return (((this.x+this.width+velocity[0] > cb.x && this.x+this.width+velocity[0] < cb.x+cb.width) ||
				(this.x+velocity[0] > cb.x && this.x+velocity[0] < cb.x+cb.width)) &&
				(this.y+this.height+velocity[1] == cb.y));
	}
	
	public void move(double[] velocity) {
		this.x += velocity[0];
		this.y += velocity[1];
	}
}
