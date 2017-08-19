package shape;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

public abstract class Ashape implements Observer{
	int x;
	int y;
	int vx;
	int vy;
	public abstract void print(Graphics g);
	public abstract void update(Observable O, Object arg);
}
