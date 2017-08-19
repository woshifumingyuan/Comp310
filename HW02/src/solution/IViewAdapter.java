package solution;

import java.awt.Container;

public interface IViewAdapter {
	public void repaint();
    public static final IViewAdapter NULL_OBJECT = new IViewAdapter() {
		@Override
		public void repaint() {
			// TODO Auto-generated method stub
			
		}
    };
}
