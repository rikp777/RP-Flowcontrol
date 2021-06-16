<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
<!--    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->
    <button @click="getAllArticles()"></button>
  </div>
</template>

<script>
// import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import {mapActions, mapGetters} from "vuex";

export default {
  name: "test",
  components: {
  },
  methods: {
    ...mapActions("article", {
      fetchArticlesAction: "fetchArticles",
    }),
    getAllArticles(){
      this.fetchArticlesAction()
    }
  },
}
</script>
