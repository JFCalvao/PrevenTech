<%-- 
    Document   : minhasRequisicoes
    Created on : 20 de jan de 2025, 14:27:28
    Author     : rafav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/minhasrequisicoes.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="Security/security.jsp" %>
        <%@include file="header.jsp" %>

        <main>
            <div class="minhas-requisicoes">
                <div id="titulo">
                    <h3>Minhas Requisições</h3>
                    <div class="linha-titulo"></div>
                </div>

                <div class="body">
                    <div class="requisicao">
                        <div class="view">
                            <div class="informacoes-basicas">
                                <h4>Requisição <span id="numero-requisicao">01</span>/<span id="numero-requisicoes">10</span></h4>
                                <p>Requisitor: Cristiano Amaral Maffort</p>
                            </div>
                            <div class="setinha-expandir-retrair"></div>
                        </div>
                        <div class="informacoes-expandir ocultar">
                            <div id="linha-requisicao"></div>
                            <div id="status">
                                <span id="cor-status"></span>
                                <span id="txt-status">Pendente</span>
                            </div>

                            <div id="informacoes-e-chat">
                                <div id="informacoes">
                                    <div id="tecnico">
                                        <span id="txt-tecnico">Técnico responsável: </span>
                                        <span id="txt-nome-tecnico">Caio Silveira</span>
                                    </div>
                                    <div id="data-horario-envio">
                                        <span id="txt-enviado">Enviado: </span>
                                        <span id="txt-horario">17:02</span>
                                        <span id="txt-data"> - 04/10/2024</span>
                                    </div>
                                    <div id="categoria">
                                        <span id="txt-categoria">Categoria: </span>
                                        <span id="nome-categoria">Falha nos cabos de conexão</span>
                                    </div>
                                </div>
                                <div id="chat">
                                    <button id="botao-chat">
                                        <img id="img-chat" src="imgs/chat.jpg">
                                    </button>
                                </div>
                            </div>
                        
                            <div id="equipamentos">
                                <span id="txt-equipamentos">Equipamentos: </span>

                                <div class="maquina">
                                    <div id="informacoes">
                                        <span id="numero-nome">
                                            <span id="numero">3902</span>
                                            <span id="nome">Ventilador Tron - 156165</span>
                                        </span>
                                        <span id="local">Local: Sala 107, Prédio 20</span>
                                        <span id="span-situacao">Situação:
                                            <span id="situacao">
                                                <span id="cor-situacao"></span>
                                                <span id="txt-situacao" style="color: red">Com defeito</span>
                                            </span>
                                        </span>
                                    </div>
                                
                                    <div class="linha-maquina"></div>
                                </div>
                                
                                <div class="maquina">
                                    <div id="informacoes">
                                        <span id="numero-nome">
                                            <span id="numero">3902</span>
                                            <span id="nome">Ventilador Tron - 156165</span>
                                        </span>
                                        <span id="local">Local: Sala 107, Prédio 20</span>
                                        <span id="span-situacao">Situação:
                                            <span id="situacao">
                                                <span id="cor-situacao"></span>
                                                <span id="txt-situacao" style="color: red">Com defeito</span>
                                            </span>
                                        </span>
                                    </div>
                                
                                    <div class="linha-maquina"></div>
                                </div>
                            </div>

                            <div id="descricao">
                                <h5>Descrição</h5>
                                <span>As máquinas da sala 310 estão com falhas em seus cabos de rede e algumas não estão sendo alimentadas.
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
