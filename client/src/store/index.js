import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)


export default new Vuex.Store({
  state:{
      user:{
        username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username
      },
      essayId: window.sessionStorage.getItem("essayId") == null ? 0 : JSON.parse(window.sessionStorage.getItem("essayId")),
      topicId: window.sessionStorage.getItem("topicId") == null ? 0 : JSON.parse(window.sessionStorage.getItem("topicId")),
      //访问个人主页时的用户信息
      personal: {
        personalUsername: window.sessionStorage.getItem('personal' || '[]') == null ? 0 : JSON.parse(window.sessionStorage.getItem('personal' || '[]')).personalUsername,
        identity: window.sessionStorage.getItem('personal' || '[]') == null ? 0 : JSON.parse(window.sessionStorage.getItem('personal' || '[]')).identity //是否为本人
      }
  },
  mutations: {
    login (state, data) {
      state.user = data;
      window.localStorage.setItem('user', JSON.stringify(data))
    },
    logout (state){
      state.user = [];
      window.localStorage.removeItem('user')
    },

    setPersonal: (state, data) => {
      state.personal = data;
      window.sessionStorage.setItem('personal', JSON.stringify(data))
    },

    setEssayId: (state, essayId) => {
      state.essayId = essayId;
      window.sessionStorage.setItem('essayId', JSON.stringify(essayId))
    },

    setTopicId: (state, topicId) => {
      state.topicId = topicId;
      window.sessionStorage.setItem('topicId', JSON.stringify(topicId))
    }

  }
})
