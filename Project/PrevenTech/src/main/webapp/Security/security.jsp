<%-- 
    Document   : security
    Created on : 14 de jan de 2025, 14:04:18
    Author     : jfcalvao
--%>


<%@ page import="cefetmg.inf.preventech.services.UserService, cefetmg.inf.preventech.services.DefaultService, java.util.List, jakarta.servlet.http.HttpSession" %>

<%
        String url = request.getRequestURL().toString(); 
        String uri = request.getRequestURI(); 
        String actualPage = uri.substring(uri.lastIndexOf('/') + 1);
        
        UserService userService = (UserService)session.getAttribute("service");
        if(userService == null) {
            userService = new DefaultService();
            response.sendRedirect("erro.jsp?erro=Acesso negado para " + actualPage + "&url=index.jsp");
        } else if(!userService.hasAccess(actualPage)) {
            response.sendRedirect("erro.jsp?erro=Acesso negado para " + actualPage + "&url=" + userService.getInitialPage());
        }
        
        //response.sendRedirect("erro.jsp?erro=Acesso Inválido&url=index.jsp");
%>
