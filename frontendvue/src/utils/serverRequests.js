import axios from "axios";
import router from "@/router";  


export const getRouterInstance = ()=>{return useRouter()}

export const getProfile =async ()=>{
    return await axios.post(import.meta.env.VITE_RES_BASE_URL+'/profile',null,
    {
        headers: {
            Authorization : 'Bearer '+ localStorage.getItem('token'),
        }
    }
    ).then((response)=>{

        if(response.data.registrationNo == null){
            notifyError("Session Expired, Login!")
            logout();
        }
        
        else
        {
            localStorage.setItem('email', response.data.email);
            localStorage.setItem('gender',response.data.gender);
            localStorage.setItem('roomStatus',response.data.roomStatus);
            localStorage.setItem('roomID', response.data.roomID)
            return response.data
        }
    })
    .catch((error)=>{
        logout();
        console.log('Error: '+ error.message);
    })
    }

export const logout = ()=>{
        localStorage.clear()
        router.push('/')
}

export const getVacantRooms =async ()=>{
    await axios.get(import.meta.env.VITE_RES_BASE_URL+'/vacant',
    {
        headers: {
            Authorization : 'Bearer '+ localStorage.getItem('token'),
        }
    }
    ).then((response)=>{

        if(response.data == null)
            notifyError("Server Error, contact Admin!")
        
        else
        {
            localStorage.setItem('rooms',JSON.stringify(response.data) );
        }
    })
    .catch((error)=>{
        console.log('Error: '+ error.message)
    })

}

export const selectRoom = async (label)=>{
    console.log(label);
    await axios.post(import.meta.env.VITE_RES_BASE_URL+'/select',null,
        {
            headers: {
                Authorization : 'Bearer '+ localStorage.getItem('token'),
            },
            params: {
                roomLabel: label
            }
        },
      
    ).then(async(response)=>{
         await getProfile();

        if(response.data.registrationNo == null){
            notifyError("Session Expired, Login!")
        }
        
        else
        {
            console.log('something something');
        }
    })
    .catch((error)=>{
        console.log('Error: '+ error.message);
    })
}