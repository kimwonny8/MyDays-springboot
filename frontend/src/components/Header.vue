<template>
    <div>
      <router-link to="/" >HOME</router-link> |
        <router-link to="/login"  v-if="!$store.state.user.id">로그인</router-link>
        <button @click="logout()" v-else>로그아웃</button>
      </div>
  </template>
  
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";

  export default {
    name: 'Header',
    methods:{
      logout(){
        store.commit('setUser', 0);
        router.push({path: "/"});
        sessionStorage.clear;
    }
    }
  }
</script>
  