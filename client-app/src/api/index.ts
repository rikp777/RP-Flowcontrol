import axios from "axios";
import Store from "@/store";

// @ts-ignore: config does exist
const url = "http://localhost:8080/";


const api = axios.create({
    baseURL: url,
})

api.interceptors.request.use((config) => {
    const authData = Store.getters["auth/getAuthData"];
    if (authData == null) {
        return config;
    }

    config.headers.common["Authorization"] = `bearer ${authData.token}`;
    return config;
});

api.interceptors.response.use(
    (response) => {
        return response;
    },
    async (error) => {
        if (error.response.status === 401) {
            const authData = Store.getters["auth/getAuthData"];
            const payload = {
                accessToken: authData.token,
                refreshToken: authData.refreshToken,
            };

            let response = await axios.post(
                "http://127.0.0.1:8762/auth/api/v1/auth/refresh",
                payload
            );
            await Store.dispatch("auth/saveTokenData", response.data);
            error.config.headers[
                "Authorization"
                ] = `bearer ${response.data.access_token}`;
            return axios(error.config);
        } else {
            Store.dispatch('auth/logout')
            return Promise.reject(error);
        }
    }
);

export default api