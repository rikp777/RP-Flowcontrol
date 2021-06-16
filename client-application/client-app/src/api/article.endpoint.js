
import ApiService from "@/services/api.service";

class articleEndpoint {
    constructor() {
        this.resource = "/article/api/v1/articles/"
        this.api = new ApiService(this.resource);
    }

    getAll(){
        return this.api.get()
    }

    getById(id){
        return this.api.getByParam(id);
    }

    create(payload) {
        return this.api.post(payload);
    }

    update(id, payload) {
        return this.api.put(id, payload);
    }

    delete(id){
        return this.api.delete(id);
    }
}

export default articleEndpoint