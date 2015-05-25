/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Midi implements MetaEventListener {

    private Midi() {
    }

    public static Midi getInstance() {
        return new Midi();
    }

    // The drum track in the example Midi file
    private static final int DRUM_TRACK = 1;

    private MidiPlayer player;

    public void run() {
        player = new MidiPlayer();
        // load a sequence
        Sequence sequence = player.getSequence("sound/1beatit.mid");

        // play the sequence
        player.play(sequence, true);
    }
    
    public void stop() {
        player.stop();
    }
    
    public void pause() {
        player.setPaused(true);
    }
    
    public void start() {
        player.setPaused(false);
    }

    /**
     * This method is called by the sound system when a meta event occurs. In
     * this case, when the end-of-track meta event is received, the drum track
     * is turned on.
     *
     * @param event
     */
    @Override
    public void meta(MetaMessage event) {
        if (event.getType() == MidiPlayer.END_OF_TRACK_MESSAGE) {
            Sequencer sequencer = player.getSequencer();
            if (sequencer.getTrackMute(DRUM_TRACK)) {
                // turn on the drum track
                System.out.println("Turning on drums...");
                sequencer.setTrackMute(DRUM_TRACK, false);
            } else {
                // close the sequencer and exit
                System.out.println("Exiting...");
                player.close();
                System.exit(0);
            }
        }
    }

}

class MidiPlayer implements MetaEventListener {

    // Midi meta event
    public static final int END_OF_TRACK_MESSAGE = 47;
    private static final Logger log = new util.Logger(Midi.class);
    private Sequencer sequencer;

    private boolean loop;

    private boolean paused;

    /**
     * Creates a new MidiPlayer object.
     */
    public MidiPlayer() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addMetaEventListener(this);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
        } catch (MidiUnavailableException ex) {
            sequencer = null;
        }
    }

    /**
     * Loads a sequence from the file system. Returns null if an error occurs.
     */
    public Sequence getSequence(String filename) {
        try {
            return getSequence(new FileInputStream(filename));
        } catch (IOException ex) {
            log.err(ex.getMessage());
            return null;
        }
    }

    /**
     * Loads a sequence from an input stream. Returns null if an error occurs.
     */
    public Sequence getSequence(InputStream is) {
        try {
            if (!is.markSupported()) {
                is = new BufferedInputStream(is);
            }
            Sequence s = MidiSystem.getSequence(is);
            is.close();
            return s;
        } catch (InvalidMidiDataException | IOException ex) {
            log.err(ex.getMessage());

            return null;
        }
    }

    /**
     * Plays a sequence, optionally looping. This method returns immediately.
     * The sequence is not played if it is invalid.
     */
    public void play(Sequence sequence, boolean loop) {
        if (sequencer != null && sequence != null && sequencer.isOpen()) {
            try {
                sequencer.setSequence(sequence);
                sequencer.start();
                this.loop = loop;
            } catch (InvalidMidiDataException ex) {
                log.err(ex.getMessage());
            }
        }
    }

    /**
     * This method is called by the sound system when a meta event occurs. In
     * this case, when the end-of-track meta event is received, the sequence is
     * restarted if looping is on.
     */
    @Override
    public void meta(MetaMessage event) {
        if (event.getType() == END_OF_TRACK_MESSAGE) {
            if (sequencer != null && sequencer.isOpen() && loop) {
                sequencer.start();
            }
        }
    }

    /**
     * Stops the sequencer and resets its position to 0.
     */
    public void stop() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.stop();
            sequencer.setMicrosecondPosition(0);
        }
    }

    /**
     * Closes the sequencer.
     */
    public void close() {
        if (sequencer != null && sequencer.isOpen()) {
            sequencer.close();
        }
    }

    /**
     * Gets the sequencer.
     */
    public Sequencer getSequencer() {
        return sequencer;
    }

    /**
     * Sets the paused state. Music may not immediately pause.
     */
    public void setPaused(boolean paused) {
        if (this.paused != paused && sequencer != null && sequencer.isOpen()) {
            this.paused = paused;
            if (paused) {
                sequencer.stop();
            } else {
                sequencer.start();
            }
        }
    }

    /**
     * Returns the paused state.
     */
    public boolean isPaused() {
        return paused;
    }

}
