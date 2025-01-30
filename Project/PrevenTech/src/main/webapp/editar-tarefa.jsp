<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="cefetmg.inf.preventech.dto.Equipamento" %>
<%@ page import="cefetmg.inf.preventech.util.DatabaseManager" %>
<%@ page import="cefetmg.inf.preventech.dto.Requisicao" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Tarefa</title>
</head>
<body>
    <form action="atualizarTarefa" method="post">
       <%
    Requisicao requisicao = (Requisicao) request.getAttribute("requisicao");
    if (requisicao == null) {
        response.sendRedirect("erro.jsp");
    }

    List<Equipamento> equipamentos = DatabaseManager.getAllEquipamentos();
%>


        <!-- Escolha de Equipamento -->
        <label for="equipamento">Escolher Equipamento:</label>
        <select id="equipamento" name="equipamento">
            <% for (Equipamento eq : equipamentos) { %>
            <option value="<%= eq.getId() %>" <%= requisicao.getEquipamentoId() == eq.getId() ? "selected" : "" %>>
                <%= eq.getNome() %>
            </option>
            <% } %>
        </select>

        <!-- Estado da Máquina -->
        <label for="estado-maquina">Estado da Máquina:</label>
        <select id="estado-maquina" name="estado-maquina">
            <option value="Funcionando" <%= "Funcionando".equals(requisicao.getEstado()) ? "selected" : "" %>>Funcionando</option>
            <option value="Falha" <%= "Falha".equals(requisicao.getEstado()) ? "selected" : "" %>>Falha</option>
            <option value="Aguardando Manutenção" <%= "Aguardando Manutenção".equals(requisicao.getEstado()) ? "selected" : "" %>>Aguardando Manutenção</option>
        </select>

        <!-- Botão de Enviar -->
        <br><br>
        <button type="submit">Atualizar Tarefa</button>
    </form>
</body>
</html>
