/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("message").onkeyup = function(e) {
    if(e.key === "Enter") {
        send();
    }
};

document.getElementById("sendButton").onclick = send;

function send() {
    var message = document.getElementById("message").value;
    
    const json = {
        "type": "MSG",
        "content": {
            "id": id,
            "msg": message
        }
    };
    
    socket.send(JSON.stringify(json));
    sendOutgoing(message);
}

function sendOutgoing(message) {
    var chatBox = document.getElementById("chatBox");

    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message", "outgoing");
    messageDiv.innerHTML = `<p><span>${username}</span> ${message}</p>`;
    console.log("Enviou " + message + " de " + username);
    chatBox.appendChild(messageDiv);
    document.getElementById("message").value = "";

    chatBox.scrollTop = chatBox.scrollHeight;
}

function sendIncoming(nome, message) {
    var chatBox = document.getElementById("chatBox");

    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message", "incoming");
    messageDiv.innerHTML = `<p><span>${nome}</span> ${message}</p>`;

    chatBox.appendChild(messageDiv);
    document.getElementById("message").value = "";

    chatBox.scrollTop = chatBox.scrollHeight;
}

function putAllMsgs() {
    
}

/* * Aqui começa o código para a comunicação do chat * */
const url = "ws://" + document.location.host + "/PrevenTech/chat";
const socket = new WebSocket(url);

console.log("Chat: " + url);

socket.onopen = function() {
    console.log("Foi possível abrir o chat com sucesso!");
    try {
        const json = {
            "type": "INIT",
            "content": {
                "cpf": userCPF,
                "id": id
            }
        };

        socket.send(JSON.stringify(json));
    } catch(e) {
        console.log(e);
    }
};

socket.onmessage = function(event) {
    const json = JSON.parse(event.data);
    
    if(json.status === "OK") {
        switch(json.type) {
            case "INIT": {
                const oldMsgs = json.content;
                oldMsgs.forEach(msg => {
                    if(msg.nome === username) {
                        sendOutgoing(msg.msg);
                    } else {
                        sendIncoming(msg.nome, msg.msg);
                    }
                });
            }
            break;
            case "MSG":
                const newMsg = json.content;
                sendIncoming(newMsg.nome, newMsg.msg);
                console.log("Recebeu " + newMsg.msg + " de " + newMsg.nome);
            break;
            default: break;
        }
    } else {
        socket.close();
        window.location.href = "erro.jsp?erro=" + json.error + "&url=" + initialPage; 
    }
};

socket.onclose = function() {
    console.log("Foi possível fechar o chat com sucesso!");
};