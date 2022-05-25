<template>
  <div class="container">
    <div class="wrapper col-6">
      <b-form @submit="confirm">
        <label for="feedback-user">User ID</label>
        <b-form-input
          v-model="user.userid"
          id="feedback-user"
          required
        ></b-form-input>
        <b-form-text id="password-help-block" class="m-2">
          Your user ID must be 5-12 characters long.
        </b-form-text>
        <br /><br />

        <label for="text-password">Password</label>
        <b-form-input
          v-model="user.userpwd"
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

        <b-button type="button" variant="primary" class="m-1" @click="confirm"
          >로그인</b-button
        >
        <b-button to="/user/regist" type="button" variant="success" class="m-1">
          회원가입
        </b-button>
      </b-form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signup" });
    },
  },
};
</script>

<style scoped>
.wrapper {
  display: flex;
  align-items: center;
  min-height: 40vh;
}
</style>
