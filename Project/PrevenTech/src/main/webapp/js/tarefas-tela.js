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
                                <div class="view" data-requisicao-id="${requisicao.requisicao_id}">
                                    <div class="informacoes-basicas">
                                        <h4>Requisição <span>${requisicao.requisicao_id}</span></h4>
                                        <p>Requisitor: ${requisicao.requisitor_nome}</p>
                                    </div>
                                    <div class="setinha-expandir-retrair"></div>
                                </div>
                                <div class="informacoes-expandir escondido">
                                    <div class="chat">
                                        <button class="botao-chat" onclick="goChat(${requisicao.requisicao_id})"><img class="img-chat" src="imgs/chat.jpg"></button>
                                    </div>
                                    <div id="status">
                                        <span id="cor-status"></span>
                                        <span id="txt-status">${requisicao.categoriaString}</span>
                                    </div>
                                    <div id="descricao">
                                        <h5>Descrição</h5>
                                        <span>${requisicao.descricao}</span>
                                    </div>
                                    <div class="equipamento">
                                        <h4>Equipamentos Relacionados</h4>
                                        ${requisicao.equipamentos.map(function(equipamento) {
                                            return `
                                                <div class="equipamento-item">
                                                    <p>Nome: ${equipamento.nome}</p>
                                                    <p>Patrimônio: ${equipamento.n_patrimonio}</p>
                                                    <p>Local: ${equipamento.local}</p>
                                                    <p>Estado Atual: <span class="estado-texto">${equipamento.estado}</span></p>

                                                    <select class="estado-select" data-patrimonio="${equipamento.n_patrimonio}">
                                                        <option value="funcionamento" ${equipamento.estado === "funcionamento" ? "selected" : ""}>FUNCIONANDO</option>
                                                        <option value="defeito" ${equipamento.estado === "defeito" ? "selected" : ""}>COM DEFEITO</option>
                                                        <option value="manutencao" ${equipamento.estado === "manutencao" ? "selected" : ""}>EM MANUTENÇÃO</option>
                                                    </select>

                                                    <button class="btn alterar-estado" data-patrimonio="${equipamento.n_patrimonio}">
                                                        Alterar Estado
                                                    </button>
                                                </div>
                                            `;
                                        }).join('')}
                                    </div>
                                    <div class="botoes-acao">
                                        <button class="btn finalizar-requisicao" data-id="${requisicao.requisicao_id}">
                                            Finalizar e Enviar Relatório
                                        </button>
                                        <button class="btn cancelar-requisicao" data-id="${requisicao.requisicao_id}">
                                            Cancelar
                                        </button>
                                    </div>
                                </div>
                            </div>
                        `;
                        $("#requisicoes-dinamicas").append(requisicaoHTML);
                    });
                    
                    $('.btn.alterar-estado').click(function() {
                        const patrimonio = $(this).data('patrimonio');
                        const select = $(`.estado-select[data-patrimonio="${patrimonio}"]`);
                        const novoEstado = select.val();

                        $.ajax({
                            url: 'AtualizarEstadoServlet',
                            type: 'POST',
                            data: {
                                n_patrimonio: patrimonio,
                                estado: novoEstado
                            },
                            success: function(response) {
                                alert("Estado atualizado com sucesso!");
                                location.reload();
                            },
                            error: function() {
                                alert("Erro ao atualizar o estado.");
                            }
                        });
                    });

                    $('.btn.finalizar-requisicao').click(function() {
                        const requisicaoId = $(this).data('id');
                        window.location.href = `finalizarSolicitacao.jsp?requisicao_id=${requisicaoId}`;
                    });

                    $('.btn.cancelar-requisicao').click(function() {
                        window.location.href = 'tarefas-tela.jsp';
                    });

                    $('.view').click(function() {
                        const requisicaoId = $(this).data('requisicao-id');
                        const informacoesExpandir = $(`#requisicao-${requisicaoId} .informacoes-expandir`);
                        const setinha = $(`#requisicao-${requisicaoId} .setinha-expandir-retrair`);
                        informacoesExpandir.toggleClass('escondido');
                        setinha.toggleClass('rotacionar');
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

    $("#btn-voltar").click(function() {
        window.location.href = "tecnico.jsp";
    });
});

function goChat(id) {
    window.location.href = "chat.jsp?id=" + id;
}
