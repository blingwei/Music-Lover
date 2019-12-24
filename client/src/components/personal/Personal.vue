<template>
  <div class="display">
    <div class="top" >
      <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" ></el-avatar>
    </div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick" class="sideMenu">
      <el-tab-pane label="我的信息" name="userInfo"><UserInfo class="userMain"/></el-tab-pane>
      <el-tab-pane label="我的收藏" name="userCollect">我的收藏</el-tab-pane>
      <el-tab-pane label="我的动态" name="userState">我的动态</el-tab-pane>
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
    margin-top: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
  .display {

    margin-left: 20%;
    width: 60%;
  }
  .top {
    text-align: left;
    margin-top: 50px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    width: 100%;
    height: 100px;
    top: 50px;
  }
</style>
