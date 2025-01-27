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
                    <!-- As requisições serão carregadas aqui -->
                </div>
            </div>
        </main>

        <script src="https://code.jquery.com/jquery-3.6.4.js" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function() {
                function carregarRequisicoes() {
                    $.ajax({
                        url: 'tecnico',
                        type: 'GET',
                        dataType: 'json',
                        success: function(data) {
                            if (data.status === "OK") {
                                const requisicoes = data.content;
                                requisicoes.forEach(function(requisicao) {
                                    let requisicaoHTML = `
                                        <div class="requisicao">
                                            <div class="view">
                                                <div class="informacoes-basicas">
                                                    <h4>Requisição <span>${requisicao.requisicao_id}</span></h4>
                                                    <p>Requisitor: ${requisicao.requisitor_nome}</p>
                                                </div>
                                                <div class="setinha-expandir-retrair"></div>
                                            </div>
                                            <div class="informacoes-expandir escondido">
                                                <div id="status">
                                                    <span id="cor-status"></span>
                                                    <span id="txt-status">${requisicao.categoriaString}</span>
                                                </div>
                                                <div id="tecnico">
                                                    <span id="txt-tecnico">Técnico responsável: </span>
                                                    <span id="txt-nome-tecnico">${requisicao.responsavel_nome || 'Ninguém'}</span>
                                                </div>
                                                <div id="data-horario-envio">
                                                    <span id="txt-enviado">Enviado: </span>
                                                    <span id="txt-horario">${requisicao.data_inicio}</span>
                                                </div>
                                                <div id="categoria">
                                                    <span id="txt-categoria">Categoria: </span>
                                                    <span id="nome-categoria">${requisicao.categoria}</span>
                                                </div>
                                                <div id="descricao">
                                                    <h5>Descrição</h5>
                                                    <span>${requisicao.descricao}</span>
                                                </div>
                                            </div>
                                        </div>
                                    `;
                                    $(".requisicoes .body").append(requisicaoHTML);
                                });
                            } else {
                                alert("Erro ao carregar as requisições.");
                            }
                        },
                        error: function() {
                            alert("Erro de conexão com o servidor.");
                        }
                    });
                }
                carregarRequisicoes();

            });
        </script>
        <script src="js/expandir-retrair-div.js"></script>
    </body>
</html>
