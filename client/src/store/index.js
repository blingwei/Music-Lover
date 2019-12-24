import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
  state:{
      user:{
        username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
      },
      essayId:  window.sessionStorage.getItem("essayId") == null ? 0 : JSON.parse(window.sessionStorage.getItem("essayId"))
  },
  // getters: {
  //   essayId: state => {
  //     let essayId = state.essayId;
  //     if(!essayId){
  //       essayId = JSON.parse(window.localStorage.getItem('essayId') || null)
  //     }
  //     return essayId
  //   }
  // },
  mutations: {
    login (state, data) {
      state.user = data;
      window.localStorage.setItem('user', JSON.stringify(data))
    },
    logout (state){
      state.user = [];
      window.localStorage.removeItem('user')
    },

    setEssayId: (state, essayId) => {
      state.essayId = essayId;
      window.sessionStorage.setItem('essayId', JSON.stringify(essayId))
    }
  }
})
