<template>
  <div id="app">
    <button class="goToMainBtn" ><img src="./assets/mydaysLogo.png"></button>
    <login />
  </div>
</template>

<script>
import login from './components/Login.vue'

export default {
  name: 'App',
  components: {
    login  
  }
}
</script>

<style>
@font-face {
    font-family: 'KyoboHand';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
#app {
  font-family: 'KyoboHand','Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  font-size: large;
}
.goToMainBtn{
  border: none;
  background-color: white;
  height: 15vh;
  margin-bottom: 7vh;
}

.goToMainBtn:hover {
 opacity: 0.7;
}

.goToMainBtn > img{
  height: 15vh;
  object-fit:cover
}
</style>
