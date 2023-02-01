<template>
    <div>
        <h1>login</h1>
        <div v-if="!$store.state.user.id">
        <p>이메일: <input type="text" v-model="state.form.email"></p>
        <p>비밀번호: <input type="password" v-model="state.form.password"></p>
        <button @click="login()">로그인</button>
        </div>
        <button @click="logout()" v-else>로그아웃</button>
    </div>
</template>
<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";
import router from "@/scripts/router";

export default {
  setup() {
    const state = reactive({
      form: {
        email: "",
        password: ""
      }
    })

    const login = () => {
      axios.post("/api/user/login", state.form).then((res) => {
        store.commit('setUser', res.data);
        sessionStorage.setItem("id", res.data);
        router.push({path: "/"});
        window.alert("로그인하였습니다.");
      }).catch(() => {
        window.alert("로그인 정보가 존재하지 않습니다.");
      });
    }

    return {state, login}
  }
}
</script>