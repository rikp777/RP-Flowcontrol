import {Action, Module, Mutation, VuexModule} from "vuex-module-decorators";
import Api from "@/services/api.service"
import ticketApi from "@/api/ticket.endpoint";
import palletLabelApi from "@/api/palletLabel.endpoint";
import PalletLabelEndpoint from "@/api/palletLabel.endpoint";

@Module({
    namespaced: true
})
class PalletLabelModule extends VuexModule {

    public palletLabels : any = []
    public palletLabel : any = {}

    public tickets : any = []
    public ticket: any = {}

    public interruptions : any = []
    public interruption : any = {}

    public interruptionReasons: any = [
        {
            value: 1,
            label: "Break",
            stopProcess: false
        },
        {
            value: 2,
            label: "New foil",
            stopProcess: false
        },
        {
            value: 3,
            label: "Sticker change",
            stopProcess: false
        },
        {
            value: 4,
            label: "Product swap",
            stopProcess: true
        },
        {
            value: 5,
            label: "Product disapproval",
            stopProcess: true
        },
        {
            value: 6,
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

    get getPalletLabel() : any {
        return this.palletLabel
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
    public async getPalletLabelById(palletLabelId: number) {
        this.context.commit("startLoading")
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/transport/api/v1/farmers/${farmerId}/palletlabels`)
        await api.get(palletLabelId.toString())
            .then((response: any) => {
                this.context.commit("setPalletLabel", response.data);
                this.context.commit("endLoading")
            }).catch((error :any) => {
                this.context.commit("endLoading")
            })
    }

    @Action({ rawError: true })
    public async getAllTicketsBelongingToPalletLabelId(palletLabelId: number) {
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
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${this.palletLabel.id}/tickets`)
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
    public async closeTicket(ticket: any) {
        const farmerId = localStorage.getItem('farmer')
        const api = new Api(`/production/api/v1/farmers/${farmerId}/palletlabels/${ticket.palletLabel.id}/tickets/${ticket.id}/close`)
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