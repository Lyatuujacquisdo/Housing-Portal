<template>
    <div v-if="profile" class="w-1/2 min-w-[300px] mx-auto">
        <div class="w-3/4 mx-auto flex flex-col space-y-4 justify-center min-w-[400px]">
                    <div class="border-b-[2px] mb-8 px-2">
                        <label class="mb-4 font-medium text-2xl text-[#51751d]" for="email">Profile</label>
                    </div>
                    <div class="flex flex-row justify-between">
                        <div class="mr-2">
                            <label class="mb-4 font-medium text-lg text-gray-700" for="email">First Name</label>
                            <input
                                disabled
                                type="text"
                                v-model="profile.firstName"
                                placeholder="Email Address"
                                class="block min-h-[auto] border rounded w-full bg-transparent p-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                                id="email"
                            />
                        </div>
                        <div class=" ">
                            <label class="mb-4 font-medium text-lg text-gray-700" for="email">Last Name</label>
                            <input
                                disabled
                                type="text"
                                v-model="profile.lastName"
                                placeholder="Email Address"
                                class="block min-h-[auto] border rounded w-full bg-transparent p-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                                id="email"
                            />
                        </div>
                    </div>

                    <div class=" w-full px-2">
                        <label class="mb-4 font-medium text-lg text-gray-700" for="regNo">Registration Number</label>
                        <input
                            disabled
                            type="text"
                            v-model="profile.registrationNo"
                            placeholder="Email Address"
                            class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                            id="regNo"
                        />
                    </div>

                    <div class=" w-full px-2">
                        <label class="mb-4 font-medium text-lg text-gray-700" for="regNo">Program</label>
                        <input
                            disabled
                            type="text"
                            v-model="profile.program"
                            placeholder="Email Address"
                            class="block min-h-[auto]  w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                            id="regNo"
                        />
                    </div>
                    <div class="">
                        <label class="mb-4 font-medium text-lg text-gray-700 mx-auto" for="email">Email</label>
                        <input
                            disabled
                            type="text"
                            v-model="profile.email"
                            placeholder="Email Address"
                            class="block min-h-[auto]  w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                            id="email"
                        />
                    </div>
                    <div class="">
                        <label class="mb-4 font-medium text-lg text-gray-700" for="email">Gender</label>
                        <input
                            disabled
                            type="text"
                            v-model="profile.gender"
                            placeholder="Email Address"
                            class="block min-h-[auto]  w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                            id="email"
                        />
                    </div>
                    <div class="">
                        <label class="mb-4 font-medium text-lg text-gray-700" for="email">Room <span  :class="{'text-green-600':profile.roomStatus == 'ASSIGNED','text-red-500':profile.roomStatus == 'NOT_ASSIGNED'}" >({{profile.roomStatus}})</span> </label>
                        <input
                            v-if="profile.roomStatus == 'ASSIGNED'"
                            disabled
                            type="text"
                            v-model="profile.roomID"
                            placeholder="Email Address"
                            class="block min-h-[auto] w-full border rounded  bg-transparent px-3 py-[0.32rem]  outline-none transition-max-height duration-300 ease-in-out"
                            id="email"
                        />
                    </div>
        </div>


    </div>
</template>

<script setup>
import { ref } from "vue";
const {profile} = defineProps(['profile'])


</script>