<template>
    <div>
      <router-link to="/"><img src="../assets/mydaysLogo.png" width="200" class="logo"></router-link> 
        <p v-if="$store.state.user.name !== null">{{ $store.state.user.name }}</p>
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
<style>
.logo {
  padding: 20px;
}
</style>
  