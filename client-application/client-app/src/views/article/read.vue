<template>
  <div>read {{id}}</div>
</template>

<script>
export default {
  name: "read",
  data(){
    return {
      id: this.$route.params.id //this is the id from the browser
    }
  },
  mounted() {
    console.log("mounte")
  }
}
</script>

<style scoped>

</style>