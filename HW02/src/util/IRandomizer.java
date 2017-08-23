package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public interface IRandomizer {
	
	public Point randomLoc (Rectangle rect);
	public Point randomLoc (Dimension dim);
	public int randomInt(int min,int max);
	public int randomDouble(double min, double max);
	public Color randomColor();
	public Object randomChoice(Object x, Object y, double proX);
}
