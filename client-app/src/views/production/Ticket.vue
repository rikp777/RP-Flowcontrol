<template>
  <div id="main-content" class="w-full flex-1">
    <div class="flex flex-1 flex-wrap">
      <div class="w-full xl:w-2/3 p-6 xl:max-w-6xl">
        <!--"Container" for the graphs"-->
        <div class="max-w-full lg:max-w-3xl xl:max-w-5xl">
          <router-view />
          <!--Graph Card-->
          <div class="p-3">
            <div class="border-b p-3">
              <h5 class="font-bold text-black">Productie</h5>
            </div>
            <div class="p-5 bg-white">
              <div>
                <div v-if="form.lineId">Lijn nr.: {{form.lineId}}</div>
                <div v-else-if="tickets[0]">Lijn nr.: {{tickets[this.tickets.length -1].line.id}}</div>
              </div>
              <div>
                <div v-if="form.palletLabelId">Pallet label nr.: {{form.palletLabelId}}</div>
                <div v-else-if="palletLabel">Pallet label nr.: {{palletLabel.id}}</div>
              </div>

              <div v-if="palletLabel.article">Artikel: {{palletLabel.article.name}}</div>
              <div v-if="palletLabelIsFullyUsed">Status: Verwerkt</div>
            </div>
          </div>

          <!--/Graph Card-->

          <!--Table Card-->
          <!--                      :headers="['Ticket Nr.', 'Pallet label Nr.', 'Start tijd', 'Eind tijd', 'Verbruikt aantal',-->
          <!--                      'Bijvul aantal', 'Interruptie aantal']"-->
<!--          {{tickets}}-->
          <div class="p-3" v-if="tickets.length > 0">
            <div class="border-b p-3">
              <h5 class="font-bold text-black">Ticket Data</h5>
            </div>
            <div class="p-5 bg-white">
              <t-table


                  :headers="[
                          {value: 'id', text: 'Id'},
                          {value: 'startAt', text: 'Start tijd'},
                          {value: 'endAt', text: 'Eind Tijd'},
                          {value: 'articleAmountUsed', text: 'Verbruikt aantal'},
                          {value: 'refillTrays', text: 'Bijvul aantal'},
                          {value: 'interruptions', text: 'Interruptie aantal'},
                          ]"
                  :data="tickets"
              >
                <template v-slot:row="props">
                  <tr :class="[props.trClass, props.rowIndex % 2 === 0 ? 'bg-gray-100' : '']">
                    <td :class="props.tdClass">{{ props.row.id }}</td>
                    <td :class="props.tdClass">{{ props.row.startAt }}</td>
                    <td :class="[props.tdClass, rowClass(props)]">{{ props.row.endAt }}</td>
                    <td :class="props.tdClass">{{ props.row.articleAmountUsed }}</td>
                    <td :class="props.tdClass">{{ props.row.refillTrays }}</td>
                    <td :class="props.tdClass">
                      <div class="bg-gray-100  rounded px-6">
                        <div class="border-l-4 border-red-400 -ml-6 p-6 flex items-center justify-between">
                          <div class="font-semibold text-gray-800">Total: {{ props.row.interruptions.length }}</div>
                        </div>
                        <div  v-for="interruption in props.row.interruptions" :key="interruption.id">
                          <hr class="-mx-6"/>
                          <div class="flex items-center justify-between py-4">
                            <div class="flex-1 pl-2">
                              <div class="text-gray-700 font-semibold">
                                {{interruption.interruptionReason.name}}
                              </div>
                            </div>
                            <div class="text-red-400" v-if="interruption.endAt != null">{{
                                Math.floor((Math.abs(
                                    new Date(interruption.endAt) - new Date(interruption.startAt)
                                )/1000)/60)
                              }} min</div>
                          </div>
                        </div>
                      </div>
                    </td>
                  </tr>
                </template>
              </t-table>
            </div>
          </div>

          <div class="p-3" v-if="tickets.length > 0">
            <div class="border-b p-3">
              <h5 class="font-bold text-black">Interruptie data current ticket: {{tickets[this.tickets.length -1].id}}</h5>
            </div>
            <div class="p-5 bg-white">
              <t-table
                  :headers="[
                          'Interruptie Nr.',
                          'Stop reden',
                          'Start tijd',
                          'Eind tijd',
                       ]"
                  :data="interruptions"
              >
                <template  slot="row" slot-scope="props">
                  <tr :class="[props.trClass, props.rowIndex % 2 === 0 ? 'bg-gray-100' : '']">
                    <td :class="props.tdClass">{{ props.row.id }}</td>
                    <td :class="props.tdClass">{{ props.row.interruptionReason.name }}</td>
                    <td :class="props.tdClass">{{ props.row.startAt }}</td>
                    <td :class="[props.tdClass, rowClass(props)]">{{ props.row.endAt }}</td>
                  </tr>
                </template>

              </t-table>
            </div>
          </div>

        </div>
      </div>

      <div
          class="w-full xl:w-1/3 p-6 xl:max-w-4xl border-l-1 border-gray-300"
      >
        <form v-on:submit.prevent>
          <div class="shadow sm:rounded-md">
            <div class="px-4 py-5 bg-white space-y-6 sm:p-6 ">
              <div v-if="palletLabelIsFullyUsed">Palletlabel is op! scan aub een ander id</div>
              <!-- First block-->
              <div class="grid grid-cols-6 gap-6">
                <div class="col-span-6 sm:col-span-3">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Pallet label Nr.:
                  </label>
                  <div class="mt-1">
                    <t-input
                        type="number"
                        name="pallet_label_id"
                        id="pallet_label_id"
                        v-model="form.palletLabelId"
                        @input="getTickets"
                    />
                  </div>
                  <p class="mt-2 text-sm text-gray-500">
                    Kan zowel gescand worden als met de hand ingevoerd worden.
                  </p>
                </div>
                <div class="col-span-6 sm:col-span-3" v-if="!palletLabelIsFullyUsed">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Lijn Nr.:
                  </label>
                  <div class="mt-1" >
                    <t-select
                        placeholder="Select an option"
                        :options="[1,2,3]"
                        v-model="form.lineId"
                    ></t-select>
                  </div>
                  <p class="mt-2 text-sm text-gray-500">
                    Kan zowel gescand worden als met de hand ingevoerd worden.
                  </p>
                </div>
                <div class="col-span-3  " v-if="!palletLabelIsFullyUsed && canCreateNewTicket">
                  <div class="" >
                    <t-button @click="createTicket" v-bind:class="{
                          'text-opacity-25 w-full h-full cursor-not-allowed transition duration-100 ease-in-out':
                          form.lineId == null || palletLabel == null }">Maak een
                      nieuw ticket'</t-button>
                  </div>
                </div>
                <div class="col-span-3" v-if="!canCreateNewTicket && lastTicketIsOpen">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Wanneer ticket is afgerond
                  </label>
                  <div class="mt-1">
                    <t-button @click="closeTicket">Sluit ticket</t-button>
                  </div>
                </div>
              </div>

              <div class="grid grid-cols-6 gap-6" v-if="!canCreateNewTicket && lastTicketIsOpen">
                <div class="col-span-6 sm:col-span-3">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Bijvul aantal: {{form.refillAmount}}
                  </label>
                  <div class="mt-1" >
                    <t-input type="number" placeholder="example: (50)" v-model="form.refillAmount"/>
                  </div>
                </div>
              </div>

              <!-- Second block-->
              <!--                  {{lastTicketIsOpen}}-->
              <div class="grid grid-cols-6 gap-6" v-if="!canCreateNewTicket && !isInterruptionOpen && lastTicketIsOpen" >
                <div class="col-span-6 sm:col-span-3">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Interruptie reden:
                  </label>
                  <div class="mt-1" >
                    <t-rich-select
                        v-model="form.interruptionReasonId"
                        :options="interruptionReasonsOptions"
                    >
                    </t-rich-select>
                  </div>
                </div>
                <div class="col-span-6 sm:col-span-3" v-if="processStops">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Artikelen verbruikt: {{form.usedArticleAmount}}
                  </label>
                  <div class="mt-1" >
                    <t-input type="number" placeholder="example: (50)" v-model="form.usedArticleAmount"/>
                  </div>
                </div>
                <div class="col-span-6 sm:col-span-3 ">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Waneer er iets gebeurt op de lijn
                  </label>
                  <div class="">
                    <t-button @click="createInterruption">Maak een interruptie</t-button>
                  </div>
                </div>
              </div>



              <div class="grid grid-cols-6 gap-6" v-if="isInterruptionOpen">
                <div class="col-span-6 sm:col-span-3 ">
                  <label for="pallet_label_id" class="block text-sm font-medium text-gray-700">
                    Om interruptie te sluiten
                  </label>
                  <div class="">
                    <t-button @click="closeInterruption">Sluit openstaande interruptie</t-button>
                  </div>
                </div>
              </div>


            </div>
            Has been fully used: {{palletLabelIsFullyUsed}}
            <br><br>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>

import {mapActions, mapGetters} from "vuex";

const apiBaseUrl = "http://localhost:7073/api/v1/farmers/1";
export default {
  data() {
    return {
      article: null,
      // palletLabel: null,
      // ticket: [],
      // tickets: [],
      // interruption: [],
      // interruptions: [],
      stillRemaining: 0,
      lastTicketIsOpen: true,
      form: {
        palletLabelId: null,
        lineId: null,
        interruptionReasonId: null,
        usedArticleAmount: null,
        refillAmount: null
      },
      interruptionReasonsOptions: [
        {
          value: 1,
          label: "Break"
        },
        {
          value: 2,
          label: "New foil"
        },
        {
          value: 3,
          label: "Sticker change"
        },
        {
          value: 4,
          label: "Product swap"
        },
        {
          value: 5,
          label: "Product disapproval"
        },
        {
          value: 6,
          label: "Production line error"
        }
      ],
      request: {
        headers: {
          "Content-Type": "application/json",
        }
      },
    }
  },
  watch: {
    tickets: {
      handler: function(val, oldVal) {
        console.log('val ', val)
        console.log('val', val.length)
        if(val != null && val.length > 0 && val != ""){
          if(val[val.length -1].endAt == null){
            this.lastTicketIsOpen = true
          }else{
            this.lastTicketIsOpen = false
          }
        }
        // console.log("watch id palletlabel is fully used")
        // console.log(val.length)
        // if(val.length > 0 && this.palletLabel) {
        //   const hasEnd = val[val.length -1].endAt != null
        //   let usedAmount = 0
        //   val.forEach(ticket => {
        //     usedAmount = usedAmount + ticket.articleAmountUsed
        //   })
        //   console.log("used amount ", usedAmount)
        //   console.log("has end ", this.palletLabel.articleAmount <= usedAmount && hasEnd)
        //   if(this.palletLabel.articleAmount <= usedAmount && hasEnd){
        //     this.palletLabelIsFullyUsed = true;
        //     console.log(this.palletLabelIsFullyUsed)
        //   }
        // }
        // this.palletLabelIsFullyUsed = false;
      }
    }
  },
  computed: {
    ...mapGetters("ticket", {
      tickets: "getTickets",
      interruptions: "getInterruptions",
      palletLabel: "getPalletLabel"
    }),
    // lastTicketIsOpen: function () {
    //   if(this.canCreateNewTicket){
    //     console.log(this.tickets)
    //     return null
    //   }
    //   return false;
    // },
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
    // canCreateNewTicket: function () {
    //   if(this.tickets.length == 0) return true;
    // },
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
      fetchPalletLabelAction: "fetchPalletLabel",
      getAllTicketsBelongingToPalletLabelId: "getAllTicketsBelongingToPalletLabelId",
      fetchInterruptionssAction: "fetchInterruptions",
      closeTicketAction: "closeTicket",
      closeInterruptionAction: "closeInterruption",
      purgeDataAction: "purgeData"
    }),
    test(test){
      return test
    },
    rowClass(item){
      if(item.row.endAt == null){
        return 'bg-red-100 text-sm text-center'
      }
    },
    tableRowClassName(row){
      if(row.columnIndex == "endAt" || row.columnIndex == "refillTrays" && row.text == null){
        return 'bg-red-100 text-sm text-center';
      } else {
        return 'success-row';
      }
    },
    calculate(tickets, palletLabelArticleAmount ){

      let leftOver = palletLabelArticleAmount;
      this.tickets.forEach(ticket => {
        console.log(ticket.articleAmountUsed)
        leftOver = leftOver - ticket.articleAmountUsed
      })
      this.stillRemaining = leftOver;
    },
    getInterruptions(){
      this.fetchInterruptionssAction(this.tickets[this.tickets.length -1].id)
      // console.log("ticket lenght", this.tickets.length)
      // if(this.tickets.length > 0){
      //   const apiUrl =
      //       `http://127.0.0.1:8762/production/api/v1/farmers/1/palletlabels/${palletLabelId}/tickets/${ticketId}/interruptions`
      //   this.axios.get(apiUrl).then((response) => {
      //     this.interruptions = response.data._embedded.interruptions;
      //   })
      // }
    },
    async getTickets(){
      await this.purgeDataAction();
      await this.getAllTicketsBelongingToPalletLabelId(this.form.palletLabelId)
      await this.fetchPalletLabelAction(this.form.palletLabelId)
      await this.fetchInterruptionssAction(this.tickets[this.tickets.length -1])

      // this.getPalletLabel(this.form.palletLabelId);
      // this.error = null;
      // const apiUrl = `http://127.0.0.1:8762/production/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets`
      // this.axios.get(apiUrl).then((response) => {
      //   console.log("tickets", response)
      //   const isDataAvailable = response.data && response.data.length;
      //
      //   console.log("isDataAvailable", isDataAvailable)
      //
      //   if(isDataAvailable){
      //     this.tickets = response.data._embedded.tickets;
      //     this.form.lineId = this.tickets[0].line.id
      //     this.getPalletLabel(this.tickets[0].palletLabel.id);
      //     this.getInterruptions(this.tickets[0].palletLabel.id, this.tickets[this.tickets.length -1].id)
      //   }else{
      //     this.article = null;
      //     this.stillRemaining = null;
      //     this.interruptions = null;
      //   }
      // })
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
  }
}
</script>
