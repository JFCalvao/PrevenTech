<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Finalizar Solicitação</title>
     <link rel="stylesheet" type="text/css" href="css/formularioFinal.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
     <jsp:include page="header.jsp"></jsp:include>
    <div id="header">
    </div>
    <main>
        <form action="processarFinalizacao.jsp" method="post" enctype="multipart/form-data" id="finalizar-form">
            <label for="anexo">Anexar Relatório (docx, pdf):</label>
            <input type="file" id="anexo" name="anexo" accept=".docx,.pdf" required>
            <br><br>
            <input type="hidden" name="solicitacaoId" value="${param.solicitacaoId}">
            <button type="submit" id="finalizar-btn">Finalizar</button>
        </form>
    </main>
</body>
</html>