$(document).ready(function() {
    function carregarRequisicoes() {
        $.ajax({
            url: 'Tecnico',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                console.log(data);
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
                                                    <p>Estado: ${equipamento.estado}</p>
                                                    <select class="estado-select" data-patrimonio="${equipamento.n_patrimonio}">
                                                        <option value="funcionamento" ${equipamento.estado === 'funcionamento' ? 'selected' : ''}>FUNCIONANDO</option>
                                                        <option value="defeito" ${equipamento.estado === 'defeito' ? 'selected' : ''}>COM DEFEITO</option>
                                                        <option value="manutencao" ${equipamento.estado === 'manutencao' ? 'selected' : ''}>EM MANUTENÇÃO</option>
                                                    </select>
                                                    <button class="btn alterar-estado" 
                                                            data-patrimonio="${equipamento.n_patrimonio}">
                                                        Alterar Estado
                                                    </button>
                                                </div>
                                            `;
                                        }).join('')}
                                    </div>
                                </div>
                            </div>
                        `;
                        $("#requisicoes-dinamicas").append(requisicaoHTML);
                    });

            
                    $('.view').click(function() {
                        const requisicaoId = $(this).data('requisicao-id');
                        $(`#requisicao-${requisicaoId} .informacoes-expandir`).toggleClass('escondido');
                    });

        
                    $('.btn.alterar-estado').click(function(e) {
                        e.preventDefault();

                        const patrimonio = $(this).data('patrimonio');
                        const novoEstado = $(`select[data-patrimonio="${patrimonio}"]`).val();

                        $.ajax({
                            url: 'AtualizarEstadoServlet',
                            type: 'POST',
                            data: {
                                n_patrimonio: patrimonio,
                                estado: novoEstado
                            },
                            success: function(response) {
                                alert("Estado atualizado com sucesso!");
                                location.reload(); // Recarregar a página para refletir a mudança
                            },
                            error: function() {
                                alert("Erro ao atualizar o estado.");
                            }
                        });
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
