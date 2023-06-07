import { createStore } from "vuex";
import { createVuexPersistedState } from "vue-persistedstate";
import axios from "axios";
import router from "@/scripts/router";

const store = createStore({
  plugins: [createVuexPersistedState()],
  state() {
    return {
      accessToken: null,
      user: {
        id: 0,
        email: null,
        name: null,
      },
      diary: {
        diaryIdx: null,
        date: null,
        face: null,
        content: null,
        exercise: null,
      },
    };
  },
  getters: {
    isLogin(state) {
      return state.accessToken == null ? false : true;
    },
    headers(state) {
      return {
        Authorization: `Bearer ${state.accessToken}`,
      };
    },
  },
  actions: {
    async getAccessToken({ commit }) {
      try {
        const response = await axios.post("/api/v2/jwt");
        const accessToken = response.data.accessToken;
        commit("setAccessToken", accessToken);
        console.log("Access Token 발급 완료: " + accessToken);
        return true;
      } catch (error) {
        console.log("Access Token 발급 실패: " + error);
        return false;
      }
    },
  },
  mutations: {
    setAccessToken(state, _accessToken) {
      state.accessToken = _accessToken;
    },
    setUser(state, _email) {
      state.user.email = _email;
    },
    setAccessTokenAndUser(state, accessToken) {
      state.accessToken = accessToken;
      state.user.email = null;
      alert('세션 만료로 로그아웃 되었습니다. 다시 로그인 후 이용해주세요');
      location.href="/";
    },
    setDiary(state, payload) {
      state.diary.diaryIdx = payload.diaryIdx;
      state.diary.date = payload.date;
      state.diary.face = payload.face;
      state.diary.content = payload.content;
      state.diary.exercise = payload.exercise;
    },
  },
});

export default store;
