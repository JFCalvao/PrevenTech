let request = new Request();
request.setOperation('GET');
request.setType('RQ');
request.setData({});

function exibirRequisicoes(data) {
    
}

$.ajax ({
    url: 'MainServlet?' + request.getRequest(),
    dataType: 'json',
    beforeSend:() => {
        $('body').html('Carregando...');
    },
    success:(data) => {
        let response = new Response(data);
        data = response.getData();
    }
});