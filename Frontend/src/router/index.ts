import { createRouter, createWebHistory } from 'vue-router';
import type { RouteRecordRaw } from 'vue-router';
import MainPage from '@/views/MainPage/MainPage.vue'
import Login from '@/views/LoginAndSignup/Login.vue'
import Signup from '@/views/LoginAndSignup/Signup.vue'

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: MainPage,
        // children:[{
        //     path:'/user',
        //     component:Lululu
        // }]
    },
    {
        path: '/login',
        component: Login,
        meta: {
            title: "Login"
        }
    },
    {
        path: '/signup',
        component: Signup,
        meta: {
            title: "Signup"
        }
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
