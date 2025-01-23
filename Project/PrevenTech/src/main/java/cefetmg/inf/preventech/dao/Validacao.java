/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import java.sql.SQLException;

/**
 *
 * @author rafav
 */
public interface Validacao<T> {
    // T: representa a entidade da classe persistente
    
    // método que retorna um boolean caso o valor da classe
    // persistente exista
    public boolean has(T entidade) throws SQLException, EncryptationException;
}
