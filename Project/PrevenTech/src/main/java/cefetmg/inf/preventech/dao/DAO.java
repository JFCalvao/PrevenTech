package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import java.sql.SQLException;
import java.util.List;

// Criei a interface DAO (passível de alterações)

public interface DAO<T, U> extends CRUD<T, U> {
    // T: representa a entidade da classe persistente
    // U: representa a chave
    
    // método para pegar todos os valores da classe persistente
    public List<T> getAll() throws SQLException, EncryptationException;
}