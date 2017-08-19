package view;

import java.awt.Graphics;

import javax.swing.Timer;

public class BallModel {
	private IModel2ViewAdapter _model2ViewAdapt = IModel2ViewAdapter.NuLL_OBJECT;
	public BallModel(IModel2ViewAdapter model2ViewAdpt) {
		_model2ViewAdapt = model2ViewAdpt;
	}
	private int _timeSlice = 50;
	private Timer _timer = new Timer(_timeSlice, (e)-> _model2ViewAdapt.update());
	
	
	public void update(Graphics g) {
		myDispatcher.notifyAll(g);
	}
}
