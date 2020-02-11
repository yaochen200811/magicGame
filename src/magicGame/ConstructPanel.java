package magicGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ConstructPanel {
	Image bordBack, bordSide, bordStateBar;
	Image leftMagic = new Image("/images/leftMagic.png"),
			rightMagic = new Image("/images/rightMagic.png"),
			leftArrow = new Image("/images/leftArrow.png"),
			rightArrow = new Image("/images/rightArrow.png"),
			upArrow = new Image("/images/upArrow.png"),
			downArrow = new Image("/images/downArrow.png"),
			iconBack = new Image("/images/iconBack.png"),
			iconBackRed = new Image("/images/iconBackRed.png"),
			iconBackYel = new Image("/images/iconBackYel.png");
	ImageView downArrowView, upArrowView, rightArrowView, leftArrowView, leftMagicView, rightMagicView, bordBackView, bordSideView, bordStateBarView, iconBackView, iconBackRedView, iconBackYelView;
	Group view;
	ConstructFrames frames;
	ArrayList<ConstructBox> boxs;
	Integer[] magicL, magicR;
	Stage stage;
	ArrayList<IconParticle> ips;
	MagicFrames mfs;
	int nowIcon, nowBox, nowStart;
	
	private Pattern pFile = Pattern.compile("^.*\\.magic$");
	
	public ConstructPanel(Stage stage) {
		this.stage = stage;
		view = new Group();
		painter.pane.getChildren().add(view);
		view.setVisible(false);
		ips = new ArrayList<IconParticle>();
		mfs = new MagicFrames();
		
		bordBack = new Image("/images/bordBack.png");
		bordBackView = new ImageView(bordBack);
		bordBackView.relocate(96, 3);
		view.getChildren().add(bordBackView);
		
		bordSide = new Image("/images/bordSide.png");
		bordSideView = new ImageView(bordSide);
		bordSideView.relocate(4, 3);
		view.getChildren().add(bordSideView);
		frames = new ConstructFrames(this);
		
		bordStateBar = new Image("/images/bordStateBar.png");
		bordStateBarView = new ImageView(bordStateBar);
		bordStateBarView.relocate(4, 667);
		view.getChildren().add(bordStateBarView);

		leftMagicView = new ImageView(leftMagic);
		leftMagicView.relocate(1150, 667);
		view.getChildren().add(leftMagicView);

		rightMagicView = new ImageView(rightMagic);
		rightMagicView.relocate(1200, 667);
		view.getChildren().add(rightMagicView);
		rightMagicView.setVisible(false);
		
		downArrowView = new ImageView(downArrow);
		downArrowView.relocate(25, 616);
		view.getChildren().add(downArrowView);
		downArrowView.setVisible(false);

		upArrowView = new ImageView(upArrow);
		upArrowView.relocate(25, 4);
		view.getChildren().add(upArrowView);
		upArrowView.setVisible(false);
		
		rightArrowView = new ImageView(rightArrow);
		rightArrowView.relocate(1226, 307);
		view.getChildren().add(rightArrowView);
		rightArrowView.setVisible(false);

		leftArrowView = new ImageView(leftArrow);
		leftArrowView.relocate(100, 307);
		view.getChildren().add(leftArrowView);
		leftArrowView.setVisible(false);
		
		
		
		magicL = new Integer[] {0,15};
		magicR = new Integer[] {0,15};
		nowIcon = 0;
		nowBox = 0;
		nowStart = 0;
		
		boxs = new ArrayList<ConstructBox>();
		switchToLeft();
	}
	
	public void updateLR() {
		leftArrowView.setVisible((nowStart > 0));
		rightArrowView.setVisible((nowStart + 5 < boxs.size()));
	}
	
	public void updateUD() {
		upArrowView.setVisible((frames.nowStart > 0));
		downArrowView.setVisible((frames.nowStart  + 9 < frames.spells.size()));
	}
	
	public void loadMagic(Integer[] magic, int deepth) {
		boxs.get(boxs.size() - 1).setSpell(0, magic[0]);
		boxs.get(boxs.size() - 1).setSpell(3, magic[1]);
		
		int[] avalibleSort = {1,2,4,5};
		int aIndex = 0;
		int index = 2;
		while ((index < magic.length) && (magic[index] < 30) && (magic[index] >= 20)) {
			boxs.get(boxs.size() - 1).setSpell(avalibleSort[aIndex], magic[index]);
			aIndex ++;
			index ++;
		}
		
		int[] avalibleSort2 = {6,7,8};
		aIndex = 0;
		int links = 0;
		ArrayList<Integer> link = new ArrayList<Integer>();
		while ((index < magic.length) && (magic[index] >= 30)) {
			link.add(magic[index]);
			boxs.get(boxs.size() - 1).setSpell(avalibleSort2[aIndex], magic[index]);
			index ++;
			link.add(magic[index]);
			boxs.get(boxs.size() - 1).setAngel(avalibleSort2[aIndex], magic[index]);
			index ++;
			links +=2;
			aIndex ++;
		}
		
		ArrayList<Integer> leftFrame = new ArrayList<Integer>();
		if (links > 0) {
			int checkCounter = 1;
			while (checkCounter >= 0) {
				if ((index >= magic.length) || magic[index] < 10) {
					checkCounter --;
				}else if ((index < magic.length) && magic[index] >= 30) {
					leftFrame.add(magic[index]);
					index ++;
					checkCounter ++;
				}
				if (checkCounter >= 0) {
					leftFrame.add(magic[index]);
					index ++;
				}
			}
			boxs.add(new ConstructBox(this, 
					boxs.get(boxs.size() - 1).x + 1,
					(deepth + 1), deepth + 1));
			loadMagic(leftFrame.toArray(new Integer[leftFrame.size()]), deepth + 1);
		}
		if (links > 2) {
			leftFrame = new ArrayList<Integer>();
			int checkCounter = 1;
			while (checkCounter >= 0) {
				
				if ((index >= magic.length) || magic[index] < 10) {
					checkCounter --;
				}else if ((index < magic.length) && magic[index] >= 30) {
					leftFrame.add(magic[index]);
					index ++;
					checkCounter ++;
				}
				if (checkCounter >= 0) {
					leftFrame.add(magic[index]);
					index ++;
				}
			}
			boxs.add(new ConstructBox(this, 
					boxs.get(boxs.size() - 1).x + 1,
					(deepth + 1), deepth + 1));
			loadMagic(leftFrame.toArray(new Integer[leftFrame.size()]), deepth + 1);
		}
		if (links > 4) {
			leftFrame = new ArrayList<Integer>();
			int checkCounter = 1;
			while (checkCounter >= 0) {
				
				if ((index >= magic.length) || magic[index] < 10) {
					checkCounter --;
				}else if ((index < magic.length) && magic[index] >= 30) {
					leftFrame.add(magic[index]);
					index ++;
					checkCounter ++;
				}
				if (checkCounter >= 0) {
					leftFrame.add(magic[index]);
					index ++;
				}
			}
			boxs.add(new ConstructBox(this, 
					boxs.get(boxs.size() - 1).x + 1,
					(deepth + 1), deepth + 1));
			loadMagic(leftFrame.toArray(new Integer[leftFrame.size()]), deepth + 1);
		}
		nowBox = 0;
		nowIcon = 0;
		nowStart = 0;
		boxs.get(nowBox).setFocus(nowIcon);
		updateLR();
	}
	
	public void showPanel() {
		view.toFront();
		for (ConstructBox box: boxs) {
			box.showBox(nowStart);
		}
		view.setVisible(true);
	}
	
	public void closePanel() {
		view.setVisible(false);
	}
	
	public void moveUp() {
		if (nowIcon > 2) {
			boxs.get(nowBox).resetFocus(nowIcon);
			nowIcon -= 3;
			boxs.get(nowBox).setFocus(nowIcon);
		}
	}
	
	public void moveDown() {
		if (nowIcon < 6) {
			boxs.get(nowBox).resetFocus(nowIcon);
			nowIcon += 3;
			boxs.get(nowBox).setFocus(nowIcon);
		}
	}
	
	public void moveLeft() {
		if (nowIcon > 0) {
			boxs.get(nowBox).resetFocus(nowIcon);
			nowIcon -= 1;
			boxs.get(nowBox).setFocus(nowIcon);
		}
	}
	
	public void moveRight() {
		if (nowIcon < 8) {
			boxs.get(nowBox).resetFocus(nowIcon);
			nowIcon += 1;
			boxs.get(nowBox).setFocus(nowIcon);
		}
	}
	
	public void GroupLeft() {
		if (nowBox > 0) {
			boxs.get(nowBox).resetFocus(nowIcon);
			nowBox -= 1;
			boxs.get(nowBox).setFocus(nowIcon);
			if (nowBox < nowStart) {
				nowStart --;
				for (ConstructBox box: boxs) {
					box.showBox(nowStart);
					box.updateView();
				}
				updateLR();
			}
		}
	}
	
	public void GroupRight() {
		if (nowBox < boxs.size() - 1) {
			boxs.get(nowBox).resetFocus(nowIcon);
			nowBox += 1;
			boxs.get(nowBox).setFocus(nowIcon);
			if (nowBox > nowStart + 4) {
				nowStart ++;
				for (ConstructBox box: boxs) {
					box.showBox(nowStart);
					box.updateView();
				}
				updateLR();
			}
		}
	}
	
	public void clearBoxs() {
		for (ConstructBox box: boxs) {
			box.clearBox();
		}
		nowBox = 0;
		nowIcon = 0;
		nowStart = 0;
		boxs = new ArrayList<ConstructBox>();
	}
	
	public void switchToLeft() {
		clearBoxs();
		boxs.add(new ConstructBox(this, 0, 0, 0));
		boxs.get(0).setFocus(0);
		loadMagic(magicL, 0);
		updateLR();
		leftMagicView.setVisible(true);
		rightMagicView.setVisible(false);
		showPanel();
	}
	
	public void switchToRight() {
		clearBoxs();
		boxs.add(new ConstructBox(this, 0, 0, 0));
		boxs.get(0).setFocus(0);
		loadMagic(magicR, 0);
		updateLR();
		leftMagicView.setVisible(false);
		rightMagicView.setVisible(true);
		showPanel();
	}
	
	public void saveMagic() {
		FileChooser fc = new FileChooser();
		ExtensionFilter ef = new ExtensionFilter("magic", "*.magic");
		fc.getExtensionFilters().add(ef);
		fc.setSelectedExtensionFilter(ef);
		File file = fc.showSaveDialog(stage);
		try {
			// This is where a real application would open the file.
			System.out.println("Saving: " + file.getName() + "\n");
			PrintWriter writer = new PrintWriter(file);
			if(leftMagicView.isVisible()) {
				for (Integer i: magicL) {
					writer.write((int)i + "\n");
				}
			}else {
				for (Integer i: magicR) {
					writer.write((int)i + "\n");
				}
			}
			writer.close();
		} catch(Exception e) {
			System.out.println("Save command cancelled by user." + "\n");
		}
	}
	
	public void loadMagic() {
		FileChooser fc = new FileChooser();
		ExtensionFilter ef = new ExtensionFilter("magic", "*.magic");
		fc.getExtensionFilters().add(ef);
		fc.setSelectedExtensionFilter(ef);
		File file = fc.showOpenDialog(stage);
		try {
			Matcher m=pFile.matcher(file.getName());
			if(!m.matches()) {
				throw new Exception();
			}
			System.out.println("Opening: " + file.getName() + "\n");
			FileReader fr= new FileReader(file);
			BufferedReader bufferedReader= new BufferedReader(fr);
			String l;
			ArrayList<Integer> magicC = new ArrayList<Integer>();
			while ((l = bufferedReader.readLine()) != null) {
				magicC.add(Integer.parseInt(l));
			}
			if(leftMagicView.isVisible()) {
				magicL = magicC.toArray(new Integer[magicC.size()]);
				switchToLeft();
			}else {
				magicR = magicC.toArray(new Integer[magicC.size()]);
				switchToRight();
			}
			fr.close();
		} catch(Exception e) {
			System.out.println("Open command cancelled by user." + "\n");
		}
	}
	
	public void setFrames() {
		if (nowIcon == 0) {
			frames.setSpells("boot");
		}else if (nowIcon == 3) {
			frames.setSpells("element");
		}else if ((nowIcon == 1) || (nowIcon == 2) || (nowIcon == 4) || (nowIcon == 5)) {
			frames.setSpells("change");
		}else if ((nowIcon == 6) || (nowIcon == 7) || (nowIcon == 8)) {
			frames.setSpells("link");
		}
	}
	
	public void frameUp() {
		if (frames.nowX > 0) {
			frames.nowX -= 1;
			for (IconParticle ip: ips) {
				ip.nowX = frames.nowX;
			}
			this.updateUD();
		}else if(frames.nowX == 0) {
			frames.nowX = frames.spells.size() - 1;
			for (IconParticle ip: ips) {
				ip.nowX = frames.nowX;
			}
			this.updateUD();
		}
	}
	
	public void frameDown() {
		if (frames.nowX < frames.spells.size() - 1) {
			frames.nowX += 1;
			for (IconParticle ip: ips) {
				ip.nowX = frames.nowX;
			}
			this.updateUD();
		} else if (frames.nowX == frames.spells.size() - 1) {
			frames.nowX = 0;
			for (IconParticle ip: ips) {
				ip.nowX = frames.nowX;
			}
			this.updateUD();
		}
	}
	
	public void setSpell() {
		//System.out.println(nowIcon);
		int spellId = boxs.get(nowBox).getSpell(nowIcon);
		if (spellId == frames.getSpellID()) {
			boxs.get(nowBox).setSpell(nowIcon, -1);
			if (frames.getSpell().type == "link") {
				boxs.get(nowBox).setAngel(nowIcon, 0);
				int removeIndex = nowBox + 1;
				int countIndex = nowIcon;
				while (countIndex > 6) {
					countIndex -= 1;
					if (boxs.get(nowBox).getSpell(countIndex) != -1) {
						removeIndex ++;
					}
				}
				boxs.get(removeIndex).clearBox();
				boxs.remove(removeIndex);
				for (int i = removeIndex; i < boxs.size(); i ++) {
					boxs.get(i).setX(boxs.get(i).x - 1);
				}
			}
		}
		else {
			boxs.get(nowBox).setSpell(nowIcon, frames.getSpellID());
			MagicFrame mf = new MagicFrame(spellId);
			if (frames.getSpell().type == "link" && mf.type != "link") {
				boxs.get(nowBox).setAngel(nowIcon, 0);
				int addIndex = nowBox + 1;
				int countIndex = nowIcon;
				while (countIndex > 6) {
					countIndex -= 1;
					if (boxs.get(nowBox).getSpell(countIndex) != -1) {
						addIndex ++;
					}
				}
				boxs.add(addIndex, new ConstructBox(this, 
						addIndex,
						(boxs.get(nowBox).deepth + 1), boxs.get(nowBox).deepth + 1));
				for (int i = addIndex + 1; i < boxs.size(); i ++) {
					boxs.get(i).setX(boxs.get(i).x + 1);
				}
			}
		}
		for (ConstructBox box: boxs) {
			box.showBox(nowStart);
			box.updateView();
		}
		frames.clearSpells();
	}
	
	public void updateIPS() {
		int moveIndex = 0;
		while(moveIndex < ips.size()) {
			if (ips.get(moveIndex).update()) {
				moveIndex ++;
			}
		}
	}
	
	public void startIPS() {
		double hue = (Math.random() * 120 + 60);
		for (int i = 0; i < 30; i ++) {
			painter.cp.ips.add(new IconParticle(0, Math.PI / 15 * i, hue));
		}
	}
	
	public void endIPS() {
		for (IconParticle ip: ips) {
			ip.startFade(boxs.get(nowBox).getStartx() + 60 * (nowIcon % 3) + 25, boxs.get(nowBox).getStarty()  + 60 * (nowIcon/3) + 25);
		}
	}
	
	public void endEdit() {
		ArrayList<Integer> tempMagic = new ArrayList<Integer>();
		
		for (ConstructBox cb: boxs) {
			if (!cb.hasError()) {
				tempMagic.addAll(cb.getMagic());
			}else {
				return;
			}
		}
		
		if (leftMagicView.isVisible()) {
			magicL = tempMagic.toArray(new Integer[tempMagic.size()]);
		}else {
			magicR = tempMagic.toArray(new Integer[tempMagic.size()]);
		}
		
//		for (Integer i: magicL) {
//			System.out.println("s: " + i);
//		}
	}

}
