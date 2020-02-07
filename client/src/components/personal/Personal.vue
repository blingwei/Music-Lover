<template>
  <div class="display">
    <div class="top" >
      <UserInfo class="userMain"/>
    </div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick" class="sideMenu">
      <el-tab-pane label="动态" name="userState">动态</el-tab-pane>
      <el-tab-pane label="收藏" name="userCollect"><UserCollect/></el-tab-pane>
      <el-tab-pane label="关注" name="userAttention">关注</el-tab-pane>
      <el-tab-pane label="创作" name="userPublic">创作</el-tab-pane>
      <el-tab-pane label="专栏" name="userColumn">专栏</el-tab-pane>
    </el-tabs>

  </div>


</template>

<script>
  import UserInfo from "./UserInfo";
  import UserCollect  from "./UserCollect";



  export default {
        name: "Personal",
        components: {UserInfo, UserCollect},
        data() {
            return {
                activeName: 'userState'
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
    height: 200px;
    top: 50px;
  }
  .userMain{
    width: 100%;
    height: 100%;
  }
</style>
