import article_endpoints from "@/api/article.endpoint";

const endpoints = {
    articles: article_endpoints
}

export const endpointFactory = {
    get: name => endpoints[name]
}