<template>
  <Header></Header>
  <router-view />
  <Footer></Footer>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import store from "@/scripts/store";
import axios from "axios";

export default {
  name: "App",
  components: {
    Footer,
    Header,
  },
  // setup() {
  //   const check = () => {
  //     axios.get("/api/v1/member/check").then(({ data }) => {
  //       console.log(data);
  //       const name = sessionStorage.getItem("name");
  //       if (name) {
  //         store.commit("setUser", name);
  //       }
  //       store.commit("setUser", { "id": data || 0, "name": name });
  //     })
  //   };
  // },
  methods: {
    getAccessToken() {
      return new Promise((resolve, reject) => {
        axios.post("/api/v2/jwt",{},
          {
            headers: {
              'Authorization-refresh': 'Bearer ' + this.$store.state.refreshToken,
            },
          })
          .then((res) => {
            const bearerToken = res.headers['authorization'];
            const accessToken = bearerToken.substring(7);
            store.commit("setAccessToken", accessToken);
            console.log("accessToken 발급 완료" + accessToken);
            resolve(accessToken);
          })
          .catch((err) => {
            console.log("accessToken 발급 실패" + err);
            reject(false);
          });
      });
    }

  }
};
</script>

<style>
@font-face {
  font-family: 'KyoboHand';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/KyoboHand.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

#app {
  font-family: 'KyoboHand', 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  font-size: large;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
