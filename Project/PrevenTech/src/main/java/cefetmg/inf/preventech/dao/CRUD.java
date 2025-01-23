package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import java.sql.SQLException;

// Criei a interface CRUD (passível de alterações)

public interface CRUD<T, U> {
    // T: representa a entidade da classe persistente
    // U: representa a chave
    
    // método para inserir os dados no banco de dados
    public void create(T entidade) throws SQLException, EncryptationException;
    
    // método para ler um registro no banco de dados
    public T read(U key) throws SQLException, EncryptationException;
    
    // método que atualiza um registro do banco de dados
    public void update(T entidade) throws SQLException, EncryptationException;
    
    // método que deleta um registro do banco de dados
    public void delete(U key) throws SQLException, EncryptationException;
}