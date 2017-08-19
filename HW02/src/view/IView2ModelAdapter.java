package view;

import java.awt.Graphics;

public interface IView2ModelAdapter {
	public void paint(Graphics g);
	
	public static final IView2ModelAdapter NULL_OBJECTI = new IView2ModelAdapter() {
		public void paint(Graphics g) {}
	};
}
