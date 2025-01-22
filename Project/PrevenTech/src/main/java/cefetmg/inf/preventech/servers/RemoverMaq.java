package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.dto.Remover; 
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RemoverMaq", urlPatterns = {"/RemoverMaq"})
public class RemoverMaq extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nPatrimonio = request.getParameter("n_patrimonio");

        if (nPatrimonio == null || nPatrimonio.trim().isEmpty()) {
            out.print("{\"status\":\"ERROR\", \"message\":\"Número de patrimônio inválido.\"}");
            return;
        }

        Remover remover = new Remover(); 
        boolean sucesso = remover.removerEquipamento(nPatrimonio); 

        if (sucesso) {
            out.print("{\"status\":\"OK\", \"message\":\"Máquina removida com sucesso.\"}");
        } else {
            out.print("{\"status\":\"ERROR\", \"message\":\"Erro ao remover a máquina. Verifique se o número de patrimônio está correto.\"}");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para remover um equipamento do banco de dados";
    }
}
