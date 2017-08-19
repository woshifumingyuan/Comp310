package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FirstGui extends JFrame {

	/**
	 * 
	 */
	private int count;

	public void setCount(int value) {
		this.count = value;
	}

	public int getCount() {
		return this.count;
	}

	private static final long serialVersionUID = -3464208445964474222L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstGui frame = new FirstGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Hello world");
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Click!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setText("Hello again");
				String s = textField.getText();
				lblNewLabel.setText(s);
				System.out.print("Clicked!");
			}
		});
		panel.add(btnNewButton);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7773914098008529188L;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.RED);
				g.fillOval(75, 100, 20, 40);
			}

		};
		contentPane.add(panel_1, BorderLayout.CENTER);
	}

}
