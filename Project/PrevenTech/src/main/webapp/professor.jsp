<%-- 
    Document   : professor
    Created on : 17 de dez de 2024, 11:21:35
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="cefetmg.inf.preventech.services.ProfessorService" %>
<%@ page import="cefetmg.inf.preventech.util.Logout" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/professor.css">
    </head>
    <body>
        <%@include file="Security/security.jsp" %>
        <%@include file="header.jsp" %>
        
        <main>
            <div id="container">
                <section id="form-header">
                    <line></line>
                    <h2 id="form-title">Menu</h2>
                    <line></line>
                </section>
                <section id="opcoes-container">
                    <lista id="opcoes">
                        <a href="meus-dados.jsp"><li class="opcao" >Minhas Informações</li></a>
                        <a href="estados.jsp"><li class="opcao">Ver Equipamentos</li></a>
                        <a href="solicitacao.jsp"><li class="opcao">Realizar Solicitação de Manutenção</li></a>
                        <a href="historicos.jsp"><li class="opcao">Ver Histórico</li></a>
                        <a href="requisicoes.jsp"><li class="opcao">Ver todas as requisições</li></a>
                        <a href="minhasRequisicoes.jsp"><li class="opcao">Ver minhas requisições</li></a>
                        <a href="Logout"><li class="opcao logout">Logout</li></a>
                    </lista>
                </section>
            </div>
        </main>
    </body>
</html>
