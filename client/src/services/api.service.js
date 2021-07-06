
import jwtService from "@/services/jwt.service";
import applyConverters from "axios-case-converter";
const baseDomain = "http://127.0.0.1"; //dont delete the http:// :)
const baseDomainPort = 8762


import axios from "axios";

const baseURL = `${baseDomain}:${baseDomainPort}`;
const requestSender = axios.create({
    baseURL: `${baseURL}`,
    /* other custom settings */
});

requestSender.interceptors.request.use((config) => {
    const debug = false;
    let token = new jwtService().getAuthToken()
    if(debug) console.log("token is", token)
    if ( token == null){
        console.log("token is null");
        return config;
    }
    config.headers.common["Accept"] = "application/json"
    config.headers.common["Content-Type"] = "application/json"
    //config.headers.common["Authorization"] = `Bearer ${token}`;

    if(debug) console.log("Authorization Header", config.headers.common["Authorization"]);
    return config;
});

requestSender.interceptors.response.use(
    (response) => {
        console.log("Request interceptor");
        return response;
    },
    async (error) => {
        if (error.response.status === 401) {
            const authData = this.$store.getters["auth/getAuthData"];
            const payload = {
                accessToken: authData.token,
                refreshToken: authData.refreshToken,
            };

            let response = await axios.post(
                "http://127.0.0.1:8762/auth/api/v1/auth/refresh",
                payload
            );
            await this.$store.dispatch("auth/saveTokenDataAction", response.data);
            // error.config.headers[
            //     "Authorization"
            //     ] = `bearer ${response.data.access_token}`;
            return axios(error.config);
        } else {
            return Promise.reject(error);
        }
    }
);


class ApiService {
    constructor(resource){
        this.resource = resource
        // axios.defaults.baseURL = process.env.VUE_APP_ENDPOINT || baseURL;
        this.jwtService = new jwtService();
        requestSender.defaults.headers.common["Accept"] = `application/json`
        requestSender.defaults.headers.common["Content-Type"] = `application/json`
    }

    setHeader(){
        const authToken = this.jwtService.getAuthToken()

        // if(authToken){
        //     requestSender.defaults.headers.common["Authorization"] = `Bearer ${authToken}`
        // }
        requestSender.defaults.headers.common["Accept"] = `application/json`
        requestSender.defaults.headers.common["Content-Type"] = `application/json`

        //demo
        requestSender.defaults.headers.common["Authorization"] = `Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE2MjMwNTgxNDEsImV4cCI6MTk5OTk5OTk5NywiYXVkIjoidGVzdEBmbG93Y29udHJvbC5jb20ifQ.yXx2fU3NWFCg5ETbZy6zo1_oVaN2xUa_yOE27VuOYiYQ27T1fmb72y8mJFPTxnFnfXDEqtRubcTI1gGENzFZTA`
    }

    query(params = "") {
        console.log(`[Flowcontrol] query ${this.resource} with param ${JSON.stringify(params)}`)

        const request = applyConverters(requestSender)
            .get(`${this.resource}`, {params})
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
        return request;
    }

    getByParams(params = {}) {
        console.log(`[Flowcontrol] getByParam ${this.resource} with param ${JSON.stringify(params)}`)
        const request = applyConverters(requestSender)
            .get(`${this.resource}`, { params })
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
        return request;
    }

    get(slug = "") {
        let message = `[Flowcontrol] get ${this.resource}`
        if(slug != ""){
            message = message + `with slug ${slug}`
        }
        let final_resouce = `${this.resource}`
        if(slug != ""){
            final_resouce = `${this.resource}/${slug}`
        }

        console.log(message)
        const request = applyConverters(requestSender)
            .get(final_resouce)
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
        return request;
    }

    post(data, params = null) {
        console.log(params)
        console.log(`[Flowcontrol] post ${this.resource} with params ${JSON.stringify(params)}`)
        const request = applyConverters(requestSender)
            .post(`${this.resource}`,data, { params })
            .catch((error) => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
        console.log(request)
        return request;
    }

    update(slug, params) {
        console.log(`[Flowcontrol] update ${this.resource} with slug ${slug} and params`, params)
        const request = applyConverters(requestSender)
            .put(`${this.resource}/${slug}`, params)
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource}/${slug} \n ${error}`;
            });
        return request;
    }

    put(slug, params) {
        console.log(`[Flowcontrol] update ${this.resource} with params ${params}`)
        const request = applyConverters(requestSender)
            .put(`${this.resource}/${slug}`, params)
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
        return request
    }

    delete(slug) {
        console.log(`[Flowcontrol] update ${this.resource} with slug ${slug}`)
        const request = applyConverters(requestSender)
            .delete(`${this.resource}/${slug}`)
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource}/${slug} \n ${error}`;
            });
        return request
    }

    static getId(resource){
        let id = 0
        if(resource){
            let segments = resource.split("/")
            return segments[segments.length - 1];
        }
        return id
    }


}

export default ApiService;