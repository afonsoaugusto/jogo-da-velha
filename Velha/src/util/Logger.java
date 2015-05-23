/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author AFONSO
 */
public class Logger {

    private final org.apache.log4j.Logger logger;

    public Logger(Class c) {
        logger = org.apache.log4j.Logger.getLogger(c);
        PropertyConfigurator.configure("log4j.properties");
    }

    public void err(Object obj) {
        logger.error(obj);
        //saida(obj);
    }

    public void inf(Object obj) {
        logger.info(obj);
        //saida(obj);
    }

    public void alert(Object obj) {
        logger.debug(obj);
    }

    public void question(Object obj) {
        logger.trace(obj);
    }

    public void errClient(Object obj) {
        JOptionPane.showMessageDialog(null, obj.toString(), "Erro", 0);
        err(obj);
    }

    public void infClient(Object obj) {
        JOptionPane.showMessageDialog(null, obj.toString(), "Info", 1);
        inf(obj);
    }

    public void alertClient(Object obj) {
        JOptionPane.showMessageDialog(null, obj.toString(), "Alerta", 2);
        alert(obj);
    }

    public void questionClient(Object obj) {
        JOptionPane.showMessageDialog(null, obj.toString(), "Questão", 3);
        question(obj);
    }

   // private void saida(Object obj){
    //     System.out.println(obj.toString());
    // }
}
