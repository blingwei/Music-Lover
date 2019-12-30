<template>
  <div>
    <MusicPlay style="margin-top: 50px"></MusicPlay>
    <div class="body">
      <EssayContent class="essayContent">
      </EssayContent>
      <comment ref="childComment"></comment>
    </div>
    <div class="badge">
      <el-badge :value="200" :max="99" class="item">
        <el-button size="small" @click="isComment">评论</el-button>
      </el-badge>
      <el-badge :value="100" :max="10" class="item">
        <el-button size="small" @click="pickUp">点赞</el-button>
      </el-badge>
      <el-badge :value="100" :max="10" class="item">
        <el-button size="small">打赏</el-button>
      </el-badge>
      <el-badge :value="100" :max="10" class="item">
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
                }
            }
        },
        methods:{
            isComment(){
                this.$refs.commentItem.dialogVisible = true;
            },
            pickUp(){

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
            }
        }
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

</style>
