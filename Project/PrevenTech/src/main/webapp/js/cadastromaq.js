
let resposta = document.getElementById('resposta');

let clicar = document.getElementById('cadastro');
clicar.addEventListener('click', casdastrar);

function casdastrar() {
    
    const nome = document.querySelector("#maquina-cad").value;
    const nPatrimonio = document.querySelector("#n-patrimonio").value;
    const local = document.querySelector("#local").value;
    const estado = document.querySelector("#estados").value;

    const url = "MainServlet";
    const ajax = new XMLHttpRequest();

    console.log("clicou");
    console.log("url: " + url);
    ajax.open("POST", url, true);
    ajax.onload = function() {
        if (ajax.status == 200) {
            var res = ajax.responseText;
            resposta.innerHTML = "Envio bem-sucedido: " + res;
            console.log(ajax.status)
            resposta.style.color = 'black';
            window.alert('lala')
        } else {
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


}
