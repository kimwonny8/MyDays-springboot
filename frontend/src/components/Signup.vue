<template>
    <div>
      <br />
      <div class="signUpInput">
        <p>아이디 :</p>
        <input type="text" class="input" v-model="id" />
      </div>
      <div class="signUpInput">
        <p>비밀번호 :</p>
        <input type="password" class="input" v-model="pw" />
      </div>
      <div class="signUpInput">
        <p>비밀번호 확인 :</p>
        <input type="password" class="input" v-model="pwChk" />
      </div>
      <div class="signUpInput">
        <p>이름 :</p>
        <input type="text" class="input" v-model="name" />
      </div>
      <div class="signUpInput">
        <p>생년월일 :</p>
        <input
          type="text"
          class="input"
          v-model.number="birth"
          placeholder="ex) 19970117"
        />
      </div>
      <div>
        <button class="submitBtn" @click="signUp">회원가입</button>
      </div>
    </div>
  </template>

  <script>
  export default {
    name: "signup",
    data() {
      return {
        id: "",
        pw: "",
        pwChk: "",
        name: "",
        birth: ""
      };
    },
    computed: {
      data(){
        return {
          id: this.id, 
          pw: this.pw, 
          name: this.name, 
          birth: this.birth
      }
    },
    methods: {
      signUp(){
        if (this.id == "" || this.pw == "" || this.pwChk == "" || this.name == "" || this.birth == "") {
          alert("모든 항목을 입력해주세요.");
        } else if (this.pw !== this.pwChk ) {
          alert("비밀번호가 일치하지 않습니다.");
        } else {
          alert("회원가입 완료! 로그인 후 사용해주세요!");
          // alert(this.$store.state.userList);
          this.$store.commit("signup", this.data);
        }
      }
    }
  };
  </script>
  <style>
  *{
      margin: 0 auto;
  }
  .signUpForm {
    width: 30vw;
    padding: 3vh;
    border: 0.01rem solid grey;
    display: flex;
    flex-direction: column;
  }
  .signUpInput {
    width: 20vw;
    padding: 1vh;
    margin-top: 1vh;
    display: flex;
    flex-direction: row;
  }
  .signUpInput > p {
    font-size: large;
    width: 50%;
  }
  
  .submitBtn {
    font-family: 'KyoboHand';
    font-size: large;
    margin: 3vh 1vh;
    width: 10vw;
    height: 4vh;
    border: none;
    background-color: rgb(226, 226, 226);
    border-radius: 10px;
  }
  .submitBtn:hover {
    border: 0.01rem solid gray;
    background-color:  white;
  }
  </style>