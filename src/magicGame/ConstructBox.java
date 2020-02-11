package magicGame;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConstructBox {
	private static final int STARTX = 146, STARTY = 38;
	ArrayList<ImageView> icons;
	ArrayList<ImageView> spellView;
	ArrayList<MagicFrame> spells;
	ArrayList<Integer> error, angels;
	Group view;
	ConstructPanel cp;
	int x, y, startI;
	int deepth;
	
	public ConstructBox(ConstructPanel cp, int x, int y, int deepth) {
		icons = new ArrayList<ImageView>();
		spells = new ArrayList<MagicFrame>();
		spellView = new ArrayList<ImageView>();
		error = new ArrayList<Integer>();
		angels = new ArrayList<Integer>();
		view = new Group();
		this.deepth = deepth;
		this.cp = cp;
		this.x = x;
		this.y = y;
		for (int j = 0; j < 3; j ++) {
			for (int i = 0; i < 3; i ++) {
				icons.add(new ImageView(cp.iconBack));
				icons.get(icons.size()-1).relocate(getStartx() + (60 * i), getStarty()  + (60 * j));
				view.getChildren().add(icons.get(icons.size()-1));
				spellView.add(new ImageView());
				spellView.get(spellView.size()-1).relocate(getStartx() + (60 * i), getStarty()  + (60 * j));
				view.getChildren().add(spellView.get(spellView.size()-1));
				spells.add(new MagicFrame(-1));
			}
			angels.add(0);
		}
		error.add(0);
		resetFocus(0);
		cp.view.getChildren().add(view);
	}
	
	public double getStartx() {
		return STARTX + (x - startI) * 200;
	}
	
	public double getStarty() {
		return STARTY + y * 60;
	}
	
	public void setX(int xValue) {
		x = xValue;
		updateView();
	}
	
	public void updateView() {
		for (int j = 0; j < 3; j ++) {
			for (int i = 0; i < 3; i ++) {
				icons.get(j*3 + i).relocate(getStartx() + (60 * i), getStarty()  + (60 * j));
				spellView.get(j*3 + i).relocate(getStartx() + (60 * i), getStarty()  + (60 * j));
			}
		}
	}
	
	public void showBox(int startI) {
		this.startI = startI;
		if ((x >= startI) && (x <= startI + 4)){
			view.setVisible(true);
			view.toFront();
		}else {
			view.setVisible(false);
		}
	}
	
	public void setSpell(int index, int ID) {
		if (ID == -1) {
			view.getChildren().remove(spellView.get(index));
			spells.set(index, cp.mfs.getMagicFrame(ID));
			return;
		}
		view.getChildren().remove(spellView.get(index));
		spellView.set(index, new ImageView(cp.mfs.getImage(ID)));
		spellView.get(index).relocate(getStartx() + 60 * (index % 3), getStarty()  + 60 * (index/3));
		view.getChildren().add(spellView.get(index));
		spells.set(index, cp.mfs.getMagicFrame(ID));
	}
	
	public int getSpell(int index) {
		return spells.get(index).id;
	}
	
	public void setAngel(int index, int angel) {
		angels.set(index - 6, angel);
	}
	
	public void resetFocus(int nowIcon) {
		view.getChildren().remove(icons.get(nowIcon));
		if (error.indexOf(nowIcon) != -1) {
			icons.set(nowIcon, new ImageView(cp.iconBackRed));
		}else {
			icons.set(nowIcon, new ImageView(cp.iconBack));
		}
		icons.get(nowIcon).relocate(getStartx() + 60 * (nowIcon % 3), getStarty()  + 60 * (nowIcon/3));
		view.getChildren().add(icons.get(nowIcon));
		spellView.get(nowIcon).toFront();
	}
	
	public void setFocus(int nowIcon) {
		view.getChildren().remove(icons.get(nowIcon));
		icons.set(nowIcon, new ImageView(cp.iconBackYel));
		icons.get(nowIcon).relocate(getStartx() + 60 * (nowIcon % 3), getStarty()  + 60 * (nowIcon/3));
		view.getChildren().add(icons.get(nowIcon));
		spellView.get(nowIcon).toFront();
	}
	
	public void clearBox() {
		cp.view.getChildren().remove(view);
	}
	
	public boolean hasError() {
		return error.size() > 0;
	}
	
	public ArrayList<Integer> getMagic(){
		ArrayList<Integer> tempMagic = new ArrayList<Integer>();
		for (int i: new int[]{0,3,1,2,4,5}) {
			if (spells.get(i).type != "empty") {
				tempMagic.add(spells.get(i).id);
			}
		}
		
		for (int i: new int[]{0,1,2}) {
			int ti = i + 6;
			if (spells.get(ti).type != "empty") {
				tempMagic.add(spells.get(ti).id);
				tempMagic.add(angels.get(i));
			}
		}
		return tempMagic;
	}
}
