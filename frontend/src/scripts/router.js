import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Home from "@/views/Home";
import Login from "@/views/Login";
import Signup from "@/views/Signup";
import Calendar from "@/views/Calendar";
import Post from "@/views/Post";
import SelectDiary from "@/views/SelectDiary"

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/signup', component: Signup},
    {path: '/calendar', component: Calendar},
    {path: '/post', component: Post},
    {path: '/selectDiary', component: SelectDiary},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;