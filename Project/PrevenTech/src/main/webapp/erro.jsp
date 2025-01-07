<%-- 
    Document   : erro
    Created on : 6 de jan de 2025, 15:09:59
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/erro.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <main>
            <erro>
                <h1>ERRO</h1>
                <line></line>
                <c:set var="descricao" value="${param.erro}"></c:set>
                <c:if test="${descricao != null}">
                    <descricao>${descricao}</descricao>
                </c:if>
                <c:if test="${descricao == null}">
                    <descricao>
                        Um erro desconhecido ocorreu. Tente novamente mais tarde
                        ou contate um suporte do nosso time para ajudá-lo a
                        resolver o problema. Pedimos perdão pelo incoveniente.
                    </descricao>
                </c:if>
                <c:set var="url" value="${param.url}"></c:set>
                <c:if test="${url == null}">
                    <c:set var="url" value="index.jsp"></c:set>
                </c:if>
                <button>VOLTAR</button>
            </erro>
        </main>
        <script>
            let btn = document.querySelector("button");
            
            btn.addEventListener("click", click);
            
            function click() {
                let url = "<c:out value="${url}"></c:out>";
                window.location.href = url;
            }
        </script>
    </body>
</html>
