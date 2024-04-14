
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
        children:[
            {
                name:"dashboard",
                path:"",
                component : ProfileView,
            },
            {
                name:"room",
                path:"/rooms",
                component : RoomApplicationView,
            },
            {
                name:"notifications",
                path:"/notifications",
                component : NotificationView,
            },
        ]
    },

]
const router=createRouter({
    history:createWebHistory(), 
    routes
})

export default router