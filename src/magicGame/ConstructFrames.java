package magicGame;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class ConstructFrames {
	private static final int STARTX = 25, STARTY = 70;
	ArrayList<ImageView> spellView;
	ArrayList<MagicFrame> spells;
	ArrayList<ImageView> icons;
	Group view;
	ConstructPanel cp;
	int nowX, nowStart;
	
	public ConstructFrames(ConstructPanel cp) {
		spells = new ArrayList<MagicFrame>();
		spellView = new ArrayList<ImageView>();
		view = new Group();
		this.cp = cp;
		nowX = 0;
		cp.view.getChildren().add(view);
		icons = new ArrayList<ImageView>();
		for (int i = 0; i < 9; i++) {
			icons.add(new ImageView(cp.iconBack));
			icons.get(i).relocate(STARTX, STARTY + (i) * 60);
			view.getChildren().add(icons.get(i));
		}
	}
	
	public int getSpellID() {
		return spells.get(nowX).id;
	}
	
	public MagicFrame getSpell() {
		return spells.get(nowX);
	}
	
	public void clearSpells() {
		for (ImageView iv: spellView) {
			view.getChildren().remove(iv);
		}
		spells =  new ArrayList<MagicFrame>();
		spellView = new ArrayList<ImageView>();
	}
	
	public void showSpells() {
		for (int i = 0; i < spells.size(); i++) {
			if ((i >= nowX) && (i < 9)){
				spellView.get(i).relocate(STARTX, STARTY + (i-nowX) * 60);
				spellView.get(i).setVisible(true);
			}else {
				spellView.get(i).setVisible(false);
			}
		}
	}
	
	public void setSpells(String type) {
		clearSpells();
		nowX = 0;
		spells.addAll(cp.mfs.getMfs(type));
		for (MagicFrame mf: spells) {
			spellView.add(new ImageView(mf.image));
			view.getChildren().add(spellView.get(spellView.size() - 1));
		}
		showSpells();
	}
}
