<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PrevenTech - Meus dados</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <main>
            <section id="dados-pessoais-secao">
                <h2>Cadastro</h2>
                <img id="usuario-img" src="imgs/login.png">
                <div id="editar-nome-usuario">
                    <h3 id="nome-usuario">Nome de usuário</h3>
                    <button class="edit-btn"><img class="edit-img" src="imgs/pencil.png"></button>
                </div>
                <label>Email:<input type="text" name="email" id="email">
                </label>
                <button>Salvar</button>
            </section>
        </main>
    </body>
</html>
