<template>
  <el-menu
    :default-active="currentPath"
    class="el-menu-admin"
    router
    mode="vertical"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    :collapse="isCollapse">
    <div style="height: 80px;"></div>
    <!--index 没有用但是必需字段-->
    <el-submenu v-for="(item,i) in menus" :key="i" style="text-align: left" index="i">
        <span slot="title" style="font-size: 17px;">
          <i :class="item.icon"></i>
          {{item.nameZh}}
        </span>
      <el-menu-item v-for="(child, index) in item.children" :key="index" :index="child.path">
        <i :class="child.icon"></i>
        {{ child.nameZh }}
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
  import router from '../router'

  export default {
    name: 'Menu',
    data() {
      return {
        isCollapse: false,
        menus: [],
        userMenus: [
          {
            nameZh: '作品管理',
            icon: 'el-icon-user',
            children: [
              {
                nameZh: '我的作品',
                icon: 'el-icon-user',
                path: 'Publish'
              },
              {
                nameZh: '发表作品',
                icon: 'el-icon-user',
                path: 'myCoach'
              },
            ]
          },
          {
            nameZh: '专栏管理',
            icon: 'el-icon-user',
            children: [
              {
                nameZh: '我的专栏',
                icon: 'el-icon-user',
                path: 'recentSport'
              },
              {
                nameZh: '投稿审核',
                icon: 'el-icon-user',
                path: 'SportRecord'
              },
            ]
          },
          {
            nameZh: '论坛',
            icon: 'el-icon-user',
            children: [
              {
                nameZh: '发表',
                icon: 'el-icon-user',
                path: 'creation'
              },
              {
                nameZh: '论坛',
                icon: 'el-icon-user',
                path: 'forum'
              },
            ]
          },


        ],
        coachMenus: [
          {
            nameZh: '个人中心',
            icon: 'el-icon-user',
            children: [
              {
                nameZh: '个人信息',
                icon: 'el-icon-user',
                path: 'userInfo'
              },
            ]
          },
          {
            nameZh: '学员管理',
            icon: 'el-icon-user',
            children: [
              {
                nameZh: '我的学员',
                icon: 'el-icon-user',
                path: 'myUser'
              },
            ]
          },
        ]
      }
    },
    methods: {
      init() {
        if (this.$store.state.user.role === '1') {
          this.menus = this.userMenus;
        } else {
          this.menus = this.coachMenus;
        }

      }
    },
    mounted() {
      this.init()
    },
    computed: {
      currentPath() {
        return this.$route.path
      }
    }
  }
</script>

<style scoped>
  .el-menu-admin {
    border-radius: 5px;
    height: 100%;
  }
</style>
