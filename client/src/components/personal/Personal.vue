<template>
  <div class="display">
    <div class="top" >
      <UserInfo class="userMain" ref="userInfo"/>
    </div>
    <el-tabs v-model="activeName" type="card" @tab-click="handleClick" class="sideMenu">
      <el-tab-pane label="动态" name="userState"><UserCondition /></el-tab-pane>
      <el-tab-pane label="收藏" name="userCollect"><UserCollect /></el-tab-pane>
      <el-tab-pane label="关注" name="userAttention"><UserAttention /></el-tab-pane>
      <el-tab-pane label="创作" name="userPublic"><UserPublic /></el-tab-pane>
      <el-tab-pane label="专栏" name="userTopic"><UserTopic /></el-tab-pane>
    </el-tabs>

  </div>


</template>

<script>
  import UserInfo from "./UserInfo";
  import UserCollect  from "./UserCollect";
  import UserAttention from "./UserAttention";
  import UserPublic from "./UserPublic";
  import UserTopic from "./UserTopic";
  import UserCondition from "./UserCondition"



  export default {
        name: "Personal",
        components: {UserInfo, UserCollect, UserAttention, UserPublic, UserTopic, UserCondition},
        data() {
            return {
              activeName: 'userState',
            }
        },
        methods:{
            init() {
              if(this.$route.params.userName != null){
                let personalUsername = this.$route.params.userName;
                let identity = this.$route.params.userName === personalUsername;
                let data = {
                  personalUsername: personalUsername,
                  identity : identity
                };
                this.$store.commit('setPersonal', data)
              }

            },
            handleClick(tab, event) {
                console.log(tab, event);
            },


        },
        beforeMount(){
          this.init();
        },

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
