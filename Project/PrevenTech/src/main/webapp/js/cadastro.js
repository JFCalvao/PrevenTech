
const cargos = document.querySelectorAll('.cargo, .subcargo');
let selectedCargo = null;

cargos.forEach(cargo => {
    cargo.addEventListener('click', function() {
        if (selectedCargo) {
            selectedCargo.classList.remove('cargo-selecionado');
        }
        selectedCargo = cargo;
        selectedCargo.classList.add('cargo-selecionado');
    });
});
const tecnicoDiv = document.querySelector('.tecnico-div');
const subcargos = document.querySelectorAll('.subcargo');

tecnicoDiv.addEventListener('click', function() {
    subcargos.forEach(subcargo => {
        subcargo.classList.remove('escondido');
    });
});

cargos.forEach(cargo => {
    cargo.addEventListener('click', function() {
        if (!cargo.classList.contains('tecnico-div') && !cargo.classList.contains('subcargo')) {
            subcargos.forEach(subcargo => {
                subcargo.classList.add('escondido');
            });
        }
    });
});

const continuarBtn = document.getElementById('continuar-btn');
const cargosSecao = document.getElementById('cargos-secao');
const cadastroSecao = document.getElementById('cadastro-secao');

continuarBtn.addEventListener('click', function() {
    cargosSecao.classList.add('escondido');
    cadastroSecao.classList.remove('escondido');
});

const cadastrarBtn = document.getElementById('cadastrar-btn');
const formCadastro = document.getElementById('form-cadastro');
const resposta = document.getElementById('resposta');

cadastrarBtn.addEventListener('click', function(event) {
    event.preventDefault();

    const nome = document.querySelector("#nome").value;
    const usuario = document.querySelector("#usuario").value;
    const email = document.querySelector("#email").value;
    const senha = document.querySelector("#senha").value;
    const confirmarSenha = document.querySelector("#confirmar-senha").value;

    if (senha !== confirmarSenha) {
        resposta.innerHTML = "As senhas não coincidem.";
        resposta.style.color = 'red';
        return;
    }

    const url = "MainServlet";
    const ajax = new XMLHttpRequest();

    ajax.open("POST", url, true);
    ajax.onload = function() {
        if (ajax.status === 200) {
            var res = ajax.responseText;
            resposta.innerHTML = "Cadastro bem-sucedido: " + res;
            resposta.style.color = 'black';
            res = new Response(ajax.responseText);
            if (res.getStatus() !== "OK") {
                window.location.href = "erro.jsp?erro=" + res.getError() + "&url=" + window.location.href;
            }
        } else {
            resposta.innerHTML = "Erro ao enviar dados.";
            resposta.style.color = 'red';
        }
    };

    let json = new Request();
    json.setOperation("INSERT");
    json.setType("US");
    json.setData({
        "nome": nome,
        "cpf": usuario,
        "email": email,
        "senha": senha,
        'profissao': selectedCargo.getAttribute('data-profisso')
    });

    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    ajax.send(json.getRequest());
});