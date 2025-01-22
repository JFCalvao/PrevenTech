package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.dao.Remover;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet(name = "RemoverMaq", urlPatterns = {"/RemoverMaq"})
public class RemoverMaq extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject json = new JSONObject();

        try {
            String jsonData = request.getParameter("json");
            if (jsonData == null || jsonData.isEmpty()) {
                json.put("status", "ERROR");
                json.put("error", "JSON inválido ou ausente");
                response.getWriter().print(json);
                return;
            }

            JSONObject input = new JSONObject(jsonData);
            JSONObject content = input.getJSONObject("content");
            String nPatrimonio = content.getString("n_patrimonio");

            if (nPatrimonio == null || nPatrimonio.trim().isEmpty()) {
                json.put("status", "ERROR");
                json.put("error", "Número de patrimônio não pode estar vazio");
                response.getWriter().print(json);
                return;
            }

            Remover remover = new Remover();
            boolean sucesso = remover.removerEquipamento(nPatrimonio);

            if (sucesso) {
                json.put("status", "OK");
            } else {
                json.put("status", "ERROR");
                json.put("error", "Falha ao remover o equipamento");
            }

        } catch (Exception e) {
            json.put("status", "ERROR");
            json.put("error", e.getMessage());
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(json);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para remover um equipamento do banco de dados";
    }
}
