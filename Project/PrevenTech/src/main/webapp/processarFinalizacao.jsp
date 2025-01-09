<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/processarFinalizacao.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <main>
            
             <form action="MainServlet" method="post" enctype="multipart/form-data" id="insert">
                <label for="anexo">Anexar Relatório (docx, pdf):</label>
                <input type="file" id="anexo" name="anexo" accept=".docx,.pdf" required>
                <br><br>
                <input type="hidden" name="solicitacaoId" id="id_file" value="${param.solicitacaoId}">
                <button type="submit" id="finalizar-btn">Finalizar</button>
            </form>
            <response></response>
        </main>
        <script src="js/json.js"></script>
        <script>
            let id_file = document.querySelector("#id_file");
            let insert = document.querySelector("#insert");
            let response = document.querySelector("response");
            
            insert.addEventListener("submit", (e) => {
                e.preventDefault(); // <--- isto pára o envio da form
                
                let file = document.querySelector("#anexo").files[0];
                var reader = new FileReader();
                
                reader.onload = function(e) {
                    var fileContent = e.target.result;
                    
                    const url = insert.action; // <--- o url que processa a form
                    const ajax = new XMLHttpRequest();

                    ajax.open(insert.method, url, true);
                    ajax.onload = function() {
                        if (ajax.status === 200) {
                        var res = ajax.responseText;
                        response.innerHTML = res;
                      } else {
                        alert('Algo falhou...');
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