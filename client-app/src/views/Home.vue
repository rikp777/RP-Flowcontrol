<template>
  <div class="home">
    <!--    <img alt="Vue logo" src="../assets/logo.png" />-->
    <!--    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App" />-->
    <Production></Production>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import HelloWorld from "@/components/HelloWorld.vue";
import Production from "@/components/Production.vue"; // @ is an alias to /src

@Component({
  components: {
    Production,
    HelloWorld
  }
})
export default class Home extends Vue {}
</script>
