import { VuexModule, Module, Mutation, Action, getModule } from 'vuex-module-decorators';


import articleEndpointHATEOAS from "@/api/article.endpoint";

@Module({
    namespaced: true
})
class ArticleModule extends VuexModule {
    article_endpoint = new articleEndpointHATEOAS()



    @Action({ rawError: true })
    // eslint-disable-next-line @typescript-eslint/explicit-function-return-type
    public fetchArticles() {
        this.article_endpoint
            .getAll()
    }
    count = 0


}


export default ArticleModule;