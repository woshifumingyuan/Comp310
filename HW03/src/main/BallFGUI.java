package main;

import javax.swing.JFrame;

public class BallFGUI<TDropListItem> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995592361174758812L;
	private IModelControlAdapter<TDropListItem> _modelControlAdpt;
	private JComboBox<TDropListItem> _list1DL;
	private JComboBox<TDropListItem> _list2DL;
	public BallGui(ImodeControlAdapter<TDropListItem> modelCtrlAdpt, IModelUpdateAdapter modelUpdateAdpt) {
		_modelControlAdpt = modelCtrlAdpt;
		
		
		
		
	}
	private void initGUI() {
		
	}
}
