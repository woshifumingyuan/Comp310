package tetris;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Tetris extends JFrame{
	public Tetris() {
		TerisBlock tb = new TerisBlock();
		addKeyListener(tb);
		add(tb);
	}
	public static void main(String[] args) {
		Tetris frame = new Tetris();
		JMenuBar menu = new JMenuBar();
		frame.setJMenuBar(menu);
		JMenu game = new JMenu("Game");
		JMenuItem newgame = game.add("New game");
		JMenuItem pause = game.add("Pause");
		JMenuItem resume = game.add("Resume");
		JMenuItem exit = game.add("Exit");
		JMenu help = new JMenu("Help");
		JMenuItem about = help.add("About");
		menu.add(game);
		menu.add(help);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440,550);
		frame.setTitle("Tetris");
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
