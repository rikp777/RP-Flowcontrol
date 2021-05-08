import Vue from "vue";
import Vuex, {createLogger} from "vuex";

Vue.use(Vuex);


// import user from "@/store/modules/userModule";
// import apiRequest from "@/store/modules/apiRequestModule";
import authModule from "@/store/modules/authModule";
import ticketModule from "@/store/modules/ticketModule";




const debug = process.env.NODE_ENV !== "production";

export default new Vuex.Store({
  state: {},
  mutations: {},
  actions: {},


  modules: {
    auth: authModule,
    ticket: ticketModule
  },

  strict: debug,
  plugins: debug ? [createLogger()] : []
});
