/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

/**
 *
 * @author jfcalvao
 */

package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.*;
import cefetmg.inf.preventech.util.Categorias;
import cefetmg.inf.preventech.dto.Coordenador;
import cefetmg.inf.preventech.dto.Equipamento;
import cefetmg.inf.preventech.dto.Historico;
import cefetmg.inf.preventech.dto.Professor;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.Tecnico;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.services.CoordenadorService;
import cefetmg.inf.preventech.services.ProfessorService;
import cefetmg.inf.preventech.services.TecnicoService;
import cefetmg.inf.preventech.services.UserService;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.Encryption;
import cefetmg.inf.preventech.util.UsersList;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.jws.soap.SOAPBinding;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
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
        
        JSONObject jsonResponse = new JSONObject();
        
        try {
            if(operation.equals("INSERT")) {
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
//                        if(DatabaseManager.hasRequisicao(requisicao)) {;
//                            throw new RequisicaoJaExisteException();
//                        }
                        DatabaseManager.insertRequisicao(requisicao);
                    }
                    break;
                    case "HS": {
                        Historico historico = getHistorico(content);
                        System.out.println("Pegando id: " + historico.getId());
                        System.out.println("Pegando file: " + historico.getConteudoArquivo());
                        Requisicao requisicao = DatabaseManager.searchRequisicao(historico.getId());
                        System.out.println("Pego");
                        
                        historico.setRequisitor_cpf(requisicao.getRequisitor_cpf());
                        historico.setResponsavel_cpf(requisicao.getResponsavel_cpf());
//                        historico.setRequisitor_cpf("12909832498");
//                        historico.setResponsavel_cpf("75739677302");
                        System.out.println("Req: " + requisicao.getRequisitor_cpf());
                        System.out.println("Res: " + requisicao.getResponsavel_cpf());
                        if(DatabaseManager.hasHistorico(historico)) {
                            System.out.println("Ja existe");
                            throw new HistoricoJaExisteException();
                        }
                        
                        System.out.println("Arquivo uploading...");
                        String savePath = getServletContext().getRealPath("uploads");
                        historico.uploadFile(savePath);
                        System.out.println("Arquivo uploaded");
                        DatabaseManager.insertHistorico(historico);
                        System.out.println("Inseted");
                    }
                    break;
                    case "US": {
                        User usuario = getCadastro(content);
                        
                        if(DatabaseManager.hasUsuario(usuario)) {
                            throw new UsuarioJaExisteException();
                        }
                        
                        DatabaseManager.insertUsuario(usuario);
                        UserService service = getUserService(usuario);

                        HttpSession session = request.getSession();
                        usuario.setServer(session);
                        session.setAttribute("usuario", usuario);
                        session.setAttribute("nome", usuario.getNome());
                        session.setAttribute("service", service);
                        
                        UsersList.add(usuario);

                        getRedirectJSP(jsonResponse, usuario);
                        
                        
                    }      
                    break;
                    case "CH": {
                        
                    }
                    break;
                    default: break;
                }
            } else if(operation.equals("GET")) {
                switch (type) {
                    case "EQ":
                        jsonResponse.put("content", DatabaseManager.getAllEquipamentos());
                        break;
                    case "RQ":
                        System.out.println("Olhando requisições");
                        List<Requisicao> all = DatabaseManager.getAllRequisicoes();
                        jsonResponse.put("content", all);
                        System.out.println(jsonResponse.toString());
                        User usuario = UsersList.get(request.getSession());
                        
                        if(usuario == null) { // tem que remover
                            jsonResponse.put("content", all);
                            break;
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
                                for(Requisicao req : all) {
                                    if(categorias.contains(req.getCategoria())) {
                                        accepted.add(req);
                                    }
                                }
                                jsonResponse.put("content", accepted);
                            break;
                        }
                    break;
                    case "US":
                        break;
                    case "CH":
                        break;
                    default:
                        break;
                }
            } else if(operation.equals("UPDATE")) {
                switch (type) {
                    case "US":
                        User usuario = getUserUpdate(content);

                        DatabaseManager.updateUsuario(usuario);

                        HttpSession session = request.getSession();
                        usuario.setServer(session);
                        session.setAttribute("usuario", usuario);
                        session.setAttribute("nome", usuario.getNome());
                        UsersList.add(usuario);

                        jsonResponse.put("redirect", "meus-dados.jsp");
                        break;
                }
            } else if(operation.equals("REMOVE")) {
                
            } else if(operation.equals("LOGIN")) {
                User usuarioLogin = getLogin(content);

                if (DatabaseManager.hasUsuario(usuarioLogin)) {
                    User usuario = DatabaseManager.searchUsuario(usuarioLogin.getCPF());

                    if(!Encryption.encrypt(usuarioLogin.getSenha()).equals(usuario.getSenha()))
                        throw new Exception("Dados de login invalidos");

                    HttpSession session = request.getSession();
                    usuario.setServer(session);
                    session.setAttribute("usuario", usuario);
                    UsersList.add(usuario);
                    UserService service = getUserService(usuario);
                    session.setAttribute("service", service);

                    getRedirectJSP(jsonResponse, usuario);
                }
            } else {

            }
            
            jsonResponse.put("status", "OK");
            jsonResponse.put("error", "NOERROR");
            
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
    
    private UserService getUserService(User user) {
        String profissao = user.getProfissao();
        switch (profissao) {
            case "Professor": {
                Professor prof = new Professor(user);
                return new ProfessorService(prof);
            }
            case "Coordenador": {
                Coordenador coord = new Coordenador(user);
                return new CoordenadorService(coord);
            }
            case "Tecnico em Informatica":
            case "Tecnico em Eletronica": {
                Tecnico tec = new Tecnico(user);
                return new TecnicoService(tec);
            }
            default:
        }
        return null;
    }

    private void getRedirectJSP(JSONObject jsonResponse, User usuario) {
        switch (usuario.getProfissao()) {
            case "Professor":
                jsonResponse.put("redirect", "professor.jsp");
                break;
            case "Coordenador":
                jsonResponse.put("redirect", "coordenador.jsp");
                break;
            case "Tecnico em Informatica":
                jsonResponse.put("redirect", "tecnico.jsp");
                break;
            case "Tecnico em Eletronica":
                jsonResponse.put("redirect", "tecnico.jsp");
                break;
        }
    }

    private Equipamento getEquipamento(JSONObject content) {
        String nome = content.getString("nome");
        String n_patrimonio = content.getString("n_patrimonio");
        String local = content.getString("local");
        String estado = content.getString("estado");
        return new Equipamento(nome, n_patrimonio, local, estado);
    }
    
    private Requisicao getRequisicao(JSONObject content) throws NoSuchCategoriaException {
        String requisicao_id = "";
        String requisitor_cpf = content.getString("requisitor");
        String responsavel_cpf = "";
        String data_inicio = "";
        String categoria = content.getString("categoria");
        String equipamentos = content.getString("equipamentos");
        String descricao = content.getString("descricao");
        
        System.out.println("R: " + requisitor_cpf + "\nC: " + categoria + "\nE: " + equipamentos + "\nD: " + descricao);
        
        return new Requisicao(requisicao_id, requisitor_cpf, responsavel_cpf, 
                              data_inicio, Categorias.getCategoriaCode(categoria), 
                              equipamentos, descricao);
    }
    
    private Historico getHistorico(JSONObject content) {
        String id = content.getString("id");
        String fileContent = content.getString("file");
        
        return new Historico(id, fileContent);
    }

    private User getCadastro(JSONObject content) {
        String nome = content.getString("nome");
        String cpf = content.getString("cpf");
        String senha = content.getString("senha");
        String email = content.getString("email");
        String profissao = content.getString("profissao");
        
        return new User(nome, cpf, senha, email, profissao);
    }

    private User getUserUpdate(JSONObject content) throws SQLException, EncryptationException {
        String nome = content.getString("nome");
        String cpf = content.getString("cpf");
        String senha = content.getString("senha");
        String email = content.getString("email");

        User usuario = DatabaseManager.searchUsuario(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);

        if (senha.length() > 6) {
            usuario.setSenha(senha);
        } else {
            usuario.setSenha(Encryption.decrypt(usuario.getSenha()));
        }

        return usuario;
    }
    
    private User getLogin(JSONObject content) {
        String cpf = content.getString("cpf");
        String senha = content.getString("senha");
        
        return new User(cpf, senha);
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
