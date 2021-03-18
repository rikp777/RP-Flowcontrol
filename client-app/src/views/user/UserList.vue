<template>
  <div>
    <h1>{{ msg }}</h1>
    <h2>To add a contact, please enter the contact details and click '+'</h2>
    <h2>To remove, just click '-' next to the contact</h2>
    <input type="text" v-model="newContactName" placeholder="First Name" />
    <input type="text" v-model="newContactLastName" placeholder="Last Name" />
    <input
      type="tel"
      name="phone"
      placeholder="123-456-7890"
      pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"
      maxlength="10"
      v-model="newContactNumber"
      @keydown="keyMonitor"
      required
    />
    <button v-on:click="addNumber">+</button>

    <ul>
      <li v-for="(contact, index) in contacts" v-bind:key="contact.id">
        <div>
          {{ contact.name }} {{ contact.lastName }} -
          <span itemprop="telephone">
            <a :href="`callto:+31${contact.number}p123`">{{ contact.number}}</a>
          </span>
        </div>
        <button v-on:click="removeNumber(index)">-</button>
      </li>
    </ul>
    <span v-if="invalidNumber" class="error"
      >Please enter atleast first name and a valid phone number</span
    >
  </div>
</template>

<script>
const contactsFromLocalStorage = JSON.parse(localStorage.getItem("contacts"));
if(contactsFromLocalStorage == null){
  localStorage.setItem("contacts", JSON.stringify([]));
}


export default {
  name: "UserList",
  data() {
    return {
      newContactName: null,
      newContactNumber: null,
      newContactLastName: null,
      msg: "Welcome to my Phone book App created in Vue.js  :)",
      invalidNumber: false,
      contacts: contactsFromLocalStorage
    };
  },
  methods: {
    addNumber() {
      if (this.newContactName && this.newContactNumber) {
        this.contacts.push({
          name: this.newContactName,
          number: this.newContactNumber,
          lastName: this.newContactLastName
        });

        localStorage.setItem("contacts", JSON.stringify(this.contacts));

        this.newContactName = "";
        this.newContactNumber = "";
        this.newContactLastName = "";
      } else {
        this.invalidNumber = true;
      }
    },
    removeNumber(index) {
      this.contacts.splice(index, 1);
      localStorage.setItem("contacts", JSON.stringify(this.contacts));
    },
    keyMonitor(e) {
      const key = e.keyCode ? e.keyCode : e.which;
      this.invalidNumber = false;
      if (
        !(
          [8, 9, 13, 27, 46, 110, 190].indexOf(key) !== -1 ||
          (key == 65 && (e.ctrlKey || e.metaKey)) ||
          (key >= 35 && key <= 40) ||
          (key >= 48 && key <= 57 && !(e.shiftKey || e.altKey)) ||
          (key >= 96 && key <= 105)
        )
      )
        e.preventDefault();
    }
  }
};
</script>

<style scoped></style>
