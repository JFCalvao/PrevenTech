package cefetmg.inf.preventech.servers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dao.RequisicaoDAO;
import cefetmg.inf.preventech.util.Categorias;
import cefetmg.inf.preventech.Exceptions.*;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.UsersList;

/**
 *
 * @author rafav
 */
@WebServlet(name = "RequisicaoServlet", urlPatterns = {"/RequisicaoServlet"})
public class RequisicaoServlet extends HttpServlet {
    
    private JSONObject jsonResponse = new JSONObject();

    protected Requisicao getRequisicao(JSONObject content) throws NoSuchCategoriaException {
        String requisicao_id = ""; // Você pode gerar um ID ou receber do JSON
        String requisitor_cpf = content.getString("requisitor");
        String responsavel_cpf = ""; // Responsável pode ser vazio inicialmente
        String data_inicio = ""; // Data inicial pode ser vazia
        String categoria = content.getString("categoria");
        String equipamentos = content.getString("equipamentos");
        String descricao = content.getString("descricao");

        System.out.println("R: " + requisitor_cpf + "\nC: " + categoria + "\nE: " + equipamentos + "\nD: " + descricao);

        return new Requisicao(requisicao_id, requisitor_cpf, responsavel_cpf,
                              data_inicio, Categorias.getCategoriaCode(categoria),
                              equipamentos, descricao);
    }
    
    protected void insert(RequisicaoDAO dao, JSONObject content) 
            throws NoSuchCategoriaException, SQLException, EncryptationException, RequisicaoJaExisteException {
        Requisicao requisicao = getRequisicao(content);

        if (dao.has(requisicao)) {
            throw new RequisicaoJaExisteException();
        }
        dao.create(requisicao);
        jsonResponse.put("status", "success");
        jsonResponse.put("message", "Requisição criada com sucesso.");
    }
    
    protected void get(RequisicaoDAO dao, HttpServletRequest request) 
            throws SQLException, EncryptationException, NoSuchCategoriaException, NoSuchTableException {
        
        List<Requisicao> all = dao.getAll();
        jsonResponse.put("content", all);
        User usuario = UsersList.get(request.getSession());

        if(usuario == null) { // tem que remover
            jsonResponse.put("content", all);
            return;
        }   


        String profissao = usuario.getProfissao();
        switch(profissao) {
            case "Professor":
            case "Coordenador":
                jsonResponse.put("content", all);
                break;
            case "Tecnico em Informatica":
            case "Tecnico em Eletronica":
                List<Requisicao> accepted = new ArrayList<>();
                List<String> categorias = Categorias.acceptedCategoriesFor(profissao);
                for(Requisicao req : all) 
                    if(categorias.contains(req.getCategoriaString())) 
                        accepted.add(req);
                
                jsonResponse.put("content", accepted);
                
            break;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        JSONObject json = new JSONObject(request.getParameter("json"));
        String operation = json.getString("operation");
        JSONObject content = json.getJSONObject("content");
        RequisicaoDAO dao = new RequisicaoDAO();
        
        try {
            if (operation.equals("INSERT")) 
                insert(dao, content);
            else if (operation.equals("GET")) 
                get(dao, request);
            else if (operation.equals("UPDATE")) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Requisição atualizada com sucesso.");
            } 
            else 
                jsonResponse.put("error", "Operação desconhecida: " + operation);
            
            jsonResponse.put("error", "NOERROR");
        }
        catch(Exception e) {
            jsonResponse.put("error", "Tabela não encontrada: " + e.getMessage());
            System.out.println("Exception: " + e.getMessage());   
        }
        
        finally {
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                System.out.println("Response: " + jsonResponse);
                out.print(jsonResponse);
            }   
            
        }
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
