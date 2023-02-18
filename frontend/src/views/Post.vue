<template>
    <div>
        <p>날짜: <input type="date" v-model="diaryForm.date"></p>
        <p>내용: <input type="text" v-model="diaryForm.content"></p>
        <p>기분: <input type="radio" v-model="diaryForm.face" name="face" id="face_good" value="good"><label for="face_good">좋아요</label>
        <input type="radio" v-model="diaryForm.face" name="face" id="face_bad" value="bad"><label for="face_bad">나빠요</label></p>
        <p>사진: <input type="file" @change="setImage" accept="image/*" id="diaryImage" multiple></p>
        <button @click="post()">작성 완료</button>
    </div>
</template>
<script>
import {reactive} from "vue";
import store from "@/scripts/store";
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
                Image: '',
            }
        }
    },
    methods: {
        setImage(){
            let frm = new FormData();
            let ImageFile = document.getElementById("diaryImage");
            console.log(ImageFile.files[0]);

            frm.append("diaryImage", ImageFile.files[0]);
            console.log(frm);

            axios.post("/api/image/save", frm, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then(()=>{
                console.log("1");
            })
            .catch((e)=> {
                console.log(e);
            })
        },
        post() {
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
</script>