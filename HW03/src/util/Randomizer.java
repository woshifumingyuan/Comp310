package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class Randomizer{

	public static Point randomLoc(Rectangle rect) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static Point randomLoc(Dimension dim) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static int randomInt(int min, int max) {
		// TODO Auto-generated method stub
		return (int)Math.floor((Math.random()*(1+max-min))+min);
	}

	public int randomDouble(double min, double max) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static Color randomColor() {
		// TODO Auto-generated method stub
		return new Color(randomInt(0,255),randomInt(0,255),randomInt(0,255));
	}

	public static Object randomChoice(Object x, Object y, double proX) {
		// TODO Auto-generated method stub
		return (Math.random()<proX) ? x:y;
	}

}
