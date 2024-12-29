<%-- 
    Document   : header
    Created on : 17 de dez de 2024, 12:12:05
    Author     : jfcalvao
--%>

<header id="header">
    <h2 id="project-title">PrevenTech</h2>
    <img src="imgs/cefet_1x1.png" alt="" id="imgcefet">
</header>

<style>
@import url('https://fonts.googleapis.com/css2?family=Contrail+One&display=swap');

#header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: clamp(60px, 12vh, 150px);
    height: auto;
    background: #004aad;
}

#project-title {
    margin: 0;
    margin-left: 1%;
    display: flex;
    font-size: clamp(50px, 5vh, 100px);
    color: white;
    font-family: "Contrail One", serif;
}

#imgcefet {
    height: clamp(60px, 12vh, 150px);
    width: clamp(60px, 12vh, 150px);
    display: flex;
    margin: 0;
    margin-right: 1%;
}
</style>
