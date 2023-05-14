<template>
    <div class="postForm">
        <h2>일기 쓰기</h2>
        <p>날짜: <input type="date" v-model="diaryForm.date"></p>
        <p>내용: <input type="text" v-model="diaryForm.content"></p>
        <p>운동: <input type="text" v-model="diaryForm.exercise"></p>
        <div class="selectFace">
            <input type="radio" v-model="diaryForm.face" name="face" id="face_happy" value="행복해요"><label for="face_bad">😄</label>
            <input type="radio" v-model="diaryForm.face" name="face" id="face_good" value="좋아요"><label for="face_good">😊</label>
            <input type="radio" v-model="diaryForm.face" name="face" id="face_soso" value="그냥그래요"><label for="face_soso">😶</label>
            <input type="radio" v-model="diaryForm.face" name="face" id="face_hmm" value="음"><label for="face_hmm">🤔</label>
            <input type="radio" v-model="diaryForm.face" name="face" id="face_sad" value="슬퍼요"><label for="face_sad">😭</label>
            <input type="radio" v-model="diaryForm.face" name="face" id="face_angry" value="화나요"><label for="face_angry">😠</label>
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
            diaryForm : {
                email : sessionStorage.getItem("email"),
                date: '',
                content: '',
                face: '',
            }
        }
    },
    methods: {
        post() {
            if(this.diaryForm.date == '' || this.diaryForm.date == null){
                alert("날짜를 선택해주세요!");
            }
            else if(this.diaryForm.face == '' || this.diaryForm.face == null) {
                alert("기분을 선택해주세요!");
            }
            else {
                axios.post("/api/diary/save", this.diaryForm)
                .then(() => {
                    router.push('/');
                    alert("일기가 작성되었습니다!");
                    alert
                })
                .catch(()=> {
                    alert("이미 존재하는 날짜입니다.");
                })
            }
        }
    }
}
</script>
<style>
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
.selectFace > label {
    margin-right: 10px;
}

</style>