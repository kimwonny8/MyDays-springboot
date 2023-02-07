<template>
    <div>
        <h1>login</h1>
        <div v-if="!$store.state.user.id">
        <p>이메일: <input type="text" v-model="state.form.email"></p>
        <p>비밀번호: <input type="password" v-model="state.form.password"></p>
        <button @click="login()">로그인</button>
        <br><br>
        <router-link to="/signup" >회원가입</router-link>  
        </div>
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
        sessionStorage.setItem("name", res.data);   
        sessionStorage.setItem("email", state.form.email);   
        this.$router.push('/');
        alert("로그인하였습니다.");
      }).catch(() => {
        //alert("로그인 정보가 존재하지 않습니다.");
      });
    }

    return {state, login}
  }
}
</script>