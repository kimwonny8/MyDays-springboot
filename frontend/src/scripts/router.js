import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import Home from "@/views/Home";
import Login from "@/views/Login";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;