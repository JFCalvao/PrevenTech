/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let historicos = [];

function getHistoricos() {
    let request = new Request();
    request.setOperation("GET");
    request.setType("HS");
    
    let ajax = new XMLHttpRequest();
    ajax.open("POST", "MainServlet", true);
    
    ajax.onload = function() {
        if (ajax.status === 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() === "OK") {
                historicos = response.getData();
                putHistoricos();
            } else {
                window.location.href = "erro.jsp?erro=" + response.getError() + "&url=" + window.location.href; 
            }
        } else {
            window.location.href = "erro.jsp?erro=Parece que você está com um erro de conexão. Por favor, tente novamente mais tarde." + "&url=" + initialPage;
        }
    };
    
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send(request.getRequest());
}

getHistoricos();

function addBtnsEvents() {
    const btns = document.querySelectorAll("historico-right > button");
    
    btns.forEach(btn => {
       btn.addEventListener("click", () => {
           // fazer donwload
       }); 
    });
}

function putHistoricos() {
    historicos.forEach(historico => {
       const el = `
            <article class="historico">
                <div class="historico-top">
                    <h3 class="historico-title">Requisição ${historico}</h3>
                    <line></line>
                </div>
                <div class="historico-bottom">
                    <section class="historico-left">
                        <box>
                            <label>Requisitor:</label>
                            <value>${}</value>
                        </box>
                        <box>
                            <label>Técnico responsável:</label>
                            <value>${}</value>
                        </box>
                        <box>
                            <label>Concluido:</label>
                            <value>${}</value>
                        </box>
                    </section>
                    <section class="historico-right">
                        <button>
                            <img src="imgs/download-file.png" />
                            <h3 class="text" >Relatório Final</h3>
                        </button>
                    </section>
                </div>
            </article>
        `; 
    });
}