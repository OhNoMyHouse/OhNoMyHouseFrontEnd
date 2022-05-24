<template>
  <div class="container">
    <div class="wrapper col-6">
      <b-form @submit="confirm">
        <label for="feedback-user">User ID</label>
        <b-form-input
          v-model="userid"
          id="feedback-user"
          required
        ></b-form-input>
        <b-form-text id="userid-help-block" class="m-2">
          Your user ID must be 5-12 characters long.
        </b-form-text>
        <br /><br />

        <label for="text-password">Password</label>
        <b-form-input
          v-model="password"
          required
          type="password"
          id="text-password"
          aria-describedby="password-help-block"
        ></b-form-input>
        <b-form-text id="password-help-block" class="m-2">
          Your password must be 8-20 characters long, contain letters and
          numbers, and must not contain spaces, special characters, or emoji.
        </b-form-text>
        <br /><br />

        <label for="feedback-user">Name</label>
        <b-form-input v-model="name" required></b-form-input>
        <b-form-text id="name-help-block" class="m-2"> </b-form-text>
        <br /><br />

        <label for="feedback-user">Address</label>
        <b-form-input v-model="address"></b-form-input>
        <b-form-text id="address-help-block" class="m-2"> </b-form-text>
        <br /><br />

        <label for="feedback-user">Telephone</label>
        <b-form-input v-model="telephone"></b-form-input>
        <b-form-text id="telephone-help-block" class="m-2">
          010-xxxx-xxxx
        </b-form-text>
        <br /><br />

        <b-button type="button" variant="primary" class="m-1" @click="confirm"
          >가입 완료</b-button
        >
        <b-button type="reset" variant="danger" class="m-1"> 초기화 </b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userid: "",
      password: "",
      name: "",
      address: "",
      telephone: "",
    };
  },
  computed: {
    validation() {
      return this.userid.length > 4 && this.userid.length < 13;
    },
  },
  methods: {
    confirm() {
      console.log("회원가입 시도");
    },
  },
};
</script>

<style scoped>
.wrapper {
  display: flex;
  align-items: center;
  margin-top: 40px;
}
</style>
