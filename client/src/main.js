// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from "./store"
import el from "element-ui/src/locale/lang/el";

//设置反向代理
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8421/api'
axios.defaults.withCredentials = true

Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)

router.beforeEach((to, from, next) => {
    if(store.state.user.username){
       axios.get('/authentication').then(resp => {
         if(resp) next({
           query: {time: from.path}
         })
       })
      next()
    }else{
      if(to.path == "/login"){
        next();
      }else{
        next({
          path: '/login',
          query: {redirect: to.fullPath}
        })
      }
    }


})




/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
