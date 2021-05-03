import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";


import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)


// @ts-ignore
import VueQrcodeReader from "vue-qrcode-reader";
Vue.use(VueQrcodeReader);

import "./assets/tailwind.css";
import VueTailwind from 'vue-tailwind/dist/full'

const settings = {
}

Vue.use(VueTailwind, settings)


Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
