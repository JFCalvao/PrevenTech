<%-- 
    Document   : requisicoes
    Created on : 20 de jan de 2025, 14:27:00
    Author     : rafav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/requisicoes.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="Security/security.jsp" %>
        <%@include file="header.jsp" %>

        <main>
            <div class="requisicoes">
                <div id="titulo">
                    <h3>Requisições</h3>
                    <div class="linha-titulo"></div>
                </div>

                <div class="body">
                    <!-- Requisições aqui -->
                </div>
            </div>
            <section id="voltar-area">
                <a id="voltar-link" href="<%= userService.getInitialPage() %>">VOLTAR</a>
            </section>
        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/expandir-retrair-div.js"></script>
    <script src="js/json.js"></script>
    <script src="js/requisicoes.js"></script>
</html>
