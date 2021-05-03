<template>
  <body class="flex h-screen bg-gray-100 font-sans">

    <!-- Side bar-->
    <div
      id="sidebar"
      class="h-screen w-16 menu bg-white text-white px-4 flex items-center nunito static fixed shadow"
    >
      <ul class="list-reset ">
        <li class="my-2 md:my-0">
          <a
            href="#"
            class="block py-1 md:py-3 pl-1 align-middle text-gray-600 no-underline hover:text-indigo-400"
          >
            <i class="fas fa-home fa-fw mr-3"></i
            ><span class="w-full inline-block pb-1 md:pb-0 text-sm">Home</span>
          </a>
        </li>
        <li class="my-2 md:my-0 ">
          <a
            href="#"
            class="block py-1 md:py-3 pl-1 align-middle text-gray-600 no-underline hover:text-indigo-400"
          >
            <i class="fas fa-tasks fa-fw mr-3"></i
            ><span class="w-full inline-block pb-1 md:pb-0 text-sm">Tasks</span>
          </a>
        </li>
        <li class="my-2 md:my-0">
          <a
            href="#"
            class="block py-1 md:py-3 pl-1 align-middle text-gray-600 no-underline hover:text-indigo-400"
          >
            <i class="fa fa-envelope fa-fw mr-3"></i
            ><span class="w-full inline-block pb-1 md:pb-0 text-sm"
              >Messages</span
            >
          </a>
        </li>
        <li class="my-2 md:my-0">
          <a
            href="#"
            class="block py-1 md:py-3 pl-1 align-middle text-gray-600 no-underline hover:text-indigo-400"
          >
            <i class="fas fa-chart-area fa-fw mr-3 text-indigo-400"></i
            ><span class="w-full inline-block pb-1 md:pb-0 text-sm"
              >Analytics</span
            >
          </a>
        </li>
        <li class="my-2 md:my-0">
          <a
            href="#"
            class="block py-1 md:py-3 pl-1 align-middle text-gray-600 no-underline hover:text-indigo-400"
          >
            <i class="fa fa-wallet fa-fw mr-3"></i
            ><span class="w-full inline-block pb-1 md:pb-0 text-sm"
              >Payments</span
            >
          </a>
        </li>
      </ul>
    </div>
    <div class="flex flex-row flex-wrap flex-1 flex-grow content-start pl-16">
      <div class="h-40 lg:h-20 w-full flex flex-wrap">
        <nav
          id="header"
          class="bg-gray-200 w-full lg:max-w-sm flex items-center border-b-1 border-gray-300 order-2 lg:order-1"
        >
          <div class="px-2 w-full">
            <select
              name=""
              class="bg-gray-300 border-2 border-gray-200 rounded-full w-full py-3 px-4 text-gray-500 font-bold leading-tight focus:outline-none focus:bg-white focus:shadow-md"
              id="form-field2"
            >
              <option value="Default">default</option>
              <option value="A">report a</option>
              <option value="B">report b</option>
              <option value="C">report c</option>
            </select>
          </div>
        </nav>
        <nav
          id="header1"
          class="bg-gray-100 w-auto flex-1 border-b-1 border-gray-300 order-1 lg:order-2"
        >
          <div class="flex h-full justify-between items-center">
            <!--Search-->
            <div class="relative w-full max-w-3xl px-6">
              <div
                class="absolute h-10 mt-1 left-0 top-0 flex items-center pl-10"
              >
                <svg
                  class="h-4 w-4 fill-current text-gray-600"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path
                    d="M12.9 14.32a8 8 0 1 1 1.41-1.41l5.35 5.33-1.42 1.42-5.33-5.34zM8 14A6 6 0 1 0 8 2a6 6 0 0 0 0 12z"
                  ></path>
                </svg>
              </div>

              <input
                id="search-toggle"
                type="search"
                placeholder="search"
                class="block w-full bg-gray-200 focus:outline-none focus:bg-white focus:shadow-md text-gray-700 font-bold rounded-full pl-12 pr-4 py-3"
                onkeyup="updateSearchResults(this.value);"
              />
            </div>
            <!-- / Search-->

            <!--Menu-->

            <div class="flex relative inline-block pr-6">
              <div class="relative text-sm">
                <button
                  id="userButton"
                  class="flex items-center focus:outline-none mr-3"
                >
                  <img
                    class="w-8 h-8 rounded-full mr-4"
                    src="http://i.pravatar.cc/300"
                    alt="Avatar of User"
                  />
                  <span class="hidden md:inline-block">Hi, User </span>
                  <svg
                    class="pl-2 h-2"
                    version="1.1"
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 129 129"
                    xmlns:xlink="http://www.w3.org/1999/xlink"
                    enable-background="new 0 0 129 129"
                  >
                    <g>
                      <path
                        d="m121.3,34.6c-1.6-1.6-4.2-1.6-5.8,0l-51,51.1-51.1-51.1c-1.6-1.6-4.2-1.6-5.8,0-1.6,1.6-1.6,4.2 0,5.8l53.9,53.9c0.8,0.8 1.8,1.2 2.9,1.2 1,0 2.1-0.4 2.9-1.2l53.9-53.9c1.7-1.6 1.7-4.2 0.1-5.8z"
                      ></path>
                    </g>
                  </svg>
                </button>
                <div
                  id="userMenu"
                  class="bg-white nunito rounded shadow-md mt-2 absolute mt-12 top-0 right-0 min-w-full overflow-auto z-30 invisible"
                >
                  <ul class="list-reset">
                    <li>
                      <a
                        href="#"
                        class="px-4 py-2 block text-gray-900 hover:bg-indigo-400 hover:text-white no-underline hover:no-underline"
                        >My account</a
                      >
                    </li>
                    <li>
                      <a
                        href="#"
                        class="px-4 py-2 block text-gray-900 hover:bg-indigo-400 hover:text-white no-underline hover:no-underline"
                        >Notifications</a
                      >
                    </li>
                    <li>
                      <hr class="border-t mx-2 border-gray-400" />
                    </li>
                    <li>
                      <a
                        href="#"
                        class="px-4 py-2 block text-gray-900 hover:bg-indigo-400 hover:text-white no-underline hover:no-underline"
                        >Logout</a
                      >
                    </li>
                  </ul>
                </div>
              </div>
            </div>

            <!-- / Menu -->
          </div>
        </nav>
      </div>


      <!--Dash Content -->
      <div
        id="dash-content"
        class="bg-gray-200 py-6 lg:py-0 w-full lg:max-w-sm flex flex-wrap content-start"
      >
        <div class="w-1/2 lg:w-full">
          <div
            class="border-2 border-gray-400 border-dashed hover:border-transparent hover:bg-white hover:shadow-xl rounded p-6 m-2 md:mx-10 md:my-6"
          >
            <div class="flex flex-col items-center">
              <div class="flex-1">
                <h3 class="font-bold text-3xl">
                  {{ stillRemaining }}
                  <span class="text-green-500"
                    ><i class="fas fa-caret-up"></i
                  ></span>
                </h3>
                <h5 class="font-bold text-gray-500">Nog te gebruiken</h5>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!--Graph Content -->
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
                  <div v-if="form.lineId">Lijn nr.: {{form.lineId}}</div>
                  <div v-if="form.palletLabelId">Pallet label nr.: {{form.palletLabelId}}</div>
                  <div v-if="article">Artikel: {{article}}</div>
                </div>
              </div>

              <!--/Graph Card-->

              <!--Table Card-->
              <!--                      :headers="['Ticket Nr.', 'Pallet label Nr.', 'Start tijd', 'Eind tijd', 'Verbruikt aantal',-->
              <!--                      'Bijvul aantal', 'Interruptie aantal']"-->
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
                  <h5 class="font-bold text-black">Interruptie data</h5>
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
                  <div v-if="stillRemaining ===0">Palletlabel is op!</div>
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
                    <div class="col-span-6 sm:col-span-3" v-if="canCreateNewTicket || !lastTicketIsOpen">
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
                    <div class="col-span-3  " v-if="canCreateNewTicket || !lastTicketIsOpen">
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

                <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
                  <t-alert variant="error" show>
                    Afgehandeld
                  </t-alert>
                </div>
              </div>
            </form>
          </div>
        </div>
        {{tickets}}
        {{palletLabel}}
        <br>
        <br>
        {{interruptions}}
        {{palletLabel}}
      </div>
    </div>
  </body>
</template>
<script>

const apiBaseUrl = "http://localhost:7073/api/v1/farmers/1";
  export default {
    data() {
      return {
        article: null,
        palletLabel: null,
        ticket: [],
        tickets: [],
        interruption: [],
        interruptions: [],
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
        }
      }
    },
    computed: {
      // lastTicketIsOpen: function () {
      //   if(this.canCreateNewTicket){
      //     console.log(this.tickets)
      //     return null
      //   }
      //   return false;
      // },
      processStops: function () {
        return  this.form.interruptionReasonId == 4 ||
            this.form.interruptionReasonId == 5 || this.form.interruptionReasonId == 6
      },
      canCreateNewTicket: function () {
        if(this.tickets.length == 0) return true;
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
      getInterruptions(palletLabelId, ticketId){
        if(this.tickets.length > 0){
          const apiUrl =
              `http://localhost:7073/api/v1/farmers/1/palletlabels/${palletLabelId}/tickets/${ticketId}/interruptions`
          this.axios.get(apiUrl).then((response) => {
            this.interruptions = response.data;
          })
        }
      },
      getTickets(){
        this.getPalletLabel(this.form.palletLabelId);
        this.error = null;
        const apiUrl = `http://localhost:7073/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets`
        this.axios.get(apiUrl).then((response) => {
          this.tickets = response.data;
          if(response.data.length > 0){
            this.form.lineId = this.tickets[0].lineId
            this.getPalletLabel(this.tickets[0].palletLabelId);
            this.getInterruptions(this.tickets[0].palletLabelId, this.tickets[this.tickets.length -1].id)
          }else{
            this.article = null;
            this.stillRemaining = null;
            this.interruptions = null;
          }
        })
      },
      getPalletLabel(palletLabelId){
        this.error = null;
        const apiUrl = `http://localhost:7072/api/v1/farmers/1/palletlabels/${palletLabelId}`
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
        const data = {}
        const headers = this.request.headers
        const apiUrl = `http://localhost:7073/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets`
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
        if (this.form.usedArticleAmount != null || this.form.usedArticleAmount != ""){

          params.interruptionReasonId = this.form.interruptionReasonId;
          params.usedArticleAmount = this.form.usedArticleAmount;
        }else {

          params.interruptionReasonId = this.form.interruptionReasonId;
          params.usedArticleAmount = this.palletLabel.usedArticleAmount;
        }

        const headers = this.request.headers
        const apiUrl = `http://localhost:7073/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions`
        this.axios.post(apiUrl, data, { params, headers }).then((response) => {

          this.interruption = response.data;
          this.getTickets();
        })
      },
      closeTicket(){
        const data = {}
        const headers = this.request.headers
        const apiUrl = `http://localhost:7073/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}`
        this.axios.post(apiUrl, data, { headers }).then((response) => {
          console.log(response.data)
          this.ticket = response.data;
          this.getTickets();
        })
      },
      closeInterruption(){
        const data = {}
        const headers = this.request.headers
        const apiUrl =
            `http://localhost:7073/api/v1/farmers/1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions/${this.interruptions[this.interruptions.length -1].id}`
        this.axios.post(apiUrl, data, {  headers }).then((response) => {

          this.interruption = response.data;
          this.getTickets();
        })
      }
    }
  }
</script>
<style lang="scss">
.nunito {
  font-family: "nunito", font-sans;
}

.border-b-1 {
  border-bottom-width: 1px;
}

.border-l-1 {
  border-left-width: 1px;
}

hover\:border-none:hover {
  border-style: none;
}

#sidebar {
  transition: ease-in-out all 0.3s;
  z-index: 9999;
}

#sidebar span {
  opacity: 0;
  position: absolute;
  transition: ease-in-out all 0.1s;
}

#sidebar:hover {
  width: 150px;
  box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.25);
  /*shadow-2xl*/
}

#sidebar:hover span {
  opacity: 1;
}
</style>
