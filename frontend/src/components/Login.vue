<template>
  <div>
    <!-- 로그인 폼 -->
    <div v-if="!$store.getters.isLogin" class="loginForm">
      <div class="loginInput">
        <p>아이디:</p>
        <input type="text" class="input" v-model.trim="form.email" />
      </div>
      <div class="loginInput">
        <p>비밀번호:</p>
        <input type="password" class="input" v-model.trim="form.password" />
      </div>
      <div class="btns">
        <button @click="login()" class="submitBtn">로그인</button>
        <router-link to="/signup" class="submitBtn">회원가입</router-link>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  data() {
    return{
      form: {
        email: "",
        password: ""
      }
    }
  },
  methods: {
    async login() {
      await axios.post(`${process.env.VUE_APP_API_PATH}/api/v1/member`, this.form)
        .then((res) => {
          store.commit("setAccessToken", res.data);
          store.commit("setUser", this.form.email);

          router.push('/');
          alert("로그인하였습니다.");
        }).catch(() => {
          alert("로그인 정보가 존재하지 않습니다.");
        });
    },
  }

}
</script>
<style>
* {
  margin: 0 auto;
}

.loginForm {
  padding: 30px;
  border: 1px solid grey;
  width: 350px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.loginInput {
  width: 250px;
  padding: 10px;
  display: flex;
  flex-direction: row;
  font-size: large;
}

.loginInput>p {
  width: 50%;
}

.input {
  height: 20px;
  width: 200px;
  border: none;
  border-bottom: 1px solid gray;
  font-family: 'KyoboHand', 'Avenir', Helvetica, Arial, sans-serif;
}

.btns {
  margin-top: 10px;
  width: 70%;
  display: flex;
}

.submitBtn {
  font-family: "KyoboHand";
  font-size: large;
  width: 110px;
  height: 30px;
  border: none;
  background-color: rgb(226, 226, 226);
  border-radius: 10px;
  color: black;
  margin-top: 10px;
  text-decoration-line: none;
  display: flex;
  align-items: center;
  justify-content: center;
}

.submitBtn:hover {
  border: 1px solid gray;
  background-color: white;
}
</style>
