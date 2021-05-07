const debug = false;


import axios from "axios";
import Store from "@/store";

// @ts-ignore: config does exist
const url = "http://localhost:8080/";




const api = axios.create({
    baseURL: url,
})

api.interceptors.request.use((config) => {
    const token = localStorage.getItem("access_token")
    console.log("token is", token)
    if ( token == null){
        if(debug) console.log("token is null");
        return config;
    }

    config.headers.common["Authorization"] = `Bearer ${token}`;
    if(debug) console.log("Authorization Header", config.headers.common["Authorization"]);
    if(debug) console.log("Authorization Header Set");
    return config;
});

api.interceptors.response.use(
    (response) => {
        if(debug) console.log("Request interceptor");
        return response;
    },
    async (error) => {
        if (error.response.status === 401) {
            const authData = Store.getters["auth/getAuthData"];
            console.log("hier", authData)
            const payload = {
                accessToken: authData.token,
                refreshToken: authData.refreshToken,
            };

            let response = await axios.post(
                "http://127.0.0.1:8762/auth/api/v1/auth/refresh",
                payload
            );
            await Store.dispatch("auth/saveTokenDataAction", response.data);
            error.config.headers[
                "Authorization"
                ] = `bearer ${response.data.access_token}`;
            return axios(error.config);
        } else {
            return Promise.reject(error);
        }
    }
);

export default api