import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import './index.scss'

// Security
import AccessControl from '@/services/accessControl.service'


const access = new AccessControl();
access.router(store, router)

import Buefy from 'buefy'
Vue.use(Buefy, {
    defaultIconComponent: 'r-icon',
    defaultIconPack: 'fas',
})

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { fas } from "@fortawesome/free-solid-svg-icons";
library.add(fas);
Vue.component("r-icon", FontAwesomeIcon);

import moment from 'vue-moment';
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
Vue.use(moment);

const app = new Vue({
    router,
    store,
    render: (h) => h(App),
}).$mount("#app");

