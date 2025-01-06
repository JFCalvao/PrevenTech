<%-- Document : cadastrar-maq Created on : 6 de jan. de 2025, 10:39:12 Author : aluno --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Cadastrar</title>
            <link rel="stylesheet" href="css/cadastromaq.css">
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>
            <jsp:include page="header.jsp"></jsp:include>
            <main>
       
                    <h1>Cadastrar</h1>
                     <div id="linha-titulo"></div>
                
                    
                    <form>
                        
                        <div class="campo-texto">
                    <label for="n-patrimonio">Número patrimônio: </label>
                    <input type="text" name="n-patrimonio" id="n-patrimonio">
                    </div>
                    
                    <div class="campo-texto">
                    <label for="maquina-cad">Maquina:  </label>
                        <input type="text" name="maquina-cad" id="maquina-cad">
                   </div>

                    <div class="campo-texto">
                        <label for="local">Local: </label>
                        <input type="text" name="local" id="local">
                    </div>

                    <p> Estado: <select id="estados"> <br>
                            <option value="funcionamento">FUNCIONANDO</option>
                            <option value="defeito">COM DEFEITO</option>
                            <option value="manuencao">EM MANUTENÇÃO</option>
                        </select> </p> <br>

                    <p> <button onclick="clicar()">CADASTRAR</button> <br></p> //comit
                    
                    </form>

            </main>
        </body>

        </html>