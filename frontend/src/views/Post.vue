<template>
  <div class="postForm">
    <h2>일기 쓰기</h2>
    <p>날짜: <input type="date" v-model="diaryForm.date" class="diary_date"></p>
    <p>내용: <input type="text" v-model="diaryForm.content" class="diary_text"></p>
    <p>운동: <input type="text" v-model="diaryForm.exercise" class="diary_text"></p>
    <div class="selectFace">
      <input type="radio" v-model="diaryForm.face" name="face" id="face_happy" value="행복해요"><label
        for="face_bad">😄</label>
      <input type="radio" v-model="diaryForm.face" name="face" id="face_good" value="좋아요"><label
        for="face_good">😊</label>
      <input type="radio" v-model="diaryForm.face" name="face" id="face_soso" value="그냥그래요"><label
        for="face_soso">😶</label>
      <input type="radio" v-model="diaryForm.face" name="face" id="face_hmm" value="음"><label for="face_hmm">🤔</label>
      <input type="radio" v-model="diaryForm.face" name="face" id="face_sad" value="슬퍼요"><label for="face_sad">😭</label>
      <input type="radio" v-model="diaryForm.face" name="face" id="face_angry" value="화나요"><label
        for="face_angry">😠</label>
    </div>
    <button class="submitBtn" @click="post()">작성 완료</button>
  </div>
</template>
<script>
import router from "@/scripts/router";
import axios from "axios";

export default {
  name: 'Post',
  data() {
    return {
      diaryForm: {
        email: this.$store.state.user.email,
        date: new Date().toISOString().substring(0, 10),
        content: '',
        face: '',
      }
    }
  },
  methods: {
    async post() {
      if (this.diaryForm.date == '' || this.diaryForm.date == null) {
        alert("날짜를 선택해주세요!");
      }
      else if (this.diaryForm.face == '' || this.diaryForm.face == null) {
        alert("기분을 선택해주세요!");
      }
      else {
        try {
          await axios.post("/api/v1/diary", this.diaryForm, {
            headers: this.$store.getters.headers
          });
          router.push('/');
          alert("일기가 작성되었습니다!");
        } catch (err) {
          if (err.response && err.response.status === 401) {
            const accessToken = await this.$store.dispatch('getAccessToken');
            if (accessToken) {
              await this.post();
            } else {
              console.log("토큰 가져오기 실패");
            }
          } else {
            alert("선택된 날짜에 이미 일기가 작성되어있습니다.");
          }
        }
      }
    }
  }
}
</script>
<style>
* {
  font-family: 'KyoboHand', 'Avenir', Helvetica, Arial, sans-serif;
}
.postForm {
  margin-top: 30px;
  margin-bottom: 30px;
  width: 500px;
  height: 300px;
  border: 1px solid gray;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  border-radius: 5px;
}

.selectFace>label {
  margin-right: 10px;
}
.diary_date{
  width: 130px;
  height: 30px;
  text-align: center;
  border-radius: 5px;
  border: 1px solid #ddd;
  padding-right: 10px;
}

.diary_text {
  width: 250px;
  height: 30px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(233, 233, 233);
}
</style>