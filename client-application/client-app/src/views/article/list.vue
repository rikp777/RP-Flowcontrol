<template>
  <div>
    <button v-if="!isLoading && data == null" @click="getAllArticles()">get data</button>
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
            <td><button @click="read(article)">Go to</button></td>
          </tr>
        </table>
      </div>
    </div>

  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import api from '@/services/api.service'

export default {
  name: "articleList",
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
    },
    read(article){
      if(article.links.self.href != null){
        let id = api.getId(article.links.self.href)
        this.$router.push({ name: 'readArticle', params: { id: id } });
      }
    }
  },
  async mounted() {
    await this.getAllArticles()
  }
}
</script>
