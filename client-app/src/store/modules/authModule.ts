import { VuexModule, Module, Mutation, Action } from "vuex-module-decorators";
import api from "@/api";
import jwt_decode from "jwt-decode";
import { store } from "@/store";
import getMAC, { isMAC } from 'getmac'



export interface AuthUser {
  auth: string;
  uid: string;
  email: string;
}
interface Meta {
  loggedIn: boolean;
}
interface AuthData {
  token: string;
  refreshToken: string;
  expiryDuration: number;
  userId: number;
  email: string;
}

@Module({
  namespaced: true
  // dynamic: true,
  // stateFactory: true,
  // store,
  // name: "auth"
})
class Auth extends VuexModule {

  public authUser: AuthUser = {
    auth: "",
    uid: "",
    email: ""
  };

  public meta: Meta = {
    loggedIn: false
  };

  public authData: AuthData = {
    token: "",
    refreshToken: "",
    expiryDuration: 0,
    userId: 0,
    email: ""
  };

  get getMetaData() {
    return this.meta;
  }

  get getAuthData() {
    return this.authData;
  }

  get getIsTokenActive() {
    if (!this.authData.expiryDuration) {
      return false;
    }
    if (Date.now() >= this.authData.expiryDuration * 2000) {
      return false;
    }
    return true;
  }

  get getIsLoggedIn() {
    return this.meta.loggedIn;
  }

  get getCurrentUser() {
    return this.authData.email;
  }



  @Action({ rawError: true })
  public async login(credentials: any) {
    // eslint-disable-next-line @typescript-eslint/no-var-requires

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
    await api
      .post("http://127.0.0.1:8762/auth/api/v1/auth/login", body)
      .then(this.handleResponse)
      .then(authResponse => {
        if (authResponse.data.accessToken) {
          // @ts-ignore
          this.context.commit("loginSuccess", this.authUser);
          // @ts-ignore
          this.context.commit("saveTokenData", authResponse.data);
        }

        return authResponse;
      });
  }
  @Action({ rawError: true })
  public logout() {
    this.context.commit("purgeData");
  }
  @Action({rawError: true})
  public saveTokenDataAction(data: any){
    this.context.commit("saveTokenData", data);
  }

  @Action
  private handleResponse(response: any) {
    return response.text().then((text: any) => {
      const data = text && JSON.parse(text);
      if (!response.ok) {
        if (response.status === 401) {
          this.logout();
        }

        const error = (data && data.message) || response.statusText;
        return Promise.reject(error);
      }

      return data;
    });
  }

  @Mutation
  saveTokenData(data: any) {
    localStorage.setItem("access_token", data.accessToken);
    localStorage.setItem("refresh_token", data.refreshToken);

    const jwtDecodedValue = jwt_decode(data.accessToken);

    //Set fresh
    // @ts-ignore
    const newTokenData = {
      token: data.accessToken,
      refreshToken: data.refreshToken,
      expiryDuration: jwtDecodedValue.exp
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

    this.meta.loggedIn = true;
  }

  @Mutation
  loginSuccess(authUser: AuthUser) {
    this.meta.loggedIn = true;
  }

  @Mutation
  loginFailure() {
    this.meta.loggedIn = false;
  }

  @Mutation
  purgeData(){
    this.authData.token = "";
    this.authData.userId = 0;
    this.authData.refreshToken = "";
    this.authData.expiryDuration = 0;
    this.authData.email = "";
    this.meta.loggedIn = false;
    localStorage.removeItem("uuid");
    localStorage.removeItem("email");
    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
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
