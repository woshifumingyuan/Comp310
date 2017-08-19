package view;

import javax.swing.JFrame;

public class BallGUI extends JFrame{
	public void update() {
		_pnlCanvas.repaint();
	}
	private IView2ModelAdapter _view2ModelAdpt = IView2ModelAdapter.NULL_OBJECT;
	
}
