<template>
  <el-row :gutter="20">
    <el-col :span="12" :offset="6">
      <div class="grid-content bg-purple">
        <el-container style="height: 1300px; border: 1px solid #eee">
          <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu :default-openeds="['1', '3']">
              <el-submenu index="1">
                <template slot="title"><i class="el-icon-message"></i>Navigator One</template>
                <el-menu-item-group>
                  <template slot="title">Group 1</template>
                  <el-menu-item index="1-1">Option 1</el-menu-item>
                  <el-menu-item index="1-2">Option 2</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group 2">
                  <el-menu-item index="1-3">Option 3</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="1-4">
                  <template slot="title">Option4</template>
                  <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
                </el-submenu>
              </el-submenu>
              <el-submenu index="2">
                <template slot="title"><i class="el-icon-menu"></i>Navigator Two</template>
                <el-menu-item-group>
                  <template slot="title">Group 1</template>
                  <el-menu-item index="2-1">Option 1</el-menu-item>
                  <el-menu-item index="2-2">Option 2</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group 2">
                  <el-menu-item index="2-3">Option 3</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="2-4">
                  <template slot="title">Option 4</template>
                  <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
                </el-submenu>
              </el-submenu>
              <el-submenu index="3">
                <template slot="title"><i class="el-icon-setting"></i>Navigator Three</template>
                <el-menu-item-group>
                  <template slot="title">Group 1</template>
                  <el-menu-item index="3-1">Option 1</el-menu-item>
                  <el-menu-item index="3-2">Option 2</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group 2">
                  <el-menu-item index="3-3">Option 3</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="3-4">
                  <template slot="title">Option 4</template>
                  <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
                </el-submenu>
              </el-submenu>
            </el-menu>
          </el-aside>

          <el-container>
            <el-header style="text-align: right; font-size: 12px">
              <el-dropdown>
                <i class="el-icon-setting" style="margin-right: 15px"></i>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>View</el-dropdown-item>
                  <el-dropdown-item>Add</el-dropdown-item>
                  <el-dropdown-item>Delete</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              <span>Rik</span>
            </el-header>

            <el-main>


              <el-form ref="form" :model="form" label-width="120px">
                <el-alert
                    v-if="error"
                    :title="error"
                    type="error"
                    effect="dark"
                >
                </el-alert>

                    <el-form-item label="Pallet label Nr:">
                      <el-input v-model="form.palletLabelId" @input="getTickets"></el-input>
                    </el-form-item>

                    <el-form-item label="Line Nr:">
                      <el-select v-model="form.lineId" placeholder="Select">
                        <el-option
                            v-for="item in lineOptions"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>

                <el-form-item>
                  <el-button @click="createTicket">Create ticket</el-button>
                </el-form-item>



                <el-form-item label="Interruption reason">
                  <el-select v-model="form.interruptionReasonId" placeholder="Select" @input="checkProcess()">
                    <el-option-group
                        v-for="group in interruptionReasonsOptions"
                        :key="group.label"
                        :label="group.label">
                      <el-option
                          v-for="item in group.options"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                      </el-option>
                    </el-option-group>
                  </el-select>
                </el-form-item>
                <el-alert
                    v-if="show.restAmountOpen"
                    title="Also fill the refill quantity"
                    type="warning"
                    effect="dark">
                </el-alert>
                <el-form-item label="Amount used" v-if="show.restAmountOpen">

                  <el-input-number v-model="form.restAmount" :disabled="!show.restAmountOpen"></el-input-number>
                </el-form-item>
                <el-form-item>
                  <el-button @click="createInterruption">Create interruption</el-button>
                  <el-button @click="closeInterruption">Close interruption</el-button>
                </el-form-item>


                <el-form-item label="refill quantity">
                  <el-input-number v-model="form.refillAmount" ></el-input-number>
                </el-form-item>
                <el-form-item>
                  <el-button @click="closeTicket">Close ticket</el-button>
                </el-form-item>

              </el-form>



              <el-table
                  v-if="tickets.length > 0"
                  :data="tickets"
                  style="width: 100%"
                  :row-class-name="tableRowClassName"
              >
                <el-table-column
                    prop="id"
                    label="Ticket Nr.">
                </el-table-column>
                <el-table-column
                    prop="palletLabelId"
                    label="Pallet label Nr.">
                </el-table-column>
                <el-table-column
                    prop="startAt"
                    label="Started at">
                </el-table-column>
                <el-table-column
                    prop="endAt"
                    label="Ended at">
                </el-table-column>
                <el-table-column
                    prop="articleAmountUsed"
                    label="Amount used">
                </el-table-column>
                <el-table-column
                    prop="refillTrays"
                    label="Refill quantity">
                </el-table-column>
                <el-table-column
                    prop="interruptions.length"
                    label="Interruptions">
                </el-table-column>
              </el-table>



              <el-table
                  v-if="tickets.length > 0"
                  :data="interruptions"
                  style="width: 100%"
                  :row-class-name="tableRowClassName"
              >
                <div slot="append" v-if="interruptions.length=='0'">
                  <small class="p-4">No data</small>
                </div>
                <el-table-column
                    prop="id"
                    label="Interruptions Nr.">
                </el-table-column>
                <el-table-column
                    prop="interruptionReason.name"
                    label="Name">
                </el-table-column>
                <el-table-column
                    prop="startAt"
                    label="Started At">
                </el-table-column>
                <el-table-column
                    prop="endAt"
                    label="Ended At">
                </el-table-column>
              </el-table>

              <br><br><br>
              {{ tickets }}
              <br><br><br>
              {{interruptions}}
            </el-main>
          </el-container>
        </el-container>
      </div>
    </el-col>
    <qrcode-stream></qrcode-stream>
    <qrcode-drop-zone></qrcode-drop-zone>
    <qrcode-capture></qrcode-capture>
  </el-row>
</template>

<style>
.el-table__empty-block {
  display: none!important;
}
.el-table .warning-row {
  background: #E6A23C;
}

.el-table .success-row {
  background: #67C23A;
}
</style>

<script>


const apiBaseUrl = "http://localhost:7073";


export default {

  data() {
    return {
      error: null,
      tickets: [],
      ticket: [],
      interruptions: [],
      interruption: [],
      form: {
        palletLabelId: "",
        lineId: 0,
        interruptionReasonId: 0,
        restAmount: 0,
        refillAmount: 0,
      },
      lineOptions: [
        {
          value: 1,
          label: "line One"
        },
        {
          value: 2,
          label: "line Two"
        },
        {
          value: 3,
          label: "line Three"
        }
      ],
      interruptionReasonsOptions: [
          {
            label: 'Normal',
            options: [
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
            ]
          },
        {
          label: 'Stop process',
          options: [
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
          ]
        },
      ],
      request: {
        headers: {
          "Content-Type": "application/json",
        }
      },
      show: {
        restAmountOpen: false
      }
    }
  },
  methods: {
    tableRowClassName({row}){

      if(row.endAt != null){
        return 'warning-row';
      } else {
        console.log(row)
        return 'success-row';
      }
    },


    checkProcess(){
      if(this.form.interruptionReasonId === 4 || this.form.interruptionReasonId === 5 || this.form.interruptionReasonId ===6){
        this.show.restAmountOpen = true;
      }else {
        this.show.restAmountOpen = false;
      }
    },

    getTickets(){
      this.error = null;
      const apiUrl = `${apiBaseUrl}/api/v1/palletlabels/${this.form.palletLabelId}/tickets`
      this.axios.get(apiUrl).then((response) => {
        this.tickets = response.data;
        this.getInterruptions()
      })
    },
    getInterruptions(){
      if(this.tickets.length > 0){
        const apiUrl = `${apiBaseUrl}/api/v1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions`
        this.axios.get(apiUrl).then((response) => {
          this.interruptions = response.data;
        })
      }
    },


    createTicket(){

      const params = {
        lineId: this.form.lineId
      }
      const data = {}
      const headers = this.request.headers
      const apiUrl = `${apiBaseUrl}/api/v1/palletlabels/${this.form.palletLabelId}/tickets/`
      this.axios.post(apiUrl, data, { params, headers }).then((response) => {
        console.log(response.data)
        this.ticket = response.data;
        this.getTickets();
      }).catch((error) => {
        this.getTickets()
        this.error = error.response.data.data;
        console.log(error.response.data.data)
      })
    },
    closeTicket(){
      const data = {}
      const headers = this.request.headers
      const apiUrl = `${apiBaseUrl}/api/v1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}`
      this.axios.post(apiUrl, data, { headers }).then((response) => {
        console.log(response.data)
        this.ticket = response.data;
        this.getTickets();
      })
    },


    createInterruption(){
      const data = {}
      const params = {}
      console.log(this.form.restAmount)
      if (this.form.restAmount != null || this.form.restAmount != ""){
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = this.form.restAmount;
      }else {
        params.interruptionReasonId = this.form.interruptionReasonId;
        params.usedArticleAmount = 0;
      }

      console.log(params)
      const headers = this.request.headers
      const apiUrl = `${apiBaseUrl}/api/v1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions`
      this.axios.post(apiUrl, data, { params, headers }).then((response) => {
        console.log(response.data)
        this.interruption = response.data;
        this.getTickets();
      })
    },
    closeInterruption(){
      const data = {}
      const headers = this.request.headers
      const apiUrl = `${apiBaseUrl}/api/v1/palletlabels/${this.form.palletLabelId}/tickets/${this.tickets[this.tickets.length -1].id}/interruptions/${this.interruption.id}`
      this.axios.post(apiUrl, data, {  headers }).then((response) => {
        console.log(response.data)
        this.interruption = response.data;
        this.getTickets();
      })
    }

  }
};
</script>
