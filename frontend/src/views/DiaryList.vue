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
      email: sessionStorage.getItem("email"),
      content: null,
      pageNum: 0,
      pageSize: 0,
      headers: {
        'Authorization': `Bearer ${this.$store.state.accessToken}`
      }
    };
  },
  async created() {
    await axios.get("/api/v1/diary", { params: { email: this.email }, headers: this.headers })
      .then((res) => {
        this.showList = []; // 배열 초기화
        this.diaryList = res.data;
        this.pageSize = Math.ceil(this.diaryList.length / 10); // 페이지 개수 계산
        for (let i = 0; i < 10; i++) {
          if (i < this.diaryList.length) {
            this.showList.push(this.diaryList[i]);
          }
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },

  methods: {
    selectDiary(arg) {
      console.log(arg);
      axios.get("/api/v1/diary/" + arg, { headers: this.headers })
        .then((res) => {
          console.log(res.data[0]);
          store.commit("setDiary", res.data[0]);
          sessionStorage.setItem("diaryIdx", store.state.diary.diaryIdx);
          router.push("/selectDiary");
        });
    },
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