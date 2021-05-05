<template>
  <div>Logging out</div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "Logout.vue",
  methods: {
    ...mapActions("auth", {
      loginAction: "logout"
    }),
  },
  created(){
      this.loginAction()
          .then(() => {
            this.$router.push("/login");
          })
  }
}
</script>
