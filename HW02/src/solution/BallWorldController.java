package solution;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.Timer;

public class BallWorldController {
	
	private BallWorldView view;
	private BallWorldModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override 
			public void run() {
				try {
					BallWorldController controller = new BallWorldController();
					controller.start();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public BallWorldController() {
		model = new BallWorldModel(new IViewAdapter(){
			@Override
			public void repaint() {
				view.panel.repaint();
			}
		});
		
		view = new BallWorldView (new IModelPaintAdapter() {
			@Override
			public void paintBalls(Graphics g) {
				model.paintBalls(g);
			}

			@Override
			public void addBall(Graphics g) {
				// TODO Auto-generated method stub
				model.addBall(g);
			}
			@Override
			public void clearBalls(Graphics g) {
				model.clearBalls(g);
			}
			
		});
	}
	
	public void start() {
		model.start();
		view.start();
	}
	
	
}
