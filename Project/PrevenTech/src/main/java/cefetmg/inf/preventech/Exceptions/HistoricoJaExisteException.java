/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class HistoricoJaExisteException extends Exception {
    
    public HistoricoJaExisteException() {
        super("Esta requisição já foi finalizada em nosso site."
            + "Caso isso seja um engano, por favor, contate a "
            + "nossa equipe de suporte.");
    }
    
}
