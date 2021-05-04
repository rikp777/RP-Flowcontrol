<template>
  <div>Navbar</div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "NavBar",
  methods: {
    ...mapActions("auth", ["login", "logout"])
  },
  created() {
    let email = "test@flowcontrol.com";
    let password = "test"
    this.login({ email, password});
  }
};
</script>