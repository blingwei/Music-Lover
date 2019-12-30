<template>
  <div>
    <h1 >{{this.essay.title}}</h1>
    <div class="user" style="margin-bottom: 10px">
      <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      <div>
        <span style="margin-right: 2%">{{username}}</span>
        <span>{{this.essayWithSong.createTime}}</span>
        <i class="el-icon-view"> {{this.essayWithSong.views}}</i>
      </div>

    </div>
    <div class="content" v-html="this.essay.content">
    </div>
  </div>
</template>

<script>
    export default {
        name: "EssayContent",

        data() {
            return {
                username: this.$store.state.user.username,
                essay:{
                    title: " ",
                    content: " ",
                },
                essayWithSong: {
                    createTime: " ",
                    pickNum: "0",
                    reward: "0",
                    commitNum: "0",
                    views: "0",
                    collectNum: "0"
                }

            }

        },
        methods: {
            init() {
                this.$axios.get("musicSea/findEssayWithSongByEssayId", {
                    params: {
                        id: this.$store.state.essayId
                    }
                }).then(res =>{
                    if(res.data.code === 200){
                        this.essay = res.data.data.essay
                        this.essayWithSong = res.data.data.essayWithSong
                        console.log(res.data.data.song)
                        this.$root.$emit("initSong", res.data.data.song)
                    }
                    if(res.data.code === 400){
                       this.$message.error(res.data.message)
                    }
                })
            }
        },
        mounted(){
            this.init()
        }
    }
</script>

<style scoped>
  .content{
    width: 100%;
    /*display: block;*/
    padding-top: 30px;
    background: #F2F6FC;
    line-height: 30px;
    padding-bottom: 30px;
  }
</style>
