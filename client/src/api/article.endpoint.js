
import ApiService from "@/services/api.service";

class articleEndpoint {
    constructor() {
        this.resource = "/article/api/v1/articles/"
        this.api = new ApiService(this.resource);
    }

    getAll(page){
        let params = {
            page: page,
            size: 6
        }
        return this.api
            .getByParams(params)
    }

    getById(id){
        return this.api.get(id);
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