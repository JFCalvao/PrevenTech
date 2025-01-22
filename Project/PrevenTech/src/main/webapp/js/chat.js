/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.getElementById("sendButton").onclick = function() {
    var message = document.getElementById("message").value;
    var chatBox = document.getElementById("chatBox");

    var messageDiv = document.createElement("div");
    messageDiv.classList.add("message", "outgoing");
    messageDiv.innerHTML = `<p><span>User 2:</span> ${message}</p>`;

    chatBox.appendChild(messageDiv);
    document.getElementById("message").value = "";

    chatBox.scrollTop = chatBox.scrollHeight;
};
