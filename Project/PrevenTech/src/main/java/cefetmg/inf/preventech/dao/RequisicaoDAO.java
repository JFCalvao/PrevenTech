/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.dto.Equipamento;
import cefetmg.inf.preventech.util.DataManager;
import static cefetmg.inf.preventech.util.DatabaseManager.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.Categorias;
import static cefetmg.inf.preventech.util.DatabaseManager.getConnection;
import static cefetmg.inf.preventech.util.DatabaseManager.searchEquipamento;
import static cefetmg.inf.preventech.util.DatabaseManager.searchUsuario;
import cefetmg.inf.preventech.util.Encryption;
import java.sql.ResultSet;
import java.util.ArrayList;
import cefetmg.inf.preventech.dao.interfaces.EntityExistenceChecker;
import cefetmg.inf.preventech.dao.interfaces.GenericDAO;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;

/**
 *
 * @author rafav
 */

public class RequisicaoDAO<K> implements GenericDAO<Requisicao, K>, EntityExistenceChecker<Requisicao> {
    @Override
    public void create(Requisicao requisicao) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String values = DataManager.formatRequisicao(requisicao);
        String sql = "INSERT INTO `requisicoes` (`requisitor_cpf`, `responsavel_cpf`, `data_inicio`, `categoria`, `equipamentos`, `descricao`) VALUES(" + values + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    @Override
    public Requisicao search(K value) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM `requisicoes` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        Requisicao requisicao = null;
        
        if(rs.next()) {
            String requisicao_id = rs.getString("requisicao_id");
            String requisitor_cpf = rs.getString("requisitor_cpf");
            String responsavel_cpf = rs.getString("responsavel_cpf");
            String data_inicio = rs.getString("data_inicio");
            int categoria = rs.getInt("categoria");
            String equipamentos = rs.getString("equipamentos");
            String descricao = rs.getString("descricao");
            
            requisicao = new Requisicao(requisicao_id, requisitor_cpf, 
                                            responsavel_cpf, data_inicio, 
                                            categoria, equipamentos, 
                                            descricao);
        }
        
        return DataManager.unformatRequisicao(requisicao);
    }
    
    @Override
    public void update(Requisicao requisicao) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String sql = "UPDATE `requisicoes` SET requisitor_cpf = ?, responsavel_cpf = ?, data_inicio = ?, "
                + " categoria = ?, equipamentos = ?, descricao = ? WHERE requisicao_id = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, Encryption.encrypt(requisicao.getRequisitor_cpf()));
        pstmt.setString(2, Encryption.encrypt(requisicao.getResponsavel_cpf()));
        pstmt.setString(3, Encryption.encrypt(requisicao.getData()));
        pstmt.setInt(4, requisicao.getCategoria());
        pstmt.setString(5, Encryption.encrypt(requisicao.getEquipamentos()));
        pstmt.setString(6, Encryption.encrypt(requisicao.getDescricao()));
        pstmt.setString(7, requisicao.getId());

        pstmt.executeUpdate();
        connection.close();
    }
    
    @Override
    public void delete(K value) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        
        String sql = "DELETE FROM `requisicoes` WHERE requisicao_id = ?"; 
        
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setObject(1, value);

        pstmt.executeUpdate();
        connection.close();
    }
    
    @Override
    public List<Requisicao> getAll() throws SQLException, EncryptationException, NoSuchCategoriaException, NoSuchTableException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM `requisicoes`";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        List<Requisicao> requisicoes = new ArrayList<>();
        
        while (rs.next()) {
            Requisicao requisicao = new Requisicao();
            
            requisicao.setCategoria(rs.getInt("categoria"));
            requisicao.setData(rs.getString("data_inicio"));
            requisicao.setDescricao(rs.getString("descricao"));
            requisicao.setEquipamentos(rs.getString("equipamentos"));
            requisicao.setID(rs.getString("requisicao_id"));
            requisicao.setRequisitor_cpf(rs.getString("requisitor_cpf"));
            requisicao.setResponsavel_cpf(rs.getString("responsavel_cpf"));
            
            String categoriaString = Categorias.getCategoriaString(requisicao.getCategoria());
            
            if(categoriaString != null)
                requisicao.setCategoriaString(categoriaString);
            else 
                requisicao.setCategoriaString("Nenhum");
            
            User requisitor = searchUsuario(Encryption.decrypt(requisicao.getRequisitor_cpf()));
            
            if(requisitor != null) 
                requisicao.setRequisitorString(requisitor.getNome());
            else 
                requisicao.setRequisitorString("Ninguém");
            
            User responsavel = searchUsuario(Encryption.decrypt(requisicao.getResponsavel_cpf()));
            
            if(responsavel != null) {
                requisicao.setResponsavelString(responsavel.getNome());
                requisicao.setStatus("Em andamento");
            }
            else { 
                requisicao.setResponsavelString("Ninguém");
                requisicao.setStatus("Pendente");
            }
            
            String equipamentosStr = requisicao.getEquipamentos();
            equipamentosStr = Encryption.decrypt(equipamentosStr);
            
            if(equipamentosStr != null && !equipamentosStr.isEmpty()) {
                List<Equipamento> equipamentosAssociados = new ArrayList<>();
                String[] equipamentos = equipamentosStr.split("_");
                
                for(String equipamentoStr:equipamentos) {
                    Equipamento equipamento = searchEquipamento(equipamentoStr.trim());
                    if(equipamento != null) {
                        equipamentosAssociados.add(equipamento);
                    }
                }
                
                requisicao.setArrEquipamentos(equipamentosAssociados);
            }
            
            requisicao = DataManager.unformatRequisicao(requisicao);
          
            requisicoes.add(requisicao);
        } 
        
        return requisicoes;
    }
    
    @Override
    public boolean has(Requisicao requisicao) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = requisicao.getId();
        String sql = "SELECT * FROM `requisicoes` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
}