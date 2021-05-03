import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";

import "./assets/tailwind.css"

// Axios
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)

// QR Code
// @ts-ignore
import VueQrcodeReader from "vue-qrcode-reader";
Vue.use(VueQrcodeReader);


// Vue Tailwind Components
import VueTailwind from 'vue-tailwind'
import VueTailwindSettings from "../VueTailwindSettings";
Vue.use(VueTailwind, VueTailwindSettings)


Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
