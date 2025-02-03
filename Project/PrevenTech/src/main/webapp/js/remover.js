window.onload = function () {
    const nPatrimonioInput = document.querySelector("#n-patrimonio");
    let resposta = document.querySelector('#resposta');
    let remover = document.getElementById('remove');
    let maquinas = [];

    function buscarDados() {
        const nPatrimonio = nPatrimonioInput.value.trim();

        if (!nPatrimonio) {
            resposta.innerHTML = "Por favor, insira um número de patrimônio.";
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
                    let response = JSON.parse(ajax.responseText);

                    if (response.status === "OK") {
                        maquinas = response.data;
                        let found = false;
                        console.log(maquinas)
                        resposta.innerHTML = " Remoção bem sucedida.";
                    } else {
                        resposta.innerHTML = "Erro ao processar a resposta: " + response.error;
                    }
                } catch (e) {
                    resposta.innerHTML = "Erro ao interpretar a resposta do servidor: " + e.message;
                }
            } else {
                resposta.innerHTML = "Erro ao enviar dados. Status: " + ajax.status;
            }
        };

        ajax.onerror = function () {
            resposta.innerHTML = "Erro ao conectar ao servidor.";
        };

        ajax.send(payload);
    }

    function removerMaquina() {
        const nPatrimonio = nPatrimonioInput.value.trim();

        if (!nPatrimonio) {
            resposta.innerHTML = "Por favor, insira um número de patrimônio válido.";
            return;
        }

        resposta.innerHTML = "Removendo máquina...";

        let maquinaEncontrada = false;
        for (let i = 0; i < maquinas.length; i++) {
            if (maquinas[i].n_patrimonio === nPatrimonio) {
                maquinas.splice(i, 1);
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

    if (remover) {
        remover.addEventListener('click', function() {
            buscarDados();
            removerMaquina();
        });
    }
};
