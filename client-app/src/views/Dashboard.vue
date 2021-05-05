<template>
  <div>
<!--    <div v-if="apiToken && user">Logged in</div>-->
<!--    <div v-else>Not logged in</div>-->
    Dashboard


  </div>

</template>

<script lang="ts">
  import { mapGetters} from "vuex";


  export default {
    name: "Dashboard",
    computed: {
      // ...mapGetters("user", ["apiToken", "user"])
    }
  }
</script>