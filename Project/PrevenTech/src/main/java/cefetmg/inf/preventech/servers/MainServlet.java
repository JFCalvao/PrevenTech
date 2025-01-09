/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */

package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.EquipamentoJaExisteException;
import cefetmg.inf.preventech.Exceptions.HistoricoJaExisteException;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import cefetmg.inf.preventech.Exceptions.RequisicaoJaExisteException;
import cefetmg.inf.preventech.dao.Equipamento;
import cefetmg.inf.preventech.dao.Historico;
import cefetmg.inf.preventech.dao.Requisicao;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.DataManager;
import cefetmg.inf.preventech.util.SQLData;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    
    private static Map<String, Integer> Users = new HashMap();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JSONObject json = new JSONObject(request.getParameter("json"));
        
        String type = json.getString("type");
        String operation = json.getString("operation");
        JSONObject content = json.getJSONObject("content");
        String tableName = getTableName(type);
        
        JSONObject jsonResponse = new JSONObject();
        
        try {
            if(operation.equals("INSERT")) {
//                String[] params = getParams(0, type, tableName, content);;
//                
//                SQLData formattedData = DataManager.format(params);
//                DatabaseManager.insert(tableName, formattedData);
//                
//                List<SQLData> list = DatabaseManager.getAll(tableName);
//                for(int i = 0; i < list.size(); i++)
//                    jsonResponse.put(String.valueOf(i), DataManager.unformat(list.get(i)));

                switch(type) {
                    case "EQ": {
                        Equipamento equipamento = getEquipamento(content);
                        if(DatabaseManager.hasEquipamento(equipamento)) {
                            throw new EquipamentoJaExisteException();
                        }
                        DatabaseManager.insertEquipamento(equipamento);
                    }
                    break;
                    case "RQ": {   
                        Requisicao requisicao = getRequisicao(content);
                        if(DatabaseManager.hasRequisicao(requisicao)) {
                            throw new RequisicaoJaExisteException();
                        }
                        DatabaseManager.insertRequisicao(requisicao);
                    }
                    break;
                    case "HS": {
                        Historico historico = getHistorico(content);
                        Requisicao requisicao = DatabaseManager.searchRequisicao(historico.getId());
                        
                        historico.setRequisitor_cpf(requisicao.getRequisitor_cpf());
                        historico.setResponsavel_cpf(requisicao.getResponsavel_cpf());
                        
                        if(DatabaseManager.hasHistorico(historico)) {
                            throw new HistoricoJaExisteException();
                        }
                        
                        String savePath = "src/main/java/cefetmg/inf/preventech/uploads";
                        historico.uploadFile(savePath);
                        
                        DatabaseManager.insertHistorico(historico);
                    }
                        
                    break;
                    case "US": {
                        
                    }      
                    break;
                    case "CH": {
                        
                    }
                    break;
                    default: break;
                }
            } else if(operation.equals("GET")) {
                switch(type) {
                    case "EQ":         
                    break;
                    case "RQ":         
                    break;
                    case "HS": {
                        String savePath = "src/main/java/cefetmg/inf/preventech/uploads";
                        Historico historico = getHistorico(content);
                        historico = DatabaseManager.searchHistorico(historico.getId());

                        File file = historico.getFile(savePath);
                        String fileName = historico.getNomeArquivo();

                        if(file.exists()) {
                            byte[] fileBytes = Files.readAllBytes(file.toPath()); 
                            String fileContentBase64 = Base64.getEncoder().encodeToString(fileBytes);

                            JSONObject fileInfo = new JSONObject(); 
                            fileInfo.put("nome", fileName); 
                            fileInfo.put("file", fileContentBase64);

                            jsonResponse.put("content", fileInfo);
                        }
                    }
                    break;
                    case "US":         
                    break;
                    case "CH":
                    break;
                    default: break;
                }
            } else if(operation.equals("REMOVE")) {
                
            } else {

            }
        } catch(SQLException e) {
           jsonResponse.put("status", "ERRO");
           jsonResponse.put("error", "Estamos com falhas para guardar estas informações. "
                          + "Por favor, tente novamente mais tarde.");
        } catch(Exception e) {
           jsonResponse.put("status", "ERRO");
           jsonResponse.put("error", e.getMessage());
        } finally {
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                System.out.println("Response: " + jsonResponse);
                out.print(jsonResponse);
            }    
        }
    }
    
    private String getTableName(String type) {
        switch(type) {
            case "EQ": return "equipamentos";
            case "RQ": return "requisicoes";
            case "HS": return "historico";
            case "US": return "users";
            case "CH": return "chats";
            default: break;
        }
        return "";
    }
    
    private Equipamento getEquipamento(JSONObject content) {
        String nome = content.getString("nome");
        String n_patrimonio = content.getString("n_patrimonio");
        String local = content.getString("local");
        String estado = content.getString("estado");
        return new Equipamento(nome, n_patrimonio, local, estado);
    }
    
    private int getCategoriaCode(String categoria) throws NoSuchCategoriaException {
        List<String> falhasEletronicas = List.of(
            "Desgaste mecânico", "Danos físicos",
            "Tela com defeito", "Botões quebrados",
            "Problemas com capacitores", "Circuitos danificados",
            "Computador não liga"
        );
        
        List<String> falhasInformatica = List.of(
          "Problemas de software", "Falhas na rede",
          "Atualizações faltando", "Configurações erradas",
          "Falha nos cabos de conexão", "Manuntenção de hardware"
        );
        
        int code = 0;
        
        if(falhasEletronicas.indexOf(categoria) != -1) {
            code = falhasEletronicas.indexOf(categoria);
        } else if(falhasInformatica.indexOf(categoria) != -1) {
            code = falhasEletronicas.size() + falhasInformatica.indexOf(categoria);
        } else {
            throw new NoSuchCategoriaException();
        }
        
        return code;
    }
    
    private String getCategoriaString(int code) throws NoSuchCategoriaException {
        List<String> falhasEletronicas = List.of(
            "Desgaste mecânico", "Danos físicos",
            "Tela com defeito", "Botões quebrados",
            "Problemas com capacitores", "Circuitos danificados",
            "Computador não liga"
        );
        
        List<String> falhasInformatica = List.of(
          "Problemas de software", "Falhas na rede",
          "Atualizações faltando", "Configurações erradas",
          "Falha nos cabos de conexão", "Manuntenção de hardware"
        );
        
        String categoria = "";
        
        if(code < falhasEletronicas.size()) {
            categoria = falhasEletronicas.get(code);
        } else if((code - falhasEletronicas.size()) < falhasInformatica.size()) {
            categoria = falhasInformatica.get((code - falhasEletronicas.size()));
        } else {
            throw new NoSuchCategoriaException();
        }
        
        return categoria;
    }
    
    private Requisicao getRequisicao(JSONObject content) throws NoSuchCategoriaException {
        String requisicao_id = "";
        String requisitor_cpf = "";
        String responsavel_cpf = "";
        String data_inicio = "";
        String categoria = content.getString("categoria");
        String equipamentos = content.getString("equipamentos");
        String descricao = content.getString("descricao");
        return new Requisicao(requisicao_id, requisitor_cpf, responsavel_cpf, 
                              data_inicio, getCategoriaCode(categoria), equipamentos, descricao);
    }
    
    private Historico getHistorico(JSONObject content) {
        String id = content.getString("id");
        String fileContent = content.getString("file");
        
        return new Historico(id, fileContent);
    }
    
    private String[] getParams(int operation, String type, String tableName, JSONObject content) 
                     throws NoSuchTableException {
        String[] params = null;
        String[] columns = DatabaseManager.getColumns(tableName);

        if(operation == 0)         /*============[INSERT Operation]===========*/
            switch(type) {
                case "EQ":         /* * * * * * * * * * * * * * * * * * * * * */ 
                case "RQ":         /*------[All are treated the same way]-----*/
                case "HS":         /*------[Because to insert in Database]----*/
                case "US":         /*--------[you need all columns info]------*/
                case "CH":         /* * * * * * * * * * * * * * * * * * * * * */
                    params = new String[columns.length];
                    for(int i = 0; i < columns.length; i++) {
                        params[i] = content.getString(columns[i]);
                    }
                default: break;
            }
        else if(operation == 1)    /*==============[GET Operation]============*/
            switch(type) {         
                case "US":         /*------------[Login operation]------------*/     
                break;
                case "CH":         /*-----------[Getting chat msgs]-----------*/     
                break;
                case "EQ": break;  /* * * * * * * * * * * * * * * * * * * * * */                                
                case "RQ": break;  /*-------------[No Use at all]-------------*/
                case "HS": break;  /* * * * * * * * * * * * * * * * * * * * * */
                default: break;
            }
        else
            switch(type) {
                case "EQ":         /* * * * * * * * * * * * * * * * * * * * * */ 
                case "RQ":         /*------[All are treated the same way]-----*/
                case "HS":         /*------[Because to remove in Database]----*/
                case "US":         /*--------[you need all columns info]------*/
                case "CH":         /* * * * * * * * * * * * * * * * * * * * * */
                    params = new String[columns.length];
                    for(int i = 0; i < columns.length; i++) {
                        params[i] = content.getString(columns[i]);
                    }
                default: break;
            }
        return params;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
