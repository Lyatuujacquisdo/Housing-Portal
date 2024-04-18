<script setup>
import { ref, computed, watch } from "vue";
import {selectRoom} from "@/utils/serverRequests.js" 
import {useRoute, useRouter} from 'vue-router'

    const vacantRooms = JSON.parse(localStorage.getItem('rooms')) 
    const roomStatus = computed(()=>localStorage.getItem('roomStatus'))
    const roomSelected = computed(()=>localStorage.getItem('roomID'))

    let gender = localStorage.getItem('gender')
    let appropriateBlock = gender === 'male' ? 'A' : 'B';

    let filteredRooms = computed(()=>{
           return vacantRooms?.filter(room => room.side === appropriateBlock) 
    })
    
    //const filteredRooms = computed(vacantRooms.value.filter(room => room.block === appropriateBlock)) ;
    //watch(filteredRooms,()=> console.log(filteredRooms))

    
</script>
<template>
    <div class="w-full h-full overflow-hidden">

        <div v-if="roomStatus == 'NOT_ASSIGNED'" class="w-full h-full">
            <div class="w-1/5 mb-4">
            <label for="countries" class="block mb-2 text-md font-medium text-gray-900">Select Block</label>

            <select id="countries" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                <option selected disabled>Choose Block</option>
                <option value="US">United States</option>
                <option value="CA">Canada</option>
                <option value="FR">France</option>
                <option value="DE">Germany</option>
            </select>
        </div>

        <div class=" w-full h-[88%] flex flex-row flex-wrap items-center mx-auto overflow-auto">
             <div v-for="room in filteredRooms" :key="room.id" class="border-[2px] m-1 border-[#51751d] min-w-[300px] w-1/5 flex flex-col hover:shadow-md transition-all duration-100 ease-in">
                <div class="flex flex-row space-x-8 p-4 items-center">
                    <span class="material-symbols-outlined text-3xl text-gray-600 border rounded-full py-1 px-2 border-[#51751d]">
                        bed
                    </span>
                    <div class="font-sans text-lg text-[#51751d] font-medium">
                    {{room.roomLabel}}
                    </div>
                </div>

                <div class="px-4 text-lg font-medium">
                    Vacancy : {{room.vacancy}} left
                </div>

                <div class="flex flex-col p-4 space-y-1 border border-gray-200 m-4 rounded-md">
                    <span class="underline text-gray-600 text-[18px]">Members</span>
                    <div class="flex flex-row justify-between">
                    
                        <div  v-for="member in room.students" :key="member" class="flex flex-col text-[16px]">
                            <div>{{member.firstName}}  {{member.lastName}} <span class="text-gray-700">({{member.program}}{{ member.yos }})</span> </div>
                        </div>
                        <div class="text-gray-500 text-sm" v-if="room.students.length == 0">
                            No members Yet
                        </div>
                        <div class="flex items-end justify-end">
                            <button @click=" selectRoom(room.roomLabel)"  class="h-10 bg-[#51751d] px-2 rounded-md hover:bg-opacity-80 text-white">Select</button>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        </div>

        <div v-else class="text-[#51751d] text-md font-bold flex justify-center">
            You Already Selected Room&nbsp;<span class="underline">{{roomSelected}} </span>
        </div>
   
    </div>
</template>