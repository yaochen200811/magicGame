package magicGame;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class MagicFrames {
	ArrayList<MagicFrame> mfList;
	
	public MagicFrames() {
		mfList = new ArrayList<MagicFrame>();
		for (int i = 0; i < 36; i ++) {
			mfList.add(new MagicFrame(i));
		}
	}
	
	public ArrayList<MagicFrame> getMfs(String type) {
		ArrayList<MagicFrame> mfListRe = new ArrayList<MagicFrame>();
		for (MagicFrame mf: mfList) {
			if (mf.type == type) {
				mfListRe.add(mf);
			}
		}
		return mfListRe;
	}
	
	public Image getImage(int id) {
		for (MagicFrame mf: mfList) {
			if (mf.id == id) {
				return mf.image;
			}
		}
		return new Image("/images/ELight.png");
	}
	
	public MagicFrame getMagicFrame(int id) {
		for (MagicFrame mf: mfList) {
			if (mf.id == id) {
				return mf;
			}
		}
		return new MagicFrame(-1);
	}
	
}
