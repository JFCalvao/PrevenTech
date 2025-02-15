package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.dto.Remover;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet(name = "RemoverMaq", urlPatterns = {"/RemoverMaq"})
public class RemoverMaq extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONObject json = new JSONObject();

        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            JSONObject input = new JSONObject(jsonData);
            JSONObject content = input.getJSONObject("content");
            String nPatrimonio = content.getString("n_patrimonio");

            Remover remover = new Remover();
            boolean sucesso = remover.removerEquipamento(nPatrimonio);

            if (sucesso) {
                json.put("status", "OK");
                json.put("data", obterMaquinas()); 
            } else {
                json.put("status", "ERROR");
                json.put("error", "Falha ao remover o equipamento");
            }

        } catch (EncryptationException | JSONException e) {
            json.put("status", "ERROR");
            json.put("error", e.getMessage());
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(json);
        }
    }

    private JSONArray obterMaquinas() {
      
        return new JSONArray();
    }

    @Override
    public String getServletInfo() {
        return "Servlet para remover um equipamento do banco de dados";
    }
}
