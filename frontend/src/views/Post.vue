<template>
    <div>
        <h1>작성 테스트</h1>
        <p>날짜: <input type="date" v-model="postForm.date"></p>
        <p>내용: <input type="text" v-model="postForm.content"></p>
        <p>기분: <input type="radio" v-model="postForm.face" name="face" id="face_good" value="good"><label for="face_good">좋아요</label>
        <input type="radio" v-model="postForm.face" name="face" id="face_bad" value="bad"><label for="face_bad">나빠요</label></p>
        <p>사진1: <input type="text" v-model="postForm.photo"></p>
        <p>사진2: <input type="text" v-model="postForm.photo2"></p>
        <p>사진3: <input type="text" v-model="postForm.photo2"></p>
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
            postForm : {
                email : sessionStorage.getItem("email"),
                date: '',
                content: '',
                face: '',
                photo: '',
                photo2: '',
                photo3: '',
            }
        }
    },
    methods: {
        post() {
            console.log(this.postForm);
            axios.post("/api/diary/post", this.postForm)
            .then(() => {
                this.$router.push('/calendar');
                alert("작성 완료");
            })
            .catch(()=> {
                alert("이미 존재하는 날짜입니다.");
            })
        }
    }
}
</script>