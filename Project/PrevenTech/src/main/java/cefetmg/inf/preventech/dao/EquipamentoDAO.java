package cefetmg.inf.preventech.dao;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.dao.interfaces.CRUD;
import cefetmg.inf.preventech.dao.interfaces.EntityExistenceChecker;
import cefetmg.inf.preventech.dao.interfaces.GenericDAO;
import cefetmg.inf.preventech.dto.Equipamento;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.Encryption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO implements CRUD<Equipamento, String>, GenericDAO<Equipamento, String>, EntityExistenceChecker<Equipamento> {

    @Override
    public void create(Equipamento equip) throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "INSERT INTO equipamentos (n_patrimonio, nome, local, estado) VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, equip.getN_patrimonio());
            pstmt.setString(2, equip.getNome());
            pstmt.setString(3, equip.getLocal());
            pstmt.setString(4, equip.getEstado());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public Equipamento search(String n_patrimonio) throws SQLException {
        Equipamento equip;
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT * FROM equipamentos WHERE n_patrimonio = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, n_patrimonio);
            ResultSet rs = pstmt.executeQuery();
            equip = null;
            if (rs.next()) {
                String nome = rs.getString("nome");
                String local = rs.getString("local");
                String estado = rs.getString("estado");
                equip = new Equipamento(nome, n_patrimonio, local, estado);
            }
        }
        return equip;
    }

    @Override
    public void update(Equipamento equip) throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "UPDATE equipamentos SET nome = ?, local = ?, estado = ? WHERE n_patrimonio = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, equip.getNome());
            pstmt.setString(2, equip.getLocal());
            pstmt.setString(3, equip.getEstado());
            pstmt.setString(4, equip.getN_patrimonio());
            
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(String n_patrimonio) throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "DELETE FROM equipamentos WHERE n_patrimonio = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, n_patrimonio);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Equipamento> getAll() throws SQLException {
        List<Equipamento> equipamentos;
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT * FROM equipamentos";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            equipamentos = new ArrayList<>();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String n_patrimonio = rs.getString("n_patrimonio");
                String local = rs.getString("local");
                String estado = rs.getString("estado");
                Equipamento equip = new Equipamento(nome, n_patrimonio, local, estado);
                equipamentos.add(equip);
            }
        }
        return equipamentos;
    }

    @Override
    public boolean has(Equipamento equip) throws SQLException {
        boolean exists;
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "SELECT * FROM equipamentos WHERE n_patrimonio = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, equip.getN_patrimonio());
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
        }
        return exists;
    }

    public void updateEstado(String n_patrimonio, String novoEstado) throws SQLException, EncryptationException {
        try (Connection connection = DatabaseManager.getConnection()) {
            String sql = "UPDATE equipamentos SET estado = ? WHERE n_patrimonio = ?";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, Encryption.encrypt(novoEstado));
            pstmt.setString(2, Encryption.encrypt(n_patrimonio));
            
            pstmt.executeUpdate();
        }
    }
}
