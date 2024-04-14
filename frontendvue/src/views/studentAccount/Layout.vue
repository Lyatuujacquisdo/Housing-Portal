<template>
    <div class="w-full h-screen">

        <div class="flex flex-row">

            <div class="absolute h-auto w-full flex justify-between px-2 max-md:opacity-0 z-20 transition-opacity duration-400 ease-in items-center ">
                <h1 @click="router.push({name:home})" class="text-3xl font-mono font-semibold text-pretty text-[#51751d] transition-all duration-800 p-2 ease-in"
                    >HousingApp </h1>

                <div  class="relative flex flex-row space-x-4 px-3 font-medium text-lg transition duration-300 ease-out">
                    <button @click="showLogoutModal=!showLogoutModal" class="flex flex-row items-center">
                         <div class="p-1 border-2 rounded-full border-gray-600">
                            <svg width="20px" height="20px" class="text-white" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M16 7C16 9.20914 14.2091 11 12 11C9.79086 11 8 9.20914 8 7C8 4.79086 9.79086 3 12 3C14.2091 3 16 4.79086 16 7Z" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                <path d="M12 14C8.13401 14 5 17.134 5 21H19C19 17.134 15.866 14 12 14Z" stroke="#000000" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </svg>
                         </div>
                         <svg class="transition duration-200 ease-in-out" :class="{'translate-y-2':showLogoutModal}" width="30px" height="30px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M12.7071 14.7071C12.3166 15.0976 11.6834 15.0976 11.2929 14.7071L6.29289 9.70711C5.90237 9.31658 5.90237 8.68342 6.29289 8.29289C6.68342 7.90237 7.31658 7.90237 7.70711 8.29289L12 12.5858L16.2929 8.29289C16.6834 7.90237 17.3166 7.90237 17.7071 8.29289C18.0976 8.68342 18.0976 9.31658 17.7071 9.70711L12.7071 14.7071Z" fill="#000000"/>
                        </svg>
                    </button>

                    <div  
                    class=" absolute -bottom-28 rounded -left-36 w-30 bg-[#2248a8] text-white flex flex-col h-24 p-2 transition-all duration-400 ease-in" :class="{' opacity-0 -translate-y-4 cursor-not-allowed hidden':!showLogoutModal,' opacity-1 translate-y-0': showLogoutModal}">
                        <div class="border-b-[1px] border-gray-50 text-sm text-left p-2">pbsemufali21@gmail.com</div>
                        <button @click="logout()" class="flex flex-row items-center space-x-2 p-1 my-1 hover:bg-gray-100 hover:bg-opacity-10  hover:rounded-lg">
                            <!-- <svg width="25px" height="25px" viewBox="0 -0.5 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M11.75 9.874C11.75 10.2882 12.0858 10.624 12.5 10.624C12.9142 10.624 13.25 10.2882 13.25 9.874H11.75ZM13.25 4C13.25 3.58579 12.9142 3.25 12.5 3.25C12.0858 3.25 11.75 3.58579 11.75 4H13.25ZM9.81082 6.66156C10.1878 6.48991 10.3542 6.04515 10.1826 5.66818C10.0109 5.29121 9.56615 5.12478 9.18918 5.29644L9.81082 6.66156ZM5.5 12.16L4.7499 12.1561L4.75005 12.1687L5.5 12.16ZM12.5 19L12.5086 18.25C12.5029 18.25 12.4971 18.25 12.4914 18.25L12.5 19ZM19.5 12.16L20.2501 12.1687L20.25 12.1561L19.5 12.16ZM15.8108 5.29644C15.4338 5.12478 14.9891 5.29121 14.8174 5.66818C14.6458 6.04515 14.8122 6.48991 15.1892 6.66156L15.8108 5.29644ZM13.25 9.874V4H11.75V9.874H13.25ZM9.18918 5.29644C6.49843 6.52171 4.7655 9.19951 4.75001 12.1561L6.24999 12.1639C6.26242 9.79237 7.65246 7.6444 9.81082 6.66156L9.18918 5.29644ZM4.75005 12.1687C4.79935 16.4046 8.27278 19.7986 12.5086 19.75L12.4914 18.25C9.08384 18.2892 6.28961 15.5588 6.24995 12.1513L4.75005 12.1687ZM12.4914 19.75C16.7272 19.7986 20.2007 16.4046 20.2499 12.1687L18.7501 12.1513C18.7104 15.5588 15.9162 18.2892 12.5086 18.25L12.4914 19.75ZM20.25 12.1561C20.2345 9.19951 18.5016 6.52171 15.8108 5.29644L15.1892 6.66156C17.3475 7.6444 18.7376 9.79237 18.75 12.1639L20.25 12.1561Z" fill="#ffffff"/>
                            </svg> -->
                            <p class="text-[14px] text-left">Logout</p> 
                        </button>
                    </div>
                </div>
            </div> 

            <!-- sidebar -->
            <div @click="showLogoutModal = false" class="relative max-md:absolute max-md:z-20 max-md:bg-[#ecf2ff] left-0 z-16 h-full flex flex-col space-y-2 overflow-hidden transition-width duration-300 ease-in pt-[110px]" :class="{'md:w-20 max-md:w-0':!sidebarExpanded,'md:w-60 max-md:w-3/4':sidebarExpanded }">
                <div class="absolute md:hidden top-0 flex flex-row justify-between px-4 w-full"> 
                  
                    
                    <button type="button" class=" p-1 right-0 top-2 ml-auto transition duration-300 ease-in z-16 m-2" :class="{'  bg-white flex items-center justify-center flex-col':!sidebarExpanded}" @click="sidebarExpanded =!sidebarExpanded">
                          <div class="w-[23px] h-[2px] transition ease-in duration-150 bg-[#2248a8] mb-1" :class="{'rotate-45 mb-0 translate-y-[1px]':sidebarExpanded,'rotate-0 mb-1':!sidebarExpanded}"></div>
                          <div class="w-[23px] h-[2px] transition ease-in duration-150 bg-[#2248a8] mb-1" :class="{'hidden mb-0':sidebarExpanded,'mb-1':!sidebarExpanded}"></div>
                          <div class="w-[23px] h-[2px] transition ease-in duration-150 bg-[#2248a8] mb-1" :class="{'-rotate-45 mb-0 translate-y-[-1px]':sidebarExpanded,'rotate-0 mb-1':!sidebarExpanded}"></div>
                   </button>
                </div>

                <router-link 
                :to="{ name: 'notifications' }"
                v-motion
                :initial="{ x: 60 }"
                :enter="{ x: 0, transition: { duration: 1000 } }"
                :class="{'bg-blue-800 bg-opacity-10':useRoute().name==='admTours'}" 
                class="py-1 flex flex-row text-blue-800 px-2 space-x-4  border-[1px] border-transparent hover:border-blue-800 border-b-gray-200  mx-3 rounded-md transition-opacity transition-border duration-150 ease-in">
                    <svg width="35px" height="35px" class="min-w-[35px] min-h-[35px]" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M16 7C16 9.20914 14.2091 11 12 11C9.79086 11 8 9.20914 8 7C8 4.79086 9.79086 3 12 3C14.2091 3 16 4.79086 16 7Z" stroke="#2248a8" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M12 14C8.13401 14 5 17.134 5 21H19C19 17.134 15.866 14 12 14Z" stroke="#2248a8" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    <div :class="{'opacity-0':!sidebarExpanded}" class="text-xl font-semibold mt-auto text-blue-800" >Tours</div>

                </router-link>

                <router-link 
                :to="{ name: 'dashboard' }"
                v-motion
                :initial="{ x: 60 }"
                :enter="{ x: 0, transition: { duration: 1000 } }"
                :class="{ 'bg-blue-800 bg-opacity-10':useRoute().name==='admProfile'}" 
                class="py-1 flex flex-row text-blue-800 px-2 space-x-4 border-[1px] border-transparent hover:border-blue-800  border-b-gray-200  mx-3 rounded-md transition-opacity duration-150 ease-in">
               
                    <svg width="35px" height="35px" class="min-w-[35px] min-h-[35px]" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M16 7C16 9.20914 14.2091 11 12 11C9.79086 11 8 9.20914 8 7C8 4.79086 9.79086 3 12 3C14.2091 3 16 4.79086 16 7Z" stroke="#2248a8" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M12 14C8.13401 14 5 17.134 5 21H19C19 17.134 15.866 14 12 14Z" stroke="#2248a8" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    <span :class="{'opacity-0':!sidebarExpanded}" class="text-xl font-semibold mt-auto text-blue-800" >Profile</span>

                </router-link>

                <router-link 
                :to="{ name: 'room' }"
                v-motion
                :initial="{ x: 60 }"
                :enter="{ x: 0, transition: { duration: 1000 } }"
                :class="{ 'bg-blue-800 bg-opacity-10':useRoute().name==='admProfile'}" 
                class="py-1 flex flex-row text-blue-800 px-2 space-x-4 border-[1px] border-transparent hover:border-blue-800  border-b-gray-200  mx-3 rounded-md transition-opacity duration-150 ease-in">
               
                    <svg width="35px" height="35px" class="min-w-[35px] min-h-[35px]" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M16 7C16 9.20914 14.2091 11 12 11C9.79086 11 8 9.20914 8 7C8 4.79086 9.79086 3 12 3C14.2091 3 16 4.79086 16 7Z" stroke="#2248a8" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        <path d="M12 14C8.13401 14 5 17.134 5 21H19C19 17.134 15.866 14 12 14Z" stroke="#2248a8" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                    <span :class="{'opacity-0':!sidebarExpanded}" class="text-xl font-semibold mt-auto text-blue-800" >Profile</span>

                </router-link>

            </div>


            <div @click="showLogoutModal = false"  class="flex-grow h-full transition-width duration-300 ease-in flex flex-col mt-12 pt-3 rounded-md shadow-lg">
                <nav class="h-4 w-full relative">
                    <button type="button" class="absolute p-1 left-0 top-2 mr-auto transition duration-300 ease-in z-16 m-2" :class="{'  bg-white flex items-center justify-center flex-col':!sidebarExpanded}" @click="sidebarExpanded =!sidebarExpanded">
                          <div class="w-[23px] h-[2px] transition ease-in duration-150 bg-[#2248a8] mb-1" :class="{'rotate-45 mb-0 translate-y-[1px]':sidebarExpanded,'rotate-0 mb-1':!sidebarExpanded}"></div>
                          <div class="w-[23px] h-[2px] transition ease-in duration-150 bg-[#2248a8] mb-1" :class="{'hidden mb-0':sidebarExpanded,'mb-1':!sidebarExpanded}"></div>
                          <div class="w-[23px] h-[2px] transition ease-in duration-150 bg-[#2248a8] mb-1" :class="{'-rotate-45 mb-0 translate-y-[-1px]':sidebarExpanded,'rotate-0 mb-1':!sidebarExpanded}"></div>
                   </button>
                </nav>

                <!-- main content -->
                <div class="flex-grow mt-8 px-4 -z-5 relative max-h-[860px] overflow-x-hidden" :class="{'max-md:blur-sm max-md:pointer-events-none':sidebarExpanded}" >
                    <router-view></router-view> 
                </div>
            </div>
        </div>
    </div>
    
  </template>


<script setup>

    import {ref,onMounted} from 'vue'
    import {useRoute, useRouter} from 'vue-router'
  
    const router = useRouter();
    const showLogoutModal = ref(false)
    const sidebarExpanded = ref(true)

    const logout = ()=>{
        console.log('went through logout function');
        store.logout();
    }

</script>

