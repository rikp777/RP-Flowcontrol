import api from '@/services/api.service'

class AccessControl {
    constructor(){
        this.api = new api();
    }
    router(store, router){
        router.beforeEach((to, from, next) => {
            this.api.setHeader()
            next();
        })
    }
}

export default AccessControl;