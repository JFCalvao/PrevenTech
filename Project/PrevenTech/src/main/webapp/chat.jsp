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
        <script src="js/chat.js" defer ></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <main>
            <div class="chat-container">
                <div class="chat-box" id="chatBox">
                    <!-- Aqui as mensagens serão exibidas -->
                    <div class="message incoming">
                        <p><span>User 1:</span> Olá! Como você está?</p>
                    </div>
                    <div class="message outgoing">
                        <p><span>User 2:</span> Estou bem, obrigado! E você?</p>
                    </div>
                </div>
                <div class="input-box">
                    <input type="text" id="message" placeholder="Digite sua mensagem...">
                    <button type="button" id="sendButton">Enviar</button>
                </div>
            </div>
        </main>
    </body>
</html>
