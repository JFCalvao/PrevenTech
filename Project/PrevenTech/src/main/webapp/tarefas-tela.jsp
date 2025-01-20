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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Minhas tarefas</title>
        <link rel="shortcut icon" href="imgs/cefet.png" type="image/x-icon">
        <link rel="stylesheet" href="css/tarefas.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <main>
            <div class="requisicoes">
                <div id="titulo">
                    <h3>Minhas tarefas</h3>
                    <div id="linha-titulo"></div>
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
                        <div class="informacoes-expandir escondido">
                            <div id="linha-requisicao"></div>
                            
                            <div id="mutavel-1">
                                   <div id="status">
                                <span id="cor-status"></span>
                                <span id="txt-status">Pendente</span>
                            </div>
                            <div id="tecnico">
                                <span id="txt-tecnico">Técnico responsável: </span>
                                <span id="txt-nome-tecnico">Ninguém</span>
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
                         
                            <div id="mutavel-2">
                                      
                            <div id="equipamentos">
                                
                                <span id="txt-equipamentos">Equipamentos: </span>

                                <div class="maquina">
                                   <div class="informacoes>
                                        <span id="maquina-cad">Computador Dell</span>
                                        <span id="n-patrimonio"> FNQQVN2</span>
                                        <p id="local">Local: Sala 107, Prédio 20</p>
                                         <p  id="situacao">Situação: </p> 
                                    </div>
                            </div>
                          
                                  
             
                                    <div class="linha-maquina"></div>
                                </div>
                             
                                
                            </div>
                            

                            <div id="descricao">
                                <h5>Descrição</h5>
                                <span>As máquinas da sala 310 estão com falhas em seus cabos de rede e algumas não estão sendo alimentadas.
                                </span
                               
                            </div>
                            
                        </div>
                        
                        <div id="botoes">
                             <div id="botoes-menores">
                            
                         <button onclick="cancelar()" id="btn-cancela">CANCELAR</button>
                        <button onclick="editar()" id="btn-edita">EDITAR</button>
                        
                        </div>
                        
                        <div id="botao-maior">
                             <button onclick="finalizar()" id="btn-finaliza">FINALIZAR E ENVIAR RELATÓRIO</button>
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