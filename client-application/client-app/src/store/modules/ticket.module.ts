import {Action, Module, Mutation, VuexModule} from "vuex-module-decorators";
import api from "@/services/api.service"
import ticketApi from "@/api/ticket.endpoint";
import palletLabelApi from "@/api/palletLabel.endpoint";
import PalletLabelEndpoint from "@/api/palletLabel.endpoint";

@Module({
    namespaced: true
})
class PalletLabelModule extends VuexModule {

    public entities : any = []
    public entity : any = {}

    private debug = true;
    private palletLabel_endpoint = new PalletLabelEndpoint()

    public hasError = true
    public isLoading = false


    @Action({ rawError: true })
    public async fetchPalletLabel(palletLabelId: number) {
        await this.palletLabel_endpoint.getById(palletLabelId)
            .then((response: any) => {
                this.context.commit("setEntities", response.data);
            })
    }

    @Mutation
    private setEntities(data : any) : void{
        if(this.debug) console.log("[ArticleModule] setEntities()", data)
        if(data === null) return
        this.entities = data;
    }
    @Mutation
    private setEntity(data : any) : void{
        if(this.debug) console.log("[ArticleModule] setEntity()", data)
        if(data === null) return
        this.entity = data;
    }

    @Mutation
    private setError(state : boolean) : void{
        if(state === null) return
        this.hasError = state;
        this.isLoading = false;
    }

    @Mutation
    private endLoading() : void{
        this.isLoading = false;
    }

    @Mutation
    private startLoading() : void{
        this.hasError = false;
        this.isLoading = true;
    }
}

export default PalletLabelModule;