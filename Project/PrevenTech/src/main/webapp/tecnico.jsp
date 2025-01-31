<%-- 
    Document   : tecnico
    Created on : 29 de jan de 2025, 11:30:00
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu Técnico</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/tecnico.css">
    </head>
    <body>
       
        <%@include file="header.jsp" %>
        <%@include file="Security/security.jsp" %>
        <main>
            <div id="container">
                <section id="form-header">
                    <line></line>
                    <h2 id="form-title">Menu Técnico</h2>
                    <line></line>
                </section>
                <section id="opcoes-container">
                    <lista id="opcoes">
                        <a href="meus-dados.jsp"><li class="opcao">Minhas Informações</li></a>
                        <a href="estados.jsp"><li class="opcao">Equipamentos</li></a>
                        <a href="historicos.jsp"><li class="opcao">Histórico</li></a>
                        <a href="requisicoes-tecnico.jsp"><li class="opcao">Todas Requisições</li></a>
                        <a href="tarefas-tela.jsp"><li class="opcao">Minhas Requisições</li></a>
                        <a href="Logout"><li class="opcao">Logout</li></a>
                    </lista>
                </section>
            </div>
        </main>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/expandir-retrair-div.js"></script>
        <script src="js/json.js"></script>
        <script src="js/requisicoes.js"></script>
    </body>
</html>
