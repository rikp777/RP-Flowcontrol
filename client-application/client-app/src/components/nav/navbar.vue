<template>
  <div>
    <!--    <div>navbar</div>-->
    <!--    <router-link :to="{ name: 'articleDashboard' }">Articles</router-link>-->
    <b-navbar>

      <template #brand>
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          <img
              src="https://www.limax.nl/images/limax-logo@2x.png"
              alt="Limax Flowcontrol"
          >
        </b-navbar-item>
      </template>
      <template #start v-if="isLoggedIn">
        <b-navbar-dropdown :label="currentFarmer" v-if="farmers.length > 0">
          <template v-for="(data, index) in farmers">
            <b-navbar-item :key="index" @click="setCurrentFarmer(data.farmer)">
              {{data.farmer.name}}
            </b-navbar-item>
          </template>

        </b-navbar-dropdown>
      </template>

      <template #end>
        <b-navbar-dropdown label="Profile" v-if="isLoggedIn">
          <b-navbar-item href="#">
            {{ authData.email }}
          </b-navbar-item>
          <b-navbar-item @click="redirectLogout()">
            Logout
          </b-navbar-item>
        </b-navbar-dropdown>
        <b-navbar-item tag="div">
          <div class="buttons">
            <a
                v-if="!isLoggedIn && !loginPageLoaded"
                @click="redirectLogin()"
                class="button is-light"
            >
              Login
            </a>
          </div>
        </b-navbar-item>
      </template>
    </b-navbar>
  </div>
</template>

<script>

import {mapActions, mapGetters} from "vuex";

export default {
  name: "Navbar",
  data() {
    return {
      isActive: true
    }
  },
  watch: {
    authData(newVal, oldVal){
      console.log("authData", newVal)
      this.getAllFarmersThatAreAttachedToUser(newVal.userId)
    }
  },
  computed: {
    ...mapGetters(
        "auth", {
          isLoggedIn: "getIsLoggedIn",
          authData: "getAuthData",
        }
    ),
    ...mapGetters(
        "farmer", {
          farmers: "getAllFarmers",
          currentFarmer: "getCurrentFarmerByName"
        }
    ),
    loginPageLoaded(){
      return this.$route.path === "/auth/login"
    }
  },
  methods: {
    ...mapActions("farmer", {
      farmerGetAllByUser: "getAllFarmersThatAreAttachedToUser",
      setLocalFarmer: "setLocalFarmer"
    }),
    setCurrentFarmer(farmer){
      this.setLocalFarmer(farmer)
    },
    redirectLogin(){
      this.$router.push({ name: 'authLogin'})
    },
    redirectLogout(){
      this.$router.push({ name: 'authLogout'})
    }
  },
  mounted() {
    this.farmerGetAllByUser(this.authData.userId)
  }
}
</script>