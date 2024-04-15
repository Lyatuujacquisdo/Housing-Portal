<script setup>
    import {ref, watchEffect, watch} from "vue"
    import {notifyError, notifySuccess} from "@/utils/notificationService.js"
    import axios from "axios"
    import { useRouter } from "vue-router"

    const router = useRouter();

    const registerData = ref({
      registrationNo:"",
      email:"",
      password:"",
      program:"",
      yos:"",
      firstName:"",
      lastName:"",
      gender:""
    })

    const reset = ()=>{
      registerData.value.registrationNo = ""
      registerData.value.program = ""
      registerData.value.yos = ""
      registerData.value.firstName = ""
      registerData.value.lastName = ""
      registerData.value.gender = ""
      registerData.value.email = ""
      registerData.value.password = ""
      registerData.value.confirmPassword = ""
    }

  const isPasswordValid = ref(true);

  const validationMessage = ref("")

    const getValidation = ()=> {
      const messages = [];
      let isValid = true;

      if(!registerData.value.password){
        return
      }
      if (!/(?=.*[a-z])/.test(registerData.value.password)) {
        isValid = false;
        messages.push('at least one lowercase letter');
      }
      if (!/(?=.*[A-Z])/.test(registerData.value.password)) {
        isValid = false;
        messages.push('at least one uppercase letter');
      }
      if (!/(?=.*\d)/.test(registerData.value.password)) {
        isValid = false;
        messages.push('at least one digit');
      }
      if (!/(?=.*[@$!%*?&])/.test(registerData.value.password)) {
        isValid = false;
        messages.push('at least one symbol');
      }

      if(messages.length > 0)
        validationMessage.value = `Password must contain ${messages.join(', ')}.`;
      else{
        validationMessage.value = ""
      }

      if(registerData.value.confirmPassword && registerData.value.confirmPassword !== registerData.value.password){
          validationMessage.value = validationMessage.value + " Passwords do not match"
      }

      return isValid ;
    }

    watchEffect(()=>{
       isPasswordValid.value = getValidation();
    })

      const register = ()=>{
          if(!isPasswordValid.value){
              return
          } 

          delete registerData.value.confirmPassword 
          axios.post(import.meta.env.VITE_AUTH_BASE_URL+'/register',registerData.value).then((response)=>{
          reset()
        
          if(response.data.token == null)
              notifyError("User Already Exists!")
          
          else
          {
              notifySuccess("Successfully Registered")
              localStorage.setItem('token', response.data.token);
              localStorage.setItem('email', response.data.registrationNo);
              router.push('/login')
          }
          })
          .catch((error)=>{
            notifyError(error.message)
              console.log('Error: '+ error.message)
          })
      }

</script>
<template>
    <div class="flex flex-grow flex-col md:flex-row w-3/4 mx-auto">
        <div 
        v-motion
        :initial="{ x: 60 }"
        :visible="{ x: 0, transition: { duration: 1000 } }"
        class="w-full md:w-2/5 min-w-[560px] h-full flex flex-col justify-center max-md:hidden">
            <img src="@/assets/images/Moving-pana.svg" alt="">
        </div>
        <div
        v-motion
        :initial="{ x: -60 }"
        :visible="{ x: 0, transition: { duration: 1000 } }"
        class="w-full md:w-3/5 ml-4 min-w-[380px] md:h-full flex justify-center p-4">
            <div class=" mx-auto border-[2px] border-gray-200 shadow-sm flex justify-center items-center ">
                <form @submit.prevent="register()" class="form flex flex-col gap-[10px] relative rounded-sm p-[20px] h-full py-10 mx-auto items-center justify-center overflow-hidden">
                    <p class="title">Register</p>
                    <p class="message">Sign up now and get full access to our app. </p>
                    <div class="flex justify-center">
                        <label>
                            <input v-model="registerData.firstName" required placeholder="" type="text" class="input">
                            <span>Firstname</span>
                        </label>

                        <label >
                            <input v-model="registerData.lastName" required placeholder="" type="text" class="input" >
                            <span>Lastname</span>
                        </label>
                    </div>   
                    <label class="w-full">
                        <input v-model="registerData.email" required placeholder="" type="email" class="input">
                        <span>Email</span>
                    </label> 
                    <div class="flex justify-center">
                        <label>
                            <input v-model="registerData.program" required placeholder="" type="text" class="input">
                            <span>Program</span>
                        </label>

                        <label >
                            <input v-model="registerData.yos" required placeholder="" type="text" class="input" >
                            <span>Year Of Study</span>
                        </label>
                    </div>  
                    <label class="w-full">
                        <input v-model="registerData.registrationNo" required placeholder="" type="text" class="input">
                        <span>Registration Number</span>
                    </label>
                    <label class="w-full">
                        <input v-model="registerData.gender" required placeholder="" type="text" class="input">
                        <span>Gender</span>
                    </label>
                        
                    <label class="w-full">
                        <input v-model="registerData.password" required placeholder="" type="password" class="input">
                        <span>Password</span>
                    </label>
                    <label class="w-full">
                        <input v-model="registerData.confirmPassword" required placeholder="" type="password" class="input">
                        <span>Confirm password</span>
                    </label>

                    <div class="w-80 h-auto text-sm font-bold text-[#51751d] text-wrap transition-all duration-300 ease-in">
                        {{ validationMessage }}
                    </div>
                    <div class="flex flex-col w-full space-y-3">
                        <button class="submit">Submit</button>
                        <p class="signin">Already have an account ? <router-link to="/login" class="text-royalblue">Sign In</router-link> </p>
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