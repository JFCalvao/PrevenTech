<%@page import="java.util.List"%>
<%@page import="cefetmg.inf.preventech.dao.EquipamentoDAO"%>
<%@page import="cefetmg.inf.preventech.dto.Equipamento"%>
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
                    <!-- Requisições já existentes -->
                    <div id="requisicoes-dinamicas">
                        <!-- Requisições serão carregadas aqui dinamicamente -->
                    </div>
                    <div id="equipamentos-dinamicos">
                        <h3>Equipamentos</h3>
                        <%
                            cefetmg.inf.preventech.dao.EquipamentoDAO equipamentoDAO = new cefetmg.inf.preventech.dao.EquipamentoDAO();
                            List<cefetmg.inf.preventech.dto.Equipamento> equipamentos = equipamentoDAO.getAll();
                            for (cefetmg.inf.preventech.dto.Equipamento equipamento : equipamentos) {
                        %>
                        <div class="equipamento">
                            <h4>Equipamento: <%= equipamento.getNome() %></h4>
                            <p>Patrimônio: <%= equipamento.getN_patrimonio() %></p>
                            <p>Local: <%= equipamento.getLocal() %></p>
                            <p>Estado: <%= equipamento.getEstado() %></p>
                            <button class="btn alterar-estado" 
                                    data-patrimonio="<%= equipamento.getN_patrimonio() %>" 
                                    data-estado="<%= equipamento.getEstado() %>">
                                Alterar Estado
                            </button>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </main>

        <!-- Modal para atualizar o estado -->
        <div id="modal-estado" class="modal escondido">
            <div class="modal-conteudo">
                <h3>Alterar Estado do Equipamento</h3>
                <form id="form-atualizar-estado" action="AtualizarEstadoServlet" method="post">
                    <input type="hidden" id="n_patrimonio" name="n_patrimonio">
                    <label for="novo-estado">Novo Estado:</label>
                    <select id="novo-estado" name="estado" required>
                        <option value="Funcionando">Funcionando</option>
                        <option value="Com Defeito">Com Defeito</option>
                        <option value="Em Manutenção">Em Manutenção</option>
                    </select>
                    <button type="submit" class="btn">Salvar</button>
                    <button type="button" class="btn fechar-modal">Cancelar</button>
                </form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.4.js" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function() {
                function carregarRequisicoes() {
                    $.ajax({
                        url: 'Tecnico',
                        type: 'GET',
                        dataType: 'json',
                        success: function(data) {
                            if (data.status === "OK") {
                                const requisicoes = data.content;
                                requisicoes.forEach(function(requisicao) {
                                    let requisicaoHTML = `
                                        <div class="requisicao" id="requisicao-${requisicao.requisicao_id}">
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
                                            <div class="botoes-requisicao">
                                                <button class="btn" onclick="window.location.href='finalizarSolicitacao.jsp?requisicao_id=${requisicao.requisicao_id}';">Finalizar e Enviar Relatório</button>
                                                <button class="btn" onclick="window.location.href='tarefas-tela.jsp';">Cancelar</button>
                                            </div>
                                        </div>
                                    `;
                                    $("#requisicoes-dinamicas").append(requisicaoHTML);
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

                $(".alterar-estado").on("click", function() {
                    const patrimonio = $(this).data("patrimonio");
                    const estadoAtual = $(this).data("estado");

                    $("#n_patrimonio").val(patrimonio);
                    $("#novo-estado").val(estadoAtual);
                    $("#modal-estado").addClass("mostrar");
                });

                $(".fechar-modal").on("click", function() {
                    $("#modal-estado").removeClass("mostrar");
                });
            });
        </script>
        <script src="js/expandir-retrair-div.js"></script>
    </body>
</html>
