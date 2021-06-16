import { VuexModule, Module, Mutation, Action } from 'vuex-module-decorators';


import articleEndpointHATEOAS from "@/api/article.endpoint";
// import api from "@/services/api.service";


interface IPageLinks {
    first: any,
    self: any,
    next: any,
    last: any
}

@Module({
    namespaced: true
})
class ArticleModule extends VuexModule {
    private article_endpoint = new articleEndpointHATEOAS()
    public pageLinks : IPageLinks = {
        first: null,
        self: null,
        next: null,
        last: null
    }
    public data : any = []
    public hasError = true
    public isLoading = false

    get getIsLoading() : boolean {
        return this.isLoading;
    }
    get getHasError() : boolean {
        return this.hasError
    }

    get getPageLinks() : IPageLinks {
        return this.pageLinks
    }

    get getData() : any {
        return this.data
    }

    @Action({ rawError: true })
    // eslint-disable-next-line @typescript-eslint/explicit-function-return-type
    public async fetchArticles() {
        this.context.commit("startLoading")
        await this.article_endpoint
            .getAll()
            .then(({data}) => {
                console.log(data.embedded.articles)
                this.context.commit("setPageLinks", data.links);
                this.context.commit("setData", data.embedded.articles);
                this.context.commit("endLoading")
            })
            .catch((error) => {
                this.context.commit("setError", true)
                throw error
            })
    }

    @Action({ rawError: true })
    // eslint-disable-next-line @typescript-eslint/explicit-function-return-type
    public fetchArticlesById(id : number) {
        return this.article_endpoint
            .getById(id)
            .then(({data}) => {
                console.log(data)
            })
    }

    @Mutation
    private setError(state : boolean) : void{
        if(state === null) return
        this.hasError = state;
        this.isLoading = false;
    }

    @Mutation
    private setData(data : any) : void{
        if(data === null) return
        this.data = data;
    }

    @Mutation
    private setPageLinks(pageLinks : IPageLinks) : void{
        if(pageLinks === null) return
        this.pageLinks = pageLinks;
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


export default ArticleModule;