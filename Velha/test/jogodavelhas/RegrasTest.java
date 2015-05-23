/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelhas;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author AFONSO
 */
public class RegrasTest {

    Regras regras;
    Campo campo;

    @Test
    public void testHaveWinner() {
        criaCampoCerto();
        Assert.assertTrue(regras.isWinner());
    }

    @Test
    public void testOwner() {
        criaCampoCerto();
        Assert.assertEquals(String.valueOf(Opcoes.X), String.valueOf(regras.getWinner()));
    }

    @Test
    public void testVelha() {
        try {
            criaCampoVelha();
            Assert.assertTrue(regras.isVelha());
        } catch (Exception ex) {
            Logger.getLogger(RegrasTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testVelha2() {
        try {
            criaCampoCerto();
            Assert.assertFalse(regras.isVelha());
        } catch (Exception ex) {
            Logger.getLogger(RegrasTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criaCampoVelha() {
        try {
            campo = new Campo();
            regras = new Regras(campo);
            campo.realzarJogada(1, Opcoes.X);
            campo.realzarJogada(2, Opcoes.O);
            campo.realzarJogada(3, Opcoes.X);
            campo.realzarJogada(4, Opcoes.X);
            campo.realzarJogada(5, Opcoes.O);
            campo.realzarJogada(6, Opcoes.X);
            campo.realzarJogada(7, Opcoes.O);
            campo.realzarJogada(8, Opcoes.X);
            campo.realzarJogada(9, Opcoes.O);
            System.out.println(campo.toString());
        } catch (Exception ex) {
            Logger.getLogger(RegrasTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void criaCampoCerto() {
        try {
            campo = new Campo();
            regras = new Regras(campo);
            campo.realzarJogada(1, Opcoes.X);
            campo.realzarJogada(2, Opcoes.X);
            campo.realzarJogada(3, Opcoes.X);
            campo.realzarJogada(4, Opcoes.O);
            System.out.println(campo.toString());
        } catch (Exception ex) {
            Logger.getLogger(RegrasTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
