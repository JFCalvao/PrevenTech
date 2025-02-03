<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Remover</title>
    <link rel="stylesheet" href="css/cadastromaq.css">
    <link rel="stylesheet" href="css/style.css">
    <%@include file="Security/security.jsp" %>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
    <main>
        <h1>Remover M�quina</h1>

        <form>
            <div class="campo-texto">
                <label for="n-patrimonio">N patrim�nio: </label>
                <input type="text" name="n-patrimonio" id="n-patrimonio">
            </div>

            <div class="campo-texto">
                <label for="maquina-cad">M�quina: </label>
                <input disabled type="text" name="maquina-cad" id="maquina-cad">
            </div>

            <div class="campo-texto">
                <label for="local">Local: </label>
                <input disabled type="text" name="local" id="local">
            </div>

            <div class="campo-texto">
                <label for="estados">Estados:</label>
                <input disabled type="text" name="estados" id="estados">
            </div>

            <div id="botao-container">
                <button id="remove" type="button">REMOVER</button>
                <button type="button" id="cancelar" onclick="window.location.href='coordenador.jsp';">CANCELAR</button>
            </div>
            <div id="resposta"></div>
        </form>
    </main>
    <script src="js/json.js"></script>
    <script src="js/remover.js"></script>
</body>

</html>
