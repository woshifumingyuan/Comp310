package solution;


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
	
	private shape.Ball loadBall(String className){
		int x = util.Randomizer.randomInt(20,100);
		int y = util.Randomizer.randomInt(20,100);
		int vx = util.Randomizer.randomInt(2,20);
		int vy = util.Randomizer.randomInt(2,20);
		try {
			Object[] args = new Object[] {x,y,vx,vy};
			java.lang.reflect.Constructor<?> cs[] = 
					Class.forName(className).getConstructors();
			java.lang.reflect.Constructor<?> c = null;
			for(int i = 0; i < cs.length; i++) {
				if(args.length == (cs[i]).getParameterTypes().length) {
					c = cs[i];
					break;
				}
			}
			return (shape.Ball) c.newInstance(args);
		}catch(Exception ex) {
			System.err.println("Class "+className+" failed to load. \nException = \n"+ex);
			ex.printStackTrace();
			return null;
		}
	} 
	
	
	public void addBall(Graphics g) {
		dispatcher.addObserver(loadBall("shape.Ball"));
		paintBalls(g);
	}


	public void clearBalls(Graphics g) {
		// TODO Auto-generated method stub
		dispatcher.deleteObservers();
		viewadp.repaint();
	}

	@Override
	public void repaint() {};
	
}
