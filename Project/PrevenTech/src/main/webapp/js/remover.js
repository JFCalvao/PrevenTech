window.onload = function () {
    const nPatrimonioInput = document.querySelector("#n-patrimonio");
    let resposta = document.querySelector('#resposta');
    let remover = document.getElementById('remove');

    function buscarDados() {
        const nPatrimonio = nPatrimonioInput.value;

        if (!nPatrimonio) {
            document.querySelector("#resposta").innerHTML = "Por favor, insira um número de patrimônio.";
            return;
        }

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
                        document.querySelector("#resposta").innerHTML = "Máquina não encontrada.";
                    }
                } else {
                    document.querySelector("#resposta").innerHTML = "Erro ao processar a resposta: " + response.getError();
                }
            } else {
                document.querySelector("#resposta").innerHTML = "Erro ao enviar dados. Status: " + ajax.status;
            }
        };

        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ajax.send(request.getRequest());
    }

    function removerMaquina() {
        const nPatrimonio = nPatrimonioInput.value;

        if (!nPatrimonio) {
            resposta.innerHTML = "Por favor, insira um número de patrimônio válido.";
            return;
        }
        resposta.innerHTML = " ";

        let request = new Request();
        request.setOperation("DELETE");
        request.setType("EQ");
        request.setData({ "n_patrimonio": nPatrimonio });

        const ajax = new XMLHttpRequest();
        ajax.open("POST", 'MainServlet ', true);  
        ajax.onload = function() {
            if (ajax.status == 200) {
                let response = new Response(ajax.responseText);

                if (response.getStatus() == "OK") {
                    document.querySelector("#maquina-cad").value = "";
                    document.querySelector("#local").value = "";
                    document.querySelector("#estados").value = "";
                    document.querySelector("#resposta").innerHTML = "Máquina removida com sucesso.";
                } else {
                    document.querySelector("#resposta").innerHTML = "Erro ao remover a máquina: " + response.getError();
                }
            } else {
                document.querySelector("#resposta").innerHTML = "Erro ao enviar dados. Status: " + ajax.status;
            }
        };

        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        ajax.send(request.getRequest());
    }

    if (remover) {
        remover.addEventListener('click', removerMaquina);
    }
};