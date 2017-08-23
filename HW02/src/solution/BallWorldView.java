package solution;

import java.awt.BorderLayout;
import java.awt.Container;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BallWorldView extends JFrame implements IModelPaintAdapter, IModelCtrlAdapter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -418711573305259097L;
	private JPanel contentPane;
	JButton btnDeletBalls = new JButton("Delete balls");
	JButton btnMakeBall = new JButton("Make ball");
	JPanel panel_1 = new JPanel();
	JPanel panel = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -9016974197903712917L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			model.paintBalls(g);
		}
	};
	Graphics gg;
	public IModelPaintAdapter model;
	
	public BallWorldView(IModelPaintAdapter _model) {
		model = _model;
	}
	
	public void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		btnMakeBall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gg = panel.getGraphics();
				model.addBall(gg);
			}
		});
		panel_1.add(btnMakeBall);
		btnDeletBalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gg = panel.getGraphics();
				model.clearBalls(gg);
			}
		});
		panel_1.add(btnDeletBalls);
		this.setVisible(true);
	}
	
	public Container getCanvas() {
		return panel;
	}
	
	@Override
	public void loadBall() {}
	@Override
	public void paintBalls(Graphics g) {}
	@Override
	public void addBall(Graphics g) {}
	@Override
	public void clearBalls() {}
	@Override
	public void clearBalls(Graphics g) {}
}
