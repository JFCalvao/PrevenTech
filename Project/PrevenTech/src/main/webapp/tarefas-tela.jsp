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
                <div id="requisicoes-dinamicas">
                    <!-- Requisições serão carregadas aqui dinamicamente -->
                </div>
            </div>
        </main>

        <script src="https://code.jquery.com/jquery-3.6.4.js" crossorigin="anonymous"></script>
        <script>
            $(document).ready(function() {
                function carregarRequisicoes() {
                    $.ajax({
                        url: 'Tecnico',
                        type: 'GET',
                        dataType: 'json',
                        success: function(data) {
                            console.log(data); // Verifique se os dados estão sendo retornados corretamente
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
                                                <div id="descricao">
                                                    <h5>Descrição</h5>
                                                    <span>${requisicao.descricao}</span>
                                                </div>
                                                <div class="equipamento">
                                                    <h4>Equipamento Relacionado</h4>
                                                    <p>Nome: ${requisicao.equipamento.nome}</p>
                                                    <p>Patrimônio: ${requisicao.equipamento.n_patrimonio}</p>
                                                    <p>Local: ${requisicao.equipamento.local}</p>
                                                    <p>Estado: ${requisicao.equipamento.estado}</p>
                                                    <button class="btn alterar-estado" 
                                                            data-patrimonio="${requisicao.equipamento.n_patrimonio}" 
                                                            data-estado="${requisicao.equipamento.estado}">
                                                        Alterar Estado
                                                    </button>
                                                </div>
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
            });
        </script>
    </body>
</html>
