/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.Exceptions;

/**
 *
 * @author jfcalvao
 */
public class NoSuchTableException extends Exception {

    public NoSuchTableException() {
        super("No such table in database");
    }
    
}
