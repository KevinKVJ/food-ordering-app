import { createRouter, createWebHistory,RouteRecordRaw } from 'vue-router';
import MainPage from '@/views/PageStyle1.vue'
import Lululu from '@/views/lululu.vue'

const routes:RouteRecordRaw[] = [
    {
        path:'/',
        component:MainPage,
        // children:[{
        //     path:'/user',
        //     component:Lululu
        // }]
    },
    // {
    //     path:'/lululu',
    //     component:Lululu,
    //     name:'lalala'
    //     // meta:{
    //     //     title:"666"
    //     // }
    // }
]

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
