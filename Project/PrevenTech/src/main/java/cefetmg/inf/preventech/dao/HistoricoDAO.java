/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import cefetmg.inf.preventech.dao.interfaces.EntityExistenceChecker;
import cefetmg.inf.preventech.dao.interfaces.GenericDAO;
import cefetmg.inf.preventech.dto.Equipamento;
import cefetmg.inf.preventech.dto.Historico;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.Categorias;
import cefetmg.inf.preventech.util.DataManager;
import static cefetmg.inf.preventech.util.DatabaseManager.getConnection;
import static cefetmg.inf.preventech.util.DatabaseManager.searchEquipamento;
import static cefetmg.inf.preventech.util.DatabaseManager.searchUsuario;
import cefetmg.inf.preventech.util.Encryption;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
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

public class HistoricoDAO implements GenericDAO<Historico, String>, EntityExistenceChecker<Historico> {
    @Override
    public void create(Historico historico) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String values = DataManager.formatHistorico(historico);
        String sql = "INSERT INTO `historicos` VALUES(" + values + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    @Override
    public Historico search(String id) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = id;
        String sql = "SELECT * FROM `historicos` WHERE requisicao_id = " + Integer.valueOf(value);
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        Historico historico = null;
        while(rs.next()) {
            String requisitor_cpf = rs.getString("requisitor_cpf");
            String responsavel_cpf = rs.getString("responsavel_cpf");
            String data_fim = rs.getString("data_fim");
            String nomeArquivo = rs.getString("nome_arquivo");
            
            historico = new Historico(id, requisitor_cpf, responsavel_cpf, data_fim, nomeArquivo);
        }
        
        
        connection.close();
        return DataManager.unformatHistorico(historico);
    }
    
    @Override
    public void update(Historico historico) throws SQLException, EncryptationException {
        return;
    }
    
    @Override
    public void delete(String value) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        
        String sql = "DELETE FROM `historicos` WHERE requisicao_id = ?"; 
        
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setObject(1, value);

        pstmt.executeUpdate();
        connection.close();
    }
    
    @Override
    public List<Historico> getAll() throws SQLException, EncryptationException, NoSuchCategoriaException, NoSuchTableException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM `historicos`";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        List<Historico> historicos = new ArrayList<>();
        while (rs.next()) {
            Historico data = new Historico();
            
            data.setId(rs.getString("requisicao_id"));
            data.setRequisitor_cpf(rs.getString("requisitor_cpf"));
            data.setResponsavel_cpf(rs.getString("responsavel_cpf"));
            data.setData(rs.getString("data_fim"));
            data.setNomeArquivo(rs.getString("nome_arquivo"));
            
            data = DataManager.unformatHistorico(data);
            
            historicos.add(data);
        } 
        
        return historicos;
    }
    
    @Override
    public boolean has(Historico historico) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = historico.getId();
        String sql = "SELECT * FROM `historicos` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
}
