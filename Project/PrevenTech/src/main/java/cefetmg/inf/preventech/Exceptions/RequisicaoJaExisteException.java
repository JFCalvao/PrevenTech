/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class RequisicaoJaExisteException extends Exception {
    
    public RequisicaoJaExisteException() {
        super("Esta requisição já está cadastrada em nosso site. "
            + "Caso queira cadastrar uma nova requisição tente novamente.");
    }
    
}
