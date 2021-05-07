// import Router from "@/router"
//
// export interface User {
//     email: string;
//     uid: string;
//     orgs: Orgs;
// }
// export interface Orgs {
//     uid: string;
// }
//
//
// interface State {
//     apiToken: boolean | string;
//     user: User;
// }
//
//
// interface Root {
//     commit: (mutation: string, params?: any) => void;
//     dispatch: (action: string, params?: {}, options?: {}) => void;
//     state: State;
// }
//
//
// const state = {
//     apiToken: false,
//     user: null
// }
// const getters = {
//     apiToken: (state: State) => {
//         return state.apiToken
//     },
//     user: (state: State) => {
//         return state.user;
//     }
// }
// const actions = {
//     login(root: Root){
//         const apiToken = localStorage.getItem("apiToken") ?? false;
//         if(apiToken){
//             root.dispatch("setTokens", {
//                 apiToken: apiToken
//             });
//         }
//         root.dispatch("apiRequest/enableFetch", {}, { root: true})
//     },
//
//
//     logout(root, Root){
//         localStorage.removeItem("apiToken");
//         Router.go(0)
//     },
//
//     setTokens(
//         root: Root,
//         params: { apiToken: string },
//     ) {
//         root.commit("SetToken", {
//             apiToken: params.apiToken
//         });
//
//         root.dispatch("loadUserData");
//     },
//
//     loadUserData(root, Root){
//         root.commit("setUser", { user: "test"})
//     }
//
// }
// const mutations = {
//     setToken(
//         state: State,
//         params: { apiToken: string }
//     ){
//         state.apiToken = params.apiToken;
//         localStorage.setItem("apiToken", params.apiToken)
//     },
//
//     setUser(state: State, params: { user: User}){
//         state.user = params.user;
//     },
//     setOrgs(state: State, params: { orgs: Orgs }){
//         state.user.orgs = params.orgs
//     }
// }
//
// export default {
//     namespaced: true,
//     state,
//     getters,
//     actions,
//     mutations,
// };