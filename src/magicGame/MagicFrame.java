package magicGame;

import javafx.scene.image.Image;

public class MagicFrame {
	public int cost, id;
	public String type;
	public Image image;
	
	public MagicFrame(int id) {
		this.id = id;
		switch (id) {
		case 0:
			cost = 0;
			type = "boot";
			image = new Image("/images/BBullet.png");
			break;
		case 1:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 2:
			cost = 0;
			type = "boot";
			image = new Image("/images/BSpred.png");
			break;
		case 10:
			cost = 0;
			type = "element";
			image = new Image("/images/EEarth.png");
			break;
		case 11:
			cost = 0;
			type = "element";
			image = new Image("/images/EWater.png");
			break;
		case 12:
			cost = 0;
			type = "element";
			image = new Image("/images/EWind.png");
			break;
		case 13:
			cost = 0;
			type = "element";
			image = new Image("/images/EFire.png");
			break;
		case 14:
			cost = 0;
			type = "element";
			image = new Image("/images/EDark.png");
			break;
		case 15:
			cost = 0;
			type = "element";
			image = new Image("/images/ELight.png");
			break;
		case 20:
			cost = 0;
			type = "change";
			image = new Image("/images/CGreater.png");
			break;
		case 21:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 22:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 23:
			cost = 0;
			type = "change";
			image = new Image("/images/CJump.png");
			break;
		case 24:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 25:
			cost = 0;
			type = "change";
			image = new Image("/images/CHoming.png");
			break;
		case 26:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 30:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 31:
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		case 32:
			cost = 0;
			type = "link";
			image = new Image("/images/LAfter1.png");
			break;
		case 33:
			cost = 0;
			type = "link";
			image = new Image("/images/LAfter2.png");
			break;
		case 34:
			cost = 0;
			type = "link";
			image = new Image("/images/LAfter3.png");
			break;
		case 35:
			cost = 0;
			type = "link";
			image = new Image("/images/LAfter5.png");
			break;
		default:
			id = -1;
			cost = 0;
			type = "empty";
			image = new Image("/images/ELight.png");
			break;
		}
	}
}
