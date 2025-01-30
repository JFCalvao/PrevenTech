/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class ChatInvalidoException extends Exception {
    
    public ChatInvalidoException() {
        super("Este chat não existe ou você não possui permissão para acessá-lo.");
    }
    
}
