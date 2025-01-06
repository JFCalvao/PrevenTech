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
        <title>PrevenTech</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
    <%@include file="header.jsp" %>
    
    <main id="login">
        <div id="semi-circulo">
    
        </div>
        <form method="POST">
            <label for="user">Usuário: </label><br>
            <input type="text" id="user" name="user"><br>
            <label for="senha">Senha:</label><br>
            <input type="text" id="senha" name="senha">
            <label for="submit"></label>
            <input type="submit" id="submit"> <br>
            <a href="cadastro.jsp">Cadastrar</a>
        </form>
    </main>
    </body>
</html>
