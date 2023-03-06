<template>
    <div>
        <!-- 조회 및 삭제 -->
        <div v-if="!update" class="postForm">
            <p>날짜: {{ this.diaryForm.date }}</p>
            <p>내용: {{ this.diaryForm.content }}</p>
            <p>운동: {{this.diaryForm.exercise  }}</p>
            <p>기분: {{ this.diaryForm.face }}</p>
            <div class="btns btns_50">
                <button class="submitBtn" @click="this.update=!!!this.update">수정</button>
                <button class="submitBtn" @click="deleteDiary(this.diaryForm.diaryIdx)">삭제</button>
            </div>
        </div>

        <!-- 수정하기 -->
        <div v-else class="postForm">
            <p>날짜: <input type="date" v-model="diaryForm.date" disabled></p>
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
            diaryForm : {
                diaryIdx: sessionStorage.getItem("diaryIdx"),
                email : sessionStorage.getItem("email"),
                date: store.state.diary.date,
                content: store.state.diary.content,
                face: store.state.diary.face,
                exercise: store.state.diary.exercise,
            }
        }
    },
    methods: {
        // 수정
        updateDiary(arg){
            axios.put("/api/diary/update/"+arg, this.diaryForm)
            .then(() => {
                alert("수정이 완료되었습니다!");
                router.push('/');
            })
            .catch(() => {
                alert("수정에 실패하였습니다.")
            })
        },
        // 삭제
        deleteDiary(arg) {
            let chk = confirm("정말 삭제하시겠습니까?");
            if(chk) {
                // 디비 삭제코드
                axios.delete('/api/diary/delete/'+arg)
                .then((res)=>{
                    console.log(res);
                    localStorage.clear();
                    alert("삭제가 완료되었습니다.");
                    router.push('/');
                })
                .catch((e)=>{
                    console.log(e);
                    alert("삭제에 실패하였습니다.");
                })
               
            }
        },
      
    }
}
</script>
<style>
.btns_50{
    width: 50%;
}
</style>