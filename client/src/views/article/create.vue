<template>
  <div>
    <h1>create</h1>
    <form>
      <label for="excelCode">Excel code:</label><br>
      <input
          type="text"
          id="excelCode"
          name="excelCode"
          placeholder="Specify excel code"
          v-model="form.excelCode">
      <br><br>

      <label for="insetGram">Inset gram:</label><br>
      <input
          type="text"
          id="insetGram"
          name="insetGram"
          placeholder="Specify inset gram"
          v-model="form.insetGram">
      <br><br>

      <label for="insetLimit">Inset limit:</label><br>
      <input
          type="text"
          id="insetLimit"
          name="insetLimit"
          placeholder="Specify inset limit"
          v-model="form.insetLimit">
      <br><br>

      <label for="palletLimit">Pallet limit:</label><br>
      <input
          type="text"
          id="palletLimit"
          name="palletLimit"
          placeholder="Specify pallet limit"
          v-model="form.palletLimit">
      <br><br>

      <label for="origin">Pallet limit:</label><br>
      <input
          type="text"
          id="origin"
          name="origin"
          placeholder="Specify origin"
          v-model="form.origin">
      <br><br>

      <label for="biologic">biologic:</label><br>
      <input
          type="text"
          id="biologic"
          name="biologic"
          placeholder="Specify biologic"
          v-model="form.biologic">
      <br><br>

      <label for="additionalInfo">Additional info:</label><br>
      <input
          type="text"
          id="additionalInfo"
          name="additionalInfo"
          placeholder="Specify additional info"
          v-model="form.additionalInfo">
      <br><br>


      <input type="submit" value="Submit"><br><br>

      {{form}}
    </form>
  </div>



</template>

<script>
import api from '@/services/api.service'
export default {
  name: "articleCreateComponent",
  data() {
    return {
      form: {
        excelCode: "",
        insetGram: 0,
        insetLimit: 0,
        palletLimit: 0,
        origin: 0,
        biologic: false,
        additionalInfo: ""
      }
    }
  }
}
</script>

<style scoped>

</style>