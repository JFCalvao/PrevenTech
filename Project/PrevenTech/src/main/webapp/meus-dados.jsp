<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PrevenTech - Meus dados</title>
    <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/login.css">
    <%@include file="Security/security.jsp" %>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<main>
    <section id="dados-pessoais-secao">
        <h2>Cadastro</h2>
        <img id="usuario-img" src="imgs/login.png">
        <div id="editar-nome-usuario">
            <h3 id="nome-usuario">
                <%= userService.getNome() %>
            </h3>
            <input type="hidden" value="${sessionScope.usuario.nome}" name="nome" id="nome">
            <button class="edit-btn escondido"><img class="edit-img" src="imgs/pencil.png"></button>
        </div>
        <input type="hidden" value="${sessionScope.usuario.CPF}" name="cpf" id="cpf">
        <label>Email:<input type="text" name="email" id="email"
            value="${sessionScope.usuario.email}"
        >
        <input  type="hidden" value="" name="senha" id="senha">
        </label>
        <div id="buttons" >
            <button id="cancelar" onclick="window.location.href = '<%= userService.getInitialPage() %>';" >Voltar</button>
            <button id="salvar">Salvar</button>
        </div>
    </section>
</main>

    <script src="js/json.js"></script>
    <script src="js/meus-dados-edicao.js"></script>
</body>
</html>