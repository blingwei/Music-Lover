import Vue from 'vue'
import Router from 'vue-router'
import Home from "@/components/Home"
import Login from '@/components/Login'
import Index from "@/components/Index"
import Personal from "../components/personal/Personal";
import MusicSea from "../components/musicSea/MusicSea";
import Creation from "../components/creation/Creation";
import EssayWithSongDisplay from "../components/essayWithSongDisplay/EssayWithSongDisplay";

Vue.use(Router);


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
        {
          path: '/creation',
          name: 'Creation',
          component: Creation
        },
        {
          path: '/essayDisplay',
          name: 'EssayWithSongDisplay',
          component: EssayWithSongDisplay
        },
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },


  ]
})

const routerPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
};
