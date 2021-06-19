
import ApiService from "@/services/api.service";

class TicketEndpoint {
    constructor(palletLabelId : number) {
        //get farmer id from cookies
        let farmerId = 1
        this.resource = `/production/api/v1/farmers/${farmerId}/palletlabels/${palletLabelId}/tickets`
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

export default TicketEndpoint