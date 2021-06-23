<template>
  <div>
    <h1>Article Dashboard</h1>
    <router-link :to="{ name: 'listArticle' }">List</router-link>
    <router-view></router-view>

  </div>

</template>

<script>
export default {
  name: "articleDashboardComponent"
}
</script>

<style scoped>

</style>