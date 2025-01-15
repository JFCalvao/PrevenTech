<%-- 
    Document   : professor
    Created on : 17 de dez de 2024, 11:21:35
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="cefetmg.inf.preventech.dao.User, cefetmg.inf.preventech.services.ProfessorService, java.util.List, jakarta.servlet.http.HttpSession" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/professor.css">
    </head>
    <body>
        <%@include file="Security/security.jsp" %>
        <%@include file="header.jsp" %>
        
        <%
            HttpSession s = request.getSession(false);
            ProfessorService service = null;
            String nome = "Joao";
            
            if(s != null) {
//                service = new ProfessorService(s);
//                User user = (User)s.getAttribute("usuario");
                
//                s.setAttribute("initialPage", service.getInitialPage());
//                s.setAttribute("cpf", user.getCPF());
            }
        %>
        
        <main>
            <div id="container">
                <section id="form-header">
                    <line></line>
                    <h2 id="form-title">Opções de <%= nome %></h2>
                    <line></line>
                </section>
                <section id="opcoes-container">
                    <ul id="opcoes">
                        <li class="opcao"><a href="meus-dados.jsp">Minhas Informações</a></li>
                        <li class="opcao"><a href="estados.jsp">Ver Equipamentos</a></li>
                        <li class="opcao"><a href="solicitacao.jsp">Realizar Solicitação de Manutenção</a></li>
                    </ul>
                </section>
            </div>
        </main>
    </body>
</html>
