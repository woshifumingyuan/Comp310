package tetris;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class TerisBlock extends JPanel implements KeyListener{
	public int blockType;
	public int score = 0;
	public int turnState;
	public int x;
	public int y;
	public int i = 0;
	public int j = 0;
	public int flag = 0;
	public int speed = 1000;
	int[][] map = new int[13][23];
	private final int shapes[][][] = new int[][][] {
	    // i
	            { { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },        		
	              { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },   			
	              { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
	            // s
	            { { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	              { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
	            // z
	            { { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
	            // j
	            { { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
	              { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	            // o
	            { { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	            // l
	            { { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	              { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
	            // t
	            { { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
	              { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
	              { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };
	
	public void newBlock() {
		blockType = (int)(Math.random()*10000)%7;
		turnState = (int)(Math.random()*10000)%4;
		x = 4; y = 0;
		if(gameOver(x,y)==1) {
			newMap();
			drawWall();
			score = 0;
			JOptionPane.showInternalMessageDialog(null, "GAME OVER");
		}
	}
	
	public void drawWall() {
		for(int i = 0; i < 12; i++) {
			map[i][21] = 2;
		}
		for(int j = 0; j < 22; j++) {
			map[11][j] = 2;
			map[0][j] = 2;
		}
	}
	
	public void newMap() {
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 22; j++) {
				map[i][j] = 0;
			}
		}
	}
	
	TerisBlock(){
		newBlock();
		newMap();
		drawWall();
		Timer timer = new Timer(speed, new TimerListener());
		timer.start();
	}
	
	public void turn() {
		int tempState = turnState;
		turnState = (turnState+1)%4;
		if(blow(x,y,blockType,turnState)==1) {
		}
		if(blow(x,y,blockType,turnState)==0) {
			turnState = tempState;
		}
		repaint();
	}
	public void left() {
		if(blow(x-1,y,blockType,turnState)==1) {
			x = x -1;
		}
		repaint();
	}
	public void right() {
		if(blow(x+1,y,blockType,turnState)==1) {
			x = x+1;
		}
		repaint();
	}
	public void down() {
		if(blow(x,y+1,blockType,turnState)==1) {
			y = y+1;
			decline();
		}
		if(blow(x,y+1,blockType,turnState)==0) {
			add(x,y,blockType,turnState);
			newBlock();
			decline();
		}
		repaint();
	}
	
	public int blow(int x, int y, int blockType, int turnState) {
		for(int a = 0; a < 4; a++) {
			for(int b = 0; b < 4; b++) {
				if(((shapes[blockType][turnState][a*4+b]==1)&&(map[x+b+1][y+a]==1))
					|| ((shapes[blockType][turnState][a*4+b]==1)&&(map[x+b+1][y+a]==2))){
						return 0;
					}
			}
		}
		return 1;
	}
	public void decline() {
		int c = 0; 
		for(int b = 0; b < 22; b++) {
			for(int a = 0; a < 12; a++) {
				if(map[a][b]==1) {
					c++;
					if(c==10) {
						score += 10;
						speed += 50;
						for(int d = b; d > 0; d--) {
							for(int e = 0; e < 11; e++) {
								map[e][d] = map[e][d-1];
							}
						}
					}
				}
			}
			c = 0;
		}
	}
	public int gameOver(int x, int y) {
		if(blow(x,y,blockType,turnState)==0) {
			return 1;
		}
		return 0;
	}
	public void add(int x, int y, int blockType, int turnState) {
		int j = 0;
		for(int a = 0; a < 4; a++) {
			for(int b = 0; b < 4; b++) {
				if(map[x+b+1][y+a]==0) {
					map[x+b+1][y+a] = shapes[blockType][turnState][j];
				}
				j++;
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int j = 0; j < 16; j++) {
			if(shapes[blockType][turnState][j]==1) {
				g.fillRect((j%4+x+1)*20, (j/4+y)*20, 20, 20);
			}
		}
		for(int j = 0; j < 22; j++) {
			for(int i = 0; i < 12; i++) {
				if(map[i][j]==1) {
					g.fillRect(i*20, j*20, 20, 20);
				}
				if(map[i][j]==2) {
					g.drawRect(i*20, j*20, 20, 20);
				}
			}
		}
		g.drawString("Score="+score,250,20);
	}
	
	
	class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			repaint();
			if(blow(x,y+1,blockType,turnState)==1) {
				y = y + 1;
				decline();
			}
			if(blow(x,y+1,blockType,turnState)==0) {
				if(flag == 1) {
					add(x,y,blockType,turnState);
					decline();
					newBlock();
					flag = 0;
				}
				flag = 1;
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			down();
		}else if(arg0.getKeyCode()==KeyEvent.VK_UP) {
			turn();
		}else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			right();
		}else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			left();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
