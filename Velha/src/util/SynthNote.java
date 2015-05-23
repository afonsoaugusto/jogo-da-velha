/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Soundbank;

public class SynthNote {

    private static final int instrument = 80;
    private static final int force = 120;
    private MidiChannel[] mc = null;
    private Synthesizer synth = null;

    public SynthNote() {
        
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
        

    }

    public void close() {
        synth.close();
    }

    public void nota(int note) throws InterruptedException {
        mc[4].noteOn(note, force);
        Thread.sleep(250);
        mc[4].noteOff(note,force);
        Thread.sleep(200);
    }
}
