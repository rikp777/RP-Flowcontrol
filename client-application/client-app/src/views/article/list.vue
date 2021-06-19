<template>
  <div>
    <h1>List</h1>
    <button v-if="!isLoading && data.length === 0" @click="getAllArticles()">get data</button>
    <div>
      <div v-if="hasError">Error</div>
      <div v-if="!hasError && !isLoading">
        <button @click="redirectCreate()">Create new</button>
        <table v-if="isDataSet">
          <tr>
            <th>Name</th>
            <th>Actions</th>
          </tr>
          <tr v-for="(article, index) in data" :key="index">
            <td>{{article.fullName}}</td>
            <td>
              <button @click="redirectRead(article)">Go to</button>
              <button @click="redirectEdit(article)">Edit</button>
              <button @click="redirectDelete(article)">Delete</button>
            </td>
          </tr>
        </table>
        <pagination v-if="pageData" v-model="page" :records="pageData.totalElements" :per-page="pageData.size"/>
      </div>
    </div>

  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import api from '@/services/api.service'

export default {
  name: "articleListComponent",
  data(){
    return {
      page: 1
    }
  },
  watch: {
    page(newVal, oldVal){
      console.log("refresh list with page:", newVal)
      this.getAllArticles(newVal)
    }
  },
  components: {
  },
  computed: {
    ...mapGetters("article", {
      pageData: "getPageData",
      getPageLinks: "getPageLinks",
      hasError: "getHasError",
      isLoading: "getIsLoading",
      data: "getEntities"
    }),
    isDataSet(){
      return this.data != null && this.data.length > 0
    }
  },
  methods: {
    ...mapActions("article", {
      articleGetAll: "getAll",
    }),
    async getAllArticles(page){
      await this.articleGetAll(page)
    },
    redirectRead(article){
      if(article.links.self.href != null){
        let id = api.getId(article.links.self.href)
        this.$router.push({ name: 'readArticle', params: { id: id } });
      }
    },
    redirectEdit(article){
      if(article.links.self.href != null){
        let id = api.getId(article.links.self.href)
        this.$router.push({ name: 'editArticle', params: { id: id }})
      }
    },
    redirectDelete(article){
      if(article.links.self.href != null){
        let id = api.getId(article.links.self.href)
        this.$router.push({ name: 'deleteArticle', params: { id: id }})
      }
    },
    redirectCreate(){
      this.$router.push({ name: 'createArticle'})
    }
  },
  mounted() {
    this.getAllArticles()
  },
}
</script>
