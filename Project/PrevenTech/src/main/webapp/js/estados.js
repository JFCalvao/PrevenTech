let maquinas = null;

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
pegarMAQ();

function renderizarMaquinas(maquinas) {
    let resposta = document.getElementById('mutavel');
    resposta.innerHTML = "";

    if (!maquinas || maquinas.length === 0) {
        resposta.innerHTML = "<p>Nenhuma máquina encontrada.</p>";
        return;
    }

    maquinas.forEach((maquina) => {
        let statusClass = "";
        let statusText = "";
        let corStatus = "";

        if (maquina.estado === "defeito") {
            statusClass = "defeito";
            statusText = "Defeito";
            corStatus = "red";
        } else if (maquina.estado === "manutencao") {
            statusClass = "manutencao";
            statusText = "Manutenção";
            corStatus = "blue";
        } else {
            statusClass = "funcionamento";
            statusText = "Funcionando";
            corStatus = "green";
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
                    <div id="status" class="${statusClass}">
                        <span id="cor-status" style="border: 7.5px solid ${corStatus};"></span>
                        <span id="txt-status">${statusText}</span>
                    </div>
                </div>
            </div>`;
    });

    addEvents();
}

let nome = document.querySelector("#pesquisar");
nome.addEventListener('keyup', buscarDados);

function buscarDados() {
    const nPatrimonio = document.querySelector("#pesquisar").value.trim();
    let resposta = document.getElementById('mutavel');

    if (!nPatrimonio) {
        renderizarMaquinas(maquinas); 
        return;
    }

    resposta.innerHTML = ""; 


    const maquinasFiltradas = maquinas.filter((maquina) => {
        return maquina.n_patrimonio.includes(nPatrimonio); 
    });

    if (maquinasFiltradas.length > 0) {
        renderizarMaquinas(maquinasFiltradas);
    } else {
        resposta.innerHTML = "<p>Nenhuma máquina encontrada para este número de patrimônio.</p>";
    }
}
let cancelar = document.getElementById('cancelar')
cancelar.addEventListener('click', 'cancelar');
