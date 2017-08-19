package shape;

import java.awt.Graphics;
import java.util.Observable;

public class Sq extends Ashape{
	public Sq(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect(x, y, 20, 20);
	}

	@Override
	public void update(Observable O, Object arg) {
		// TODO Auto-generated method stub
		print((Graphics) arg);
		if(x+10 > 1000 || x-10<0) vx = -vx;
		if(y+10>1000 || y-10<0) vy = -vy;
		x = vx+x;
		y = vy+y;
	}

}
