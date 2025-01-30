<%-- 
    Document   : chat
    Created on : 22 de jan de 2025, 15:53:19
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/chat.css">
        <%@include file="Security/security.jsp" %>
        <script> 
            const initialPage = "<%= userService.getInitialPage() %>";
            const username = "<%= userService.getNome() %>"; 
            const userCPF = "<%= userService.getCPF() %>";
            const id = "1";
        </script>
        <script src="js/chat.js" defer ></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <main>
            <div class="chat-container">
                <div class="chat-box" id="chatBox">
                    <!-- Aqui as mensagens serão exibidas -->
                    
                </div>
                <div class="input-box">
                    <input spellcheck="false" autocomplete="off" type="text" id="message" placeholder="Digite sua mensagem...">
                    <button type="button" id="sendButton">Enviar</button>
                </div>
            </div>
        </main>
    </body>
</html>
