/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/********************** Para o textarea **********************/
const textarea = document.querySelector("textarea");
const textarea_defaultHeight = parseInt(getComputedStyle(textarea)
                               .getPropertyValue('height'));
                       
function resizeTextarea() {
    textarea.style.height = `${textarea_defaultHeight}px`;
    let newHeight = textarea.scrollHeight - textarea_defaultHeight;
    let mul = parseInt(newHeight/textarea_defaultHeight) + (newHeight%textarea_defaultHeight === 0 ? 0 : 1);
    textarea.style.height = `${textarea_defaultHeight*mul}px`;
}

function changeCharCounter() {
    const maxLength = parseInt(textarea.maxLength);
    const contador = document.querySelector("#contador");
    if(textarea.value.length === maxLength) 
        contador.style.color = "#28a745";
    else 
        contador.style.color = "#333";
    contador.innerHTML = textarea.value.length + ":" + maxLength;
}

textarea.addEventListener("input", () => {
    resizeTextarea();
    changeCharCounter();
});

/********************** Para as categorias **********************/
const block = document.querySelector("#categorias-block");
const categorias_block_defaultHeight = parseInt(getComputedStyle(block)
                               .getPropertyValue('height'));

function resizeCategoriesSpace() {
    const categorias = document.querySelector("#categorias");
    const options = document.querySelectorAll("#categorias > *");
    
    const blockPaddingBottom = -7.5;
    const catBorderBottom = 2;
    const optionHeight = 30 + 2 + 4; // height + padding + border
    
    const mul = parseInt(options.length);
    const result = optionHeight*mul + catBorderBottom + blockPaddingBottom;
    
    if(mul === 0)
        block.style.height = `${categorias_block_defaultHeight}px`;
    else
        block.style.height = `${categorias_block_defaultHeight + result}px`;
}

let canDestroyCategoriesList = true;

function addOptions() {
    const input = document.querySelector("#categorias-input").value;
    const options = document.querySelector("#categorias");
    options.innerHTML = "";
    
    const max_options_shown = 4;
    const firstFourElements = categorias.slice(0, max_options_shown);
    let addButton = false;

    if(input === "") {
        firstFourElements.forEach((categoria) => {
            options.innerHTML += `
                <option value="${categoria}">${categoria}</option>
            `;
        });
        addButton = true;
    } else {
        let couldAdd = 0, added = 0;
        categorias.forEach((categoria) => {
            if(categoria.toUpperCase().search(input.toUpperCase()) !== -1) {
                if(added < max_options_shown) {
                    options.innerHTML += `
                        <option value="${categoria}">${categoria}</option>
                    `;
                    added++;
                }
                couldAdd++;
            }
        });
        if(couldAdd > max_options_shown) addButton = true;
    }
    
    if(addButton) {
        options.innerHTML += `
            <section id="more-option" value="imagem"></section>
        `;
    }
}

function addAllOptions() {
    const input = document.querySelector("#categorias-input").value;
    const options = document.querySelector("#categorias");
    options.innerHTML = "";
    
    if(input === "") {
        categorias.forEach((categoria) => {
            options.innerHTML += `
                <option value="${categoria}">${categoria}</option>
            `;
        });
    } else {
        categorias.forEach((categoria) => {
            if(categoria.toUpperCase().search(input.toUpperCase()) !== -1) {
                options.innerHTML += `
                    <option value="${categoria}">${categoria}</option>
                `;
            }
        });
    }
}

function removeOptions() {
    const options = document.querySelector("#categorias");
    options.innerHTML = "";
}

function optionEvent(e) {
    const input = document.querySelector("#categorias-input");
    input.value = e.target.value;
    canDestroyCategoriesList = true;
    destroyCategoriesList();
}

function moreBtnEvent() {
    const input = document.querySelector("#categorias-input");
    addAllOptions();
    resizeCategoriesSpace();
    addOptionsEvent();
    input.focus();
}

function addOptionsEvent() {
    const options = document.querySelectorAll("#categorias > option");
    options.forEach((option) => {
       option.addEventListener("click", optionEvent); 
    });
}

function addMoreBtnEvent() {
    const more_btn = document.querySelector("#more-option");
    if(!more_btn) return;
    more_btn.addEventListener("click", moreBtnEvent);
}

function search() {
    addOptions();
    addOptionsEvent();
    addMoreBtnEvent();
    resizeCategoriesSpace();
}

function showOptions() {
    const options = document.querySelector("#categorias");
    options.style.display = "block";
}

function hideOptions() {
    const options = document.querySelector("#categorias");
    options.style.display = "none";
}

let created = false;

function createCategoriesList() {
    if(created) return;
    addOptions();
    addOptionsEvent();
    addMoreBtnEvent();
    resizeCategoriesSpace();
    showOptions();
    created = true;
}

function destroyCategoriesList() {
    if(!canDestroyCategoriesList || !created) return;
    removeOptions();
    resizeCategoriesSpace();
    hideOptions();
    created = false;
}

const categorias_input = document.querySelector("#categorias-input");
const categorias_container = document.querySelector("#categorias-container");

categorias_input.addEventListener("focus", createCategoriesList);
categorias_input.addEventListener("blur", destroyCategoriesList);
categorias_input.addEventListener("keyup", search);

categorias_container.addEventListener("mouseenter", () => canDestroyCategoriesList = false);
categorias_container.addEventListener("mouseleave", () => canDestroyCategoriesList = true);


/********************** Para o estilo dos inputs **********************/
function estilizaInputs() {
    const inputs = document.querySelectorAll("input");
    const categorias_container = document.querySelector("#categorias-container");
    const categorias = document.querySelector("#categorias");
    
    categorias_container.style.width = "";
    categorias_container.style.flexGrow = 1;
    
    inputs.forEach((input) => {
       input.style.width = ""; 
       input.style.flexGrow = 1;
    });
    
    let minWidth = parseInt(window.getComputedStyle(inputs[0]).width);
    
    inputs.forEach((input) => {
        let style = window.getComputedStyle(input);
        let val = parseInt(style.width);
        if(minWidth > val)
            minWidth = val;
    });
    
    inputs.forEach((input) => {
       input.style.width = `${minWidth}px`; 
       input.style.flexGrow = 0;
    });
    
    categorias_container.style.flexGrow = 0;
//    categorias.style.width = `${minWidth}px`;//minWidth + (parseInt(style.paddingRight) + parseInt(style.paddingLeft)) + "px";
    const categorias_input = document.querySelector("#categorias-input");
    categorias.style.top = `${categorias_input.offsetHeight}px`;
    
    const equipamento_input = document.querySelector("#maquinas-input");
    const equipamentos = document.querySelector("#equipamentos");
    equipamentos.style.top = `${equipamento_input.offsetHeight}px`;
}

estilizaInputs();
setTimeout(estilizaInputs, 50);

window.addEventListener("resize", estilizaInputs);

/********************** Para os botões **********************/
const cancel_btn = document.querySelector("#cancel-btn");
const send_btn = document.querySelector("#send-btn");
const initialPage = cancel_btn.getAttribute("data-link");

send_btn.addEventListener("click", send);
cancel_btn.addEventListener("click", cancel);

function send() {
    const requisitor = user_cpf;
    const categoria = document.querySelector("#categorias-input").value;
    const descricao = document.querySelector("#descricao").value;
    
    if(!categoria) {
        document.querySelector("#categorias-input").focus();
        return;
    }
    
    if(!categorias.includes(categoria)) {
        document.querySelector("#categorias-input").focus();
        return;
    }
    
    if(!descricao && !maquinas_adicionadas.length) {
        document.querySelector("#maquinas-input").focus();
        return;
    }
    
    let equipamentos = "";
    for(let i = 0; i < maquinas_adicionadas.length; i++) {
       equipamentos += maquinas_adicionadas[i]; 
       if(i + 1 !== maquinas_adicionadas.length) {
           equipamentos += "_";
       }
    }
    
    let ajax = new XMLHttpRequest();
    ajax.open("POST", "MainServlet", true);
    
    let request = new Request();
    request.setOperation("INSERT");
    request.setType("RQ");
    request.setData({
        "requisitor": requisitor.toString(),
        "categoria": categoria,
        "equipamentos": equipamentos,
        "descricao": descricao
    });
    
    ajax.onload = function() {
        if (ajax.status === 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() === "OK") {
                const form_inputs = document.querySelector("#form-inputs");
                form_inputs.innerHTML = `
                    <popup>
                        <h2 id="msg">Sua solicitação foi enviada com sucesso.</h2>
                        <img src="imgs/ok.png" />
                    </popup>
                `;
                
                setTimeout(
                    () => window.location.href = initialPage
                , 2000);
            } else {
                window.location.href = "erro.jsp?erro=" + response.getError() + "&url=" + window.location.href; 
            }
        } else {
            window.location.href = "erro.jsp?erro=Parece que você está com um erro de conexão. Por favor, tente novamente mais tarde." + "&url=" + initialPage;
        }
    };
    
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");;
    ajax.send(request.getRequest());
}

function cancel() {
   window.location.href = initialPage; 
};

/********************** Para as máquinas **********************/
let maquinas = null;

function getMaquinas() {
    let request = new Request();
    request.setOperation("GET");
    request.setType("EQ");
    
    let ajax = new XMLHttpRequest();
    ajax.open("POST", "MainServlet", true);
    
    ajax.onload = function() {
        if (ajax.status === 200) {
            let response = new Response(ajax.responseText);

            if (response.getStatus() === "OK") {
                maquinas = response.getData();
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

getMaquinas();

function getMaquinasInterval() {
    getMaquinas();
    setInterval(() => getMaquinasInterval(), 1000 * 60 * 5);
}

//getMaquinasInterval();

const equipamentos_adicionados_div = document.querySelector("#equipamentos-adicionados");
const equipamentos = document.querySelector("#equipamentos");
const input_maquinas = document.querySelector("#maquinas-input");

let canRemoveEquipamentoInput = true;

equipamentos.addEventListener("mouseenter", () => canRemoveEquipamentoInput = false);
equipamentos.addEventListener("mouseleave", () => canRemoveEquipamentoInput = true);

input_maquinas.addEventListener("blur", () => {
    if(!canRemoveEquipamentoInput) return;
    equipamentos.innerHTML = "";
});

input_maquinas.addEventListener("focus", () => {
    searchMaquina();
});

let maquinas_adicionadas = [];
let maquina_para_adicionar = null;

input_maquinas.addEventListener("keyup", searchMaquina);

function searchMaquina() {
    if(maquinas === null) return;
    const equipamentosRestantes = [];
    
    maquinas.forEach(maquina => {
        if(!maquinas_adicionadas.includes(maquina["n_patrimonio"]))
            equipamentosRestantes.push(maquina);
    });
    
    
    const equipamentos = document.querySelector("#equipamentos");
    let value = input_maquinas.value;
    
    if(value === "") {
        equipamentos.style.display = "none";
        equipamentos.innerHTML = "";
        maquina_para_adicionar = null;
        return;
    }
    
    for(let i = 0; i < equipamentosRestantes.length; i++) {
        if(equipamentosRestantes[i]["n_patrimonio"].startsWith(value)) {
            equipamentos.innerHTML = `
                <section class="equipamento">
                    <div class="n_patrimonio">${equipamentosRestantes[i]["n_patrimonio"]}</div>
                    <div class="nome_equipamento">${equipamentosRestantes[i]["nome"]}</div>
                </section>
            `;
            maquina_para_adicionar = {"n_patrimonio": equipamentosRestantes[i]["n_patrimonio"]};
            equipamentos.style.display = "block";
            addEquipamentoAdicionarEvent();
            return;
        }
    }

    equipamentos.style.display = "none";
    equipamentos.innerHTML = "";
    maquina_para_adicionar = null;
}

function addEquipamentoAdicionarEvent() {
    if(maquina_para_adicionar === null) return;
    const equipamento_btn = document.querySelector(".equipamento");
    const equipamentos_input = document.querySelector("#maquinas-input");

    equipamento_btn.addEventListener("click", () => {
        
        maquinas_adicionadas.push(maquina_para_adicionar["n_patrimonio"]);
        addEquipamento(maquina_para_adicionar["n_patrimonio"]);
        maquina_para_adicionar = null;
        equipamentos_input.focus();
        equipamentos_input.value = "";
        searchMaquina();
    });
}

function addRemoverBtnEvent() {
    const btns = document.querySelectorAll(".remover-btn");
    
    btns.forEach(btn => btn.addEventListener("click", (e) => {
        const n_patrimonio = e.target.getAttribute("data-n_patrimonio");
        let remover = document.getElementById(n_patrimonio);
        
        equipamentos_adicionados_div.removeChild(remover);
        maquinas_adicionadas = maquinas_adicionadas.filter(obj => obj !== n_patrimonio);
        searchMaquina();
    }));
}

function addEquipamento(n_patrimonio) {
    let maquina = maquinas.find(obj => obj["n_patrimonio"] === n_patrimonio);
    
    equipamentos_adicionados_div.innerHTML += `
        <section class="adicionado" id="${maquina["n_patrimonio"]}" >
            <div class="n_patrimonio" >${maquina["n_patrimonio"]}</div>
            <div class="nome" >${maquina["nome"]}</div>
            <div class="local" >${maquina["local"]}</div>
            <div class="estado" >${maquina["estado"]}</div>
            <img class="remover-btn" data-n_patrimonio="${maquina["n_patrimonio"]}" src="imgs/remove-btn.png" />
        </section>
    `;
    addRemoverBtnEvent();
}