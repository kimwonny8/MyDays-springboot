import {createStore} from 'vuex'

const store = createStore({
    state() {
        return {
            user: {
                email: null,
                name: null
            }
        }
    },
    mutations: {
        setUser(state, payload){
            state.user.name = payload;
        }

    }
})

export default store;