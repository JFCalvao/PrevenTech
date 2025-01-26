/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cefetmg.inf.preventech.dao.interfaces;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import java.sql.SQLException;

/**
 *
 * @author rafav
 */

// verifica a existência da entidade no banco de dados
public interface EntityExistenceChecker<E> {
    // T: representa a entidade da classe persistente
    
    // método que retorna um boolean caso o valor da classe
    // persistente exista
    public boolean has(E entidade) throws SQLException, EncryptationException;
}
