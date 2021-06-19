
import ApiService from "@/services/api.service";

class PalletLabelEndpoint {
    constructor(palletLabelId) {
        //get farmer id from cookies
        let farmerId = 1
        this.resource = `/transport/api/v1/farmers/${farmerId}/palletlabels`
        this.api = new ApiService(this.resource);
    }

    getAll(){
        return this.api.get()
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

export default PalletLabelEndpoint