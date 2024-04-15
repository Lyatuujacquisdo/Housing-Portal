<template>
    <div class="">
        <div class="border-[2px] border-[#51751d] min-w-[300px] w-1/5 flex flex-col hover:shadow-md transition-all duration-100 ease-in">
            <div class="flex flex-row space-x-8 p-4 items-center">
                <span class="material-symbols-outlined text-3xl text-gray-600 border rounded-full py-1 px-2 border-[#51751d]">
                    bed
                </span>
                <div class="font-sans text-lg text-[#51751d] font-medium">
                    Room 3A-1
                </div>
            </div>

            <!-- vacancy -->
            <div class="px-4 text-lg font-medium">
                Vacancy : 4 left
            </div>

            <!-- members -->
            <div class="flex flex-col p-4 space-y-1 border border-gray-200 m-4 rounded-md">
                <span class="underline text-gray-600 text-[18px]">Members</span>
                <div class="flex flex-row justify-between">
                   
                    <div class="flex flex-col text-[16px]">
                        <div>Pius Semufali <span class="text-gray-700">(BSc.SE3)</span> </div>
                        <div>Pius Semufali <span class="text-gray-700">(BSc.SE3)</span> </div>
                        <div>Pius Semufali <span class="text-gray-700">(BSc.SE3)</span> </div>
                        <div>Pius Semufali <span class="text-gray-700">(BSc.SE3)</span> </div>
                    </div>

                    <div class="flex items-end justify-end">
                        <button class="h-10 bg-[#51751d] px-2 rounded-md hover:bg-opacity-80 text-white">Select</button>
                    </div>
                </div>
               
            </div>
        </div>
    </div>
</template>