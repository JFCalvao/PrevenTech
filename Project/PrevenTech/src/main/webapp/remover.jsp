<%-- Document : cadastrar-maq Created on : 6 de jan. de 2025, 10:39:12 Author : aluno --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Remover</title>
            <link rel="stylesheet" href="css/cadastromaq.css">
            <link rel="stylesheet" href="css/style.css">
        </head>

        <body>
            <jsp:include page="header.jsp"></jsp:include>
            <main>
       
                    <h1>Remover Máquina</h1>
                    
                    <form>
                        
                        <div class="campo-texto">
                    <label for="n-patrimonio">N patrimônio: </label>
                    <input type="text" name="n-patrimonio" id="n-patrimonio-remov">
                    </div>
                    
                    <div class="campo-texto">
                    <label for="maquina-cad">Maquina:  </label>
                        <input type="text" name="maquina-cad" id="maquina-cad">
                   </div>

                    <div class="campo-texto">
                        <label for="local">Local: </label>
                        <input type="text" name="local" id="local">
                    </div>

                   <div class="campo-texto">
                        <label for="estados">Estados:</label> 
                              <input type="text" name="local" id="estados">
                        </div>

                    <p> <button onclick="clicar">REMOVER</button> <br></p>
                  
                    </form>

            </main>
        </body>

        </html>