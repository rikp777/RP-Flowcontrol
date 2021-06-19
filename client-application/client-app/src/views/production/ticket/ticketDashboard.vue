<template>
  <div>
    <h1>Ticket Dashboard</h1>
    <div>
      <div class="card">
        <div class="card-content">
          <div class="media">
            <div class="media-content">
              <p class="title is-4">Create ticket</p>
              <p class="subtitle is-6">Make a ticket, for processing the specified pallet.</p>
            </div>
          </div>

          <div class="content">
            <div class="columns">
              <div class="column is-two-thirds">
                <section>
                  <b-loading :is-full-page="false" v-model="isLoading" :can-cancel="false"></b-loading>
                  <b-notification aria-close-label="Close notification" class="is-primary" v-if="palletLabel.article">
                    <table class="table is-fullwidth is-transparent">
                      <tbody>

                        <tr v-if="form.palletLabelId">
                          <td width="5%"><r-icon icon="pallet"></r-icon></td>
                          <td width="35%">Pallet label nr.:</td>
                          <td width="10%">
                            <span v-if="form.palletLabelId">{{form.palletLabelId}}</span>
                            <span v-else-if="palletLabel">{{palletLabel.id}}</span>
                          </td>
                          <td ><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                        <tr v-if="form.lineId">
                          <td width="5%"><r-icon icon="people-carry"></r-icon></td>
                          <td width="35%">Line nr.:</td>
                          <td width="10%">
                            <span v-if="form.lineId">{{form.lineId}}</span>
                            <span v-else-if="tickets[0]">{{tickets[this.tickets.length -1].line.id}}</span>
                          </td>
                          <td><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                        <tr v-if="palletLabel.article">
                          <td width="5%"><r-icon icon="seedling"></r-icon></td>
                          <td width="35%">Line nr.:</td>
                          <td width="10%">{{palletLabel.article.fullName}}</td>
                          <td><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                        <tr v-if="palletLabelIsFullyUsed">
                          <td width="5%"><r-icon icon="truck-loading"></r-icon></td>
                          <td width="35%">Status:</td>
                          <td width="10%">Processed</td>
                          <td><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                      </tbody>
                    </table>
                  </b-notification>
                </section>
                <section>
                  <b-loading :is-full-page="false" v-model="isLoading" :can-cancel="false"></b-loading>
                  <div>
                    <h4>Ticket Data</h4>
                  </div>
                  <button v-if="!isLoading && tickets.length === 0">Get data</button>
                  <div v-if="hasError">Error</div>
                  <b-table
                      v-if="!hasError"
                      :data="tickets"
                      :paginated="false"
                      :loading="isLoading"
                      default-sort="user.first_name"
                      aria-next-label="Next page"
                      aria-previous-label="Previous page"
                      aria-page-label="Page"
                      aria-current-label="Current page">


<!--                    <b-table-column field="user.first_name" label="First Name" sortable v-slot="props">-->
<!--                      {{ props.row.fullName }}-->
<!--                    </b-table-column>-->

<!--                    <b-table-column label="Actions" v-slot="props">-->
<!--                      <router-link label="button" :to="{ name: 'editArticle', params: { id: getId(props.row) } }" class="button is-small is-light" >-->
<!--                        <r-icon icon="edit"></r-icon>-->
<!--                      </router-link>-->
<!--                      <router-link label="button" :to="{ name: 'readArticle', params: { id: getId(props.row) } }" class="button is-small is-primary">-->
<!--                        <r-icon icon="eye"></r-icon>-->
<!--                      </router-link>-->
<!--                    </b-table-column>-->


                  </b-table>
                </section>
              </div>
              <div class="column">
                <form v-on:submit.prevent>
                  <section v-if="form.palletLabelId">
                    <b-field grouped group-multiline>
                      <div class="control">
                        <b-tag
                            v-if="palletLabel.farmer"
                            type="is-primary"
                            attached>
                          {{palletLabel.farmer.name}}
                        </b-tag>
                      </div>
                      <div class="control">
                        <b-tag
                              v-if="palletLabel.cropDate"
                              type="is-primary"
                              attached>
                          {{palletLabel.cropDate}}
                        </b-tag>
                      </div>
                      <div class="control">
                        <b-tag
                              v-if="palletLabel.articleAmount"
                              type="is-primary"
                              attached>
                          {{palletLabel.articleAmount}}
                        </b-tag>
                      </div>
                    </b-field>
                    <div >
                    </div>
                  </section>
                  <section>
                    <b-field
                        message="Press enter after filling label"
                    >
                      <b-input placeholder="Pallet label id"
                               :lazy=true
                               v-model="form.palletLabelId"
                               type="number"
                               icon-pack="fas"
                               icon="pallet">
                      </b-input>
                    </b-field>
                  </section>
                  <section>
                    <b-field
                        message="Press enter after filling label"
                    >
                      <b-input
                              v-if="tickets.length == 0"
                              placeholder="Line id"
                              :lazy=true
                              v-model="form.lineId"
                              type="number"
                              icon-pack="fas"
                              icon="people-carry">
                      </b-input>
                    </b-field>
                  </section>

                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: "ticketDashboard",
  data() {
    return {
      form: {
        palletLabelId: null,
        lineId: null,
        interruptionReasonId: null,
        usedArticleAmount: null,
        refillAmount: null
      },
    }
  },
  watch: {
    'form.palletLabelId': function (newVal, oldVal){
      this.purgeData()
      this.getPalletLabelById(newVal)
    },
  },
  computed: {
    ...mapGetters("ticket", {
      tickets: "getTickets",
      interruptions: "getInterruptions",
      palletLabel: "getPalletLabel",
      hasError: "getHasError",
      isLoading: "getIsLoading",
    }),

    palletLabelIsFullyUsed() {
      console.log(this.tickets)
      if(this.tickets.length > 0 && this.palletLabel) {
        const hasEnd = this.tickets[this.tickets.length -1].endAt != null
        let usedAmount = 0
        this.tickets.forEach(ticket => {
          usedAmount = usedAmount + ticket.articleAmountUsed
        })

        if(this.palletLabel.articleAmount <= usedAmount && hasEnd){
          return true
        }
      }
      return false;
    },

    canCreateNewTicket(){
      console.log(this.tickets)
      if(this.tickets.length > 0 && this.palletLabel) {
        if(this.tickets[this.tickets.length -1].endAt != null){
          return true
        }else {
          return false
        }
      }
      return true;
    },

    processStops: function () {
      return  this.form.interruptionReasonId == 4 ||
          this.form.interruptionReasonId == 5 || this.form.interruptionReasonId == 6
    },

    isInterruptionOpen: function () {
      let success = false
      if(this.interruptions != null) {
        if (this.interruptions.length > 0) {
          this.interruptions.forEach(item => {
            if (item.endAt == null) {
              success = true
            }
          })
        }
      }
      return success
    }
  },
  methods: {
    ...mapActions("ticket", {
      getPalletLabelById: "getPalletLabelById",
      getAllTicketsBelongingToPalletLabelId: "getAllTicketsBelongingToPalletLabelId",
      getAllInterruptionsByTicket: "getAllInterruptionsByTicket",
      closeTicket: "closeTicket",
      closeInterruptionAction: "closeInterruption",
      purgeData: "purgeData"
    }),

    getInterruptions(){
      this.getAllInterruptionsByTicket(this.tickets[this.tickets.length -1].id)
    },
    async getTickets(){
      await this.purgeData();
      await this.fetchTicketsAction(this.form.palletLabelId)
      await this.fetchPalletLabelAction(this.form.palletLabelId)
      await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
    },
    getPalletLabel(palletLabelId){
      this.error = null;
      const apiUrl = `http://127.0.0.1:8762/transport/api/v1/farmers/1/palletlabels/${palletLabelId}`
      this.axios.get(apiUrl).then((response) => {
        console.log(response)
        this.palletLabel = response.data;
        this.article = response.data.article.name;
        this.form.usedArticleAmount = response.data.articleAmount;
        console.log(this.tickets.length)
        if(this.tickets.length > 0){
          this.calculate(this.tickets, response.data.articleAmount)
        }
      })
    },
    createTicket(){
      const params = {
        lineId: this.form.lineId
      }

      console.log("lineId", params.lineId)
      console.log("palletlabelId", this.form)
      const data = {}
      const headers = this.request.headers
      const apiUrl = `http://127.0.0.1:8762/production/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets`
      this.axios.post(apiUrl, data, { params, headers }).then((response) => {
        this.ticket = response.data;
        this.getTickets();
      }).catch((error) => {

        this.error = error.response.data.data;

      })
    },
    createInterruption(){
      const data = {}
      const params = {}
      if (this.form.usedArticleAmount != null && this.form.usedArticleAmount != ""){
        console.log("dit kan niet", this.form.usedArticleAmount)
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = this.form.usedArticleAmount;
      }else {
        console.log("raak")
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = this.palletLabel.articleAmount;
      }

      console.log(this.form.usedArticleAmount)
      const headers = this.request.headers
      const apiUrl = `http://127.0.0.1:8762/production/api/v1/farmers/1/palletlabels/${this.tickets[this.tickets.length -1].palletLabel.id}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions`
      this.axios.post(apiUrl, data, { params, headers }).then((response) => {

        this.interruption = response.data;
        this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
        this.fetchTicketsAction(this.form.palletLabelId);
      })
    },
    async closeTicket(){
      console.log(this.tickets)
      await this.closeTicketAction(this.tickets[this.tickets.length -1])
      await this.fetchTicketsAction(this.form.palletLabelId)
      await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
      let isUsed = this.palletLabelIsFullyUsed;
    },
    async closeInterruption(){
      console.log(this.tickets)
      await this.fetchTicketsAction(this.tickets[this.tickets.length -1].palletLabel.id)
      await this.closeInterruptionAction(this.tickets[this.tickets.length -1])

      await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
    }
  },
}
</script>

<style scoped>

</style>