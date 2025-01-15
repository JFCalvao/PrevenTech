
function expandirRetrairRequisicao(informacaoExpandirEl, setinhaEl) {
    if(!informacaoExpandirEl.classList.contains('escondido')) {
        setinhaEl.classList.add('girar-setinha-para-posicao-normal');
        setinhaEl.classList.remove('girar-setinha-para-baixo');
        informacaoExpandirEl.classList.add('escondido');
        informacaoExpandirEl.classList.remove('expandir');
    }
    else {
        setinhaEl.classList.remove('girar-setinha-para-posicao-normal');
        setinhaEl.classList.add('girar-setinha-para-baixo');
        informacaoExpandirEl.classList.remove('escondido');
        informacaoExpandirEl.classList.add('expandir');
    }
}

function addEvents() {
    const arrInformacoesExpandirEl = document.querySelectorAll('.informacoes-expandir');
    const arrSetinhaExpandirRetrairEl = document.querySelectorAll('.setinha-expandir-retrair');
    const arrRequisicaoEl = document.querySelectorAll('.requisicao .view');
    
    for(let i = 0; i < arrRequisicaoEl.length; i++) {
        arrRequisicaoEl[i].addEventListener('click', () => {
            expandirRetrairRequisicao(arrInformacoesExpandirEl[i], arrSetinhaExpandirRetrairEl[i]);
        });
    }
}

addEvents();
