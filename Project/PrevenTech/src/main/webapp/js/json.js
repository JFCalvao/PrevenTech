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
    status;
    error;
    content;

    constructor(responseText) {
        let response = JSON.parse(responseText);
        this.status = response?.status;
        this.error = response?.error;
        this.content = response?.content;
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