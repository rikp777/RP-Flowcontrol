import {Action, Module, Mutation, VuexModule} from "vuex-module-decorators";
import Api from "@/services/api.service"
import articleEndpointHATEOAS from "@/api/article.endpoint";

@Module({
    namespaced: true
})
class TicketModule extends VuexModule {
    public entities : any = []
    public entity : any = {}
    private currentFarmer  = 0

    private debug = true;
    private farmer_endpoint = new articleEndpointHATEOAS()

    public hasError = true
    public isLoading = false

    get getCurrentFarmerByName() : string {
        let name = "No farmer"
        if(this.entities){
            this.entities.filter((i:any) => {
                if(Api.getId(i.farmer.links.self.href) == this.currentFarmer){
                    name = i.farmer.name
                }
            })
        }
        return name
    }
    get getCurrentFarmerById() : number{
        if(this.currentFarmer){
            return this.currentFarmer
        }
        return 0
    }

    get getAllFarmers() : any{
        return this.entities
    }


    @Action({ rawError: true })
    public async setLocalFarmer(farmer : any){
        const id = Api.getId(farmer.links.self.href);
        await this.context.commit("setCurrentFarmer", id)
        await localStorage.setItem("farmer", id);
    }

    @Action({ rawError: true })
    public async getAllFarmersThatAreAttachedToUser(userId : number) : Promise<any> {
        this.context.commit("startLoading")
        console.log("[FarmerModule] getAllFarmersThatAreAttachedToUser", userId)
        if(userId){
            if(this.entities != null && this.entities.length > 0) {
                if(this.debug) console.log("[FarmerModule] data was already fetched", this.entities)
                this.context.commit("setCurrentFarmer", Api.getId(this.entities[0].farmer.links.self.href))
                this.context.commit("endLoading")
                return
            }
            const api = new Api(`/farmer/api/v1/farmers/user/${userId}`)
            await api.get()
                .then(({data}) => {
                    if(data){
                        this.context.commit("setCurrentFarmer", Api.getId(data.embedded.farmerUsers[0].farmer.links.self.href))
                        this.context.commit("setEntities", data.embedded.farmerUsers);
                        this.context.commit("endLoading")
                    }
                })
                .catch((error) => {
                    this.context.commit("setError", true)
                    throw error
                })
        }
    }

    @Mutation
    private setEntities(data : any) : void{
        if(this.debug) console.log("[FarmerModule] setEntities()", data)
        if(data === null) return
        this.entities = data;
    }
    @Mutation
    private setEntity(data : any) : void{
        if(this.debug) console.log("[FarmerModule] setEntity()", data)
        if(data === null) return
        this.entity = data;
    }
    @Mutation
    private setCurrentFarmer(id : number) : void{
        if(this.debug) console.log("[FarmerModule] setCurrentFarmer()", id)
        this.currentFarmer = id;
        localStorage.setItem('farmer', id.toString());
        console.log(this.currentFarmer)
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

export default TicketModule;