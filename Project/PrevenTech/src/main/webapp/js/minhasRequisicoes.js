/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function obterUserNome() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: 'obter-usuario.jsp',
            type: 'post',
            success: (resposta) => {
                resolve(resposta.trim());  
            },
            error: (err) => {
                reject('Erro ao obter nome');  
            }
        });
    });
}

function exibirMinhasRequisicoes(data) {
    let bodyRequisicoesEl = document.querySelector('.minhas-requisicoes .body');
    
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
    
    let userNome = obterUserNome();
    
    obterUserNome().then(userNome => {
        for (let i = 0; i < data.content.length; i++) { 
            let content = data.content[i];
            
            console.log(userNome); // Verifique o valor do userNome
            console.log(content.requisitorString); // Verifique o valor de requisitorString
            
            if (userNome !== content.requisitorString)
                continue;
            
            let color, border;
            if (content.status === "Pendente") {
                color = "red";
                border = "bolinha-red";
            }
            else if (content.status === "Em andamento") {
                color = "blue";
                border = "bolinha-blue";
            }
            else {
                color = "green";
                border = "bolinha-green";
            }
            
            let colorStatusMaquina, borderStatusMaquina;
            
            let maquinas = content.arrEquipamentos;
            
            let dataSplit = content.data.split('_');
            dataSplit[0] = dataSplit[0].replace(/-/g, '/');
            let dataEnvio = dataSplit[0];
            dataSplit[1] = dataSplit[1].replace(/-/g, ':');
            let dataStr = dataSplit.join(' - ');
            
            let equipamentosHTML = '';
            if(maquinas.length !== 0) {
                maquinas.forEach(maquina => {
                    let colorStatusMaquina, borderStatusMaquina;
                    
                    if (maquina.estado === "defeito") {
                        colorStatusMaquina = "red";
                        borderStatusMaquina = "bolinha-red";
                    }
                    else if (maquina.estado === "manutencao") {
                        colorStatusMaquina = "blue";
                        borderStatusMaquina = "bolinha-blue";
                    }
                    else {
                        colorStatusMaquina = "green";
                        borderStatusMaquina = "bolinha-green";
                    }
            
                    equipamentosHTML += `
                        <div class='maquina'>
                            <div id="informacoes">
                                <span id="numero-nome">
                                    <span id="numero">${maquina.n_patrimonio}</span>
                                    <span id="nome">${maquina.nome}</span>
                                </span>
                                <span id="local">Local: ${maquina.local}</span>
                                <span id="span-situacao">Situação:
                                    <span id="situacao">
                                       <span class="cor-situacao ${borderStatusMaquina}"></span>
                                       <span class="txt-situacao ${colorStatusMaquina}">${maquina.estado}</span>
                                    </span>
                                </span>
                            </div>
                                
                            <div class="linha-maquina"></div>
                        </div>
                    `;
                });
            }
            
            bodyRequisicoesEl.innerHTML += `
                <div class="requisicao">
                    <div class='view'>
                        <div class="informacoes-basicas">
                            <h4>Requisição ${dataEnvio}</h4>
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
                            <span id="txt-data">${dataStr}</span>
                        </div>
                        <div id="categoria">
                            <span id="txt-categoria">Categoria: </span>
                            <span id="nome-categoria">${content.categoriaString}</span>
                        </div>
                        <div id="equipamentos">
                            <span id="txt-equipamentos">Equipamentos: </span>
                            
                            ${equipamentosHTML}
                        </div>
                        <div id="descricao">
                            <h5>Descrição</h5>
                            <span>${content.descricao}</span>
                        </div>
                    </div>
                </div>`;
        }
        
        addEvents();
    }).catch(error => {
        console.log(error);
    });
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