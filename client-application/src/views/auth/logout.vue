<template>
  <div>Logging out</div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "Logout.vue",
  methods: {
    ...mapActions("auth", {
      logoutAction: "logout"
    }),
  },
  created(){
    this.logoutAction()
        .then(() => {
          this.$router.push({ name: 'authLogin'})
        })
  }
}
</script>