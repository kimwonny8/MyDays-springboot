import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Home from "@/views/Home";
import Signup from "@/views/Signup";
import Post from "@/views/Post";
import SelectDiary from "@/views/SelectDiary"
import DiaryList from "@/views/DiaryList"

const routes = [
    {path: '/', component: Home},
    {path: '/signup', component: Signup},
    {path: '/post', component: Post},
    {path: '/selectDiary', component: SelectDiary},
    {path: '/diaryList', component:DiaryList}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;