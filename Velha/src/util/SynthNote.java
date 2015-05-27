/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Soundbank;

public class SynthNote implements Runnable {

    private static final int instrument = 80;
    private static final int force = 120;
    private MidiChannel[] mc = null;
    private Synthesizer synth = null;
    private int note;

    protected SynthNote(int note) {
        this.note = note;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (Exception e) {
            System.out.println(e);
        }
        Soundbank soundbank = synth.getDefaultSoundbank();
        Instrument[] instr = soundbank.getInstruments();
        synth.loadInstrument(instr[instrument]);    //Changing this int (instrument) does nothing
        mc = synth.getChannels();
        mc[4].programChange(instr[instrument].getPatch().getProgram());    
        mc[4].controlChange(7, 300);
    }

    protected void close() {
        synth.close();
    }

    protected void nota() throws InterruptedException {
        mc[4].noteOn(note, force);
        Thread.sleep(250);
        mc[4].noteOff(note, force);
        Thread.sleep(200);
    }

    @Override
    public void run() {
        try {
            nota();
        } catch (InterruptedException ex) {
            Logger.getLogger(SynthNote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

}
