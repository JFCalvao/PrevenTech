
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