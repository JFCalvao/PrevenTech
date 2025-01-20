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
            //window.location.href = "erro.jsp?erro=Parece que você está com um erro de conexão. Por favor, tente novamente mais tarde." + "&url=" + initialPage;
        }
    };
    
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send(request.getRequest());
}

getHistoricos();

function fixBase64String(base64) { 
    base64 = base64.replace(/[^A-Za-z0-9+/=]/g, '');  
    while (base64.length % 4 !== 0) { 
        base64 += '='; 
    } 
    return base64; 
}

function downloadFileFromJson(fileJson) { 
    
    let byteCharacters = atob(""); 
    console.log(byteCharacters);
    byteCharacters = fixBase64String(byteCharacters);
    
    const byteNumbers = new Array(byteCharacters.length); 
    for (let i = 0; i < byteCharacters.length; i++) { 
        byteNumbers[i] = byteCharacters.charCodeAt(i); 
    } 
    
    const byteArray = new Uint8Array(byteNumbers); 
    
    const blob = new Blob([byteArray], { type: 'application/pdf' }); // Cria um link de download temporário 
    
    const url = URL.createObjectURL(blob); 
    const a = document.createElement('a'); a.href = url; // Define o nome do arquivo a partir dos metadados no JSON 
    a.download = fileJson.nome || 'downloaded-file.pdf'; 
    document.body.appendChild(a); // Aciona o link programaticamente 
    a.click(); // Remove o link depois que o download é iniciado 
    document.body.removeChild(a); 
    URL.revokeObjectURL(url);
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
                        downloadFileFromJson(file);
                    } else {
                        window.location.href = "erro.jsp?erro=" + response.getError() + "&url=" + window.location.href; 
                    }
                } else {
                    //window.location.href = "erro.jsp?erro=Parece que você está com um erro de conexão. Por favor, tente novamente mais tarde." + "&url=" + initialPage;
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