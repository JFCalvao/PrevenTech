let resposta = document.getElementById('resposta');

let clicar = document.getElementById('cadastro');
clicar.addEventListener('click', cadastrar);

function cadastrar() {
    const nome = document.querySelector("#maquina-cad").value;
    const nPatrimonio = document.querySelector("#n-patrimonio").value;
    const local = document.querySelector("#local").value;
    const estado = document.querySelector("#estados").value;

    if (nome.trim() === "" || nPatrimonio.trim() === "" || local.trim() === "" || estado === "") {
        resposta.innerHTML = "Por favor, preencha todos os campos.";
        resposta.style.color = 'red';
        return;
    }

    if (isNaN(nPatrimonio) || nPatrimonio.trim() === "") {
        resposta.innerHTML = "O número de patrimônio deve ser um valor numérico válido.";
        resposta.style.color = 'red';
        return;
    }

    verificarPatrimonioExistente(nPatrimonio, function(existe) {
        if (existe) {
            resposta.innerHTML = "Número de patrimônio já cadastrado. Não é possível cadastrar novamente.";
            resposta.style.color = 'red';
            return;
        }

        const url = "MainServlet";
        const ajax = new XMLHttpRequest();

        ajax.open("POST", url, true);
        ajax.onload = function() {
            if (ajax.status == 200) {
                var res = ajax.responseText;
                resposta.innerHTML = "Cadastro bem-sucedido.";
                resposta.style.color = 'black';
                res = new Response(ajax.responseText);
                if(res.getStatus() != "OK") {
                    window.location.href = "erro.jsp?erro=" + res.getError() + "&url=" + window.location.href; 
                }
            }
            else {
                resposta.innerHTML = "Erro ao enviar dados.";
            }
        };

        let json = new Request();
        json.setOperation("INSERT");
        json.setType("EQ");
        json.setData({
            "n_patrimonio": nPatrimonio,
            "nome": nome,
            "local": local,
            "estado": estado
        });

        ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        ajax.send(json.getRequest());
    });
}

function verificarPatrimonioExistente(nPatrimonio, callback) {
    const ajax = new XMLHttpRequest();
    const url = "MainServlet";

    ajax.open("POST", url, true);
    ajax.onload = function() {
        if (ajax.status == 200) {
            var res = new Response(ajax.responseText);

            if (res.getStatus() == "OK") {
                const data = res.getData();
                let existe = false;

                data.forEach(function(item) {
                    if (item.n_patrimonio == nPatrimonio) {
                        existe = true;
                    }
                });

                callback(existe);
            } else {
                callback(false);
            }
        } else {
            callback(false);
        }
    };

    const request = new Request();
    request.setOperation("GET");
    request.setType("EQ");
    request.setData({ "n_patrimonio": nPatrimonio });

    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    ajax.send(request.getRequest());
}