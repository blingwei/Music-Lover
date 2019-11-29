<template>
  <div class="display">
    <div class="top">
      <h1>touxiang</h1>
    </div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick" class="sideMenu">
      <el-tab-pane label="用户管理" name="userInfo"><UserInfo class="userMain"/></el-tab-pane>
      <el-tab-pane label="我的收藏" name="userCollect">我的收藏</el-tab-pane>
      <el-tab-pane label="我的动态" name="userState">角色管理</el-tab-pane>
      <el-tab-pane label="我的关注" name="userAttention">我的关注</el-tab-pane>
      <el-tab-pane label="我的粉丝" name="userFans">我的粉丝</el-tab-pane>
    </el-tabs>

  </div>


</template>

<script>
  import UserInfo from "./UserInfo";

    export default {
        name: "Personal",
        components: {UserInfo},
        data() {
            return {
                activeName: 'userInfo'
            }
        },
        methods:{
            handleClick(tab, event) {
                console.log(tab, event);
            }
        }
    }
</script>

<style scoped>
  .sideMenu {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    /*top: 100px;*/
  }
  .display {
    position: fixed;
    left: 20%;
    width: 60%;
  }
  .top {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    top: 50px;
  }
</style>
