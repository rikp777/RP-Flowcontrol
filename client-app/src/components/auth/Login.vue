<template>
  <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 flex flex-col">
    <div class="mb-4">
      <t-input-group
          label="Username"
      >
        <t-input
            variant="error"
            value="test@flowcontrol.com"
            type="email"
            v-model="form.username"
        />
      </t-input-group>
    </div>
    <div class="mb-6">
      <t-input-group
          label="Password"
          feedback="Password must be at least 6 characters long"
      >
        <t-input
            variant="error"
            value="password"
            type="password"
            v-model="form.password"
        />
      </t-input-group>
    </div>
    <div class="flex items-center justify-between">
      <t-button @click="login()">Sign In</t-button>
      <t-button>Forgot Password?</t-button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "Login",
  data(){
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  computed: {
    ...mapGetters("auth", {
      metaGetter: "getMetaData"
    })
  },
  methods: {
    ...mapActions("auth", {
      loginAction: "login"
    }),

    async login(){
      await this.loginAction({ email: this.form.username, password: this.form.password });
      console.log(this.metaGetter.loggedIn)
      if(this.metaGetter.loggedIn){
        await this.$router.push("/dashboard");
      }else{
        alert("error")
      }
    }
  },
};
</script>