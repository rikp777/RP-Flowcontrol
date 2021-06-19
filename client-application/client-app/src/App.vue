<template>
<!--  <div id="nav">-->
<!--&lt;!&ndash;    <auth-navbar/>&ndash;&gt;-->
<!--    <navbar />-->
<!--  </div>-->
<!--  <router-view/>-->
  <div>
    <div class="container is-widescreen" v-if="isLoggedIn">
      <navbar />
      <section class="section">
        <div class="container">
          <div class="columns">
            <div class="column is-3">
              <sidenav />
            </div>
            <div class="column is-9">
              <div class="content is-medium">
                <router-view/>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
    <div v-else>
      <router-view></router-view>
    </div>
  </div>

</template>
<script>
import navbar from "@/components/nav/navbar";
import sidenav from "@/components/nav/sidenav";
import {mapGetters} from "vuex";
export default {
  components: {navbar, sidenav},
  computed: {
    ...mapGetters("auth", {
      isLoggedIn: "getIsLoggedIn",
      authData: "getAuthData"
    }),
    loginPageLoaded(){
      return this.$route.path === "/auth/login"
    }
  },
}
</script>