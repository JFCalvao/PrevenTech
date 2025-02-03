package cefetmg.inf.preventech.servers;

import cefetmg.inf.preventech.dao.EquipamentoDAO;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AtualizarEstadoServlet", urlPatterns = {"/AtualizarEstadoServlet"})
public class AtualizarEstadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String n_patrimonio = request.getParameter("n_patrimonio");
        String novoEstado = request.getParameter("estado");

        EquipamentoDAO dao = new EquipamentoDAO();
        try {
            dao.updateEstado(n_patrimonio, novoEstado);
            response.sendRedirect("tarefas-tela.jsp?mensagem=Estado atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("tarefas-tela.jsp?mensagem=Erro ao atualizar estado.");
        }
    }
}
