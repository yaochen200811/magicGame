package magicGame;

import java.util.ArrayList;

public class SpellConstruct {
	int[] array;
	ArrayList<ArrayList<Integer>> leftFrame;
	ArrayList<Integer> leftFrame1, leftFrame2, leftFrame3, change, link;
	int boot, effect, links;
	
	public static final int BBULLET = 0, BROUND = 1, BSPRED = 2,
			EEARTH = 10, EWATER = 11, EWIND = 12, EFIRE = 13,
			EDARK = 14, ELIGHT = 15,
			CGREATER = 20, CFASTER = 21, CSLOWER = 22, CJUMP = 23,
			CPIERCE = 24, CHOMING = 25, CSPREDED = 26,
			LHIT = 30, LFADE = 31, LAFTER1 = 32, LAFTER2 = 33,
			LAFTER3 = 34, LAFTER5 = 35;
	
	
	public SpellConstruct(Integer[] frames) {
		boot = frames[0];
		effect = frames[1];
		
		change = new ArrayList<Integer>();
		int index = 2;
		while ((index < frames.length) && (frames[index] < 30) && (frames[index] >= 20)) {
			change.add(frames[index]);
			index ++;
		}
		
		links = 0;
		link = new ArrayList<Integer>();
		while ((index < frames.length) && (frames[index] >= 30)) {
			link.add(frames[index]);
			index ++;
			link.add(frames[index]);
			index ++;
			links +=2;
		}
		
		leftFrame = new ArrayList<>();
		if (links > 0) {
			leftFrame1 = new ArrayList<Integer>();
			int checkCounter = 1;
			while (checkCounter >= 0) {
				
				if ((index >= frames.length) || frames[index] < 10) {
					checkCounter --;
				}else if ((index < frames.length) && frames[index] >= 30) {
					leftFrame1.add(frames[index]);
					index ++;
					checkCounter ++;
				}
				if (checkCounter >= 0) {
					leftFrame1.add(frames[index]);
					index ++;
				}
			}
			leftFrame.add(leftFrame1);
		}
		if (links > 2) {
			leftFrame2 = new ArrayList<Integer>();
			int checkCounter = 1;
			while (checkCounter >= 0) {
				
				if ((index >= frames.length) || frames[index] < 10) {
					checkCounter --;
				}else if ((index < frames.length) && frames[index] >= 30) {
					leftFrame2.add(frames[index]);
					index ++;
					checkCounter ++;
				}
				if (checkCounter >= 0) {
					leftFrame2.add(frames[index]);
					index ++;
				}
			}
			leftFrame.add(leftFrame2);
		}
		if (links > 4) {
			leftFrame3 = new ArrayList<Integer>();
			int checkCounter = 1;
			while (checkCounter >= 0) {
				
				if ((index >= frames.length) || frames[index] < 10) {
					checkCounter --;
				}else if ((index < frames.length) && frames[index] >= 30) {
					leftFrame3.add(frames[index]);
					index ++;
					checkCounter ++;
				}
				if (checkCounter >= 0) {
					leftFrame3.add(frames[index]);
					index ++;
				}
			}
			leftFrame.add(leftFrame3);
		}
	}
	
	public SpellConstruct(ArrayList<Integer> frames) {
		this(frames.toArray(new Integer[frames.size()]));
	}
}
