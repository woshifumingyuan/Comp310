package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import parseABC.Parser;
import sequencePlayer.SequencePlayer;
import voice.VoiceNote;

import java.awt.BorderLayout;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class View {

	private JFrame frame;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setForeground(Color.YELLOW);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPlay = new JButton("Play!");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = "C:\\Users\\woshi\\Desktop\\Comp310\\Comp310\\MusicPlayer\\src\\main\\file.abc";
				//String a = textField_1.getText();
				//if(a!=null) s = a;
				Parser p = new Parser(s);
				SequencePlayer sp = null;
				try {
					sp = new SequencePlayer(80,16);
				} catch (MidiUnavailableException | InvalidMidiDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<VoiceNote> ln = p.noteList;
				for(int i = 0; i < ln.size(); i++) {
					sp.addNote(ln.get(i).pitch, ln.get(i).start, ln.get(i).start+ln.get(i).during);
				}
				try {
					sp.play();
				} catch (MidiUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPlay.setBackground(Color.YELLOW);
		frame.getContentPane().add(btnPlay, BorderLayout.SOUTH);
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.ORANGE);
		frame.getContentPane().add(textField_1, BorderLayout.CENTER);
		textField_1.setColumns(10);
	}
}
