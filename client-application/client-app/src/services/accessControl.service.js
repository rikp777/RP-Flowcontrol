import apiService from '@/services/api.service'
import Store from "@/store/index";
import jwtService from "@/services/jwt.service"


class AccessControl {
    constructor(){
        this.debug = false;
        this.api = new apiService();
        this.jwtService = new jwtService()
    }
    router(store, router){
        router.beforeEach(async (to, from, next) => {

            if (!Store.getters["auth/getAuthData"].token) {
                const accessToken = this.jwtService.getAuthToken()
                const refreshToken = this.jwtService.getRefreshToken()
                if(accessToken) {
                    const data = {
                        accessToken: accessToken,
                        refreshToken: refreshToken
                    }
                    Store.commit("auth/saveTokenData", data)
                }
            }

            let activeAuth = Store.getters["auth/getIsTokenActive"];
            const authData = Store.getters["auth/getAuthData"];
            if(!authData.token){
                if(this.debug) console.log('Token not set')
            }


            if(!activeAuth && authData.token){
                if(this.debug) console.log('Token is not valid, starting refresh protocol')

                if(authData.token){
                    const payload = {
                        accessToken: authData.token,
                        refreshToken: authData.refreshToken
                    }
                    let api = new apiService("http://127.0.0.1:8762/auth/api/v1/auth/refresh")
                    if(this.debug) console.log(api)
                    await api
                        .post(payload)
                        .then((response) => {
                            Store.commit("auth/saveTokenData", response.data)
                            activeAuth = true;
                        })
                        .catch(() => {
                            Store.dispatch("auth/logout")
                        })
                }
            }
            if(to.fullPath == "/"){
                if(this.debug) console.log("redirect to /")
                this.api.setHeader()
                return next();
            } else if(!activeAuth && to.fullPath != "/auth/login"){
                if(this.debug) console.log("not logged in", to.fullPath)
                return next({ path: "/auth/login" });
            }
            if(this.debug) console.log("redirect to next")
            this.api.setHeader()
            return next();
        })
    }
}

export default AccessControl;