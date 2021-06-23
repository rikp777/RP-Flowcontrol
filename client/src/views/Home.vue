<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
<!--    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->


    <button v-if="!isLoading" @click="getAllArticles()">get data</button>
    <div>
      <div v-if="hasError">Error</div>
      <div v-if="!hasError && !isLoading">
        <table>
          <tr>
            <th>Name</th>
            <th>Actions</th>
          </tr>
          <tr v-for="(article, index) in data" :key="index">
            <td>{{article.fullName}}</td>
            <td><button>Go to</button></td>
          </tr>
        </table>
      </div>
    </div>

  </div>
</template>

<script>
// import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import {mapActions, mapGetters} from "vuex";

export default {
  name: "test",
  components: {
  },
  computed: {
    ...mapGetters("article", {
      getPageLinks: "getPageLinks",
      hasError: "getHasError",
      isLoading: "getIsLoading",
      data: "getData"
    }),
  },
  methods: {
    ...mapActions("article", {
      fetchArticlesAction: "fetchArticles",
    }),
    async getAllArticles(){
      await this.fetchArticlesAction()

      console.log('test',this.$store.getters['article/getPageLinks'])
    }
  },
}
</script>
