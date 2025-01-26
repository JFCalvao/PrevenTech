package cefetmg.inf.preventech.dao.interfaces;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import java.sql.SQLException;
import java.util.List;

// Criei a interface DAO (passível de alterações)

public interface GenericDAO<E, K> extends CRUD<E, K> {
    // T: representa a entidade da classe persistente
    // U: representa a chave
    
    // método para pegar todos os valores da classe persistente
    public List<E> getAll() throws SQLException, EncryptationException, 
            NoSuchCategoriaException, NoSuchTableException;
}