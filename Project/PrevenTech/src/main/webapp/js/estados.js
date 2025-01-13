/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let maquinas = []
pegarMAQ()
function pegarMAQ(){
    let request = new Request();
    request.setOperation("GET");
    request.setType("EQ");
    request.setData({ "n_patrimonio": nPatrimonio });

    const ajax = new XMLHttpRequest();
    ajax.open("POST", MainServlet, true);
    ajax.onload = function() {
        if (ajax.status == 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() == "OK") {
                let data = response.getData();

                let found = false;
                data.forEach(function(item) {
                    if (item.n_patrimonio == nPatrimonio) {
                        document.querySelector("#maquina-cad").value = item.nome;
                        document.querySelector("#local").value = item.local;
                        document.querySelector("#estados").value = item.estado;
                        found = true;
                    }
                });

                if (!found) {
                    document.querySelector("#main").innerHTML = "Máquina não encontrada.";
                }
            } else {
                document.querySelector("#main").innerHTML = "Erro ao processar a resposta.";
            }
        } else {
            document.querySelector("#main").innerHTML = "Erro na requisição.";
        }
    };

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send(Request.getRequest());

}

let nome = document.querySelector("#maquina-cad")
nome.addEventListener('change', buscarDados);

function buscarDados() {
    const nPatrimonio = document.querySelector("#n-patrimonio").value;
    let resposta = document.getElementById('mutavel')

    if (!nPatrimonio) {
        document.querySelector("#main").innerHTML = "Por favor, insira um número de patrimônio.";
        return;
    }
    resposta.innerHTML = "";
    maquinas.forEach((maquina) => {
        if(maquina.n_patrimonio == nPatrimonio) {
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
    })


}  

