/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

/**
 *
 * @author AFONSO
 */
public enum Opcoes {
    X('X'),O('O');
    
    private final char valor;

    private Opcoes(char valor) {
        this.valor = valor;
    }

    public char getValor() {
        return valor;
    }

}
