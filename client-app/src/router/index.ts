import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

import Api from "@/api";
import Store from "@/store";

import routes from "./routes";

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach((to, from, next) => {
  // @ts-ignore
  if (to.redirectedFrom?.path === "/auth/gh") {
    delete to.query.code;
    to.fullPath = to.fullPath.replace(/\?code(.*)/, "");

    Api.post(
        "/signin",
        {},
        {
          params: {
            // @ts-ignore
            client_id: "test",
            // @ts-ignore
            code: to.redirectedFrom.query.code,
          },
        },
    ).then((res) => {
      Store.dispatch("user/setTokens", {
        apiToken: res?.data?.apiToken,
      });
      next();
    });
  } else {
    next();
  }
});



export default router;
