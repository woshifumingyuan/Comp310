/*package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import parseABC.Parser;
import sequencePlayer.SequencePlayer;
import voice.VoiceNote;

public class player {
	public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, FileNotFoundException, IOException {
		
		
		String s = "C:\\Users\\woshi\\Desktop\\Comp310\\Comp310\\MusicPlayer\\src\\main\\file.abc";
		Parser p = new Parser(s);
		SequencePlayer sp = new SequencePlayer(160,1);
		List<VoiceNote> ln = p.noteList;
		int cont = 0;
		for(int i = 0; i < ln.size(); i++) {
			sp.addNote(ln.get(i).pitch, ln.get(i).start, ln.get(i).start+ln.get(i).during);
		}
		sp.play();
	}
}
*/