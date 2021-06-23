import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const debug = false

import articleModule from "@/store/modules/article.module";
import ticketModule from "@/store/modules/ticket.module";
import farmerModule from "@/store/modules/farmer.module";
import authModule from "@/store/modules/auth.module";


export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    article: articleModule,
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    auth: authModule,
    farmer: farmerModule,
    ticket: ticketModule
  },
  strict: debug,
})
