
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


class ApiService {
    constructor(resource){
        this.resource = resource
        // axios.defaults.baseURL = process.env.VUE_APP_ENDPOINT || baseURL;
        this.jwtService = new jwtService();
    }

    setHeader(){
        const authToken = this.jwtService.getToken()

        if(authToken){
            requestSender.defaults.headers.common["Authorization"] = `Bearer ${authToken}`
        }
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

    getByParam(param = "") {
        console.log(`[Flowcontrol] getByParam ${this.resource} with param ${JSON.stringify(param)}`)
        const request = applyConverters(requestSender)
            .get(`${this.resource}${param}`)
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
        console.log(message)
        const request = applyConverters(requestSender)
            .get(`${this.resource}/${slug}`)
            .catch(error => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
        return request;
    }

    post(params) {
        console.log(`[Flowcontrol] post ${this.resource} with params ${JSON.stringify(params)}`)

        const request = applyConverters(requestSender)
            .post(`${this.resource}`, params[0])
            .catch((error) => {
                throw `[Flowcontrol] ApiService ${this.resource} \n ${error}`;
            });
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

    getId(){
        let id = 0
        if(this.resource){
            let segments = this.resource.split("/")
            return segments[segments.length - 1];
        }
        return id
    }


}

export default ApiService;