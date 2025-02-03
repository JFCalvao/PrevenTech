<%@page import="java.util.List"%>
<%@page import="cefetmg.inf.preventech.dao.EquipamentoDAO"%>
<%@page import="cefetmg.inf.preventech.dto.Equipamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Minhas tarefas</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/tarefas.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
<body>
    <%@include file="header.jsp" %>
    <%@include file="Security/security.jsp" %>
    <button id="btn-voltar">Voltar</button>

    <main>
        <div class="requisicoes">
            <div id="titulo">
                <div id="linha-titulo"></div>
            </div>
            <div id="requisicoes-dinamicas">
                <!-- Requisições serão carregadas aqui dinamicamente -->
            </div>
        </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.6.4.js" crossorigin="anonymous"></script>
    <script src="js/tarefas-tela.js"></script>
</body>

</html>
