package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.Exceptions.NoSuchCategoriaException;
import cefetmg.inf.preventech.Exceptions.NoSuchTableException;
import cefetmg.inf.preventech.dao.RequisicaoDAO;
import cefetmg.inf.preventech.dto.Requisicao;
import cefetmg.inf.preventech.dto.User;
import cefetmg.inf.preventech.util.Encryption;
import cefetmg.inf.preventech.util.UsersList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Tecnico", urlPatterns = {"/Tecnico"})
public class Tecnico extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

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
                requisicaoJson.put("descricao", requisicao.getDescricao());

                String nomeEquipamento = Encryption.decrypt(requisicao.getArrEquipamentos().get(0).getNome());
                String localEquipamento = Encryption.decrypt(requisicao.getArrEquipamentos().get(0).getLocal());
                String estadoEquipamento = Encryption.decrypt(requisicao.getArrEquipamentos().get(0).getEstado());

                requisicaoJson.put("equipamento", new JSONObject()
                    .put("nome", nomeEquipamento)
                    .put("n_patrimonio", requisicao.getArrEquipamentos().get(0).getN_patrimonio())
                    .put("local", localEquipamento)
                    .put("estado", estadoEquipamento)
                );

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
        } catch (JSONException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"error\": \"Erro interno\"}");
            }
        } catch (EncryptationException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.write("{\"error\": \"Erro ao descriptografar os dados\"}");
            }
        } catch (NoSuchCategoriaException ex) {
            Logger.getLogger(Tecnico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchTableException ex) {
            Logger.getLogger(Tecnico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
