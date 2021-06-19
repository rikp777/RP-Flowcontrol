<template>
  <div>
      <nav class="breadcrumb" aria-label="breadcrumbs">
        <ul>
          <li><a href="#">Production</a></li>
        </ul>
      </nav>
      <div class="card">
        <div class="card-content">
          <div class="media">
            <div class="media-content">
              <p class="title is-4">Ticket</p>
              <p class="subtitle is-6">Make a ticket, for processing of the pallet.</p>
            </div>
          </div>

          <div class="content">
            <div class="columns">
              <div class="column is-two-thirds">
                <div class="section">
                  <div>
                    <div>Lijn nr.: </div>
                  </div>
                  <div>
                    <div>Pallet label nr.: </div>
                  </div>
                  <div>
                    <div>Article: </div>
                  </div>
                </div>
                <div class="section">
                  <div>
                    <h4>Ticket Data</h4>
                  </div>
                </div>

              </div>
              <div class="column">
                Second column
              </div>
            </div>
          </div>
        </div>
      </div>

  </div>
</template>

<script>
export default {
  name: "dashboard"
}
</script>

<style scoped>

</style>