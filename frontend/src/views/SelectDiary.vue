<template>
  <div>
    <!-- 조회 및 삭제 -->
    <div v-if="!update" class="postForm">
      <p>날짜: {{ this.diaryForm.date }}</p>
      <p>내용: {{ this.diaryForm.content }}</p>
      <p>운동: {{ this.diaryForm.exercise }}</p>
      <p>기분: {{ this.diaryForm.face }}</p>
      <div class="btns btns_50">
        <button class="submitBtn" @click="this.update = !!!this.update">수정</button>
        <button class="submitBtn" @click="deleteDiary(this.diaryForm.diaryIdx)">삭제</button>
      </div>
    </div>

    <!-- 수정하기 -->
    <div v-else class="postForm">
      <p>날짜: <input type="date" v-model="diaryForm.date" disabled class="diary_date"></p>
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
        <input type="radio" v-model="diaryForm.face" name="face" id="face_sad" value="슬퍼요"><label
          for="face_sad">😭</label>
        <input type="radio" v-model="diaryForm.face" name="face" id="face_angry" value="화나요"><label
          for="face_angry">😠</label>
      </div>
      <button class="submitBtn" @click="updateDiary(this.diaryForm.diaryIdx)">수정 완료</button>
    </div>
  </div>
</template>
<script>
import store from "@/scripts/store";
import router from "@/scripts/router";
import axios from "axios";

export default {
  name: 'SelectDiary',
  data() {
    return {
      update: false,
      diaryForm: {
        diaryIdx: sessionStorage.getItem("diaryIdx"),
        email: this.$store.state.user.email,
        date: store.state.diary.date,
        content: store.state.diary.content,
        face: store.state.diary.face,
        exercise: store.state.diary.exercise,
      }
    }
  },
  methods: {
    async updateDiary(arg) {
      try {
        await axios.put("/api/v1/diary/" + arg, this.diaryForm, {
          headers: this.$store.getters.headers
        })
        alert("수정이 완료되었습니다!");
        router.push('/');
      }
      catch (err) {
        console.log(err);
        if (err.response && err.response.status === 401) {
          try {
            const accessTokenUpdated = await this.$store.dispatch("getAccessToken");
            if (accessTokenUpdated) {
              await this.updateDiary(arg);
            } else {
              store.commit('setAccessTokenAndUser', null);
            }
          } catch (err) {
            console.log(err);
          }
        }
      }
    },

    async deleteDiary(arg) {
      let chk = confirm("정말 삭제하시겠습니까?");
      if (chk) {
        try {
          const res = await axios.delete('/api/v1/diary/' + arg, {
            headers: this.$store.getters.headers
          })
          console.log(res);
          localStorage.clear();
          alert("삭제가 완료되었습니다.");
          router.push('/');
        }
        catch (err) {
          console.log(err);
          if (err.response && err.response.status === 401) {
            try {
              const accessTokenUpdated = await this.$store.dispatch("getAccessToken");
              if (accessTokenUpdated) {
                await this.deleteDiary(arg);
              } else {
                store.commit('setAccessTokenAndUser', null);
              }
            } catch (err) {
              console.log(err);
            }
          }
        }
      }
    }
  }
}
</script>
<style>
.btns_50 {
  width: 50%;
}
</style>