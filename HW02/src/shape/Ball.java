package shape;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

public class Ball extends Ashape implements Observer{
	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub
		g.fillOval(x,y,10,10);
	}
	
	public Ball(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
	
	@Override
	public void update(Observable O, Object arg) {
		// TODO Auto-generated method stub
		print((Graphics) arg);
		if(x+10 > 300 || x-10<0) vx = -vx;
		if(y+10> 300 || y-10<0) vy = -vy;
		x = (int)Math.abs(300*Math.sin(y)) ;
		y = y+vy;
	}
}
