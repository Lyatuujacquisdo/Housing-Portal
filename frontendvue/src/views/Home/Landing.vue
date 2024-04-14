<template>
    <div class="flex flex-col h-screen">
        <div class="w-3/4 mx-auto h-20 py-6 flex justify-between max-md:hidden">
            <h1 class="text-4xl  font-mono font-medium text-pretty text-[#51751d] transition-all duration-800 ease-in"
            :class="{'pl-32':useRoute().name ==='home'}">HousingApp </h1>
            <div class="flex flex-col">
                <div class="flex flex-row space-x-4 text-gray-700 font-medium text-md">
                    <router-link to="/">Home</router-link>
                    <router-link to="/login">Login</router-link>
                    <router-link to="/register">Register</router-link>
                </div>
                <div class="h-[4px] w-8 bg-[#51751d] rounded-md transition-all duration-300 ease-in-out"
                :class="{'translate-x-[65px]':useRoute().name ==='login','translate-x-[130px]':useRoute().name ==='register','translate-x-2':useRoute().name ==='home'}"
                ></div>
            </div>
        </div>

        <router-view></router-view>

        <myFooter/>
    </div>
</template>

<script setup>
    import {useRoute} from 'vue-router'
    import myFooter from "@/components/myFooter.vue"

</script>

