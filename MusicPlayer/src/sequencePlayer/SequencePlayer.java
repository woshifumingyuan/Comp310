package sequencePlayer;

import java.text.MessageFormat;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class SequencePlayer {
	private Sequencer sequencer;
	private Track track;
	private int beatsPerMinute;
	
	private static int DEFAUT_CHANNEL = 7;
	private static int DEFAUT_V = 100;
	
	private void checkRep() {
		assert sequencer != null : "sequencer should be non-null";
		assert track != null : "track should be non-null";
		assert beatsPerMinute >= 0 : "should be positive number of beats per minutes";
	}
	
	public SequencePlayer(int beats, int ticks) throws MidiUnavailableException, InvalidMidiDataException {
		this.sequencer = MidiSystem.getSequencer();
		Sequence sequence = new Sequence(Sequence.PPQ,ticks);
		this.beatsPerMinute = beats;
		this.track = sequence.createTrack();
		sequencer.setSequence(sequence);
		checkRep();
	}
	
	public void addMidiEvent(int eventType, int note, int tick) throws InvalidMidiDataException {
		ShortMessage msg = new ShortMessage();
		msg.setMessage(eventType,DEFAUT_CHANNEL,note,DEFAUT_V);
		MidiEvent event = new MidiEvent(msg,tick);
		this.track.add(event);
	}
	
	public void addNote(int note, int startTick, int numTicks) {
		try {
			addMidiEvent(ShortMessage.NOTE_ON, note, startTick);
			addMidiEvent(ShortMessage.NOTE_OFF, note, startTick+numTicks);
		}catch(InvalidMidiDataException e) {
			String msg = MessageFormat.format("Cannot add note with the pitch{0} at tick{1}"+
		"for duration of {2}", note, startTick,numTicks);
			throw new RuntimeException(msg,e);
		}
	}
	
	public void play() throws MidiUnavailableException {
		sequencer.open();
		sequencer.setTempoInBPM(this.beatsPerMinute);
		sequencer.start();
		while(sequencer.isRunning()) {
			Thread.yield();
		}
		sequencer.close();
	}
	public String toString() {
		String trackInfo = "";
		for(int i = 0; i < track.size(); i++) {
			MidiEvent e = track.get(i);
			MidiMessage msg = e.getMessage();
			String msgString = "";
			if(msg instanceof javax.sound.midi.ShortMessage) {
				ShortMessage smg = ((ShortMessage)msg);
				int command = smg.getCommand();
				String commandType = "UnknownCommand";
				if(command == ShortMessage.NOTE_OFF) {
					commandType = "NOTE_OFF";
				}else if(command == ShortMessage.NOTE_ON) {
					commandType = "NOTE.ON";
				}
				msgString = "Event: " + commandType + " Pitch: " + smg.getData1() +" ";
			}else {
				msgString = "*******end of track******";
			}
			trackInfo = trackInfo = msgString +" Tick: " + e.getTick() +"\n";
		}
		return trackInfo;
	}
	
	/*public static void main(String[] args) {
		SequencePlayer player;
		try {
			player = new SequencePlayer(120,2);
			player.addNote(69, 0, 1);
            player.addNote(71, 1, 1);
           // player.addNote(96, 2, 10);
           // player.addNote(80, 3, 1);
            player.addNote(64, 4, 1);
            player.addNote(65, 5, 1);
            player.addNote(67, 6, 1);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 7, 1);
            player.addNote(new Pitch('B').toMidiNote(), 8, 1);
            player.addNote(new Pitch('A').toMidiNote(), 9, 1);
            player.addNote(new Pitch('G').toMidiNote(), 10, 1);
            player.addNote(new Pitch('F').toMidiNote(), 11, 1);
            player.addNote(new Pitch('E').toMidiNote(), 12, 1);
            player.addNote(new Pitch('D').toMidiNote(), 13, 1);
            player.addNote(new Pitch('C').toMidiNote(), 14, 1);
			
            System.out.println(player);
            player.play();
		} catch(MidiUnavailableException e) {
			e.printStackTrace();
		} catch(InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}*/
}
