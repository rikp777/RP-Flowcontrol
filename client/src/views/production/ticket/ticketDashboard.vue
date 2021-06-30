<template>
  <div>
    <h1>Ticket Dashboard</h1>
    <div>
      <div class="card">
        <div class="card-content">
          <div class="media">
            <div class="media-content">
              <p class="title is-4">Create ticket</p>
              <p class="subtitle is-6">Make a ticket, for processing the specified pallet. <br>
                <small>
                  Please note that you are responsible for all tickets you open. Please note that you are responsible for all tickets you open.
                  Always close tickets when they are used up or when processing is interrupted.

                </small>
              </p>

            </div>
          </div>

          <div class="content">
            <section class="section">
              <div class="columns">
                <div class="column is-two-thirds">
                  <section>
                    <b-loading :is-full-page="false" v-model="isLoading" :can-cancel="false"></b-loading>
                    <b-notification :closable="true" class="is-primary" v-if="palletLabel.article">
                      <b-progress :value="(usedAmount / this.palletLabel.articleAmount) * 100" size="is-medium" show-value type="is-success">
                        used {{ usedAmount }} out of {{ this.palletLabel.articleAmount }}
                      </b-progress>
                      <table class="table is-fullwidth is-transparent">
                        <tbody>

                        <tr v-if="form.palletLabelId">
                          <td width="2%"><r-icon icon="pallet"></r-icon></td>
                          <td width="30%">Pallet label nr.:</td>
                          <td>
                            <span v-if="form.palletLabelId">{{form.palletLabelId}}</span>
                            <span v-else-if="palletLabel">{{palletLabel.id}}</span>
                          </td>
                          <td width="2%"><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                        <tr v-if="form.lineId || tickets[0]">
                          <td width="2%"><r-icon icon="people-carry"></r-icon></td>
                          <td width="30%">Line nr.:</td>
                          <td>
                            <template v-if="selectedLine">
                              <span v-if="selectedLine.name">{{selectedLine.name}}</span>
                              <span v-if="selectedLine.description"><br><small>{{selectedLine.description}}</small></span>
                            </template>

<!--                            <span v-else-if="tickets[0]">{{tickets[this.tickets.length -1].line.id}}</span>-->
                          </td>
                          <td width="2%"><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                        <tr v-if="palletLabel.article">
                          <td width="2%"><r-icon icon="seedling"></r-icon></td>
                          <td width="30%">Article:</td>
                          <td>{{palletLabel.article.fullName}}</td>
                          <td width="2%"><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>

                        <tr v-if="itemsToStillProcess !== 0">
                          <td width="2%"><r-icon icon="sort-numeric-down-alt"></r-icon></td>
                          <td width="30%">Items to still process:</td>
                          <td>
                            <span v-if="itemsToStillProcess < palletLabel.articleAmount">
                              {{itemsToStillProcess}} of {{palletLabel.articleAmount}}
                            </span>
                            <span >{{itemsToStillProcess}}</span>
                          </td>
                          <td width="2%"></td>
                        </tr>

                        <tr v-if="itemsToStillProcess === 0">
                          <td width="2%"><r-icon icon="truck-loading"></r-icon></td>
                          <td width="30%">Status:</td>
                          <td>
                            <div class="icon-text">
                                 Processed
                              <span v-if="!tickets[tickets.length -1].endAt" class="icon has-text-warning is-size-7">
                                <r-icon icon="check-circle" />
                              </span>
                              <span v-else class="icon has-text-success is-size-7">
                                <r-icon icon="check-circle" />
                              </span>
                              <small v-if="!tickets[tickets.length -1].endAt">with a proviso!</small>
                            </div>
                          </td>
                          <td width="2%"><a class="button is-small is-primary" href="#">Check</a></td>
                        </tr>
                        </tbody>
                      </table>
                    </b-notification>
                  </section>
                </div>
                <div class="column">
                  <form v-on:submit.prevent>
                    <section v-if="form.palletLabelId">
                      <p class="is-size-7 has-text-centered"><small>Pallet label meta data:</small></p>
                      <b-field grouped group-multiline>
                        <div class="control">
                          <b-tooltip label="Pallet label notes"
                                     position="is-top"
                                     type="is-success">
                            <b-tag
                                v-if="palletLabel.note"
                                type="is-primary"
                                attached>
                              {{palletLabel.note}}
                            </b-tag>
                          </b-tooltip>
                        </div>
                        <div class="control">
                          <b-tooltip label="Crop date"
                                     position="is-top"
                                     type="is-success">
                            <b-tag
                                v-if="palletLabel.cropDate"
                                type="is-primary"
                                attached>
                              {{palletLabel.cropDate}}
                            </b-tag>
                          </b-tooltip>
                        </div>
                        <div class="control">
                          <b-tooltip label="Article amount"
                                     position="is-top"
                                     type="is-success">
                            <b-tag
                                v-if="palletLabel.articleAmount"
                                type="is-primary"
                                attached>
                              {{palletLabel.articleAmount}}
                            </b-tag>
                          </b-tooltip>
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
                      <b-field label="Lines" v-if="lines && tickets.length === 0">
                        <b-select placeholder="Select a character" icon="people-carry" v-model="form.lineId">
                          <optgroup label="Lines">
                            <template v-for="(line, index) in lines ">
                              <option :value="line.id" :key="index">
                                {{line.name}}
                              </option>
                            </template>
                          </optgroup>
                        </b-select>
                      </b-field>
                      <b-field
                          v-if="canCreateNewTicket"
                      >

                        <p class="control">
                          <b-button type="is-primary" @click="actionCreateTicket()">Create ticket</b-button>
                        </p>
                      </b-field>
                      <b-field
                          v-if="!canCreateNewTicket && isLastTicketIsOpen"
                      >
                        <p class="control">
                          <b-button @click="actionCloseTicket()">Close ticket</b-button>
                        </p>
                      </b-field>
                      <b-field
                          label="Refill amount"
                          v-if="!canCreateNewTicket && isLastTicketIsOpen"
                      >
                          <b-input
                              placeholder="example: (50)"
                              :lazy=false
                              v-model="form.refillAmount"
                              type="number"
                              icon-pack="fas"
                              icon="seedling">
                          </b-input>
                      </b-field>

                      <div v-if="!canCreateNewTicket && isLastTicketIsOpen">


                      <hr >
                        <b-field label="Interruption" v-if="!isLastInterruptionOpen">
                          <b-select placeholder="Select a character" icon="pause" v-model="form.interruptionReasonId">
                            <optgroup label="Normal">
                              <template v-for="(reason, index) in interruptionReasons">
                                <option :value="reason.value" v-if="reason.stopProcess == false" :key="index">
                                  {{reason.label}}
                                </option>
                              </template>
                            </optgroup>

                            <optgroup label="Automatically close ticket">
                              <template v-for="(reason, index) in interruptionReasons">
                                <option :value="reason.value" v-if="reason.stopProcess == true" :key="index">
                                  {{reason.label}}
                                </option>
                              </template>
                            </optgroup>
                          </b-select>
                        </b-field>

                        <b-field
                            label="Used/processed article amount"
                            v-if="processStops"
                        >
                          <b-input
                              placeholder="example: (50)"
                              :lazy=false
                              v-model="form.usedArticleAmount"
                              type="number"
                              icon-pack="fas"
                              icon="seedling">
                          </b-input>
                        </b-field>

                        <b-field v-if="form.interruptionReasonId">
                          <p class="control">
                            <b-button type="is-primary" @click="actionCreateInterruption()">Make interruption</b-button>
                          </p>
                        </b-field>
                        <b-field v-if="isLastInterruptionOpen">
                          <p class="control">
                            <b-button @click="actionCloseInterruption()">Close interruption</b-button>
                          </p>
                        </b-field>
                      </div>

                    </section>

                  </form>
                </div>
              </div>
            </section>
            <section class="section">
              <b-loading :is-full-page="false" v-model="isLoading" :can-cancel="false"></b-loading>
              <div>
                <h4 >Ticket Data</h4>
              </div>
<!--              <button v-if="!isLoading && tickets.length === 0">Get data</button>-->
              <div v-if="hasError">Error</div>
              <div v-if="!hasError && tickets.length === 0 && form.palletLabelId">No tickets made for given pallet label {{form.palletLabelId}}</div>
              <b-table
                  detailed
                  detail-key="id"
                  detail-transition="fade"
                  @details-open="(row) => $buefy.toast.open(`Expanded ticket`)"
                  :show-detail-icon="true"
                  v-if="!hasError && tickets.length > 0"
                  :data="tickets"
                  :paginated="false"
                  :loading="isLoading"
                  default-sort="user.first_name"
                  aria-next-label="Next page"
                  aria-previous-label="Previous page"
                  aria-page-label="Page"
                  aria-current-label="Current page">


                <b-table-column field="user.startAt" label="Start date" sortable v-slot="props">
                  <div >
                    {{ props.row.startAt | moment("hh:mm:ss")}} | {{ props.row.startAt | moment("dddd")}}<br />
                    {{ props.row.startAt | moment("DD-MM-YYYY")}}
                  </div>

                </b-table-column>
                <b-table-column field="user.endAt" label="End date" sortable v-slot="props">
                  <span class="tag is-danger" v-if="props.row.endAt == null">No endtime yet</span>
                  <div v-else>
                    {{ props.row.endAt | moment("hh:mm:ss")}} | {{ props.row.endAt | moment("dddd")}}<br />
                    {{ props.row.endAt | moment("DD-MM-YYYY")}}
                  </div>
                </b-table-column>
                <b-table-column field="user.articleAmountUsed" label="Article amount used" sortable v-slot="props" >
                  <div class="has-text-centered" v-if="props.row.articleAmountUsed">{{ props.row.articleAmountUsed }}</div>
                </b-table-column>
                <b-table-column field="user.refillTrays" label="Refill trays" sortable v-slot="props">
                  <div v-if="props.row.refillTrays">{{ props.row.refillTrays }}</div>
                </b-table-column>

                <template slot="detail" slot-scope="props" >
                  <div v-if="props.row.interruptions.length > 0">
                    <table>
                      <thead>
                      <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>StartAt</th>
                        <th>EndAt</th>
                        <th>Time</th>
                      </tr>
                      </thead>
                      <tbody>
                      <tr v-for="(interruption, index) in props.row.interruptions" :key="index">

                        <td>{{ interruption.interruptionReason.name }}</td>
                        <td>{{ interruption.interruptionReason.description }}</td>
                        <td>
                          <div >
                            {{ interruption.startAt | moment("hh:mm:ss")}}<br />
                            {{ interruption.startAt | moment("DD-MM-YYYY")}}
                          </div>
                        <td>
                          <span class="tag is-danger" v-if="interruption.endAt == null">No endtime yet</span>
                          <div v-else>
                            {{ interruption.endAt | moment("hh:mm:ss")}}<br />
                            {{ interruption.endAt | moment("DD-MM-YYYY")}}
                          </div>
                        </td>
                        <td>
                          <div class="text-red-400" v-if="interruption.endAt != null">{{
                              Math.floor((Math.abs(
                                  new Date(interruption.endAt) - new Date(interruption.startAt)
                              )/1000)/60)
                            }} </div>
                          <div v-else>0</div>
                          min
                          <r-icon icon="clock"></r-icon>
                        </td>
                      </tr>
                      </tbody>
                    </table>
                  </div>

                  <b-notification
                      v-else
                      :closable="false"
                      type="is-success"
                      has-icon
                      icon="laugh-wink"
                      role="alert"
                      aria-close-label="Close notification">
                    No interruptions for this ticket, good job!
                  </b-notification>

                </template>
              </b-table>
            </section>
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
      this.palletLabelId = null;
      this.lineId = null;
      this.interruptionReasonId = null;
      this.usedArticleAmount = null;
      this.refillAmount = null;
      this.purgeData()
      this.actionGetPalletLabelById(newVal)
    },
  },
  computed: {
    ...mapGetters("ticket", {
      interruptionReasons: 'getInterruptionReasons',
      itemsToStillProcess: 'getItemsToStillProcess',
      tickets: "getTickets",
      interruptions: "getInterruptions",
      palletLabel: "getPalletLabel",
      palletLabelId: "getPalletLabelId",
      lines: "getLines",
      hasError: "getHasError",
      isLoading: "getIsLoading",
    }),
    selectedLine(){
      if(this.tickets.length !== 0){
        if(this.tickets[this.tickets.length -1].line){
          console.log(this.tickets[this.tickets.length -1].line)
          return this.tickets[this.tickets.length -1].line
        }
      }

      if(this.form.lineId){
        return this.lines.filter(line => line.id === this.form.lineId)[0]
      }
      return null
    },
    isLastTicketIsOpen(){
      if(this.tickets != null && this.tickets.length > 0 && this.tickets != ""){
        if(this.tickets[this.tickets.length -1].endAt == null){
          return true
        }else{
          return false
        }
      }
      return false
    },

    canCreateNewTicket(){
      if(this.palletLabel != null && this.form.lineId != null && !(this.itemsToStillProcess <= 0)) return true
      return false;
    },

    palletLabelIsFullyUsed() {
      //console.log(this.tickets)
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
    usedAmount(){
      let usedAmount = 0
      this.tickets.forEach(ticket => {
        usedAmount = usedAmount + ticket.articleAmountUsed
      })
      return usedAmount
    },

    processStops: function () {
      return  this.form.interruptionReasonId == 4 ||
          this.form.interruptionReasonId == 5 || this.form.interruptionReasonId == 6
    },

    isLastInterruptionOpen: function () {
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
      createTicket: "createTicket",
      createInterruption: "createInterruption",
      getAllLines: "getAllProductionLines",
      getPalletLabelById: "getPalletLabelById",
      getAllTicketsBelongingToPalletLabelId: "getAllTicketsBelongingToPalletLabelId",
      getAllInterruptionsByTicket: "getAllInterruptionsByTicket",
      closeTicket: "closeTicket",
      closeInterruptionAction: "closeInterruption",
      purgeData: "purgeData"
    }),

    async actionCreateTicket(){
      await this.createTicket(this.form.lineId)
      await this.getTickets();
    },
    async actionCreateInterruption(){
      const params = {}
      if (this.form.usedArticleAmount != null && this.form.usedArticleAmount != ""){
        //console.log("dit kan niet", this.form.usedArticleAmount)
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = this.form.usedArticleAmount;
      }else {
        //console.log("raak")
        params.interruptionReasonId = this.form.interruptionReasonId
        params.usedArticleAmount = this.palletLabel.articleAmount;
      }
      console.log(params)
      await this.createInterruption(params)
      await this.getTickets();
    },

    getInterruptions(){
      this.getAllInterruptionsByTicket(this.tickets[this.tickets.length -1].id)
    },
    async getTickets(){
      await this.purgeData();
      await this.getPalletLabelById(this.form.palletLabelId)
      await this.getAllTicketsBelongingToPalletLabelId(this.palletLabelId)
      // await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
    },
    async actionGetPalletLabelById(palletLabelId){
      await this.getPalletLabelById(palletLabelId)
      if(this.palletLabel){
        await this.getTickets()
      }
    },
    out(){
      const data = {}
      const params = {}
      if (this.form.usedArticleAmount != null && this.form.usedArticleAmount != ""){
        //console.log("dit kan niet", this.form.usedArticleAmount)
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = this.form.usedArticleAmount;
      }else {
        //console.log("raak")
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = this.palletLabel.articleAmount;
      }
      console.log(params)

      //console.log(this.form.usedArticleAmount)
      const headers = this.request.headers
      const apiUrl = `http://127.0.0.1:8762/production/api/v1/farmers/1/palletlabels/${this.tickets[this.tickets.length -1].palletLabel.id}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions`
      this.axios.post(apiUrl, data, { params, headers }).then((response) => {

        this.interruption = response.data;
        this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
        this.fetchTicketsAction(this.form.palletLabelId);
      })
    },
    async actionCloseTicket(){
      await this.closeTicket(this.tickets[this.tickets.length -1])
      await this.getTickets();
      // await this.fetchTicketsAction(this.form.palletLabelId)
      // await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
      // let isUsed = this.palletLabelIsFullyUsed;
    },
    async actionCloseInterruption(){
      //console.log(this.tickets)
      await this.fetchTicketsAction(this.tickets[this.tickets.length -1].palletLabel.id)
      await this.closeInterruptionAction(this.tickets[this.tickets.length -1])

      await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])
    }
  },
  async mounted() {
    this.purgeData();
    await this.getAllLines()
  }
}
</script>

<style scoped>

</style>