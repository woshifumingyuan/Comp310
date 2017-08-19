package solution;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BallWorldModel implements IViewAdapter{
	public IViewAdapter viewadp;
	public BallWorldModel(IViewAdapter adp) {
		viewadp = adp;
	}
	int timeSlice = 50;
	Dispatcher dispatcher = new Dispatcher();
	private Timer timer;
	
	public void start() {
		// TODO Auto-generated method stub
		timer = new Timer(timeSlice, (e)->{
			viewadp.repaint();
		});
		timer.start();
	}
	
	public void paintBalls(Graphics g) {
		dispatcher.notifyAll(g);
	}
	
	public void addBall(Graphics gg) {
		dispatcher.addObserver(new shape.Ball(100,100,10,10));
		paintBalls(gg);
	}


	public void clearBalls(Graphics g) {
		// TODO Auto-generated method stub
		dispatcher.deleteObservers();
		viewadp.repaint();
	}

	@Override
	public void repaint() {};
	
}
