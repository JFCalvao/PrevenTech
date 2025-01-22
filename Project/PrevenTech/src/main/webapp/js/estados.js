let maquinas = null;
pegarMAQ();

function pegarMAQ() {
    let nPatrimonio = document.querySelector('#n-patrimonio').value;
    let request = new Request();
    request.setOperation("GET");
    request.setType("EQ");
    request.setData({ "n_patrimonio": nPatrimonio });

    const ajax = new XMLHttpRequest();
    ajax.open("POST", 'MainServlet', true);
    ajax.onload = function() {
        if (ajax.status == 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() == "OK") {
                maquinas = response.getData();
                renderizarMaquinas(maquinas);
            } else {
                document.querySelector(".requisicao").innerHTML = "Erro ao processar a resposta.";
                console.log(response.response);
            }
        } else {
            document.querySelector(".requisicao").innerHTML = "Erro na requisição.";
        }
    };

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send(request.getRequest());
}

function renderizarMaquinas(maquinas) {
    let resposta = document.getElementById('mutavel');
    resposta.innerHTML = "";

    if (!maquinas || maquinas.length === 0) {
        resposta.innerHTML = "<p>Nenhuma máquina encontrada.</p>";
        return;
    }

    maquinas.forEach((maquina) => {
        let color, border;
        if(maquina.status === "defeito") {
            color = "red";
            border = "bolinha-red";
        }
        else if(maquina.status === "manutencao") {
            color = "blue";
            border = "bolinha-blue";
        }
        else {
            color = "green";
            border = "bolinha-green";
        }

        resposta.innerHTML += `
            <div class='requisicao'> 
                <div class='view'>
                    <div class="informacoes-basicas">
                        <h4 id="maquina-cad">${maquina.nome}</h4>
                        <h4 id="n-patrimonio">${maquina.n_patrimonio}</h4>
                        <p id="local">${maquina.local}</p>
                    </div>

                    <div class="setinha-expandir-retrair"></div>
                </div>
                <div class="informacoes-expandir escondido">
                    <div id="linha-requisicao"></div>
                    <div id="status" class="${border}">
                        <span id="cor-status"></span>
                        <span id="txt-status">${maquina.estado}</span>
                    </div>
                </div>
            </div>`;
    });

    addEvents();
}

let nome = document.querySelector("#pesquisar");
nome.addEventListener('keyup', buscarDados);

function buscarDados() {
    const nPatrimonio = document.querySelector("#pesquisar").value;
    let resposta = document.getElementById('mutavel');

    if (!nPatrimonio) {
        renderizarMaquinas(maquinas);  
        return;
    }

    resposta.innerHTML = "";
    maquinas.forEach((maquina) => {
        if (maquina.n_patrimonio == nPatrimonio) {
            resposta.innerHTML += `<div class='requisicao'> 
                                    <div class='view'>
                                        <div class="informacoes-basicas">
                                            <h4 id="maquina-cad">${maquina.nome}</h4>
                                            <h4 id="n-patrimonio">${maquina.n_patrimonio}</h4>
                                            <p id="local">${maquina.local}</p>
                                        </div>
                                        <div class="setinha-expandir-retrair"></div>
                                    </div>
                                    <div class="informacoes-expandir escondido">
                                        <div id="linha-requisicao"></div>
                                        <div id="status">
                                            <span id="cor-status"></span>
                                            <span id="txt-status">${maquina.estado}</span>
                                        </div>
                                    </div>
                                </div>`;
        }
    });

    addEvents();
}
