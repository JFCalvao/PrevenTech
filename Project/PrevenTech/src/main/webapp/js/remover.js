window.onload = function () {
    const nPatrimonioInput = document.querySelector("#n-patrimonio");
    let resposta = document.querySelector('#resposta');
    let remover = document.getElementById('remove');
    let maquinas = [];

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
                    console.table(maquinas);
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

    function removerMaquina() {
        const nPatrimonio = nPatrimonioInput.value.trim();

        if (!nPatrimonio) {
            resposta.innerHTML = "Por favor, insira um número de patrimônio válido.";
            return;
        }

        resposta.innerHTML = "Removendo máquina...";

        let maquinaEncontrada = false;
        let maquinaIndex = -1;

        for (let i = 0; i < maquinas.length; i++) {
            if (maquinas[i].n_patrimonio === nPatrimonio) {
                maquinaIndex = i;
                maquinaEncontrada = true;
                break;
            }
        }

        if (!maquinaEncontrada) {
            resposta.innerHTML = "Máquina não encontrada na lista.";
            return;
        }

        const payload = JSON.stringify({
            content: {
                n_patrimonio: nPatrimonio
            }
        });

        const ajax = new XMLHttpRequest();
        ajax.open("POST", 'RemoverMaq', true);
        ajax.setRequestHeader("Content-Type", "application/json");
        ajax.onload = function () {
            if (ajax.status === 200) {
                try {
                    const response = JSON.parse(ajax.responseText);

                    if (response.status === "OK") {
                        maquinas.splice(maquinaIndex, 1);
                        document.querySelector("#maquina-cad").value = "";
                        document.querySelector("#local").value = "";
                        document.querySelector("#estados").value = "";
                        resposta.innerHTML = "Máquina removida com sucesso.";
                    } else {
                        resposta.innerHTML = `Erro ao remover a máquina: ${response.error}`;
                    }
                } catch (e) {
                    resposta.innerHTML = `Erro ao processar a resposta: ${e.message}`;
                }
            } else {
                resposta.innerHTML = `Erro ao enviar dados. Status: ${ajax.status}`;
            }
        };

        ajax.onerror = function () {
            resposta.innerHTML = "Erro ao conectar ao servidor.";
        };

        ajax.send(payload);
    }

    function procurar () {
        const val = document.querySelector("#n-patrimonio").value;
        
        let nome = document.getElementById('maquina-cad');
        let local = document.getElementById('local');
        let estado = document.getElementById('estados');
        
        nome.value = "";
        local.value = "";
        estado.value = "";
        
        maquinas.forEach(maquina => {
            if(maquina.n_patrimonio === val) {
                
                
                nome.value = maquina.nome;
                local.value = maquina.local;
                estado.value = maquina.estado;
            }
        })
    }

    nPatrimonioInput.addEventListener('keyup', procurar);

    if (remover) {
        remover.addEventListener('click', function() {
            removerMaquina();
        });
    }
};