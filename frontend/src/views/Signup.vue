<template>
    <div>
        <h1>회원가입</h1>
        <p>* 이메일: <input type="email" v-model="state.form.email"></p>
        <p>* 비밀번호: <input type="password" v-model="state.form.password" id="pw"></p>
        <p>* 비밀번호 확인: <input type="password" id="chkpw"></p>
        <p>* 일기이름: <input type="text" v-model="state.form.name"></p>
        <p>생년월일: <input type="text" v-model="state.form.birth"></p>
        <button @click="signup()">회원가입</button>
    </div>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";
import router from "@/scripts/router";

export default {
    setup() {
        const state = reactive({
            form: {
                email: "",
                password: "",
                name: "",
                birth: ""
            },
        })

        const signup = () => {
            if(state.form.email == "" || state.form.password == "" ||
            state.form.name == "") {
                alert("필수 항목을 모두 입력후 진행해주세요.");
            }
            else if(document.getElementById("pw").value !== document.getElementById("chkpw").value){
                alert("비밀번호가 일치하지 않습니다.");
            }
            else {
            axios.post("/api/user/signup", state.form).then((res) => {
                //store.commit('setUser', res.data);
                router.push({ path: "/" });
                window.alert("회원가입에 성공하였습니다.");
            }).catch(() => {
                window.alert("회원가입에 실패하였습니다.");
            });
        }
    }
            return { state, signup }
    
}
}
</script>