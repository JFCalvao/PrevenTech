/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function exibirRequisicoesTecnico(data) {
    let bodyRequisicoesEl = document.querySelector('.requisicoes .body');
    
    if(data.content.length === 0) {
        bodyRequisicoesEl.classList.add('sem-requisicoes');
        bodyRequisicoesEl.innerHTML = "Você ainda não possui requisições";
        return;
    }
    
    if(data.error !== "NOERROR") {
        bodyRequisicoesEl.classList.add('erro');
        bodyRequisicoesEl.innerHTML = data.error;
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
        
        let maquinas = content.equipamentos.split('_').map(maquina => maquina.trim());
        
        let dataSplit = content.data.split('_');
        dataSplit[0] = dataSplit[0].replace(/-/g, '/');
        let dataEnvio = dataSplit[0];
        dataSplit[1] = dataSplit[1].replace(/-/g, ':');
        let dataStr = dataSplit.join(' - ');
        
        bodyRequisicoesEl.innerHTML += `
            <div class="requisicao">
                <div class='view'>
                    <div class="informacoes-basicas">
                        <h4>Requisição ${dataEnvio}</h4>
                        <p>Requisitor: <span id="nome-responsavel">${content.requisitorString}</span></p>
                    </div>
                    <div class="aceitar-e-setinha">
                        <div class="setinha-expandir-retrair"></div>
                    </div>
                </div>
                <div class="informacoes-expandir escondido">
                    <div id="linha-requisicao"></div>
                    <div id="status-e-aceitar">
                        <div id="status">
                            <span class="cor-status ${border}"></span>
                            <span class="txt-status ${color}">${content.status}</span>
                        </div>
                        <button id="botao-aceitar">Aceitar</button>
                    </div>
                    <div id="tecnico">
                        <span id="txt-tecnico">Técnico responsável: </span>
                        <span id="txt-nome-tecnico">${content.responsavelString}</span>
                    </div>
                    <div id="data-horario-envio">
                        <span id="txt-enviado">Enviado: </span>
                        <span id="txt-data">${dataStr}</span>
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
    aceitarSolicitacao();
}

function obterRequisicoesTecnico() {
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
            //console.log(data);
            exibirRequisicoesTecnico(data);
        }
    });
}

obterRequisicoesTecnico();

function aceitarSolicitacao() {
    const requisicoesBody = document.querySelector('.requisicoes .body');

    requisicoesBody.addEventListener('click', (event) => {
        if (event.target && event.target.id === 'botao-aceitar') 
            if (event.target.innerHTML !== 'Aceito') 
                event.target.innerHTML = 'Aceito';
        
        const requisicao = event.target.closest('.requisicao');  // Encontrar o contêiner da requisição

        const requisitor = requisicao.querySelector('#nome-responsavel').innerHTML;
        const status = requisicao.querySelector('.txt-status').innerHTML;
        const nomeTecnico = requisicao.querySelector('#txt-nome-tecnico').innerHTML;
        const dataHora = requisicao.querySelector('#txt-data').innerHTML;
        const categoria = requisicao.querySelector('#nome-categoria').innerHTML;
        
        const json = JSON.stringify({
                requisitor: requisitor,
                status: status,
                nomeTecnico: nomeTecnico,
                dataHora: dataHora,
                categoria: categoria
            });

        $.ajax({
            url: 'Tecnico',
            type: 'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: json,
            success: (resposta) => {
                console.log(resposta);
            },
            error: (erro) => {
                console.error('Erro ao aceitar solicitação:', erro);
            }
        });
    });
}