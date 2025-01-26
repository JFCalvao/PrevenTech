<%-- 
    Document   : obter-usuario
    Created on : 26 de jan de 2025, 15:10:23
    Author     : rafav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="cefetmg.inf.preventech.services.UserService" %>
<%
    UserService userService = (UserService)session.getAttribute("service");

    String nome = (userService != null) ? userService.getNome() : "indefinido";
    
    out.print(nome);
%>