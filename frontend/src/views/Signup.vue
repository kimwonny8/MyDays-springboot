<template>
  <div class="signUpForm">
    <div class="signUpFormInner">
      <div class="signUpInput">
        <p>아이디 :</p>
        <input type="text" class="input" v-model="this.form.email" />
      </div>
      <div class="signUpInput">
        <p>비밀번호 :</p>
        <input type="password" class="input" id="pw" v-model="this.form.password" />
      </div>
      <div class="signUpInput">
        <p>비밀번호 확인 :</p>
        <input type="password" class="input" id="chkpw" />
      </div>
      <div class="signUpInput">
        <p>이름 :</p>
        <input type="text" class="input" v-model="this.form.name" />
      </div>
      <div class="signUpInput">
        <p>생년월일 :</p>
        <input type="text" class="input" v-model.number="this.form.birth" placeholder="ex) 19970117" />
      </div>
      <div>
        <button class="submitBtn" @click="signup()">회원가입</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/scripts/router";

export default {
  data() {
    return {
      form: {
        email: "",
        password: "",
        name: "",
        birth: ""
      },
    }
  },
  methods: {
    signup() {
      if (this.form.email == "" || this.form.password == "" ||
        this.form.name == "") {
        alert("필수 항목을 모두 입력후 진행해주세요.");
      }
      else if (document.getElementById("pw").value !== document.getElementById("chkpw").value) {
        alert("비밀번호가 일치하지 않습니다.");
      }
      else {
        axios.post(`${process.env.VUE_APP_API_PATH}/api/v1/member/register`, this.form)
          .then((res) => {
            router.push({ path: "/" });
            alert("회원가입에 성공하였습니다.");
          }).catch(() => {
            alert("이미 존재하는 아이디입니다.");
          });
      }
    }
  }

}
</script>
<style>
* {
  margin: 0 auto;
}

.signUpForm {
  width: 350px;
  padding: 30px;
  border: 1px solid grey;

}

.signUpFormInner {
  width: 90%;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.signUpInput {
  display: flex;
  flex-direction: row;
}

.signUpInput>p {
  width: 100px;
}
</style>