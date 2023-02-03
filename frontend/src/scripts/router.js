import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Home from "@/views/Home";
import Login from "@/views/Login";
import Signup from "@/views/Signup";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/signup', component: Signup}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;