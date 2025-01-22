<%-- Document : cadastrar-maq Created on : 6 de jan. de 2025, 10:39:12 Author :
aluno --%> <%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cadastrar</title>
    <link rel="stylesheet" href="css/cadastromaq.css" />
    <link rel="stylesheet" href="css/style.css" />
  </head>

  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <main>
      <h1>Cadastrar</h1>
      <div id="linha-titulo"></div>

      <form>
        <div class="campo-texto">
          <label for="n-patrimonio">N patrimônio: </label>
          <input type="number" name="n-patrimonio" id="n-patrimonio" />
        </div>

        <div class="campo-texto">
          <label for="maquina-cad">Máquina:</label>
          <input type="text" name="maquina-cad" id="maquina-cad" />
        </div>

        <div class="campo-texto">
          <label for="local">Local: </label>
          <input type="text" name="local" id="local" />
        </div>

        <div class="campo-texto">
          <label for="estados">Estados:</label>
          <select id="estados">
            <option value="funcionamento">FUNCIONANDO</option>
            <option value="defeito">COM DEFEITO</option>
            <option value="manutencao">EM MANUTENÇÃO</option>
          </select>
        </div>

        <div id="botao-container">
          <button id="cadastro" type="button">CADASTRAR</button>
          <button type="button" id="cancelar">CANCELAR</button>
        </div>

        <div id="resposta"></div>
      </form>
    </main>
    <script src="js/json.js"></script>
    <script src="js/cadastromaq.js"></script>
  </body>
</html>
