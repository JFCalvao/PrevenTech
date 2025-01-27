<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finalizar Solicitação</title>
    <link rel="stylesheet" type="text/css" href="css/formularioFinal.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    
    <jsp:include page="header.jsp"></jsp:include>
    
    <div id="header"></div>
    <main>
        <form action="MainServlet" method="post" enctype="multipart/form-data" id="finalizar-form">
            <%
                String requisicaoId = request.getParameter("requisicao_id");
                if (requisicaoId == null) {
                    out.println("<script>alert('Requisição ID não encontrado!'); window.location.href = 'tarefas-tela.jsp';</script>");
                }
            %>
            <input type="hidden" name="requisicao_id" value="<%= requisicaoId %>">

            <label for="anexo">Anexar Relatório (docx, pdf):</label>
            <input type="file" id="anexo" name="anexo" accept=".docx,.pdf" required>
            <br><br>

            <div class="button-container">
                <button type="submit" id="finalizar-btn">Finalizar</button>
                <button type="button" id="cancelar-btn" onclick="window.location.href='tarefas-tela.jsp';">Cancelar</button>
            </div>
        </form>
    </main>

    <script src="js/json.js"></script>
    <script>
        let insert = document.querySelector("#finalizar-form");
        
        insert.addEventListener("submit", (e) => {
            e.preventDefault();
            
            let file = document.querySelector("#anexo").files[0];
            var reader = new FileReader();
            
            reader.onload = function(e) {
                var fileContent = e.target.result;
                
                const url = insert.action;
                const ajax = new XMLHttpRequest();

                ajax.open(insert.method, url, true);
                ajax.onload = function() {
                    if (ajax.status === 200) {
                        var res = ajax.responseText;
                        res = new Response(ajax.responseText);
                        if(res.getStatus() !== "OK") {
                            window.location.href = "erro.jsp?erro=" + res.getError() + "&url=" + window.location.href; 
                        } else {
                            successMessage.style.display = "block";
                            setTimeout(function() {
                                window.location.href = "tarefas-tela.jsp"; 
                            }, 2000); 
                        }
                    } 
                };

                let json = new Request();
                json.setOperation("INSERT");
                json.setType("HS");
                json.setData({
                    "file": fileContent,
                    "requisicao_id": "<%= requisicaoId %>" // Adiciona o requisicao_id aqui
                });

                ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                ajax.send(json.getRequest());
            }
            reader.readAsDataURL(file);
        });
    </script>
</body>
</html>
