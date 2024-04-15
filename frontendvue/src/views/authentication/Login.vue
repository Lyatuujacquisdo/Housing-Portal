
<script setup>
import {ref} from "vue"
import {notifyError} from "@/utils/notificationService.js"
import axios from "axios"
import { useRouter } from "vue-router"

const router = useRouter();
const loginData = ref({
  registrationNo:"",
  password:""
})

const reset = ()=>{
  loginData.value.registrationNo = ""
  loginData.value.password = ""
}

const login = ()=>{    
      axios.post(import.meta.env.VITE_AUTH_BASE_URL+'/login',loginData.value).then((response)=>{
      reset()
    
      if(response.data.token == null)
          notifyError("INCORRECT USERNAME OR PASSWORD")
      
      else
      {
          localStorage.setItem('token', response.data.token);
          localStorage.setItem('email', response.data.registrationNo);
          router.push('/acc')
      }
  
      })
      .catch((error)=>{
          console.log('Error: '+ error.message)
      })
  }

</script>
<template>
    <div class="flex flex-grow flex-col md:flex-row w-3/4 mx-auto">
        <div
        v-motion
        :initial="{ x: -60 }"
        :visible="{ x: 0, transition: { duration: 1000 } }"
        class="w-full md:w-2/5 min-w-[560px] h-full flex flex-col justify-center max-md:hidden">
            <img src="@/assets/images/Moving-pana.svg" alt="">
        </div>
        <div 
        v-motion
        :initial="{ x: 60 }"
        :visible="{ x: 0, transition: { duration: 1000 } }"
        class="w-full md:w-3/5 min-w-[380px] md:h-full flex p-4">
            <div class="w-1/2 mx-auto border-[2px] border-gray-200 shadow-sm flex justify-center items-center ">

                <form @submit.prevent="login()"  class="form flex flex-col gap-[10px] relative rounded-sm p-[20px] h-full py-10 mx-auto space-y-2 items-center overflow-hidden w-full ">
                    <p class="title">Login</p>
                    <p class="message">Sign in now and enjoy the services of our app. </p>
                    
                        <label class="w-full">
                            <input required v-model="loginData.registrationNo" placeholder="" type="text" class="input w-full">
                            <span>Username</span>
                        </label> 
                            
                        <label class="w-full">
                            <input required v-model="loginData.password" placeholder="" type="password" class="input ">
                            <span>Password</span>
                        </label>
                     
                        <div class="flex flex-col w-full space-y-3">
                            <button class="submit">Sign In</button>
                            <p class="signin">Don't have an account yet? <router-link to="/login" class="text-royalblue">Sign Up</router-link> </p>
                        </div>
                     
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped>


.title {
  font-size: 28px;
  color: #51751d;
  font-weight: 600;
  letter-spacing: -1px;
  position: relative;
  display: flex;
  align-items: center;
  padding-left: 30px;
}

.title::before,.title::after {
  position: absolute;
  content: "";
  height: 16px;
  width: 16px;
  border-radius: 50%;
  left: 0px;
  background-color: #51751d;
}

.title::before {
  width: 18px;
  height: 18px;
  background-color: #51751d;
}

.title::after {
  width: 18px;
  height: 18px;
  animation: pulse 1s linear infinite;
}

.message, .signin {
  color: rgba(88, 87, 87, 0.822);
  font-size: 14px;
}

.signin {
  text-align: center;
}



.signin a:hover {
  text-decoration: underline #51751d;
}


.form label {
  position: relative;
}

.form label .input {
  width: 100%;
  padding: 10px 10px 20px 10px;
  outline: 0;
  border: 1px solid rgba(105, 105, 105, 0.397);
  border-radius: 10px;
}

.form label .input + span {
  position: absolute;
  left: 10px;
  top: 15px;
  color: grey;
  font-size: 0.9em;
  cursor: text;
  transition: 0.3s ease;
}

.form label .input:placeholder-shown + span {
  top: 15px;
  font-size: 0.9em;
}

.form label .input:focus + span,.form label .input:valid + span {
  top: 30px;
  font-size: 0.7em;
  font-weight: 600;
}

.form label .input:valid + span {
  color: green;
}

.submit {
  border: none;
  outline: none;
  background-color: #51751d;
  padding: 10px;
  border-radius: 10px;
  color: #fff;
  font-size: 16px;
  transform: .3s ease;
}

.submit:hover {
  background-color:rgb(67, 109, 9)
}

@keyframes pulse {
  from {
    transform: scale(0.9);
    opacity: 1;
  }

  to {
    transform: scale(1.8);
    opacity: 0;
  }
}

</style>