//
//
//function exibirRequisicoes(data) {
//    
//}
//
//function getRequisicoes() {
//    let request = new Request();
//    request.setOperation('GET');
//    request.setType('RQ');
//    request.setData({});
//
//    $.ajax ({
//        url: 'MainServlet?' + request.getRequest(),
//        dataType: 'json',
//        beforeSend:() => {
//            $('body').html('Carregando...');
//        },
//        success:(data) => {
//            let response = new Response(data);
//            data = response.getData();
//            data = JSON.parse(data);
//            exibirRequisicoes(data);
//        }
//    });
//}
//getRequisicoes();
