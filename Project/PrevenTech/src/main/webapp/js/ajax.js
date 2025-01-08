let request = new Request();
request.setOperation();
request.setType('POST');
request.setData({});

function exibirTodasRequisicoes(data) {
    
}

function exibirMinhasRequisicoes(data) {
    
}

function exibirRequisicoesTecnico(data) {
    
}

function efetuarAjax(funcaoExibir) {
    $.ajax ({
        url: '' + request.getRequest(),
        dataType: 'json',
        beforeSend:() => {
            $('body').html('Carregando...');
        },
        success:(resposta) => {
            let response = new Response(resposta);
            resposta = response.getData();
            funcaoExibir();
        }
    });
}