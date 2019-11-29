<template>
  <el-menu
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    style="min-width: 1300px">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
      <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">BlingBlingWei - Your Music Lover</span>
      <el-dropdown @command="handleCommand" style="position: absolute;padding-top: 10px;right: 1%;">
        <el-tooltip class="el-dropdown-link" effect="dark" :content="username"  placement="left">
           <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-tooltip>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="personal">个人中心</el-dropdown-item>
          <el-dropdown-item command="creation">创作中心</el-dropdown-item>
          <el-dropdown-item command="quit">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
  </el-menu>
</template>

<script>
    export default {
        name: "NavMenu",
        data () {
            return {
                navList: [
                    {name: '/index', navItem: '首页'},
                    {name: '/musicSea', navItem: 'music sea'},
                    {name: '/', navItem: '音乐库'}
                ],
                username: this.$store.state.user.username
            }
        },
        methods:{
            handleCommand(command) {
                if(command === "quit"){
                    this.$store.commit('logout')
                }
                this.$router.push("/"+command);
            }
        }
    }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
</style>
