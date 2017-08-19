package solution;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import shape.Ball;
import view.Dispatcher;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BallWorldView extends JFrame implements IModelPaintAdapter, IModelCtrlAdapter{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void start1() {
		EventQueue.invokeLater(new Runnable() {
			private IModelPaintAdapter model;

			public void run() {
				try {
					BallWorldView frame = new BallWorldView(this.model);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	
	
	/**
	 * Create the frame.
	 */
	
	JButton btnDeletBalls = new JButton("Delet balls");
	JButton btnMakeBall = new JButton("Make ball");
	JPanel panel_1 = new JPanel();
	JPanel panel = new JPanel() {
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
				//gg.fillOval(100,100,10,10);
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
	public void loadBall() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paintBalls(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBall(Graphics g) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clearBalls() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearBalls(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
