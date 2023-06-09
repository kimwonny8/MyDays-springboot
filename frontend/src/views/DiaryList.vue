<template>
  <div>
    <div class="menu">
      <router-link to="/post">일기 쓰기</router-link>
      <router-link to="/">달력으로 보기</router-link>
    </div>
    <div>
      <div class="postForm" v-if="this.diaryList.length === 0">
        <p>등록된 일기가 없습니다.</p>
      </div>
      <table v-else>
        <tr>
          <th>날짜</th>
          <th>기분</th>
          <th>내용</th>
          <th>선택</th>
        </tr>
        <tr v-for="(value, key) in this.showList">
          <td>{{ value.date }}</td>
          <td>{{ value.face }}</td>
          <td>{{ sliceContent(value.content) }}</td>
          <td class="btnTd"><button class="readBtn" @click="selectDiary(value.diaryIdx)">읽기</button></td>
        </tr>
      </table>
      <button class="plusBtn" id="plusBtn" @click="plus()">+</button>
    </div>
  </div>
</template>
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";

export default {
  name: "DiaryList",
  data() {
    return {
      showList: [],
      diaryList: [],
      email: this.$store.state.user.email,
      content: null,
      pageNum: 0,
      pageSize: 0,
    };
  },
  async created() {
    await this.getDiary();
  },
  methods: {
    sliceContent(arg) {
      if (arg != null) {
        if (arg.length < 10) {
          return arg;
        }
        else {
          return arg.slice(0, 10) + "...";
        }
      }
    },
    plus() {
      this.pageNum += 1;
      if (this.pageNum * 10 > this.diaryList.length) {
        alert("마지막 페이지입니다.");
        document.querySelector("#plusBtn").disabled = true;
      }
      else if (this.pageNum * 10 + 10 > this.diaryList.length) {
        for (let i = this.pageNum * 10; i < this.diaryList.length; i++) {
          this.showList[i] = this.diaryList[i];
        }
      }
      else {
        for (let i = this.pageNum * 10; i < this.pageNum * 10 + 10; i++) {
          this.showList[i] = this.diaryList[i];
        }
      }
    },
    async getDiary() {
      try {
        const res = await axios.get(`${VUE_APP_API_PATH}/api/v1/diary`, {
          params: { email: this.email },
          headers: this.$store.getters.headers
        })
        this.showList = [];
        this.diaryList = res.data;
        this.pageSize = Math.ceil(this.diaryList.length / 10);
        for (let i = 0; i < 10; i++) {
          if (i < this.diaryList.length) {
            this.showList.push(this.diaryList[i]);
          }
        }
      }
      catch (err) {
        console.log(err);
        if (err.response && err.response.status === 401) {
          try {
            const accessTokenUpdated = await this.$store.dispatch("getAccessToken");
            if (accessTokenUpdated) {
              await this.getDiary();
            } else {
              store.commit('setAccessTokenAndUser', null);
            }
          } catch (err) {
            console.log(err);
          }
        }
      }
    },

    async selectDiary(arg) {
      try {
        const res = await axios.get(`${VUE_APP_API_PATH}/api/v1/diary/` + arg, {
          headers: this.$store.getters.headers
        });
        console.log(res.data[0]);
        store.commit("setDiary", res.data[0]);
        sessionStorage.setItem("diaryIdx", store.state.diary.diaryIdx);
        router.push("/selectDiary");
      }
      catch (err) {
        console.log(err);
        if (err.response && err.response.status === 401) {
          try {
            const accessTokenUpdated = await this.$store.dispatch("getAccessToken");
            if (accessTokenUpdated) {
              await this.selectDiary(arg);
            } else {
              store.commit('setAccessTokenAndUser', null);
            }
          } catch (err) {
            console.log(err);
          }
        }
      }
    },
  }
}

</script>
<style>
table {
  width: 700px;
  border-collapse: collapse;
}

th,
td {
  width: 120px;
  padding: 5px 0;
  border: none;
  border-bottom: 1px solid #ddd;
}

.btnTd {
  width: 50px;
}

.readBtn {
  background-color: rgb(212, 212, 212);
  border: none;
  font-family: inherit;
  font-size: 16px;
}

.readBtn:hover {
  background-color: white;
}

.plusBtn {
  margin-top: 10px;
  border: none;
  border-radius: 50px;
  height: 50px;
  width: 50px;
  font-size: large;
  font-weight: bold;
}

.plusBtn:hover {
  background-color: rgb(212, 212, 212);
  color: white;
}
</style>