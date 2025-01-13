/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

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
            console.log(categoria.toUpperCase() + " === " + input.toUpperCase());
            console.log("sc: " + categoria.toUpperCase().search(input.toUpperCase()));
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
}

estilizaInputs();

window.addEventListener("resize", estilizaInputs);
