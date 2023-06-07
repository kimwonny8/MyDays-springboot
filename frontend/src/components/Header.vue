<template>
    <div>
      <router-link to="/"><img src="../assets/mydaysLogo.png" width="200" class="logo"></router-link> 
      <p v-if="$store.getters.isLogin">{{ $store.state.user.email }}의 나날들😊</p>
      <button class="submitBtn" @click="logout()" v-if="$store.getters.isLogin" > 로그아웃</button> 
    </div>
  </template>
  
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";

  export default {
    name: 'Header',
    methods:{
      logout(){
        store.commit('setAccessToken', null);
        store.commit('setUser', null);
        alert('로그아웃 되었습니다.');
        router.push({path: "/"});
        sessionStorage.clear();
    }
    }
  }
</script>
<style>
.logo {
  margin-bottom: 20px;
}
.calendarTop {
  display: flex;
  align-items: flex-end;
  margin: 10px;
}
</style>
  