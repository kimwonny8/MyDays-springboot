<template>
    <div>
        <!-- 조회 및 삭제 -->
        <div v-if="!update">
            <p>날짜: {{ this.diaryForm.date }}</p>
            <p>기분: {{ this.diaryForm.face }}</p>
            <p>내용: {{ this.diaryForm.content }}</p>
            <p v-if="this.diaryForm.photo!=''">사진1: {{ this.diaryForm.photo }}</p>
            <p v-if="this.diaryForm.photo2!=''">사진2: {{ this.diaryForm.photo2 }}</p>
            <p v-if="this.diaryForm.photo3!=''">사진3: {{ this.diaryForm.photo3 }}</p>
            <button @click="this.update=!!!this.update">수정</button>
            <button @click="deleteDiary(this.num)">삭제</button>
        </div>

        <!-- 수정하기 -->
        <div v-else>
            <p>날짜: <input type="date" v-model="diaryForm.date" disabled></p>
            <p>내용: <input type="text" v-model="diaryForm.content"></p>
            <p>기분: <input type="radio" v-model="diaryForm.face" name="face" id="face_good" value="good"><label for="face_good">좋아요</label>
            <input type="radio" v-model="diaryForm.face" name="face" id="face_bad" value="bad"><label for="face_bad">나빠요</label></p>
            <p>사진1: <input type="text" v-model="diaryForm.photo"></p>
            <p>사진2: <input type="text" v-model="diaryForm.photo2"></p>
            <p>사진3: <input type="text" v-model="diaryForm.photo3"></p>
            <button @click="updateDiary(this.num)">수정 완료</button>
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
            num: sessionStorage.getItem("num"),
            update: false,
            diaryForm : {
                num: store.state.diary.num,
                email : sessionStorage.getItem("email"),
                date: store.state.diary.date,
                content: store.state.diary.content,
                face: store.state.diary.face,
                photo: store.state.diary.photo,
                photo2: store.state.diary.photo2,
                photo3: store.state.diary.photo3,
            }
        }
    },
    methods: {
        // 수정
        updateDiary(arg){
            console.log("ddd"+this.diaryForm);
            axios.put("/api/diary/update/"+arg, this.diaryForm)
            .then(() => {
                router.push('/calendar');
                alert("수정 완료");
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
                    router.push('/');
                    alert("삭제가 완료되었습니다.");
                })
                .catch((e)=>{
                    console.log(e);
                    alert("삭제 실패");
                })
               
            }
        },
      
    }
}
</script>