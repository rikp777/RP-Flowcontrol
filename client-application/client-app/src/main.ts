import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import AccessControl from '@/services/accessControl.service'

const access = new AccessControl();
access.router(store, router)


createApp(App)
    .use(store)
    .use(router)
    .mount('#app')
