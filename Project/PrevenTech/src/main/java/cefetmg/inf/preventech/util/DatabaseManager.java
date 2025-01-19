/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

/**
 *
 * @author jfcalvao
 */
import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.dao.Equipamento;
import cefetmg.inf.preventech.dao.Historico;
import cefetmg.inf.preventech.dao.Requisicao;
import cefetmg.inf.preventech.dao.Categorias;
import cefetmg.inf.preventech.dao.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final static String databaseName = "preventech_db";
    private final static String url = "localhost:3306";
    private final static List<String> tables = List.of("equipamentos","requisicoes","historico","users","chats");
    
    public static Connection getConnection() throws SQLException {
        String driverURL = "jdbc:mysql://" + url + "/" + databaseName;
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(driverURL, user, password);
    }
    
    public static void insertEquipamento(Equipamento equipamento) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String values = DataManager.formatEquipamento(equipamento);
        String sql = "INSERT INTO `equipamentos` VALUES(" + values + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    public static void insertRequisicao(Requisicao requisicao) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String values = DataManager.formatRequisicao(requisicao);
        String sql = "INSERT INTO `requisicoes` (`requisitor_cpf`, `responsavel_cpf`, `data_inicio`, `categoria`, `equipamentos`, `descricao`) VALUES(" + values + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    public static void insertHistorico(Historico historico) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String values = DataManager.formatHistorico(historico);
        String sql = "INSERT INTO `historicos` VALUES(" + values + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    public static void insertUsuario(User usuario) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String values = DataManager.formatUsuario(usuario);
        String sql = "INSERT INTO `users` VALUES(" + values + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        pstmt.executeUpdate(); 
        connection.close();
    }

    public static void updateUsuario(User usuario) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String sql = "UPDATE `users` SET nome = ?, email = ?, senha = ? WHERE cpf = ?";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        System.out.println(usuario.getNome());
        pstmt.setString(1, Encryption.encrypt(usuario.getNome()));
        pstmt.setString(2, Encryption.encrypt(usuario.getEmail()));
        pstmt.setString(3, Encryption.encrypt(usuario.getSenha()));
        pstmt.setString(4, Encryption.encrypt(usuario.getCPF()));

        pstmt.executeUpdate();
        connection.close();
    }
    
    private static void insert(String tableName, SQLData encryptedData) 
           throws SQLException, NoSuchTableException {
        
        Connection connection = getConnection();
        String sql = "INSERT INTO `" + tableName + "` VALUES(" + encryptedData.toString() + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    public static boolean hasEquipamento(Equipamento equipamento) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = Encryption.encrypt(equipamento.getN_patrimonio());
        String sql = "SELECT * FROM `equipamentos` WHERE n_patrimonio = '" + value + "'";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
    
    public static boolean hasRequisicao(Requisicao requisicao) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = requisicao.getId();
        String sql = "SELECT * FROM `requisicoes` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
    
    public static boolean hasHistorico(Historico historico) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = historico.getId();
        String sql = "SELECT * FROM `historicos` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
    
    public static boolean hasUsuario(User user) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = Encryption.encrypt(user.getCPF());
        System.out.println(value);
        String sql = "SELECT * FROM `users` WHERE cpf = '" + value + "'";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        return rs.next();
    }
    
    private static List<SQLData> search(String tableName, String key, String id) 
           throws SQLException, NoSuchTableException {
        
        Connection connection = getConnection();
        String sql = "SELECT * FROM `" + tableName + "` WHERE " + key + " = " + id;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        
        ResultSet rs = pstmt.executeQuery(); 
        List<SQLData> results = new ArrayList<>();
        
        int columnCount = rs.getMetaData().getColumnCount();
        
        while (rs.next()) { // Percorre todas as rows e coleta todas as colunas em um array de string
            SQLData data = new SQLData();
            for(int i = 0; i < columnCount; i++)
                data.add(rs.getString(i + 1)); // rs.getString() inicia a coluna em 1, 2 e ...
            results.add(data);
        } 
        
        connection.close(); 
        
        return results;
    }
    
    public static Requisicao searchRequisicao(String id) throws SQLException, EncryptationException, NoSuchCategoriaException {
        Connection connection = getConnection();
        String value = id;
        String sql = "SELECT * FROM `requisicoes` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        String requisitor_cpf = rs.getString("requisitor_cpf");
        String responsavel_cpf = rs.getString("responsavel_cpf");
        String data_inicio = rs.getString("data_inicio");
        int categoria = rs.getInt("categoria");
        String equipamentos = rs.getString("equipamentos");
        String descricao = rs.getString("descricao");
        
        Requisicao requisicao = new Requisicao(id, requisitor_cpf, 
                                               responsavel_cpf, data_inicio, 
                                               categoria, equipamentos, 
                                               descricao);
        
        return DataManager.unformatRequisicao(requisicao);
    }
        
    public static Historico searchHistorico(String id) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String value = id;
        String sql = "SELECT * FROM `historico` WHERE requisicao_id = " + value;
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        ResultSet rs = pstmt.executeQuery(); 
        
        String requisitor_cpf = rs.getString("requisitor_cpf");
        String responsavel_cpf = rs.getString("responsavel_cpf");
        String data_fim = rs.getString("data_fim");
        String nomeArquivo = rs.getString("nome_arquivo");
        
        Historico historico = new Historico(id, requisitor_cpf, responsavel_cpf, data_fim, nomeArquivo);
        
        return DataManager.unformatHistorico(historico);
    }

    public static User searchUsuario(String cpf) throws SQLException, EncryptationException {
        Connection connection = getConnection();
        String encryptedCPF = Encryption.encrypt(cpf);
        String sql = "SELECT * FROM `users` WHERE cpf = '" + encryptedCPF + "'";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        User user = null;
        if (rs.next()) {
            String nome = Encryption.decrypt(rs.getString("nome"));
            String email = Encryption.decrypt(rs.getString("email"));
            String profissao = Encryption.decrypt(rs.getString("profissao"));
            String senha = rs.getString("senha");

            user = new User(cpf, senha);
            user.setNome(nome);
            user.setEmail(email);
            user.setProfissao(profissao);
            System.out.println(user.getSenha());
        }

        connection.close();
        return user;
    }
    
    public static List<SQLData> getAll(String tableName) 
           throws SQLException, NoSuchTableException {
        
        Connection connection = getConnection();
        String sql = "SELECT * FROM `" + tableName + "`";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        
        ResultSet rs = pstmt.executeQuery(); 
        List<SQLData> results = new ArrayList<>();
        
        int columnCount = rs.getMetaData().getColumnCount();
        
        while (rs.next()) { // Percorre todas as rows e coleta todas as colunas em um array de string
            SQLData data = new SQLData();
            for(int i = 0; i < columnCount; i++)
                data.add(rs.getString(i + 1)); // rs.getString() inicia a coluna em 1, 2 e ...
            results.add(data);
        } 
        
        connection.close(); 
        
        return results;
    }

    public static List<Equipamento> getAllEquipamentos()
           throws SQLException, NoSuchTableException, EncryptationException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM `equipamentos`";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        List<Equipamento> equipamentos = new ArrayList<>();
        System.out.println("Pegando os dados");
        while (rs.next()) {
            Equipamento data = new Equipamento();
            
            data.setNome(rs.getString("nome"));
            data.setN_patrimonio(rs.getString("n_patrimonio"));
            data.setEstado(rs.getString("estado"));
            data.setLocal(rs.getString("local"));
            
            data = DataManager.unformatEquipamento(data);
            
            equipamentos.add(data);
        } 
        
        return equipamentos;
    }
    
    public static List<Requisicao> getAllRequisicoes()
           throws SQLException, NoSuchTableException, EncryptationException, NoSuchCategoriaException {
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
            
            User requisitor = searchUsuario(requisicao.getRequisitor_cpf());
            
            if(requisitor != null)
                requisicao.setRequisitorString(requisitor.getNome());
            else
                requisicao.setRequisitorString("Ninguém");
            
            User responsavel = searchUsuario(requisicao.getResponsavel_cpf());
            
            if(responsavel != null) {
                requisicao.setResponsavelString(responsavel.getNome());
                requisicao.setStatus("Em andamento");
            }
            else { 
                requisicao.setResponsavelString("Ninguém");
                requisicao.setStatus("Pendente");
            }
            
            requisicao = DataManager.unformatRequisicao(requisicao);
            
            requisicoes.add(requisicao);
        } 
        
        return requisicoes;
    }
}
