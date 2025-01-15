function exibirRequisicoes(data) {
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