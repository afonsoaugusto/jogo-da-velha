/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author AFONSO
 */
public class AudioInterface {

    private AudioInterface() {
    }
    
    public static void startAudioGame(){
        Thread t = new Thread(new Midi());
        t.start();
    }
    
    public static void startAudioTeclaDown(){
        Thread t = new Thread(new SynthNote(65));
        t.start();
    }
    
    public static void startAudioTeclaUp(){
        Thread t = new Thread(new SynthNote(64));
        t.start();
    }
}
