<template>
  <el-menu
    default-active="/userInfo"
    style="width:120px"
    router>
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      <i class="el-icon-menu"></i>
      <span slot="title">{{ item.navItem }}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    name: "SideMenu",
    data() {
      return {
        navList: [
          {name: "/userInfo", navItem: "个人信息"},
          {name: "/userCollect", navItem: "我的收藏"},
          {name: "/userState", navItem: "我的动态"},
          {name: "/userAttention", navItem: "我的关注"},
          {name: "/userFans", navItem: "我的粉丝"}
        ]
      }
    }
  }
</script>

<style scoped>

</style>
