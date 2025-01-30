/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import cefetmg.inf.preventech.dao.interfaces.CRUD;
import cefetmg.inf.preventech.dao.interfaces.EntityExistenceChecker;
import cefetmg.inf.preventech.dao.interfaces.GenericDAO;
import cefetmg.inf.preventech.dto.Mensagem;
import cefetmg.inf.preventech.util.DataManager;
import cefetmg.inf.preventech.util.DatabaseManager;
import static cefetmg.inf.preventech.util.DatabaseManager.getConnection;
import cefetmg.inf.preventech.util.Encryption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jfcalvao
 */
public class MensagemDAO implements GenericDAO<Mensagem, Integer>, EntityExistenceChecker<Mensagem> {
    @Override
    public void create(Mensagem msg) throws SQLException, EncryptationException {
        Connection connection = DatabaseManager.getConnection();
        String values = DataManager.formatMensagem(msg);
        String sql = "INSERT INTO `mensagens` (`id`, `user`, `conteudo`) VALUES(" + values + ")";
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    @Override
    public Mensagem search(Integer id) throws SQLException, EncryptationException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT * FROM `mensagens` WHERE id = " + id;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery();
        
        Mensagem msg = null;
        
        if(rs.next()) {
            String user = Encryption.decrypt(rs.getString("user"));
            String conteudo = Encryption.decrypt(rs.getString("conteudo"));
            msg = new Mensagem(id, user, conteudo);
        }
        
        connection.close();
        return msg;
    }
    
    @Override
    public void update(Mensagem msg) throws SQLException, EncryptationException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "UPDATE `mensagens` SET `user`= ?,`conteudo`= ? WHERE `id` = ?";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.setString(1, msg.getUser());
        pstmt.setString(2, msg.getMensagem());
        pstmt.setInt(3, msg.getId());
        
        pstmt.executeUpdate(); 
        connection.close();
    }
   
    @Override
    public void delete(Integer id) throws SQLException, EncryptationException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "DELETE FROM `mensagens` WHERE `id` = " + id;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    @Override
    public List<Mensagem> getAll() throws SQLException, EncryptationException, 
            NoSuchCategoriaException, NoSuchTableException {
        Connection connection = DatabaseManager.getConnection();
        String sql = "SELECT * FROM `mensagens`";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery();
        
        List<Mensagem> list = new ArrayList<>();
        
        while(rs.next()) {
            Integer id = rs.getInt("id");
            String user = Encryption.decrypt(rs.getString("user"));
            String conteudo = Encryption.decrypt(rs.getString("conteudo"));
            Mensagem msg = new Mensagem(id, user, conteudo);
            list.add(msg);
        }
        
        connection.close();
        return list;
    }
    
    @Override
    public boolean has(Mensagem msg) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM `mensagens` WHERE id = " + msg.getId();
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
}
