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
        <el-button size="small" @click="collect">收藏</el-button>
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
                    matterId: this.$store.state.essayId,
                    pid: '0',
                    replyId: '0',
                    type: "2",
                    content: "",
                },
                commentNum: 0,
                pickNum: 0,
                pickStatus: false,
                rewardNum: 0,
                collectNum: 0,
            }
        },
        methods:{
            init(){
                this.getPickNumAndStatus()
            },
            isComment(){
                this.$refs.commentItem.dialogVisible = true;
            },
            collect(){
            },
            initComment(){
                this.$refs.childComment.initComment();
            },
            reset(){
                this.comment.pid = "0";
                this.comment.replyId = "0";
                this.comment.content = "";
            },
            getPickNumAndStatus(){
                let obj = this;
                obj.$axios.get("getEssayWithSongNumAndStatus", {
                    params: {
                        matterId: obj.$store.state.essayId
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
                            matterId: obj.$store.state.essayId
                        }
                    }).then(res => {
                        obj.pickStatus = false;
                        obj.pickNum = res.data.data
                    })

                }else{
                    this.$axios.get("pickEssayWithSong", {
                        params: {
                            matterId: obj.$store.state.essayId
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
