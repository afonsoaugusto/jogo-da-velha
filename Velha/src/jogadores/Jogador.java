/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogadores;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jogodavelhas.Campo;
import jogodavelhas.Opcoes;
import jogodavelhas.Regras;
import util.Logger;

/**
 *
 * @author AFONSO
 */
public class Jogador extends Player {

    public Jogador(Campo jogo, Regras regras, String ipServidor) {
        super(jogo, regras);
        super.setOpcao(Opcoes.O);
        super.vezAtual = false;
        super.setIp(ipServidor);
        super.log = new Logger(Jogador.class);
    }

    @Override
    public void start() throws Exception {
        try {
            // make connection to server 
            //connection = new Socket(InetAddress.getLocalHost(), 12345);
            connection = new Socket(super.getIp(), 9090);
            // get streams for input and output 
            input = new Scanner(new InputStreamReader(connection.getInputStream()));
            output = new Formatter(connection.getOutputStream());
        } catch (IOException ioException) {
            log.err(ioException);
        }

        ExecutorService worker = Executors.newFixedThreadPool(1);
        worker.execute(this);
        super.startInterface();
    }

}
