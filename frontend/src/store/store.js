import { createApp } from 'vue'
import Vuex from 'vuex'
import storage from "./modules/storage";
import * as getters from "./modules/getters";
import * as mutations from "./modules/mutations";

const app = createApp();
app.use(Vuex);

export const store = new Vuex.Store({
    // state는 컴포넌트 간에 공유할 data 속성
    state: {
        userList : storage.fetch(),
        //userName: storage.fetchName(),
    },
    getters: getters,
    mutations: mutations
});