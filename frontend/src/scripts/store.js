import {createStore} from 'vuex'
import {createVuexPersistedState} from 'vue-persistedstate'

const store = createStore({
    plugins: [createVuexPersistedState()],
    state() {
        return {
            user: {
                email: null,
                name: null
            },
            diary: {
                num: null,
                date: null,
                face: null,
                content: null,
                photo: null,
                photo2: null,
                photo3: null
            }
        }
    },

    mutations: {
        setUser(state, payload){
            state.user.name = payload;
        },
        setDiary(state, payload){
            // state.diary = payload;
            state.diary.num=payload.num;
            state.diary.date=payload.date;
            state.diary.face=payload.face;
            state.diary.content=payload.content;

            if(payload.photo !== null || payload.photo !== ''){
                state.diary.photo=payload.photo;
            }
            if(payload.photo2 !== null || payload.photo2 !== ''){
                state.diary.photo2=payload.photo2;
            }
            if(payload.photo3 !== null || payload.photo3 !== ''){
                state.diary.photo3=payload.photo3;
            }
            

        }
    }
})

export default store;