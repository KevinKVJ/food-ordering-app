import { createRouter, createWebHistory } from 'vue-router';
import type { RouteRecordRaw } from 'vue-router';
import MainPage from '@/views/MainPage/MainPage.vue';
import Login from '@/views/LoginAndSignup/Login.vue';
import Signup from '@/views/LoginAndSignup/Signup.vue';
import ProdManagement from '@/views/MainPage/ProductManagement/ProductManagement.vue';
import ODManagement from '@/views/MainPage/OrderManagement/OrderManagement.vue';
import MBaseInfos from '@/views/MainPage/MerchantManagement/MBaseInfos.vue'
import MCategoies from '@/views/MainPage/MerchantManagement/MCategories.vue'

const routes: RouteRecordRaw[] = [
    {
        path: '/',
        component: MainPage,
        redirect:'/prodm',  
        children: [
            {
                path: '/odm',
                component: ODManagement,
            },
            {
                path: '/prodm',
                component: ProdManagement,
            },
            {
                path: '/mbi',
                component: MBaseInfos,
            },
            {
                path: '/mcate',
                component: MCategoies,
            },
        ],
    },
    {
        path: '/login',
        component: Login,
        meta: {
            title: 'Login',
        },
    },
    {
        path: '/signup',
        component: Signup,
        meta: {
            title: 'Signup',
        },
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
