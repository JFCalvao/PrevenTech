package cefetmg.inf.preventech.dao.interfaces;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import java.sql.SQLException;

// Criei a interface CRUD (passível de alterações)

public interface CRUD<E, K> {
    // T: representa a entidade da classe persistente
    // U: representa a chave
    
    // método para inserir os dados no banco de dados
    public void create(E entidade) throws SQLException, EncryptationException;
    
    // método para ler um registro no banco de dados
    public E search(K key) throws SQLException, EncryptationException;
    
    // método que atualiza um registro do banco de dados
    public void update(E entidade) throws SQLException, EncryptationException;
    
    // método que deleta um registro do banco de dados
    public void delete(K key) throws SQLException, EncryptationException;
}