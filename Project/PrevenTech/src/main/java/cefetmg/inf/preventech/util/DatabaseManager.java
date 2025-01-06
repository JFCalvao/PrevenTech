/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.util;

/**
 *
 * @author jfcalvao
 */
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import jakarta.ws.rs.core.HttpHeaders;
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
    
    private static void checkTable(String tableName) throws NoSuchTableException {
        if(!tables.contains(tableName))
            throw new NoSuchTableException();
    }
    
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
    
    public static String[] getColumns(String tableName) throws NoSuchTableException {
        checkTable(tableName);
        
        int index = tables.indexOf(tableName);
        
        switch(index) {
            case 0: return new String[]{"nome","n_patrimonio","local","estado"};
            case 1: return (String[])List.of("requisicao_id","requisitor_cpf","responsavel_cpf",
                                             "data_inicio","categoria","equipamentos","descricao")
                                             .toArray();
            case 2: return (String[])List.of("requisicao_id","requisitor_cpf","responsavel_cpf",
                                             "data_fim","relatorio_final")
                                             .toArray();
            case 3: return (String[])List.of("user_id","nome","cpf","senha","email","profissao",
                                             "imagem")
                                             .toArray();
            case 4: return (String[])List.of("chat_id","user_cpf","conteudo").toArray();
            default: break;
        }
        
        return null;
    }
    
    public static void insert(String tableName, SQLData encryptedData) 
           throws SQLException, NoSuchTableException {
        
        checkTable(tableName);
        
        Connection connection = getConnection();
        String sql = "INSERT INTO `" + tableName + "` VALUES(" + encryptedData.toString() + ")";
        
        PreparedStatement pstmt = connection.prepareStatement(sql); 
        
        pstmt.executeUpdate(); 
        connection.close();
    }
    
    public static List<SQLData> search(String tableName, String key, String id) 
           throws SQLException, NoSuchTableException {
        
        checkTable(tableName);
        
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
    
    public static List<SQLData> getAll(String tableName) 
           throws SQLException, NoSuchTableException {
        
        checkTable(tableName);
        
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
}
