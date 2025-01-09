/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class EquipamentoJaExisteException extends Exception {
    
    public EquipamentoJaExisteException() {
        super("Este equipamento já está cadastrado em nosso site. "
            + "Caso queira cadastrar um novo equipamento tente novamente.");
    }
    
}
