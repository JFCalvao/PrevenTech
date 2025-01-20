<%--
    Document   : index
    Created on : 17 de dez de 2024, 11:00:43
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PrevenTech</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
    <%@include file="header.jsp" %>

    <main >
        <section id="secao-login">
            <div id="semi-circulo">
                <img src="imgs/login.png" id="img-login" alt="">
            </div>
            <div id="login">
                <form id="form-login" action="POST">
                    <label>Usuário:
                        <br><input type="text" id="user" name="user">
                    </label>
                    <label>Senha:
                        <br><input type="password" id="senha" name="senha">
                    </label>
                    <button id="entrar-btn">Entrar</button>
                    <a href="cadastro.jsp">Cadastrar</a>

                </form>
            </div>
        </section>

    </main>

    <script src="js/json.js"></script>

    <script src="js/login.js"></script>
    </body>
</html>
