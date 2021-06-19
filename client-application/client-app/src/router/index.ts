import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);


import article from '@/router/routes/article.route'
import auth from '@/router/routes/auth.route'
import production from '@/router/routes/production.route'

const routes: Array<RouteConfig> = [
    ...article,
    ...auth,
    ...production
]

const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes,
});

export default router
