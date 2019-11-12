import Vue from "vue"
import Vuex from "vuex"

const state = {
  theme: ""
}

const getters = {
  getTheme(state) {
    return state.theme
  }
}

const mutations = {
  setTheme(state, theme) {
    state.theme = theme
  }
}

const actions = {
  asyncSetTheme: (context, theme) => {
    context.commit('theme', theme)
  },
}

Vue.use(Vuex);

export default new Vuex.Store({
  state: state,
  getters: getters,
  mutations: mutations,
  actions: actions,
  modules: {}
})
