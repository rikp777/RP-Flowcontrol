import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

import Api from "@/api";
import Store from "@/store";

import routes from "./routes";
import axios from "axios";

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

router.beforeEach(async (to, from, next) => {
  console.log(Store.getters["auth/getAuthData"].token);

  if (!Store.getters["auth/getAuthData"].token) {
    const accessToken = localStorage.getItem("access_token")
    const refreshToken = localStorage.getItem("refresh_token")
    if(accessToken) {
      const data = {
        accessToken: accessToken,
        refreshToken: refreshToken
      }
      Store.commit("auth/saveTokenData", data)
    }
  }

  let auth = Store.getters["auth/getIsTokenActive"];
  console.log('Token is still valid', auth)

  if(!auth){
    const authData = Store.getters["auth/getAuthData"];
    console.log('Token is not valid make using refresh token to get new token')
    if(authData.token){
      const payload = {
        accessToken: authData.token,
        refreshToken: authData.refreshToken
      }
      const refreshResponse = await axios.post(
          "http://127.0.0.1:8762/auth/api/v1/auth/refresh",
          payload
      ).catch(() => {
        Store.dispatch("auth/logout")
      })
      // @ts-ignore
      Store.commit("auth/saveTokenData", refreshResponse.data)
      auth = true;
    }
  }

  if(to.fullPath == "/"){
    return next();
  } else if(!auth && to.fullPath != "/login"){
    console.log("not logged in", to.fullPath)
    return next({ path: "/login" });
  }

  return next();
});



export default router;
