/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class UsuarioJaExisteException extends Exception {
    
    public UsuarioJaExisteException() {
        super("Este usuário já está cadastrado em nosso site."
            + "Por favor, tente cadastrar outro usuário ou "
            + "nos contate se acredita ser um engano.");
    }
    
}
