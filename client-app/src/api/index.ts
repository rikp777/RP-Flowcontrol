import axios from "axios";
import Store from "@/store";

// @ts-ignore: config does exist
const url = "http://localhost:8080/";


const api = axios.create({
    baseURL: url,
})

axios.interceptors.request.use(
    (config) => {
        if(!config.headers.Authorization){
            const apiToken = Store?.getters?.["user/token"];
            if(apiToken){
                config.headers.common["Authorization"] = apiToken;
            }
        }
        return config;
    },
    (error) => Promise.reject(error)
)

export default api