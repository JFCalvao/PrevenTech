/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class NoSuchCategoriaException extends Exception {
    
    public NoSuchCategoriaException() {
        super("Esta categoria especificada não existe."
            + "Por favor, troque por outra categoria ou "
            + "nos contate caso isto seja um engano");
    }
    
}
