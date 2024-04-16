
import {createRouter, createWebHistory} from "vue-router"

import LandingView from "@/views/Home/Landing.vue"
import HomeView from "@/views/Home/Home.vue"
import LoginView from "@/views/authentication/Login.vue"
import RegisterView from "@/views/authentication/Register.vue"
import LayoutView from "@/views/studentAccount/Layout.vue"
import ProfileView from "@/views/studentAccount/Profile.vue"
import RoomApplicationView from "@/views/studentAccount/RoomApplication.vue"
import NotificationView from "@/views/studentAccount/Notification.vue"
const routes =[
    {
        name:"landing",
        path:"/",
        component : LandingView,
        children :[
            {
                name:"home",
                path:"",
                component : HomeView,
            },
            {
                name:"login",
                path:"/login",
                component : LoginView,
            },
            {
                name:"register",
                path:"/register",
                component : RegisterView,
            },
        ] 
    },
  
    {
        name:"userAccount",
        path:"/acc",
        component : LayoutView,
        meta: {requiresAuth : true},
        children:[
            {
                name:"dashboard",
                path:"",
                component : ProfileView,
                meta: {requiresAuth : true},
            },
            {
                name:"room",
                path:"/rooms",
                component : RoomApplicationView,
                meta: {requiresAuth : true},
            },
            {
                name:"notifications",
                path:"/notifications",
                component : NotificationView,
                meta: {requiresAuth : true},
            },
        ]
    },

]
const router=createRouter({
    history:createWebHistory(), 
    routes
})

router.beforeEach((to, from, next) => {
    // to and from are both route objects. must call `next`.
    if(to.meta.requiresAuth && !localStorage.getItem('token')){
        //if it requires auth and theres no token
        next('/login');
        return;
    }
    next()
})

export default router