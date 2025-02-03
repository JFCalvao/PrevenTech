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
                                    <div id="descricao">
                                        <h5>Descrição</h5>
                                        <span>${requisicao.descricao}</span>
                                    </div>
                                    <div class="equipamento">
                                        <h4>Equipamentos Relacionados</h4>
                                        ${requisicao.equipamentos.forEach(function(equipamento) {
                                            return `
                                                <div class="equipamento-item">
                                                    <p>Nome: ${equipamento.nome}</p>
                                                    <p>Patrimônio: ${equipamento.n_patrimonio}</p>
                                                    <p>Local: ${equipamento.local}</p>
                                                    <p>Estado: ${equipamento.estado}</p>
                                                    <button class="btn alterar-estado" 
                                                            data-patrimonio="${equipamento.n_patrimonio}" 
                                                            data-estado="${equipamento.estado}">
                                                        Alterar Estado
                                                    </button>
                                                </div>
                                            `;
                                        })}
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

    $(document).on('click', '.requisicao .view', function() {
        $(this).closest('.requisicao').toggleClass('expanded');
        $(this).closest('.requisicao').find('.informacoes-expandir').slideToggle();
        $(this).find('.setinha-expandir-retrair').toggleClass('rotacionar');
    });

    carregarRequisicoes();
});
