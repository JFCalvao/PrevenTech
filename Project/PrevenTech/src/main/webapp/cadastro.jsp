<%-- 
    Document   : cadastro
    Created on : 17 de dez de 2024, 11:36:41
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <main>
            <section id="cargos-secao">
                <h2>Cadastro</h2>
                <img id="usuario-img" src="imgs/login.png">
                <div id="cargos">
                    <div class="tecnico-div">
                        <div class="cargo">Técnico</div>
                    </div>
                    <div class="professor-div">
                        <div class="cargo" data-profisso="Professor">Professor</div>
                    </div>
                    <div class="coordenador-div">
                        <div class="cargo" data-profisso="Coordenador">Coordenador</div>
                    </div>
                    <div class="tecnico-informática-div">
                        <div class="subcargo escondido" data-profisso="Técnico em Informática">Informática</div>
                    </div>
                    <div class="tecnico-eletronica-div">
                        <div class="subcargo escondido" data-profisso="Técnico em Eletronica">Eletrônica</div>
                    </div>
                    <div class="continuar">
                        <button id="continuar-btn">Continuar</button>
                        <a id="login-link" href="">Fazer login</a>
                    </div>
                  </div>
            </section>
            <section class="escondido" id="cadastro-secao">
                <h2>Cadastro</h2>
                <form id="form-cadastro" method="POST">
                    <label for="nome">Nome:
                        <input type="text" name="nome" id="nome">
                    </label>
                    <label for="usuario">Usuário (cpf):
                        <input type="text" name="usuario" id="usuario">
                    </label>
                    <label for="email">Email:
                        <input type="text" name="email" id="email">
                    </label>
                    <label for="senha">Senha:
                        <input type="password" name="senha" id="senha">
                    </label>
                    <label for="confirmar-senha">Confirmar senha:
                        <input type="password" name="confirmar-senha" id="confirmar-senha">
                    </label>
                    <button type="submit" id="cadastrar-btn">Cadastrar</button>
                    <a id="login-link" href="">Fazer login</a>
                </form>
            </section>
        </main>

<%--        <script src="jquery-3.7.1.min.js"></script>--%>
        <script src="js/json.js"></script>
        <script src="js/cadastro.js"></script>
    </body>
</html>
