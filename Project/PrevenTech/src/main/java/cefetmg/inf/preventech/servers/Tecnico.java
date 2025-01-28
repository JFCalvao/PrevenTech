package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.dao.RequisicaoDAO;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.DataManager;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.util.UsersList;
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
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet(name = "Tecnico", urlPatterns = {"/Tecnico"})
public class Tecnico extends HttpServlet {

    private DatabaseManager databaseManager;
    private DataManager dataManager;

    @Override
    public void init() throws ServletException {
        super.init();
        this.databaseManager = new DatabaseManager();
        this.dataManager = new DataManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession(false);

        User user = UsersList.get(session);

        if (user == null || user.getCPF() == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"error\": \"Usuário não autenticado\"}");
            }
            return;
        }

        String responsavelCpf = user.getCPF();

        try {
            RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
            List<Requisicao> requisicoes = requisicaoDAO.getAll();

            List<Requisicao> requisicoesFiltradas = new ArrayList<>();
            for (Requisicao requisicao : requisicoes) {
                if (responsavelCpf.equals(requisicao.getResponsavel_cpf())) { 
                    requisicoesFiltradas.add(requisicao);
                }
            }

            JSONArray requisicoesArray = new JSONArray();
            for (Requisicao requisicao : requisicoesFiltradas) {
                JSONObject requisicaoJson = new JSONObject();
                requisicaoJson.put("requisicao_id", requisicao.getId());
                
                requisicaoJson.put("requisitor_nome", requisicao.getRequisitorString());
                requisicaoJson.put("requisitor_cpf", requisicao.getRequisitor_cpf());
                requisicaoJson.put("responsavel_cpf", requisicao.getResponsavel_cpf());
                requisicaoJson.put("responsavel_nome", requisicao.getResponsavelString());
                requisicaoJson.put("data_inicio", requisicao.getData());
                requisicaoJson.put("categoria", requisicao.getCategoria());
                requisicaoJson.put("descricao", requisicao.getDescricao());
                requisicaoJson.put("equipamentos", requisicao.getArrEquipamentos());
                requisicaoJson.put("categoriaString", requisicao.getCategoriaString());

                requisicoesArray.put(requisicaoJson);
            }

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("content", requisicoesArray);
            jsonResponse.put("status", "OK");
            jsonResponse.put("error", "NOERROR");

            try (PrintWriter out = response.getWriter()) {
                out.write(jsonResponse.toString());
            }

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"error\": \"Erro ao acessar o banco de dados\"}");
            }
            e.printStackTrace();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"error\": \"Erro interno\"}");
            }
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requisitor = request.getParameter("requisitor");
        String status = request.getParameter("status");
        String nomeTecnico = request.getParameter("nomeTecnico");
        String dataHora = request.getParameter("dataHora");
        String categoria = request.getParameter("categoria");

        String[] partesRequisitor = requisitor.split(":");
        requisitor = partesRequisitor[1].trim();
    }
}