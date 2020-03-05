<template>
  <div>
    <MusicPlay style="margin-top: 50px"></MusicPlay>
    <div class="body">
      <EssayContent class="essayContent">
      </EssayContent>
      <comment ref="childComment"></comment>
    </div>
    <div class="badge">
      <el-badge :value="commentNum" :max="99" class="item" >
        <el-button size="small" @click="isComment">评论</el-button>
      </el-badge>
      <el-badge :value="pickNum" :max="10" class="item" >
        <el-button size="small" @click="toPick">
          <span v-if="pickStatus" style="color: #3377aa"> 已点赞</span>
          <span v-else>点赞</span>
        </el-button>
      </el-badge>
      <el-badge :value="rewardNum" :max="10" class="item" >
        <el-button size="small">打赏</el-button>
      </el-badge>
      <el-badge :value="collectNum" :max="10" class="item" >
        <el-button size="small" @click="collect">
          <span v-if="collectStatus" style="color: #3377aa"> 已收藏</span>
          <span v-else>收藏</span>
        </el-button>
      </el-badge>
    </div>
    <comment-item ref="commentItem"></comment-item>
    <div></div>
  </div>
</template>

<script>
  import EssayContent from "./EssayContent";
  import MusicPlay from "./MusicPlay";
  import Comment from "../comment/Comment";
  import CommentItem from "../comment/CommentItem";
    export default {
        name: "EssayWithSongDisplay",
        components: {EssayContent, MusicPlay, Comment, CommentItem},
        data() {
            return {
                comment:{
                    username: this.$store.state.user.username,
                    matterId: this.$route.query.id,
                    pid: '0',
                    replyId: '0',
                    type: "2",
                    content: "",
                },
                commentNum: 0,
                pickNum: 0,
                pickStatus: false, //是否点赞
                rewardNum: 0,
                collectNum: 0,
                collectStatus: false // 是否收藏
            }
        },
        methods:{
            init(){
                this.getPickNumAndStatus();
                this.getCollectNumAndStatus();
            },


            //收藏
            collect(){
              let obj = this;
              if(obj.collectStatus){
                this.$axios.get("cancelCollectEssayWithSong", {
                  params: {
                    matterId: obj.$route.query.id
                  }
                }).then(res => {
                  obj.collectStatus = false;
                  obj.collectNum = res.data.data;
                  console.log("123");
                  console.log(res.data.data)
                })

              }else{
                this.$axios.get("collectEssayWithSong", {
                  params: {
                    matterId: this.$route.query.id
                  }
                }).then(res => {
                  obj.collectStatus = true;
                  obj.collectNum = res.data.data;
                  console.log("123");
                  console.log(res.data.data)
                })
              }
            },

            getCollectNumAndStatus(){
              let obj = this;
              obj.$axios.get("getEssayWithSongCollectNumAndStatus", {
                params: {
                  matterId: this.$route.query.id
                }
              }).then(res => {
                obj.collectNum = res.data.data.num;
                obj.collectStatus = res.data.data.collectStatus
              })
            },

            //评论
            isComment(){
              this.$refs.commentItem.dialogVisible = true;
            },
            initComment(){
                this.$refs.childComment.initComment();
            },
            reset(){
                this.comment.pid = "0";
                this.comment.replyId = "0";
                this.comment.content = "";
            },

            //点赞
            getPickNumAndStatus(){
                let obj = this;
                obj.$axios.get("getEssayWithSongPickNumAndStatus", {
                    params: {
                        matterId: obj.$route.query.id
                    }
                }).then(res => {
                    obj.pickNum = res.data.data.num;
                    obj.pickStatus = res.data.data.pickStatus
                })
            },

            toPick(){
                let obj = this;
                if(obj.pickStatus){
                    this.$axios.get("cancelPickEssayWithSong", {
                        params: {
                            matterId: obj.$route.query.id
                        }
                    }).then(res => {
                        obj.pickStatus = false;
                        obj.pickNum = res.data.data
                    })

                }else{
                    this.$axios.get("pickEssayWithSong", {
                        params: {
                            matterId: obj.$route.query.id
                        }
                    }).then(res => {
                        obj.pickStatus = true;
                        obj.pickNum = res.data.data;
                    })
                }
            },
        },

        mounted() {
            this.init();
        },
    }
</script>

<style scoped>
  .body {
    width: 60%;
    margin-left: 20%;
  }
  .essayContent{
    margin-top: 20px;
    text-align: left;
    min-height: 500px;
    width: 100%;
    }
  .badge{
    position: fixed;
    width: 2%;
    height: 200px;
    top: 320px;
    left: 80%;
  }
 .item{
   width: 70px;
   height: 40px;
 }
</style>
