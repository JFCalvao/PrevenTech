/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


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
                                                    <p>Nome: ${requisicao.equipamentos[0].nome}</p>
                                                    <p>Patrimônio: ${requisicao.equipamentos[0].n_patrimonio}</p>
                                                    <p>Local: ${requisicao.equipamentos[0].local}</p>
                                                    <p>Estado: ${requisicao.equipamentos[0].estado}</p>
                                                    <button class="btn alterar-estado" 
                                                            data-patrimonio="${requisicao.equipamentos[0].n_patrimonio}" 
                                                            data-estado="${requisicao.equipamentos[0].estado}">
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