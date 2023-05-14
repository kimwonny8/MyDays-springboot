import {createStore} from 'vuex'
import {createVuexPersistedState} from 'vue-persistedstate'

const store = createStore({
    plugins: [createVuexPersistedState()],
    state() {
        return {
            user: {
                id: 0,
                email: null,
                name: null
            },
            diary: {
                diaryIdx: null,
                date: null,
                face: null,
                content: null,
                exercise: null,
            }
        }
    },

    mutations: {
        setUser(state, payload){
            state.user.id = payload.id;
            state.user.name = payload.name;
        },
        setDiary(state, payload){
            state.diary.diaryIdx=payload.diaryIdx;
            state.diary.date=payload.date;
            state.diary.face=payload.face;
            state.diary.content=payload.content;
            state.diary.exercise=payload.exercise;
        }
    }
})

export default store;