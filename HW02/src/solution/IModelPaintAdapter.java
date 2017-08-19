package solution;

import java.awt.Graphics;

public interface IModelPaintAdapter {
	public void paintBalls(Graphics g);
	public void addBall(Graphics g);
	public void clearBalls(Graphics g);
}
