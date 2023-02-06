<template>
    <div>
      <router-link to="/" >HOME</router-link> 
        <p v-if="$store.state.user.name !== null">{{ $store.state.user.name }}님 환영합니다!</p>
        <button @click="logout()" v-if="$store.state.user.name"> 로그아웃</button> 
      </div>
  </template>
  
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";

  export default {
    name: 'Header',
    methods:{
      logout(){
        store.commit('setUser', null);
        router.push({path: "/"});
        sessionStorage.clear();
    }
    }
  }
</script>
  