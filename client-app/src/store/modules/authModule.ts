import { VuexModule, Module, Mutation, Action } from 'vuex-module-decorators'
import api from "@/api"
import jwt_decode from "jwt-decode";
import { store } from '@/store';

export interface AuthUser {
    auth: string;
    uid: string;
    email: string
}
interface Meta {
    loggedIn: boolean
}
interface AuthData {
    token: string
    refreshToken: string
    expiryDuration: number
    userId: number
    email: string
}

@Module({
    namespaced: true,
    // dynamic: true,
    // stateFactory: true,
    // store,
    // name: "auth"
})
class Auth extends VuexModule {
    public authUser: AuthUser = {
        auth: "",
        uid: "",
        email: "",
    }

    public meta: Meta = {
        loggedIn: false,
    }

    public authData: AuthData = {
        token: "",
        refreshToken: "",
        expiryDuration: 0,
        userId: 0,
        email: "",
    }

    get getMetaData() {
        return this.meta;
    }

    get getAuthData() {
        return this.authData;
    }

    get getIsTokenActive(){
        if(!this.authData.expiryDuration){
            return false
        }
        if(Date.now() >= this.authData.expiryDuration * 1000){
            return false
        }
        return true
    }

    get getLoginStatus(){
        return this.meta.loggedIn;
    }

    @Action({rawError: true})
    public async login(credentials: any){
        console.log('Login action')
        const body = {
            username: credentials.email,
            email: credentials.email,
            password: credentials.password,
            deviceInfo: {
                deviceId: "1234",
                deviceType: "DEVICE_TYPE_LINUX",
                notificationToken: "123456"
            }
        }
        await api.post("http://127.0.0.1:8762/auth/api/v1/auth/login", body)
            .then(this.handleResponse)
            .then((authResponse) => {
                console.log(authResponse.data)
                if(authResponse.data.accessToken){
                    // @ts-ignore
                    this.context.commit('loginSuccess', this.authUser)
                    // @ts-ignore
                    this.context.commit('saveTokenData', authResponse.data)
                }

                return authResponse;
            })
    }
    @Action({rawError: true})
    public logout(){
        localStorage.removeItem('user')
    }

    @Action
    private handleResponse(response: any){
        return response.text().then((text: any) => {
            const data = text && JSON.parse(text);
            if(!response.ok){
                if(response.status === 401){
                    this.logout()
                }

                const error = (data && data.message) || response.statusText;
                return Promise.reject(error)
            }

            return data;
        })
    }


    @Mutation
    saveTokenData(data: any) {
        console.log("save data", data)
        localStorage.setItem("access_token", data.accessToken)
        localStorage.setItem("refresh_token", data.refreshToken)

        const jwtDecodedValue = jwt_decode(data.accessToken)

        // @ts-ignore
        const newTokenData = {
            token: data.accessToken,
            refreshToken: data.refreshToken,
            expiryDuration: jwtDecodedValue.exp,
            userId: jwtDecodedValue.sub,
            email: jwtDecodedValue.aud
        }
        this.authData = newTokenData;
        this.meta.loggedIn = true;
        console.log('authData', this.authData)
    }

    @Mutation
    loginSuccess(authUser: AuthUser){
        this.meta.loggedIn = true;
        console.log('meta', this.meta)
    }

    @Mutation
    loginFailure(){
        this.meta.loggedIn = false;
    }
}



export default Auth;



// const userRaw = JSON.parse(<string>localStorage.getItem('user'));
//
// export interface AuthUser {
//     auth: string;
//     uid: string;
//     email: string
// }
// interface Meta {
//     loggedIn: boolean
// }
// interface State {
//     authUser: AuthUser;
//     meta: Meta;
// }
//
//
// const state = {
//     authUser: null,
//     meta: {
//         loggedIn: false
//     }
// }
// const getters = {
//
// }
// const actions = {
//     async login({ commit }, { username, password }){
//
//     }
// }
// const mutations = {
//     loginSuccess(state: State, AuthUser: AuthUser){
//         state.meta.loggedIn = false;
//         state.authUser = AuthUser
//     },
//     loginFailure({commit} : { commit : Function }, state: State, user: AuthUser){
//         state.meta.loggedIn = false;
//         commit('emptyAuthUser')
//     },
//     logout(){
//         state.meta.loggedIn = false;
//     },
//     emptyAuthUser(){
//
//     }
// }
//
//
// export default {
//     namespaced: true,
//     state,
//     getters,
//     actions,
//     mutations,
// };