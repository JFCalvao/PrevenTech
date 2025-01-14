document.addEventListener('DOMContentLoaded', function() {
    // Elementos da página
    const editNomeButton = document.querySelector('#editar-nome-usuario .edit-btn');
    const nomeUsuarioElement = document.querySelector('#nome');
    const emailInput = document.querySelector('#email');
    const cpfInput = document.getElementById("cpf")
    const senhaInput = document.getElementById('senha');
    const salvarButton = document.getElementById("salvar");

    // Função para ativar a edição do nome de usuário
    editNomeButton.addEventListener('click', function() {
        const novoNome = prompt('Digite o novo nome de usuário:', nomeUsuarioElement.textContent);
        if (novoNome && novoNome.trim()) {
            nomeUsuarioElement.textContent = novoNome.trim();
        }
    });

    // Função para salvar as alterações
    salvarButton.addEventListener('click', function(event) {
        event.preventDefault();

        const nomeUsuario = nomeUsuarioElement.value;
        const email = emailInput.value;
        const cpf = cpfInput.value;
        const senha = senhaInput.value;

        const url = "MainServlet"; // URL do servlet ou endpoint que processará a atualização
        const ajax = new XMLHttpRequest();

        ajax.open("POST", url, true);
        ajax.onload = function() {
            if (ajax.status === 200) {
                const res = JSON.parse(ajax.responseText);
                if (res.status === "OK") {
                    // Redirecionar para a URL especificada na resposta
                    window.location.href = res.redirect;
                } else {
                    alert("Erro ao atualizar dados: " + res.error);
                }
            } else {
                alert("Erro ao enviar dados.");
            }
        };

        let json = new Request();
        json.setOperation("UPDATE");
        json.setType("US");
        json.setData({
            "nome": nomeUsuario,
            "cpf": cpf,
            "email": email,
            "senha": senha
        });

        console.log(json)
        ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        ajax.send(json.getRequest());
    });
});