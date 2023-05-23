import { createStore } from "vuex";
import { createVuexPersistedState } from "vue-persistedstate";
import axios from "axios";

const store = createStore({
  plugins: [createVuexPersistedState()],
  state() {
    return {
      accessToken: null,
      refreshToken: null,
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
    getAccessToken({ commit, state }) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            "/api/v2/jwt",
            {},
            {
              headers: {
                "Authorization-refresh": "Bearer " + state.refreshToken,
              },
            }
          )
          .then((res) => {
            const bearerToken = res.headers["authorization"];
            const accessToken = bearerToken.substring(7);
            commit("setAccessToken", accessToken);
            console.log("accessToken 발급 완료" + accessToken);
            resolve(true);
          })
          .catch((err) => {
            console.log("accessToken 발급 실패" + err);
            reject(false);
          });
      });
    },
  },
  mutations: {
    setRefreshToken(state, _refreshToken) {
      state.refreshToken = _refreshToken;
    },
    setAccessToken(state, _accessToken) {
      state.accessToken = _accessToken;
    },
    setUser(state, payload) {
      state.user.id = payload.id;
      state.user.name = payload.name;
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
