/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogadores;

import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import estrutura.Campo;
import jogo.Jogo;
import estrutura.Opcoes;
import estrutura.Regras;
import util.Logger;

/**
 *
 * @author AFONSO
 */
public abstract class Player implements Runnable {

    private String ip;
    private Opcoes opcao;
    protected final Campo campo;
    protected final Regras regras;
    protected boolean vezAtual;
    protected Jogo jogo;
    protected Scanner input;
    protected Formatter output;
    protected Logger log;
    protected Socket connection;

    public Player(Campo campo, Regras regras) {
        this.campo = campo;
        this.regras = regras;
        log = new Logger(Player.class);
    }

    public Player(String ip, Campo campo, Regras regras) {
        this.ip = ip;
        this.campo = campo;
        this.regras = regras;
    }

    public boolean isVezAtual() {
        return vezAtual;
    }

    protected void setVezAtual(boolean vezAtual) {
        this.vezAtual = vezAtual;
    }

    public abstract void start() throws Exception;

    public void realizarJogada(int posicao) throws Exception {
        campo.realzarJogada(posicao, getOpcao());
        output.format(this.getClass().getSimpleName() + campo.getCampoFormatado() + "\n");
        output.flush(); // flush output                         
        this.vezAtual = false;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Opcoes getOpcao() {
        return opcao;
    }

    public void setOpcao(Opcoes opcao) {
        this.opcao = opcao;
    }

    protected void startInterface() {
        try {
            jogo = new Jogo(campo, this, regras);
            jogo.setVisible(true);
        } catch (Exception ex) {
            log.err(ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (input.hasNextLine()) {
                String message = input.nextLine();
                processMessage(message);
                vezAtual = true;
                campo.setCampo(message);
                jogo.atualizaCampo();
            }
        }
    }

    protected void processMessage(String nextLine) {
        log.inf(this.getClass().getSimpleName() + "  " + nextLine);
    }

}
