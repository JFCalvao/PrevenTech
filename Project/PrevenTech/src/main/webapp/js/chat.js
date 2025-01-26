/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("message").onkeyup = function(e) {
    if(e.key === "Enter") {
        sendOutgoing();
    }
};

document.getElementById("sendButton").onclick = sendOutgoing;

function sendOutgoing() {
    var message = document.getElementById("message").value;
    var chatBox = document.getElementById("chatBox");

    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message", "outgoing");
    messageDiv.innerHTML = `<p><span>${username}</span> ${message}</p>`;

    chatBox.appendChild(messageDiv);
    document.getElementById("message").value = "";

    chatBox.scrollTop = chatBox.scrollHeight;
}

function sendIncoming() {
    var message = document.getElementById("message").value;
    var chatBox = document.getElementById("chatBox");

    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message", "incoming");
    messageDiv.innerHTML = `<p><span>Joao Fernando</span> ${message}</p>`;

    chatBox.appendChild(messageDiv);
    document.getElementById("message").value = "";

    chatBox.scrollTop = chatBox.scrollHeight;
}



/* * Aqui começa o código para a comunicação do chat * */
const url = "ws://" + document.location.host + "/PrevenTech/chat";
const socket = new WebSocket(url);

console.log("Chat: " + url);

socket.onopen = function() {
    console.log("Foi possível abrir o chat com sucesso!");
};