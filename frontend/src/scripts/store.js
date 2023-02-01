import {createStore} from 'vuex'

const store = createStore({
    state() {
        return {
            user: {
                id: 0
            }
        }
    },
    mutations: {
        setUser(state, payload){
            state.user.id = payload;
        }

    }
})

export default store;