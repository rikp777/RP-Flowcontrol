<template>
  <div>
    <h1>Read {{id}}</h1>

    <button v-if="!isLoading && data.length === 0" @click="getById(this.$route.params.id)">get data</button>
    <div>
      <div v-if="hasError">Error</div>
      <div v-if="!hasError && !isLoading">
        <button @click="redirectEdit(this.$route.params.id)">Create new</button>
        <ul>
          <li v-if="data.fullName">Name: {{ data.fullName }}</li>
          <li v-if="data.additionalInfo">Info: {{ data.additionalInfo }}</li>
          <li v-if="data.palletLimit">PalletLimit: {{ data.palletLimit }}</li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import api from '@/services/api.service'
export default {
  name: "articleReadComponent",
  data(){
    return {
      id: this.$route.params.id //this is the id from the browser
    }
  },
  computed: {
    ...mapGetters("article", {
      hasError: "getHasError",
      isLoading: "getIsLoading",
      data: "getEntity"
    }),
  },
  methods: {
    ...mapActions("article", {
      articleGetById: "getById",
    }),
    async getById(id){
      await this.articleGetById(id)
    },
    redirectEdit(article){
      if(article.links.self.href != null){
        let id = api.getId(article.links.self.href)
        this.$router.push({ name: 'editArticle', params: { id: id }})
      }
    }
  },
  mounted() {
    this.getById(this.$route.params.id)
  }
}
</script>

<style scoped>

</style>