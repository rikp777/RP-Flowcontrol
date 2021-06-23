<template>
<!--  <div>-->
<!--    <h1>List</h1>-->
<!--    <button v-if="!isLoading && data.length === 0" @click="getAllArticles()">get data</button>-->
<!--    <div>-->
<!--      <div v-if="hasError">Error</div>-->
<!--      <div v-if="!hasError && !isLoading">-->
<!--        <button @click="redirectCreate()">Create new</button>-->
<!--        <table v-if="isDataSet">-->
<!--          <tr>-->
<!--            <th>Name</th>-->
<!--            <th>Actions</th>-->
<!--          </tr>-->
<!--          <tr v-for="(article, index) in data" :key="index">-->
<!--            <td>{{article.fullName}}</td>-->
<!--            <td>-->
<!--              <button @click="redirectRead(article)">Go to</button>-->
<!--              <button @click="redirectEdit(article)">Edit</button>-->
<!--              <button @click="redirectDelete(article)">Delete</button>-->
<!--            </td>-->
<!--          </tr>-->
<!--        </table>-->
<!--        <pagination v-if="pageData" v-model="page" :records="pageData.totalElements" :per-page="pageData.size"/>-->
<!--      </div>-->
<!--    </div>-->

<!--  </div>-->
  <section>
    <button v-if="!isLoading && data.length === 0" @click="getAllArticles()">Get data</button>
    <div v-if="hasError">Error</div>
    <b-table
        v-if="!hasError"
        :data="data"
        :paginated="false"
        :per-page="pageData.size"
        :current-page.sync="currentPage"
        :pagination-simple="isPaginationSimple"
        :pagination-position="paginationPosition"
        :default-sort-direction="defaultSortDirection"
        :pagination-rounded="isPaginationRounded"
        :sort-icon="sortIcon"
        :sort-icon-size="sortIconSize"
        :loading="isLoading"
        default-sort="user.first_name"
        aria-next-label="Next page"
        aria-previous-label="Previous page"
        aria-page-label="Page"
        aria-current-label="Current page">


      <b-table-column field="user.first_name" label="First Name" sortable v-slot="props">
        {{ props.row.fullName }}
      </b-table-column>

      <b-table-column label="Actions" v-slot="props">
        <router-link label="button" :to="{ name: 'editArticle', params: { id: getId(props.row) } }" class="button is-small is-light" >
          <r-icon icon="edit"></r-icon>
        </router-link>
        <router-link label="button" :to="{ name: 'readArticle', params: { id: getId(props.row) } }" class="button is-small is-primary">
          <r-icon icon="eye"></r-icon>
        </router-link>
      </b-table-column>


    </b-table>

<!--    <section>-->
<!--      <b-pagination-->
<!--          :total="pageData.totalElements"-->
<!--          v-model="page"-->
<!--          :range-before="1"-->
<!--          :range-after="1"-->
<!--          :order="order"-->
<!--          :simple="isSimple"-->
<!--          :rounded="isRounded"-->
<!--          :per-page="pageData.size"-->
<!--          :icon-prev="prevIcon"-->
<!--          :icon-next="nextIcon"-->
<!--          aria-next-label="Next page"-->
<!--          aria-previous-label="Previous page"-->
<!--          aria-page-label="Page"-->
<!--          aria-current-label="Current page">-->
<!--      </b-pagination>-->
<!--    </section>-->
  </section>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import api from '@/services/api.service'

export default {
  name: "articleListComponent",
  data(){
    return {
      page: 1,
      isPaginated: true,
      isPaginationSimple: false,
      isPaginationRounded: false,
      paginationPosition: 'bottom',
      defaultSortDirection: 'asc',
      sortIcon: 'arrow-up',
      sortIconSize: 'is-small',
      currentPage: 1,
      perPage: 3,
      total: 200,
      current: 1,
      rangeBefore: 1,
      rangeAfter: 1,
      order: '',
      size: '',
      isSimple: false,
      isRounded: false,
      prevIcon: 'chevron-left',
      nextIcon: 'chevron-right'
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
    getId(article){
      if(article.links.self.href != null){
        return api.getId(article.links.self.href)
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
