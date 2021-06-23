<template>
<!--  <aside class="is-medium menu">-->
<!--    <p class="menu-label">-->
<!--      Modules-->
<!--    </p>-->
<!--    <ul class="menu-list">-->
<!--      <li>-->
<!--        <router-link :to="{ name: 'productionDashboard' }"><r-icon icon="pallet" /> Production</router-link>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a ><r-icon icon="truck-loading"/> Transport</a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a ><r-icon icon="seedling" /> Farmer</a>-->
<!--      </li>-->
<!--      <li>-->
<!--        <a ><r-icon icon="boxes" /> Article</a>-->
<!--      </li>-->
<!--    </ul>-->
<!--    <p class="menu-label">-->
<!--      Module menu-->
<!--    </p>-->
<!--    <ul class="menu-list">-->
<!--      <li><span class="tag is-white is-medium">Lorem</span></li>-->
<!--      <li><span class="tag is-white is-medium">Ipsum</span></li>-->
<!--      <li><span class="tag is-white is-medium">Dolor</span></li>-->
<!--      <li><span class="tag is-white is-medium">Animi</span></li>-->
<!--      <li><span class="tag is-white is-medium">Eximi</span></li>-->
<!--      <li><span class="tag is-white is-medium">Nullius</span></li>-->
<!--      <li><span class="tag is-white is-medium">Oxipi</span></li>-->
<!--      <li><span class="tag is-white is-medium">Vultus</span></li>-->
<!--      <li><span class="tag is-white is-medium">Voluptatis</span></li>-->
<!--      <li><span class="tag is-white is-medium">Exomarphis</span></li>-->
<!--      <li><span class="tag is-white is-medium">Finimi</span></li>-->
<!--      <li><span class="tag is-white is-medium">Aenigma</span></li>-->
<!--      <li><span class="tag is-white is-medium">Arkham</span></li>-->
<!--      <li><span class="tag is-white is-medium">Blue</span></li>-->
<!--      <li><span class="tag is-white is-medium">Medium</span></li>-->
<!--    </ul>-->
<!--  </aside>-->

  <b-menu>
    <b-menu-list label="Modules">
      <b-menu-item>
        <template #label="props">
          <router-link :to="{ name: 'productionDashboard'}" tag="div"><r-icon icon="pallet" /> Production</router-link>
          <b-icon class="is-pulled-right" :icon="props.expanded ? 'menu-down' : 'menu-up'"></b-icon>
        </template>
        <router-link :to="{ name: 'createTicket'}">
          <b-menu-item icon="account" label="Ticket"></b-menu-item>
        </router-link>
      </b-menu-item>
      <b-menu-item>
        <template #label="props">
          <r-icon icon="truck-loading" /> Transport
          <b-icon class="is-pulled-right" :icon="props.expanded ? 'menu-down' : 'menu-up'"></b-icon>
        </template>
        <b-menu-item icon="account" label="Users"></b-menu-item>
        <b-menu-item icon="cash-multiple" label="Payments" disabled></b-menu-item>
      </b-menu-item>
      <b-menu-item>
        <template #label="props">
          <r-icon icon="seedling" /> Farmer
          <b-icon class="is-pulled-right" :icon="props.expanded ? 'menu-down' : 'menu-up'"></b-icon>
        </template>
        <b-menu-item icon="account" label="Users"></b-menu-item>
        <b-menu-item icon="cash-multiple" label="Payments" disabled></b-menu-item>
      </b-menu-item>
      <b-menu-item>
        <template #label="props">
          <router-link :to="{ name: 'listArticle'}" tag="div"><r-icon icon="boxes" /> Article</router-link>
          <b-icon class="is-pulled-right" :icon="props.expanded ? 'menu-down' : 'menu-up'"></b-icon>
        </template>
        <b-menu-item icon="account" label="Users"></b-menu-item>
        <b-menu-item icon="cash-multiple" label="Payments" disabled></b-menu-item>
      </b-menu-item>
    </b-menu-list>
    <b-menu-list label="Actions">
      <b-menu-item label="Logout"></b-menu-item>
    </b-menu-list>
  </b-menu>
</template>

<script>
export default {
  name: "sidenav",
  data() {
    return {
      isActive: true
    }
  },
  methods: {
    redirectProduction(){
      this.$router.push({ name: 'authLogin'})
    }
  }
}
</script>

<style scoped>

</style>