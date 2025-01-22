/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author Aluno
 */
public class EquipamentoNaoEncontradoException extends Exception {

    public EquipamentoNaoEncontradoException(String message) {
        super(message);
    }

    public EquipamentoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}