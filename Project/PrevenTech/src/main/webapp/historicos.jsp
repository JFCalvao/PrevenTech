<%-- 
    Document   : historicos
    Created on : 16 de jan de 2025, 19:52:49
    Author     : jfcalvao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/historicos.css">
        <%@include file="Security/security.jsp" %>
        <script defer >
            const initialPage = "<%= userService.getInitialPage() %>";
        </script>
        <script src="js/json.js" defer ></script>
        <script src="js/historicos.js" defer ></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <main>
            <section id="historicos-area">
                <section id="historicos-header">
                    <h2 id="historicos-title">Históricos</h2>
                    <line></line>
                </section>
                <section id="historicos">
                    <article class="historico">
                        <div class="historico-top">
                            <h3 class="historico-title">Requisição 04/10</h3>
                            <line></line>
                        </div>
                        <div class="historico-bottom">
                            <section class="historico-left">
                                <box>
                                    <label>Requisitor:</label>
                                    <value>Cristiano Maffort</value>
                                </box>
                                <box>
                                    <label>Técnico responsável:</label>
                                    <value>Caio Silveira</value>
                                </box>
                                <box>
                                    <label>Concluido:</label>
                                    <value>17:02 04/10/2024</value>
                                </box>
                            </section>
                            <section class="historico-right">
                                <button>
                                    <img src="imgs/download-file.png" />
                                    <h3 class="text" >Relatório Final</h3>
                                </button>
                            </section>
                        </div>
                    </article>
                    
                    <article class="historico">
                        <div class="historico-top">
                            <h3 class="historico-title">Requisição 04/10</h3>
                            <line></line>
                        </div>
                        <div class="historico-bottom">
                            <section class="historico-left">
                                <box>
                                    <label>Requisitor:</label>
                                    <value>Cristiano Maffort</value>
                                </box>
                                <box>
                                    <label>Técnico responsável:</label>
                                    <value>Caio Silveira</value>
                                </box>
                                <box>
                                    <label>Concluido:</label>
                                    <value>17:02 04/10/2024</value>
                                </box>
                            </section>
                            <section class="historico-right">
                                <button>
                                    <img src="imgs/download-file.png" />
                                    <h3 class="text" >Relatório Final</h3>
                                </button>
                            </section>
                        </div>
                    </article>
                    
                    <article class="historico">
                        <div class="historico-top">
                            <h3 class="historico-title">Requisição 04/10</h3>
                            <line></line>
                        </div>
                        <div class="historico-bottom">
                            <section class="historico-left">
                                <box>
                                    <label>Requisitor:</label>
                                    <value>Cristiano Maffort</value>
                                </box>
                                <box>
                                    <label>Técnico responsável:</label>
                                    <value>Caio Silveira</value>
                                </box>
                                <box>
                                    <label>Concluido:</label>
                                    <value>17:02 04/10/2024</value>
                                </box>
                            </section>
                            <section class="historico-right">
                                <button>
                                    <img src="imgs/download-file.png" />
                                    <h3 class="text" >Relatório Final</h3>
                                </button>
                            </section>
                        </div>
                    </article>
                </section>
            </section>
        </main>
    </body>
</html>
