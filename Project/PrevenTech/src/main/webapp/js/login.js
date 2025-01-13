document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.querySelector('form');
    const resposta = document.createElement('div');
    loginForm.appendChild(resposta);

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const usuario = document.querySelector("#user").value;
        const senha = document.querySelector("#senha").value;

        const url = "MainServlet";
        const ajax = new XMLHttpRequest();

        ajax.open("POST", url, true);
        ajax.onload = function() {
            if (ajax.status === 200) {
                var res = ajax.responseText;
                resposta.innerHTML = "Login bem-sucedido: " + res;
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
        json.setOperation("LOGIN");
        json.setType("US");
        json.setData({
            "cpf": usuario,
            "senha": senha
        });

        ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        ajax.send(json.getRequest());
        ajax.onload = function() {
            if (ajax.status === 200) {
                var res = JSON.parse(ajax.responseText);
                if (res.error === "NOERROR") {
                    window.location.href = res.redirect;
                } else {
                    resposta.innerHTML = "Erro: " + res.error;
                    resposta.style.color = 'red';
                }
            } else {
                resposta.innerHTML = "Erro ao enviar dados.";
                resposta.style.color = 'red';
            }
        };
    });
});