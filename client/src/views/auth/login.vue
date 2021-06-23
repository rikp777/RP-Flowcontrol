<template>
  <section class="hero is-warning is-fullheight" v-if="!isLoggedIn">
    <div class="hero-body">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-5-tablet is-4-desktop is-3-widescreen">
            <div class="box">
              <b-notification
                  v-if="hasError"
                  type="is-danger"
                  aria-close-label="Close notification"
                  role="alert">
                Something went wrong
              </b-notification>
              <div class="field">
                <label for="" class="label">Email</label>
                <div class="control has-icons-left">
                  <input
                      v-model="form.username"
                      type="email"
                      placeholder="e.g. example@example.com"
                      class="input" required
                  >
                  <span class="icon is-small is-left">
                  <r-icon icon="envelope"></r-icon>
                </span>
                </div>
              </div>
              <div class="field">
                <label for="" class="label">Password</label>
                <div class="control has-icons-left">
                  <input
                      v-model="form.password"
                      type="password"
                      placeholder="*******"
                      class="input" required
                  >
                  <span class="icon is-small is-left">
                  <r-icon icon="lock"></r-icon>
                </span>
                </div>
              </div>
              <div class="field">
                <button
                    @click="login()"
                    class="button is-success"
                >
                  Login
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
<!--  <div>-->
<!--    Login-->
<!--    <form>-->

<!--      <label for="username">Username:</label><br>-->
<!--      <input-->
<!--          type="text"-->
<!--          id="username"-->
<!--          name="username"-->
<!--          placeholder="Specify Username"-->
<!--          v-model="form.username">-->
<!--      <br><br>-->

<!--      <label for="password">Password:</label><br>-->
<!--      <input-->
<!--          type="password"-->
<!--          id="password"-->
<!--          name="password"-->
<!--          placeholder="Specify password"-->
<!--          v-model="form.password">-->
<!--      <br><br>-->
<!--    </form>-->
<!--    <div>-->
<!--      <button @click="login()">Sign In</button>-->
<!--      <button>Forgot Password?</button>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: "authLogin",
  data() {
    return {
      form: {
        username: "test@flowcontrol.com",
        password: "test",
      }
    }
  },
  computed: {
    ...mapGetters("auth", {
      isLoggedIn: "getIsLoggedIn",
      hasError: "getHasError"
    })
  },
  methods: {
    ...mapActions("auth", {
      loginAction: "login"
    }),

    login(){
      this
          .loginAction({ email: this.form.username, password: this.form.password })
          .then(() => {
             this.$router.push("/dashboard");
          })
    }
  },
}
</script>