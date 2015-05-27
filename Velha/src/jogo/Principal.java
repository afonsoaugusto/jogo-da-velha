/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import javax.swing.JOptionPane;
import jogadores.Jogador;
import jogadores.Player;
import jogadores.Servidor;
import util.AudioInterface;
import util.Logger;

/**
 *
 * @author AFONSO
 */
public class Principal {

    private static Player player;
    private static final Logger log = new util.Logger(Principal.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (JOptionPane.showConfirmDialog(null, "Com Musica?", null, 1) == 0) {
            AudioInterface.startAudioGame();
        }

        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Você é o servidor?", null, 1);
        if (showConfirmDialog == 0) {
            log.inf("Você é o servidor");
            player = new Servidor();
            startGame();

        } else if (showConfirmDialog == 1) {
            final String ipServidor = JOptionPane.showInputDialog("Informe o ip do Servidor");
            //final String ipServidor = "127.0.0.1";
            log.inf("Informe o ip do Servidor " + ipServidor);
            player = new Jogador(ipServidor);
            startGame();
        }
    }

    private static void startGame() {
        try {
            player.start();
        } catch (Exception ex) {
            log.errClient(ex);
        }
    }

    public static Player getPlayer() {
        return player;
    }

}
