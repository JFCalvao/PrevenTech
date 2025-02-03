/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.HistoricoJaExisteException;
import cefetmg.inf.preventech.dao.HistoricoDAO;
import cefetmg.inf.preventech.dao.RequisicaoDAO;
import cefetmg.inf.preventech.dto.Historico;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author jfcalvao
 */
@WebServlet(name = "HistoricoServlet", urlPatterns = {"/HistoricoServlet"})
public class HistoricoServlet extends HttpServlet {

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
        
        String operation = json.getString("operation");
        JSONObject content = json.getJSONObject("content");
        
        JSONObject jsonResponse = new JSONObject();
        
        try {
            switch(operation) {
                case "GET": {
                    JSONArray result = new JSONArray();
                    HistoricoDAO dao = new HistoricoDAO();
                    
                    for(Historico h : DatabaseManager.getAllHistoricos()) {
                        JSONObject obj = new JSONObject();
                        obj.put("id", h.getId());
                        User requisitor = DatabaseManager.searchUsuario(h.getRequisitor_cpf());
                        User responsavel = DatabaseManager.searchUsuario(h.getResponsavel_cpf());
                        obj.put("requisitor", requisitor.getNome());
                        if(responsavel != null)
                            obj.put("responsavel", responsavel.getNome());
                        else
                            obj.put("responsavel", "Ninguem");
                        obj.put("data", h.getData());

                        result.put(obj);
                    }
                    
                    jsonResponse.put("content", result);
                } break;
                case "INSERT": {
                    Historico historico = getHistorico(content);
                    
                    if(DatabaseManager.hasHistorico(historico)) {
                        throw new HistoricoJaExisteException();
                    }
                    
                    Requisicao requisicao = DatabaseManager.searchRequisicao(historico.getId());

                    historico.setRequisitor_cpf(requisicao.getRequisitor_cpf());
                    historico.setResponsavel_cpf(requisicao.getResponsavel_cpf());

                    String savePath = getServletContext().getRealPath("uploads");
                    historico.uploadFile(savePath);
                    DatabaseManager.insertHistorico(historico);
                    
                    RequisicaoDAO<String> dao = new RequisicaoDAO<>();
                    dao.delete(requisicao.getId());
                } break;
                case "GETFILE": {
                    String savePath = getServletContext().getRealPath("uploads");
                    
                    HistoricoDAO dao = new HistoricoDAO();
                    Historico historico = getHistorico(content);
                    
                    historico = dao.search(historico.getId());

                    File file = historico.getFile(savePath);
                    String fileName = historico.getNomeArquivo();

                    if (file.exists()) {
                        byte[] fileBytes = Files.readAllBytes(file.toPath());
                        String fileContentBase64 = Base64.getEncoder().encodeToString(fileBytes);
                        String base64WithMeta = "data:application/pdf;base64," + fileContentBase64;

                        JSONObject fileInfo = new JSONObject();
                        fileInfo.put("nome", fileName);
                        fileInfo.put("file", base64WithMeta);

                        jsonResponse.put("content", fileInfo);
                    }
                } break;
                default: break;
            }

            jsonResponse.put("status", "OK");
            jsonResponse.put("error", "NOERROR");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                System.out.println("Response H: " + jsonResponse);
                out.print(jsonResponse);
            }   
            
        }
    }
    
    private Historico getHistorico(JSONObject content) {
        String id = content.getString("id");
        String fileContent = content.getString("file");
        
        return new Historico(id, fileContent);
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
