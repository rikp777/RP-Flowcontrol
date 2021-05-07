export interface apiRequest {
    id: string;
    repository: string;
    description: string;
    readme: string;
}

interface State {
    apiRequests: Array<apiRequest>;
    page: number;
    fetching: boolean;
    canFetch: boolean;
}

const state = () => ({
    apiRequests: [],
    page: 0,
    fetching: false,
    canFetch: false
})

const getters ={
    apiRequests: (state: State) => {
        return state.apiRequests;
    }
}

const actions ={
    enableFetch(root: {
        commit: ( mutation: string, params?: any ) => void;
        state: State;
    }) {
        root.commit("enableFetch")
    },

    load(root: {
        commit: ( mutation: string, params?: any ) => void;
        state: State;
    }){
        if(!root.state.canFetch) {
            setTimeout(() => {
                // @ts-ignore
                root.dispatch("load")
            }, 200)
            return;
        }

        if(root.state.fetching){
            return;
        }

        root.commit("startFetch")
    }
}


const mutations = {
    enableFetch(state: State) {
        state.canFetch = true;
    },
    startFetch(state: State) {
        state.fetching = true;
    },
    finishFetch(state: State) {
        state.fetching = false;
    },
    incrementPage(state: State) {
        state.page++;
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
};
