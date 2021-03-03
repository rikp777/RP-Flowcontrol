import Vue from 'vue'
import App from './App.vue'

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(Element)

import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)


import VueQrcodeReader from "vue-qrcode-reader";
Vue.use(VueQrcodeReader);


Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
