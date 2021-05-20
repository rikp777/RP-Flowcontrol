import {Action, Module, Mutation, VuexModule} from "vuex-module-decorators";
import api from "@/api";

const baseUrl = `http://127.0.0.1:8762/production/api/v1/farmers/1/`;

interface PalletLabel {
    id: number,
    generalId: number,
    cropDate: string,
    articleAmount: number,
    article: {
        name: string
    }
}
interface Ticket {
    id: number,
    palletLabel: PalletLabel,
    interruptions: Array<Interruption>
}
interface Interruption {
    id: number
}

@Module({
    namespaced: true
})
class Ticket extends VuexModule {

    public tickets = [];
    public interruptions: Array<Interruption> = [];
    public palletLabel : PalletLabel = {
        article: {name: ""}, 
        articleAmount: 0,
        cropDate: "",
        id: 0,
        generalId: 0
    }


    get getTickets() {
        return this.tickets;
    }
    get getInterruptions() {
        console.log(this.interruptions)
        return this.interruptions;
    }
    get getPalletLabel(){
        return this.palletLabel
    }

    @Action({ rawError: true })
    public async purgeData(){
        await this.context.commit("setPurgeData");
    }
    @Mutation
    setPurgeData() {
        this.tickets = [];
        this.interruptions = [];
        this.palletLabel.id = 0;
        this.palletLabel.article.name = ""
        this.palletLabel.articleAmount = 0;
        this.palletLabel.cropDate = ""
        this.palletLabel.generalId = 0;
    }

    @Action({ rawError: true })
    public async fetchPalletLabel(palletLabelId: number) {
        await api.get(`http://127.0.0.1:8762/transport/api/v1/farmers/1/palletlabels/${palletLabelId}`)
            .then((response) => {
                this.context.commit("setPalletLabel", response.data);
            })
    }

    @Mutation
    setPalletLabel(palletLabel: any) {
        this.palletLabel = palletLabel
    }
    
    @Action({ rawError: true })
    public async fetchTickets(palletLabelId: number) {
        await api.get(`${baseUrl}/palletlabels/${palletLabelId}/tickets`)
            .then((response) => {
                if(response.data && response.data._embedded && response.data._embedded.tickets){
                    const isDataAvailable = response.data._embedded.tickets && response.data._embedded.tickets.length;
                    const data = isDataAvailable ? response.data._embedded.tickets : [];
                    if(data.length > 0){
                        console.log("Set tickets", data)
                        this.context.commit("setTickets", response.data._embedded.tickets);
                    }
                }
            })
    }
    @Action({ rawError: true })
    public async closeTicket(ticket: Ticket) {
        await api.post(`${baseUrl}/palletlabels/${ticket.palletLabel.id}/tickets/${ticket.id}/close`)
            .then((response) => {
                this.context.commit("setPurgeData");
            })
    }

    @Mutation
    setTickets(tickets: any) {
        this.tickets = tickets
    }

    @Action({ rawError: true })
    public async fetchInterruptions(ticket: Ticket) {
        await api.get(`${baseUrl}/palletlabels/${ticket.palletLabel.id}/tickets/${ticket.id}/interruptions`)
            .then((response) => {
                if(response.data && response.data._embedded && response.data._embedded.interruptions){
                    const isDataAvailable = response.data._embedded.interruptions && response.data._embedded.interruptions.length;
                    const data = isDataAvailable ? response.data._embedded.interruptions : [];
                    if(data.length > 0){
                        console.log("Set Interruptions", data)
                        this.context.commit("setInterruptions", data);
                    }
                }
            })
    }

    @Action({ rawError: true })
    public async closeInterruption(ticket: Ticket) {
        await api.post(`${baseUrl}/palletlabels/${ticket.palletLabel.id}/tickets/${ticket.id}/interruptions/${ticket.interruptions[ticket.interruptions.length -1].id}/close`)
            .then((response) => {
                console.log("Close interruption", ticket.interruptions[ticket.interruptions.length -1].id)
            })
    }

    @Mutation
    setInterruptions(interruptions: any) {
        console.log("setting interruptions", interruptions)
        this.interruptions = interruptions
    }
}

export default Ticket