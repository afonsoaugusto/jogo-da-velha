/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura;

import java.util.Date;

/**
 *
 * @author AFONSO
 */
public class Rodada {
    
    private String ganhandor;
    private boolean isVelha;
    private final Date data;
    private final Campo campo;
    private boolean isRodadaFinalizada;
    private static int id = 0;
    private final int idRodada;

    public Rodada() {
        data = new Date();
        this.isRodadaFinalizada = false;
        this.campo = new Campo();
        id ++;
        idRodada = id;
    }

    public boolean isIsRodadaFinalizada() {
        return isRodadaFinalizada;
    }

    public void setIsRodadaFinalizada(boolean isRodadaFinalizada) {
        this.isRodadaFinalizada = isRodadaFinalizada;
    }
    

    public String getGanhandor() {
        return ganhandor;
    }

    public void setGanhandor(String ganhandor) {
        this.ganhandor = ganhandor;
    }

    public boolean isIsVelha() {
        return isVelha;
    }

    public void setIsVelha(boolean isVelha) {
        this.isVelha = isVelha;
    }

    public Date getData() {
        return data;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) throws CloneNotSupportedException {
        this.campo.setCampo(campo.getCampoFormatado());
    }

    @Override
    public String toString() {
        return "Rodada "+ idRodada+" {" + 
                "\nGanhandor= " + ganhandor + 
                ", \nDeu velha?=" + isVelha + 
                ", \nData=" + data.toString() 
                + ", \n" + this.campo.toString() + '}';
    }

    public void setGanhandor(char winner) {
        if (winner == Opcoes.O.getValor()){
            setGanhandor("Jogador");
        }else
        if (winner == Opcoes.X.getValor()){
            setGanhandor("Servidor");
        }else{
            setGanhandor(" ");
        }
        
    }    
}
