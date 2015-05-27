/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AFONSO
 */
public class Regras {

    private final Campo campo;
    private final char[][] board;
    private char winner;
    private List<Rodada> rodadas;
    private Rodada rodadaAtual;

    public Regras(Campo campo) {
        this.campo = campo;
        board = campo.getCampo();
        this.rodadas = new ArrayList<>();
        this.winner = ' ';
    }

    public boolean isJogadaValida(int pos) throws Exception {
        return String.valueOf(campo.getPosicao(pos)).equalsIgnoreCase(" ");
    }

    public boolean isWinner() {
        //Checking rows
        char current = ' ';
        for (int i = 0; i < 3; i++) {
            int i1 = 0;
            for (i1 = 0; i1 < 3; i1++) {
                if (!Character.isLetter(board[i][i1])) {
                    break;
                }
                if (i1 == 0) {
                    current = board[i][i1];
                } else if (current != board[i][i1]) {
                    break;
                }
                if (i1 == 2) {
                    //Found isWinner
                    this.winner = current;
                    return true;
                }
            }
        }
        //Checking columns
        for (int i = 0; i < 3; i++) {
            current = ' ';
            int i1 = 0;
            for (i1 = 0; i1 < 3; i1++) {
                if (!Character.isLetter(board[i1][i])) {
                    break;
                }
                if (i1 == 0) {
                    current = board[i1][i];
                } else if (current != board[i1][i]) {
                    break;
                }
                if (i1 == 2) {
                    //Found isWinner
                    this.winner = current;
                    return true;
                }
            }
        }
        //Checking diagonals
        current = board[0][0];
        if (Character.isLetter(current) && board[1][1] == current && board[2][2] == current) {
            this.winner = current;
            return true;
        }
        current = board[2][0];
        if (Character.isLetter(current) && board[1][1] == current && board[0][2] == current) {
            this.winner = current;
            return true;
        }
        return false;
    }

    public char getWinner() {
        if(this.winner == ' '){
            isWinner();
        }
        return this.winner;
    }

    public void reiniciarJogo() {
        this.campo.limpaCampo();
    }
    
    public void reiniciarJogoCompleto() {
        reiniciarJogo();
        this.rodadas = new ArrayList<>();
    }

    public void iniciaRodada() {
        this.rodadaAtual = new Rodada();
        this.rodadas.add(this.rodadaAtual);
    }

    public boolean isVelha() throws Exception {
        boolean velha = true;

        for (int i = 0; i < 9; i++) {
            
                //System.out.println(i + 1);
                //System.out.println(String.valueOf(this.campo.getPosicao(i  + 1)).equals(" "));
                //System.out.println(' ' == this.campo.getPosicao(i  + 1));
                if (String.valueOf(this.campo.getPosicao(i  + 1)).equals(" ")
                        || ' ' == this.campo.getPosicao(i  + 1)) {
                    velha = false;
                }
        }
        if (this.isWinner()) {
            velha = false;
        }
        return velha;
    }

    public void finalizarRodada() throws Exception {
        this.rodadaAtual.setGanhandor(this.winner);
        this.rodadaAtual.setIsVelha(this.isVelha());
        this.rodadaAtual.setCampo(this.campo);
        this.rodadaAtual.setIsRodadaFinalizada(true);
    }

    public String verRodadas() {
        StringBuilder Sb = new StringBuilder();
        if (isRodadasTerminadas()) {
            for (Rodada rodada : rodadas) {
                if (rodada.isIsRodadaFinalizada()) {
                    Sb.append(rodada.toString());
                    Sb.append("\n");
                }
            }
        } else {
            Sb.append("Não há rodadas finalizadas");
        }

        return Sb.toString();
    }

    private boolean isRodadasTerminadas() {
        boolean isTerminada = false;
        for (Rodada rodada : rodadas) {
            if (rodada.isIsRodadaFinalizada()) {
                isTerminada = true;
            }
        }
        return isTerminada;
    }
    
    public boolean isFimDeJogo(){
        return (rodadas.size() >3 && isRodadasTerminadas());
    }
}
