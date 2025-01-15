function exibirRequisicoes(data) {
    function exibirStatus() {
        
    }
    
    function exibirTecnico() {
        
    }
    
    function exibirDataHorario() {
        
    }
    
    function exibirCategoria() {
        
    }
    
    function exibirEquipamentos() {
        
    }
    
    function exibirDescricao() {
        
    }
    
    for(let requisicao of data) {
        let novaRequisicaoEl = document.createElement('div');
        let viewBasicoEl = document.createElement('div');
        let informacoesBasicasEl = document.createElement('div');
        
        let titleRequisicaoEl = document.createElement('h4');
        let spanNumeroRequisicaoEl = document.createElement('span');
        let spanNumeroRequisicoesEl = document.createElement('span');
        
        let informacoesExpandirEl = document.createElement('div');
        let setinhaExpandirRetrairEl = document.createElement('div');
        let linhaRequisicaoEl = document.createElement('div');
        
        let statusEl = document.createElement('div');
        let corStatusEl = document.createElement('span');
        let txtStatusEl = document.createElement('span');
        
        let tecnicoEl = document.createElement('div');
        let spanTxtTecnicoEl = document.createElement('span');
        let spanNomeTecnicoEl = document.createElement('span');
    
        let dataHorarioEnvioEl = document.createElement('div');
        let txtEnviadoEl = document.createElement('span');
        let txtHorarioEl = document.createElement('span');
        let txtDataEl = document.createElement('span');
        
        let categoriaEl = document.createElement('div');
        let txtCategoriaEl = document.createElement('span');
        let nomeCategoriaEl = document.createElement('span');
        
        let equipamentosEl = document.createElement('div');
        let txtEquipamentosEl = document.createElement('span');
        let numeroDasMaquinasEl = document.createElement('span');
        
        let descricaoEl = document.createElement('div');
        let tituloDescricaoEl = document.createElement('h5');
        let spanDescricaoEl = document.createElement('span');
    }
    
    console.log(data);
}

function obterRequisicoes() {
    let request = new Request();
    request.setOperation('GET');
    request.setType('RQ');
    request.setData({});
    
    $.ajax ({
        url: 'MainServlet?' + request.getRequest(),
        dataType: 'json',
        beforeSend:() => {
            $('body').html('Carregando...');
        },
        success:(data) => {
            exibirRequisicoes(data);
        }
    });
}

obterRequisicoes();