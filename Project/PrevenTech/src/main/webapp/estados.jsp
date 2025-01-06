<%-- 
    Document   : coordenador
    Created on : 17 de dez de 2024, 11:21:19
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estados</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/estados.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <main>
            <div class="aparelhos">
                <div id="titulo">
                    <h3>Equipamentos</h3>
                
                     
                    <div id="linha-titulo"></div>
                    
                     <input type="text" name="pesquisa" id="pesquisar">
                    <div onclick="Pesquisar()" id="btn-pesquisa"> <img src="imgs/lupa.png" alt="alt"/></div>
                    
                </div>

                <div class="body">
                    <div class="requisicao">
                        
                        <div class="view">
                             <span id="numero-das-maquinas">
                                    <span>4002</span>
                                     </span>
                            <div class="informacoes-basicas">
                                <h4>Computador Dell - FNQQVN2 <span id="numero-requisicao">01</span>/<span id="numero-requisicoes">10</span></h4>
                                <p>Local: Sala 107, Prédio 20</p>
                            </div>
                            
                            <div class="setinha-expandir-retrair"></div>
                        </div>
                        
                        <div class="informacoes-expandir escondido">
                            <div id="linha-requisicao"></div>
                            <div id="status">
                                <span id="cor-status"></span>
                                <span id="txt-status">Pendente</span>
                            </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script   src="https://code.jquery.com/jquery-3.6.4.js"   integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="   crossorigin="anonymous"></script>
        <script src="js/expandir-retrair-div.js"></script>
    </body>
</html>