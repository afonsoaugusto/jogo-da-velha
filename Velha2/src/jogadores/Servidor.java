/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogadores;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import estrutura.Campo;
import estrutura.Opcoes;
import estrutura.Regras;
import util.Logger;

/**
 *
 * @author AFONSO
 */
public class Servidor extends Player {

    private ServerSocket server;

    public Servidor(Campo jogo, Regras regras) {
        super(jogo, regras);
        super.setOpcao(Opcoes.X);
        super.vezAtual = true;
        super.log = new Logger(Servidor.class);

        try {
            server = new ServerSocket(9090);
        } catch (IOException ioException) {
            log.err(ioException);
            System.exit(1);
        }
    }

    @Override
    public void start() throws Exception {
        try {
            connection = server.accept();
            input = new Scanner(connection.getInputStream());
            output = new Formatter(connection.getOutputStream());
        } catch (IOException ioException) {
            log.err(ioException);
            System.exit(1);
        }

        ExecutorService worker = Executors.newFixedThreadPool(1);
        worker.execute(this);
        super.startInterface();
    }
}
