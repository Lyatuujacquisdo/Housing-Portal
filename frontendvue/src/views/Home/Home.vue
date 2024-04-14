
<template>
    <div v-motion-pop-visible class="flex flex-grow flex-col md:flex-row w-3/4 mx-auto">
        <div class="w-full md:w-3/5 min-w-[560px] h-full flex flex-col justify-center">
            <div class="text-4xl text-pretty md:py-10 md:px-32 py-5"> 
                If you are looking for the easiest way to apply for Housing,
                this is definitely the place for you!
            </div>
            <div class="text-xl text-gray-900 px-32">
                Fast, Secure and Convienient.
            </div>
            <div class="flex flex-row w-full md:px-32 items-center justify-left my-4 space-x-6 ">
                <button @click="router.push({name:'login'})" class="px-4 py-2 border-[2px] border-gray-900 hover:shadow-lg rounded-sm transition-all duration-200 ease-in-out">
                    Login
                </button>
                

                <button @click="router.push({name:'register'})" class="px-4 py-2 border-[2px] bg-[#51751d] text-white  hover:shadow-lg rounded-sm transition-all duration-200 ease-in-out">
                    Register
                </button>
            </div>
        </div>
        <div class="w-full md:w-2/5 ml-4 min-w-[380px] md:h-full flex justify-center">
            <img src="@/assets/images/Moving-pana.svg" alt="">
        </div>
    </div>
</template>

<script setup>
import {useRouter} from "vue-router"
const router = useRouter();
</script>
