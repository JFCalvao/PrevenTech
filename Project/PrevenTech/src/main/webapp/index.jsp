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
    <jsp:include page="header.jsp"></jsp:include>
    
    <main >
        <section id="login-secao">
            <div id="semi-circulo">
                <img src="imgs/login.png" id="img-login" alt="">
            </div>
            <div id="login">
                <img src="login.png" alt="" id="pessoa">
                <form id="form-login" action="POST">
                    <label>Usuário: 
                        <br><input type="text" id="user" name="user">
                    </label>
                    <label>Senha:
                        <br><input type="password" id="senha" name="senha">
                    </label>
                    <button id="entrar-btn">Entrar</button>
                    <a href="cadastro.html">Cadastrar</a>

                </form>
            </div>
        </section>

    </main>
    </body>
</html>
