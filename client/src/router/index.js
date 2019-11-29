import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/Home"
import Login from '@/components/Login'
import Index from "@/components/Index"
import Personal from "../components/personal/Personal";
import MusicSea from "../components/musicSea/MusicSea";
import SeaDisplay from "../components/musicSea/SeaDisplay";

Vue.use(Router)


export default new Router({
  mode: 'history',
  routes: [
    {
      path: "/",
      name: "Home",
      component: Home,
      redirect: "/index",
      children: [
        {
          path: '/index',
          name: 'Index',
          component: Index,
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/personal',
          name: 'Personal',
          component: Personal,
        },
        {
          path: '/musicSea',
          name: 'MusicSea',
          component: MusicSea ,
        },
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/test',
      name: 'SeaDisplay',
      component: SeaDisplay
    },


  ]
})
