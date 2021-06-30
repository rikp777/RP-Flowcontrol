import {Action, Module, Mutation, VuexModule} from "vuex-module-decorators";
import Api from "@/services/api.service"
import ticketApi from "@/api/ticket.endpoint";
import palletLabelApi from "@/api/palletLabel.endpoint";
import PalletLabelEndpoint from "@/api/palletLabel.endpoint";


//TODO Refactor and code cleanup
@Module({
    namespaced: true
})
class PalletLabelModule extends VuexModule {

    public lines : any = []

    public palletLabels : any = []
    public palletLabel : any = {}

    public tickets : any = []
    public ticket: any = {}

    public interruptions : any = []
    public interruption : any = {}

    public interruptionReasons: any = [
        {
            value: "833c0bf0-b070-4125-b1f8-fe30816645d5",
            label: "Break",
            stopProcess: false
        },
        {
            value: "29a3a649-2319-4bec-85c7-b25529bc2cb6",
            label: "New foil",
            stopProcess: false
        },
        {
            value: "572340cc-8894-4636-8522-6f559692d8db",
            label: "Sticker change",
            stopProcess: false
        },
        {
            value: "4e359093-2aa3-4559-8582-334ae66c7c7c",
            label: "Product swap",
            stopProcess: true
        },
        {
            value: "3a265806-3948-46a1-917b-a725f5c2e1cc",
            label: "Product disapproval",
            stopProcess: true
        },
        {
            value: "721d6aa0-e3a3-4f12-83a6-72f2d59148e9",
            label: "Production line error",
            stopProcess: true
        }
    ]


    private debug = true;
    private palletLabel_endpoint = new PalletLabelEndpoint()

    public hasError = true
    public isLoading = false


    get getItemsToStillProcess() : any{
        if(this.tickets != null && this.palletLabel != null){
            let leftOver = this.palletLabel.articleAmount;
            this.tickets.forEach((ticket : any) => {
                console.log(ticket.articleAmountUsed)
                leftOver = leftOver - ticket.articleAmountUsed
            })
            return leftOver
        }
    }

    get getLines(): any {
        return this.lines
    }

    get getPalletLabel() : any {
        return this.palletLabel
    }
    get getPalletLabelId() : string {
        if(this.palletLabel){
            console.log(this.palletLabel.links.self.href)
            return Api.getId(this.palletLabel.links.self.href)
        }
        return ""
    }
    get getTickets() : any {
        return this.tickets
    }
    get getInterruptions() : any {
        return this.interruptions;
    }
    get getInterruptionReasons() : any{
        return this.interruptionReasons
    }
    get getIsLoading() : boolean {
        return this.isLoading;
    }
    get getHasError() : boolean {
        return this.hasError
    }

    @Action({ rawError: true })
    public async getAllProductionLines() {
        this.context.commit("startLoading")
        const api = new Api(`/production/api/v1/lines`)
        await api.get()
            .then((response: any) => {
                this.context.commit("setLines", response.data);
                this.context.commit("endLoading")
            }).catch((error :any) => {
                this.context.commit("endLoading")
            })
    }

    @Action({ rawError: true })
    public async getPalletLabelById(palletLabelId: number) {
        this.context.commit("startLoading")
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/transport/api/v1/farmers/${farmerId}/palletlabels/getbygeneral`)
        await api.get(palletLabelId.toString())
            .then((response: any) => {
                this.context.commit("setPalletLabel", response.data);
                this.context.commit("endLoading")
            }).catch((error :any) => {
                this.context.commit("endLoading")
            })
    }

    @Action({ rawError: true })
    public async getAllTicketsBelongingToPalletLabelId(palletLabelId: string) {
        this.context.commit("startLoading")
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${palletLabelId}/tickets`)
        await api.get()
            .then((response:any ) => {
                console.log(response)
                if(response.data.embedded && response.data.embedded.tickets){
                    this.context.commit("setTickets", response.data.embedded.tickets);
                }
                this.context.commit("endLoading")
            })
            .catch((error) => {
                this.context.commit("setError", true)
            })
    }
    @Action({ rawError: true })
    public async createTicket(lineId : number){
        console.log(lineId)
        if(lineId == null) return
        this.context.commit("startLoading")
        const params : any = {
            lineId: lineId
        }
        const farmerId = localStorage.getItem('farmer')
        const data = {}
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${Api.getId(this.palletLabel.links.self.href)}/tickets`)
        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
        // @ts-ignore
        await api.post(data, params).then((response) => {
            this.context.commit("setTicket", response.data);
            this.context.commit("endLoading")
        }).catch((error) => {
            this.context.commit("setError", true)
        })
    }

    @Action({ rawError: true })
    public async createInterruption(params: any){
        console.log(params)
        if(this.tickets == null) return
        this.context.commit("startLoading")
        const farmerId = localStorage.getItem('farmer')
        const data = {}

        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${Api.getId(this.palletLabel.links.self.href)}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions`)

        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
        // @ts-ignore
        await api.post(data, params).then((response) => {
            this.context.commit("setInterruptions", response.data);
            this.context.commit("endLoading")
        }).catch((error) => {
            this.context.commit("setError", true)
        })
    }

    @Action({ rawError: true })
    public async closeTicket(ticket: any) {
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${Api.getId(this.palletLabel.links.self.href)}/tickets/${ticket.id}/close`)
        await api.post()
            .then((response) => {
                this.context.commit("setPurgeData");
            })
    }

    @Action({ rawError: true })
    public async getAllInterruptionsByTicket(ticket: any) {
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${ticket.palletLabel.id}/tickets/${ticket.id}/interruptions`)
        await api.get()
            .then((response: any) => {
                if(response.data && response.data._embedded && response.data._embedded.interruptions){
                    const isDataAvailable = response.data._embedded.interruptions && response.data._embedded.interruptions.length;
                    const data = isDataAvailable ? response.data._embedded.interruptions : [];
                    if(data.length > 0){
                        this.context.commit("setInterruptions", data);
                    }
                }
            })
    }

    @Action({ rawError: true })
    public async closeInterruption(ticket: any) {
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${ticket.palletLabel.id}/tickets/${ticket.id}/interruptions/${ticket.interruptions[ticket.interruptions.length -1].id}/close`)
        await api.post()
            .then((response : any) => {
                console.log("Close interruption", ticket.interruptions[ticket.interruptions.length -1].id)
            })
    }

    @Action({ rawError: true })
    public async purgeData(ticket: any) {
        this.context.commit("setPurgeData");
    }

    @Mutation
    private setPurgeData() {
        this.ticket = {};
        this.tickets = [];
        this.interruption = {};
        this.interruptions = [];
        this.palletLabel = {}
        this.palletLabels = []
    }

    @Mutation
    private setPalletLabels(data : any) : void{
        if(this.debug) console.log("[TicketModule] setPalletLabels()", data)
        if(data === null) return
        this.palletLabels = data;
    }
    @Mutation
    private setPalletLabel(data : any) : void{
        if(this.debug) console.log("[TicketModule] setPalletLabel()", data)
        if(data === null) return
        this.palletLabel = data;
    }

    @Mutation
    private setTickets(data : any) : void{
        if(this.debug) console.log("[TicketModule] setTickets()", data)
        if(data === null) return
        this.tickets = data;
    }
    @Mutation
    private setTicket(data : any) : void{
        if(this.debug) console.log("[TicketModule] setTicket()", data)
        if(data === null) return
        this.ticket = data;
    }

    @Mutation
    private setInterruptions(data : any) : void{
        if(this.debug) console.log("[TicketModule] setInterruptions()", data)
        if(data === null) return
        this.interruptions = data;
    }
    @Mutation
    private setInterruption(data : any) : void{
        if(this.debug) console.log("[TicketModule] setInterruption()", data)
        if(data === null) return
        this.interruption = data;
    }
    @Mutation
    private setLines(data : any) : void{
        if(this.debug) console.log("[TicketModule] setLines()", data)
        if(data === null) return
        this.lines = data;
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