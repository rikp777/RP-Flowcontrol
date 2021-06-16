import { createStore, createLogger } from 'vuex'
const debug = process.env.NODE_ENV !== "production";
import articleModule from "@/store/modules/article.module";

export default createStore({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    article: articleModule
  },
  strict: debug,
  plugins: debug ? [createLogger()] : []
})
