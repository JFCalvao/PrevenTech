/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function exibirMinhasRequisicoes(data) {
    let bodyRequisicoesEl = document.querySelector('.requisicoes .body');
    
    if(data.content.length === 0) {
        bodyRequisicoesEl.classList.add('sem-requisicoes');
        bodyRequisicoesEl.innerHTML = "Você ainda não possui requisições";
        return;
    }
    
    for(let i = 0; i < data.content.length; i++) {
        let content = data.content[i];
        
        let color, border;
        if(content.status === "Pendente") {
            color = "red";
            border = "bolinha-red";
        }
        else if(content.status === "Em andamento") {
            color = "blue";
            border = "bolinha-blue";
        }
        else {
            color = "green";
            border = "bolinha-green";
        }
        
        let maquinas = content.equipamentos.split(',').map(maquina => maquina.trim());
        
        bodyRequisicoesEl.innerHTML += `
            <div class="requisicao">
                <div class='view'>
                    <div class="informacoes-basicas">
                        <h4>Requisição <span id="numero-requisicao">${i+1}</span>/<span id="numero-requisicoes">${data.content.length}</span></h4>
                        <p>Requisitor: ${content.requisitorString}</p>
                    </div>
                    <div class="setinha-expandir-retrair"></div>
                </div>
                <div class="informacoes-expandir escondido">
                    <div id="linha-requisicao"></div>
                    <div id="status">
                        <span class="cor-status ${border}"></span>
                        <span class="txt-status ${color}">${content.status}</span>
                    </div>
                    <div id="tecnico">
                        <span id="txt-tecnico">Técnico responsável: </span>
                        <span id="txt-nome-tecnico">${content.responsavelString}</span>
                    </div>
                    <div id="data-horario-envio">
                        <span id="txt-enviado">Enviado: </span>
                        <span id="txt-data">${content.data === "" ? "data indefinida" : content.data}</span>
                    </div>
                    <div id="categoria">
                        <span id="txt-categoria">Categoria: </span>
                        <span id="nome-categoria">${content.categoriaString}</span>
                    </div>
                    <div id="equipamentos">
                        <span id="txt-equipamentos">Equipamentos: </span>

                        <span id="numero-das-maquinas">
                            ${maquinas.length !== 0 ? maquinas.map(maquina => `<span>${maquina}</span>`).join('') : ""}
                        </span>
                    </div>

                    <div id="descricao">
                        <h5>Descrição</h5>
                        <span>${content.descricao}</span>
                    </div>
                </div>
            </div>`;
    }
    
    addEvents();
}

function obterMinhasRequisicoes() {
    let request = new Request();
    request.setOperation('GET');
    request.setType('RQ');
    request.setData({});
    
    $.ajax ({
        url: 'MainServlet?' + request.getRequest(),
        dataType: 'json',
        beforeSend:() => {
            $('.body').html('Carregando...');
            $('.body').addClass('carregando');
        },
        success:(data) => {
            $('.body').html('');
            $('.body').removeClass('carregando');
            exibirMinhasRequisicoes(data);
        }
    });
}

obterMinhasRequisicoes();