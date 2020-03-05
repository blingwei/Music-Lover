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
    if (store.state.user.username && to.path.startsWith('/admin')) {
        axios.get('/authentication').then(resp => {
            initAdminMenu(router, store)
        })
    }

    if (store.state.user.username) {
        axios.get('/authentication').then(resp => {
            if (resp.data) {
                console.log("then" + resp.data)
                next({
                    query: {time: from.path}
                })
            } else {
                store.commit('logout')
                next({
                    path: '/login',
                    query: {redirect: to.fullPath}
                })
            }
        }).catch(resp => {
            store.commit('logout')
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        })

    } else {
        if (to.path == "/login") {
            next();
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
        }
    }


});

const initAdminMenu = (router, store) => {
    if (store.state.adminMenus.length > 0) {
        return
    }
    axios.get('/menu').then(resp => {
        if (resp && resp.status === 200) {
            var fmtRoutes = formatRoutes(resp.data.data);
            router.addRoutes(fmtRoutes);
            console.log('initAdminMenu');
            console.log(fmtRoutes)
            store.commit('initAdminMenu', fmtRoutes)
        }
    })
}

const formatRoutes = (routes) => {
    let fmtRoutes = [];
    routes.forEach(route => {
        if (route.children) {
            route.children = formatRoutes(route.children)
        }

        let fmtRoute = {
            path: route.path,
            component: resolve => {
                require(['./components/admin/' + route.component + '.vue'], resolve)
            },
            name: route.name,
            nameZh: route.nameZh,
            iconCls: route.iconCls,
            meta: {
                requireAuth: true
            },
            children: route.children
        };
        fmtRoutes.push(fmtRoute)
    });
    return fmtRoutes
}


/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    components: {App},
    template: '<App/>'
})
