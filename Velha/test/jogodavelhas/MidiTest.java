/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelhas;

/*
 DEVELOPING GAME IN JAVA 

 Caracteristiques

 Editeur : NEW RIDERS 
 Auteur : BRACKEEN 
 Parution : 09 2003 
 Pages : 972 
 Isbn : 1-59273-005-1 
 Reliure : Paperback 
 Disponibilite : Disponible a la librairie 
 */
import util.Midi;

/**
 * An example that plays a Midi sequence. First, the sequence is played once
 * with track 1 turned off. Then the sequence is played once with track 1 turned
 * on. Track 1 is the drum track in the example midi file.
 */
public class MidiTest {

    // The drum track in the example Midi file
    

    public static void main(String[] args) {
        Midi.getInstance().run();
    }
}
