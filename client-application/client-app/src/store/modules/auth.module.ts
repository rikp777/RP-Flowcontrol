import { VuexModule, Module, Mutation, Action } from "vuex-module-decorators";
import api from "@/services/api.service.js";
import jwtService from "@/services/jwt.service";
import jwt_decode from "jwt-decode";

export interface IAuthUser {
    auth: string;
    uid: string;
    email: string;
}
interface IMeta {
    loggedIn: boolean;
}
interface IAuthData {
    token: string;
    refreshToken: string;
    expiryDuration: number;
    userId: number;
    email: string;
}

@Module({
    namespaced: true
})
class AuthModule extends VuexModule {
    private debug = false
    private jwtService = new jwtService()
    public authUser: IAuthUser = {
        auth: "",
        uid: "",
        email: ""
    };

    public hasError = true
    public isLoading = false

    public authData: IAuthData = {
        token: "",
        refreshToken: "",
        expiryDuration: 0,
        userId: 0,
        email: ""
    };

    get getIsLoggedIn() : boolean {
        return this.authData.token !== ""
    }

    get getAuthData() : any{
        return this.authData;
    }

    get getIsTokenActive() : any{
        if (!this.authData.expiryDuration) {
            return false;
        }
        if (Date.now() >= this.authData.expiryDuration * 1000) {
            return false;
        }
        return true;
    }

    @Action({ rawError: true })
    public async login(credentials: any) : Promise<any> {
        this.context.commit("startLoading")
        //todo implement device info
        const body = {
            username: credentials.email,
            email: credentials.email,
            password: credentials.password,
            deviceInfo: {
                deviceId: "1234",
                deviceType: "DEVICE_TYPE_LINUX",
                notificationToken: "123456"
            }
        };
        const auth = new api("http://127.0.0.1:8762/auth/api/v1/auth/login")
        await auth.post(body)
            .then((authResponse : any) => {
                if (authResponse.data.accessToken) {
                    this.context.commit("saveTokenData", authResponse.data);
                    this.context.commit("endLoading")
                }
                return authResponse;
            }).catch((error : any) => {
                this.context.commit("setError", true);
            })
    }
    @Action({ rawError: true })
    public logout() : void {
        this.context.commit("purgeData")
    }

    @Mutation
    public purgeData() : void{
        this.authData.token = "";
        this.authData.userId = 0;
        this.authData.refreshToken = "";
        this.authData.expiryDuration = 0;
        this.authData.email = "";
        localStorage.removeItem("uuid");
        localStorage.removeItem("email");
        this.jwtService.destroyAllTokens();
    }

    @Mutation
    saveTokenData(data: any) : void {
        if(this.debug) console.log("Safe tokens")
        this.jwtService.saveAuthToken(data.accessToken)
        this.jwtService.saveRefreshToken(data.refreshToken)

        const jwtDecodedValue : any = jwt_decode(data.accessToken);

        //Set fresh
        const newTokenData : IAuthData= {
            token: data.accessToken,
            refreshToken: data.refreshToken,
            expiryDuration: jwtDecodedValue.exp,
            userId: 0,
            email: ""
        };
        this.authData = newTokenData;
        if (jwtDecodedValue.sub && jwtDecodedValue.aud) {
            localStorage.setItem("uuid", jwtDecodedValue.sub);
            localStorage.setItem("email", jwtDecodedValue.aud);
            this.authData.userId = jwtDecodedValue.sub;
            this.authData.email = jwtDecodedValue.aud;
        } else {
            this.authData.userId = Number(localStorage.getItem("uuid"));
            this.authData.email = String(localStorage.getItem("email"));
        }
    }

    @Mutation
    private setError(state : boolean) : void{
        if(state === null) return
        this.hasError = state;
        this.isLoading = false;
    }

    @Mutation
    private endLoading() : void{
        this.isLoading = false;
    }

    @Mutation
    private startLoading() : void{
        this.hasError = false;
        this.isLoading = true;
    }
}

export default AuthModule;