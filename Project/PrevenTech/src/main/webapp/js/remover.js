/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


let nome = document.querySelector("#maquina-cad");
nome.addEventListener('change', buscarDados);

let remover = 

function buscarDados() {
    const nPatrimonio = document.querySelector("#n-patrimonio").value;
    const local = document.querySelector("#local").value;
    const estado = document.querySelector("#estados").value;

    let request = new Request();
    request.setOperation("GET");
    request.setType("EQ");
    request.setData({});

    let url = request.getRequest();

    const ajax = new XMLHttpRequest();
    ajax.open("POST", MainServlet, true);
    ajax.onload = function() {
        if (ajax.status == 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() == "OK") {
                let data = response.getData();
                
                data.forEach(function(item) {
                    if (item.n_patrimonio == nPatrimonio) {
                        document.querySelector("#maquina-cad").value = item.nome;
                        document.querySelector("#local").value = item.local;
                        document.querySelector("#estados").value = item.estado;
                    }
                });
            } else {
                document.querySelector("#resposta").innerHTML = "Erro ao processar a resposta.";
            }
        } else {
            document.querySelector("#resposta").innerHTML = "Erro ao enviar dados.";
        }
        
    };

    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send(Request.getRequest());
}