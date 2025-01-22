/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cefetmg.inf.preventech.servers;


import java.io.IOException;
import java.sql.SQLException;
import cefetmg.inf.preventech.dao.Equipamento;
import cefetmg.inf.preventech.util.DatabaseManager;
import cefetmg.inf.preventech.Exceptions.EncryptationException;
import cefetmg.inf.preventech.Exceptions.EquipamentoNaoEncontradoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/equipamento")
public class EquipamentoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String n_patrimonio = request.getParameter("n_patrimonio");
        String local = request.getParameter("local");
        String estado = request.getParameter("estado");
        Equipamento equipamento = new Equipamento(nome, n_patrimonio, local, estado);

        try {
            if (!DatabaseManager.hasEquipamento(equipamento)) {
                DatabaseManager.insertEquipamento(equipamento);
            } else {
                DatabaseManager.updateEquipamento(equipamento);
            }
            response.getWriter().write("Equipamento processado com sucesso!");
        } catch (SQLException | EncryptationException | EquipamentoNaoEncontradoException e) {
            e.printStackTrace();
            response.getWriter().write("Erro ao processar o equipamento: " + e.getMessage());
        }
    }
}
