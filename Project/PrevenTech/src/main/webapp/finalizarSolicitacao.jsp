<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.sql.*" %>
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
            <label for="anexo">Anexar Relatório (docx, pdf):</label>
            <input type="file" id="anexo" name="anexo" accept=".docx,.pdf" required>
            <br><br>
            <input type="hidden" name="solicitacaoId" value="${param.solicitacaoId}">
            <div class="button-container">
                <button type="submit" id="finalizar-btn">Finalizar</button>
                <button type="button" id="cancelar-btn" onclick="window.location.href='tarefas-tela.jsp';">Cancelar</button>
            </div>
        </form>

    </main>

    <script src="js/json.js"></script>
    <script>
        let id_file = document.querySelector("#id_file");
        let insert = document.querySelector("#finalizar-form");
        let successMessage = document.querySelector("#success-message"); 
        
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
                    "id": "1"
                });

                ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                ajax.send(json.getRequest());
            }
            reader.readAsDataURL(file);
        });
    </script>
</body>
</html>
