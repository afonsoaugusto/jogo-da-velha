/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelhas;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Soundbank;

public class SynthNote {

    static int instrument = 99;
    static int note = 60;
    static int timbre = 0;
    static int force = 100;

    public static void main(String[] args) {
        Synthesizer synth = null;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (Exception e) {
            System.out.println(e);
        }
        Soundbank soundbank = synth.getDefaultSoundbank();
        Instrument[] instr = soundbank.getInstruments();
        System.out.println(instr.length);
        synth.loadInstrument(instr[instrument]);    //Changing this int (instrument) does nothing
        MidiChannel[] mc = synth.getChannels();
        System.out.println(mc.length);
        mc[4].programChange(instr[instrument].getPatch().getProgram());
        mc[4].noteOn(note, force);
        try {
            nota(mc,50);
            //nota(mc,60);
            Thread.sleep(1000);
            mc[4].noteOff(note, force);
            //nota(mc,70);
            //nota(mc,50);
            
        } catch (InterruptedException e) {
        }
        System.out.println(instr[instrument].getName());

        synth.close();

    }

    private static void nota(MidiChannel[] channels, int note) throws InterruptedException {
        channels[4].noteOn(note, force);
        Thread.sleep(100);
        channels[4].noteOff(note,force);
        Thread.sleep(200);
    }
}

/**
 * * SynthNote.java **
 */
