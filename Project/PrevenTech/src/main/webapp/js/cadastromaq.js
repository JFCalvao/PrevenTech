/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const nome = document.querySelector("#maquina-cad").value;
        const nPatrimonio = document.querySelector("#n-patrimonio").value;
        const local = document.querySelector("#local").value;
        const estado = document.querySelector("#estados").value;

        const data = {
            nome: nome,
            n_patrimonio: nPatrimonio,
            local: local,
            estado: estado
        };

        const url = insert.action;

        sendAjaxRequest(url, insert.method, data, function(err, res) {
            if (err) {
                alert(err);
            } else {
                response.innerHTML = res;
            }
        });
    });