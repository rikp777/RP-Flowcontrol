import Vue from 'vue'
import App from './App.vue'
//
// import './assets/tailwind.css'

// import Element from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.scss';
// Vue.use(Element)

import VueTailwind from 'vue-tailwind/dist/full'

Vue.use(VueTailwind)

import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)


import VueQrcodeReader from "vue-qrcode-reader";
Vue.use(VueQrcodeReader);


Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
