class Request {
    operation;
    type;
    content;
    json;

    getRequest() {
        this.json = JSON.stringify({
            "operation": this.operation,
            "type": this.type,
            "content": this.content
        });
        
        let form = new FormData();
        form.append("json", this.json);
        
        let url = new URLSearchParams(form);
        url = url.toString();
        
        return url;
    }

    setOperation(operation) {
        this.operation = operation;
    }

    setType(type) {
        this.type = type;
    }

    setData(content) {
        this.content = content;
    }

    constructor() {
        this.operation = "";
        this.type = "";
        this.content = {};
    }
}

class Response {
    response;
    status;
    error;
    content;

    constructor(responseText) {
        this.response = JSON.parse(responseText);
        this.status = this.response.status;
        this.error = this.response.error;
        this.content = this.response.content;
    }

    getStatus() {
        return this.status;
    }

    getError() {
        return this.error;
    }

    getData() {
        return this.content;
    }
}