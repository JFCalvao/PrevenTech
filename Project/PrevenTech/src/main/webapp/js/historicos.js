/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let historicos = [];

function getHistoricos() {
    let request = new Request();
    request.setOperation("GET");
    
    let ajax = new XMLHttpRequest();
    ajax.open("POST", "HistoricoServlet", true);
    
    ajax.onload = function() {
        if (ajax.status === 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() === "OK") {
                historicos = response.getData();
                putHistoricos();
                addBtnsEvents();
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

function showPDF(fileJson) { 
    var url = fileJson.file;
    var iframe = `<iframe width="100%" height="100%" src="${url}"></iframe>"`;
    var x = window.open();
    x.document.open();
    x.document.write(iframe);
    x.document.close();
    document.location.href = url;
}

function addBtnsEvents() {
    const btns = document.querySelectorAll(".historico-right > button");
    
    btns.forEach(btn => {
       btn.addEventListener("click", () => {
            const id = btn.getAttribute("data-id");
            
            let request = new Request();
            request.setOperation("GETFILE");
            request.setData({
               "id": id,
               "file": ""
            });

            let ajax = new XMLHttpRequest();
            ajax.open("POST", "HistoricoServlet", true);

            ajax.onload = function() {
                if (ajax.status === 200) {
                    let response = new Response(ajax.responseText);

                    if (response.getStatus() === "OK") {
                        let file = response.getData();
                        showPDF(file);
                    } else {
                        window.location.href = "erro.jsp?erro=" + response.getError() + "&url=" + window.location.href; 
                    }
                } else {
                    window.location.href = "erro.jsp?erro=Parece que você está com um erro de conexão. Por favor, tente novamente mais tarde." + "&url=" + initialPage;
                }
            };

            ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ajax.send(request.getRequest());
       }); 
    });
}

function putHistoricos() {
    const div = document.getElementById("historicos");
    div.innerHTML = "";
    historicos.forEach(historico => {
        const datas = historico["data"].split("_");
        const tempo = datas[0].split("-");
        const horario = datas[1].split("-");
        
        const dia = tempo[0];
        const mes = tempo[1];
        const ano = tempo[2];
        
        const horas = horario[0];
        const minutos = horario[1];
        
        const el = `
            <article class="historico">
                <div class="historico-top">
                    <h3 class="historico-title">Requisição ${dia + "/" + mes}</h3>
                    <line></line>
                </div>
                <div class="historico-bottom">
                    <section class="historico-left">
                        <box>
                            <label>Requisitor:</label>
                            <value>${historico["requisitor"]}</value>
                        </box>
                        <box>
                            <label>Técnico responsável:</label>
                            <value>${historico["responsavel"]}</value>
                        </box>
                        <box>
                            <label>Concluido:</label>
                            <value>${horas + ":" + minutos + " " + dia + "/" + mes + "/" + ano}</value>
                        </box>
                    </section>
                    <section class="historico-right">
                        <button data-id="${historico["id"]}">
                            <img src="imgs/download-file.png" />
                            <h3 class="text" >Relatório Final</h3>
                        </button>
                    </section>
                </div>
            </article>
        `; 
        
        div.innerHTML += el;
    });
}