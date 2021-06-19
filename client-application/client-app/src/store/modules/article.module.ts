import { VuexModule, Module, Mutation, Action } from 'vuex-module-decorators';


import articleEndpointHATEOAS from "@/api/article.endpoint";
import api from "@/services/api.service";


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
    private debug = true;
    private article_endpoint = new articleEndpointHATEOAS()
    public pageLinks : IPageLinks = {
        first: null,
        self: null,
        next: null,
        last: null
    }
    public entities : any = []
    public entity : any = {}
    public hasError = true
    public isLoading = false
    public pageData = {
        number: 0,
        size: 0,
        totalElements: 0,
        totalPages: 0
    }

    get getPageData() : any{
        return this.pageData
    }
    get getIsLoading() : boolean {
        return this.isLoading;
    }
    get getHasError() : boolean {
        return this.hasError
    }

    get getPageLinks() : IPageLinks {
        return this.pageLinks
    }

    get getEntity() : any {
        return this.entity
    }

    get getEntities() : any {
        return this.entities
    }

    @Action({ rawError: true })
    private checkIfDataNeedsToBeReloaded(pageThatIsNeeded : number, pageThatIsInCache : number) : boolean {
        if(pageThatIsInCache >= 0){
            if(pageThatIsInCache == pageThatIsNeeded) {
                return false
            }
        }
        return true
    }

    @Action({ rawError: true })
    public async getAll(page = -1) : Promise<any>{
        this.context.commit("startLoading")
        if(page == -1){
            if(this.pageData.number >= 0){
                page = this.pageData.number
            }
        }
        //check if list is already fetched
        console.log("[ArticleModule] getAll() for page:", page)
        if(this.pageData.number >= 0){
            if(this.pageData.number == page){
                if(this.entities != null && this.entities.length > 0) {
                    if(this.debug) console.log("[ArticleModule] data was already fetched", this.entities)
                    this.context.commit("endLoading")
                    return
                }
            }
        }


        await this.article_endpoint
            .getAll(page)
            .then(({data}) => {
                if(data){
                    this.context.commit("setPageLinks", data.links);
                    this.context.commit("setEntities", data.embedded.articles);
                    this.context.commit("setPageData", data.page)
                    this.context.commit("endLoading")
                }
            })
            .catch((error) => {
                this.context.commit("setError", true)
                throw error
            })
    }
    @Action({ rawError: true })
    public async getById(id : number) : Promise<any>{
        this.context.commit("startLoading")

        //check if item is already fetched
        if(this.entities != null && this.entities.length > 0){
            this.entities.filter((article: any) => {
                if(api.getId(article.links.self.href) === id){
                    this.context.commit("setEntity", article);
                    this.context.commit("endLoading")
                }
            })
        }else {
            await this.article_endpoint
                .getById(id)
                .then(({data}) => {
                    this.context.commit("setData", data);
                    this.context.commit("endLoading")
                })
                .catch((error) => {
                    this.context.commit("setError", true)
                    throw error
                })
        }
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
    private setPageData(data : any) : void{
        if(data === null) return
        this.pageData = data
    }

    @Mutation
    private setEntities(data : any) : void{
        if(this.debug) console.log("[ArticleModule] setEntities()", data)
        if(data === null) return
        this.entities = data;
    }
    @Mutation
    private setEntity(data : any) : void{
        if(this.debug) console.log("[ArticleModule] setEntity()", data)
        if(data === null) return
        this.entity = data;
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