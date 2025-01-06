<%-- Document : cadastrar-maq Created on : 6 de jan. de 2025, 10:39:12 Author : aluno --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Cadastrar</title>
            <link rel="stylesheet" href="css/cadastromaq.css">
            <link rel="stylesheet" href="css/login.css">
        </head>

        <body>
            <jsp:include page="header.jsp"></jsp:include>
            <main>
                <div>
                    <h1>Cadastrar</h1>
                    <p> Número patrimônio: <input type="type" name="n-patrimonio" id="n-patrimonio"> </p>
                    <p>Máquina: <input type="type" name="maquina-cad" id="maquina-cad"> </p>
                    <p>Local: <input type="type" name="local" id="local"> </p>
                    <select id="estados">
                        <option value="funcionamento"> Em funcionamento</option>
                        <option value="defeito">Com defeito</option>
                        <option value="manuencao">Em conserto</option>
                    </select>
                    <button>Cadastrar</button onclick="clicar">
                </div>
            </main>
        </body>

        </html>