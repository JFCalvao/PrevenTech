<%-- 
    Document   : solicitacao.jsp
    Created on : 10 de jan de 2025, 10:41:22
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.time.ZonedDateTime, java.time.ZoneId, java.time.format.DateTimeFormatter, cefetmg.inf.preventech.util.Categorias" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/solicitacao.css">
        <script src="js/json.js" defer ></script>
        <%@include file="Security/security.jsp" %>
        <script defer >
            const categorias = <%= Categorias.getCategorias() %>;
            const user_cpf = "<%= userService.getCPF() %>";
        </script>
        <script src="js/solicitacao.js" defer ></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <main>
            <%
                ZoneId fusoHorarioBrasil = ZoneId.of("America/Sao_Paulo"); 
                ZonedDateTime dataHoraBrasil = ZonedDateTime.now(fusoHorarioBrasil); 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
                
                int dia = dataHoraBrasil.getDayOfMonth(); 
                int mes = dataHoraBrasil.getMonthValue(); 
                int ano = dataHoraBrasil.getYear(); 
                int horas = dataHoraBrasil.getHour(); 
                int minutos = dataHoraBrasil.getMinute(); 
                
                String data = dataHoraBrasil.format(formatter);
            %>
            <div id="solicitacao-area">
                <section id="form-header">
                    <line></line>
                    <h2 id="form-title">Formulário de Requisição</h2>
                    <line></line>
                </section>
                <section id="form-inputs">
                    <block>
                        <box class="auto-preenchido">
                            <label>Requisitor: </label>
                            <input type="text" value="<%= userService.getNome() %>" spellcheck="false" disabled />
                        </box>
                    </block>
                    <block>
                        <box class="auto-preenchido">
                            <label>Data: </label>
                            <input type="text" value="<%= data %>" spellcheck="false" disabled />
                        </box>
                    </block>
                    <block id="categorias-block">
                        <box>
                            <label>Categoria do defeito: </label>
                            <section id="categorias-container">
                                <input placeholder="Escolher" id="categorias-input" />
                                    <div id="categorias">
                                        
                                    </div>
                            </section>
                        </box>
                    </block>
                    <block>
                        <box>
                            <label>Adicionar equipamento: </label>
                            <section id="equipamentos-container">
                                <input id="maquinas-input" spellcheck="false" />
                                <div id="equipamentos" ></div>
                            </section>
                        </box>
                        <div id="equipamentos-adicionados" style="display: flex !important;">
                        </div>
                    </block>
                    <block>
                        <desc>
                            <label>Descrição</label>
                            <textarea id="descricao" title="Descricao" spellcheck="false" maxlength="600" ></textarea>
                            <div id="contador">0:600</div>
                        </desc>
                    </block>
                    <buttons>
                        <button id="cancel-btn" data-link="<%= userService.getInitialPage() %>">Cancelar</button>
                        <button id="send-btn">Enviar Solicitação</button>
                    </buttons>
                </section>
            </div>
        </main>
    </body>
</html>
