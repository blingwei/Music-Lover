<template>
  <div class="me-view-comment">
    <div class="me-view-comment-title"><span>{{comments.length}} 条评论</span></div>
    <div class="me-view-comment-item" v-for="(comment, index) in firstComments" >
      <div class="me-view-comment-author"><a><el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></a>
        <div class="me-view-info"><span class="me-view-nickname">{{comment.username}}</span>
          <div class="me-view-meta"><span>{{index+1}}楼</span> <span>{{comment.createDate}}</span></div>
        </div>
      </div>
      <div><p class="me-view-comment-content">{{comment.content}}</p>
        <div class="me-view-comment-tools" @click="toComment(comment.id)">&nbsp;<a>评论</a> </div>
        <div class="me-reply-list" v-for="(replay, index) in comments.filter(showReplay.bind(null,comment.id))">
          <div class="me-reply-item">
            <div style="font-size: 14px;"><span class="me-reply-user">{{replay.username}}:</span> <span
              class="me-reply-user" v-if="replay.replayName">@{{replay.replayName}} </span> <span>{{replay.content}}</span></div>
            <div class="me-view-meta"><span style="padding-right: 10px;">{{replay.createDate}}</span> <a
              class="me-view-comment-tool" @click="toReplay(replay.userId, comment.id)">回复
            </a></div>
          </div>
        </div>
      </div>
    </div>
    <CommentItem ref="commentItem"></CommentItem>
  </div>
</template>

<script>
  import CommentItem from "./CommentItem";
    export default {
        name: "Comment",
        components:{CommentItem},
        mounted(){
            this.initComment();
        },
        data() {

            return {
                comments: [],
                replays: [],
                firstComments: [], //第一层的评论
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
            initComment(){
              this.$axios.get("comment/findEssayWithSongComment", {
                  params: {
                      id: this.$store.state.essayId
                  }
              }).then(res => {
                  if(res.data.code === 200){
                      this.comments = res.data.data;
                      console.log("this.comments");
                      console.log(this.comments);
                      this.firstComments = this.comments.filter(res => res.pid == 0);
                      console.log("this.firstcomments")
                      console.log(this.firstComments)
                  }
              })
            },
            toComment(pid){
                this.comment.pid = pid;
                this.$refs.commentItem.dialogVisible = true;
            },
            toReplay(id,pid){
                this.comment.pid = pid;
                this.comment.replyId = id;
                this.$refs.commentItem.dialogVisible = true;
            },
            showReplay(id, res){
                return res.pid == id;
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


  .me-view-comment{
    text-align: left;
  }

  .me-view-comment-title {
    font-weight: 600;
    border-bottom: 1px solid #f0f0f0;
    padding-bottom: 20px;
  }
  .me-view-comment-item {
    margin-top: 20px;
    padding-bottom: 16px;
    border-bottom: 1px solid #f0f0f0;
  }
  .me-view-comment-author {
    margin: 10px 0;
    vertical-align: middle;
  }
  .me-view-comment-content {
    line-height: 1.5;
  }
  .me-view-comment-tools {
    font-size: 14px;
    margin-top: 4px;
    margin-bottom: 10px;
  }
  .me-view-comment-tools :hover {
    cursor:pointer;
    color: #409EFF;

  }
  .me-reply-list {
    padding-left: 16px;
    border-left: 4px solid #c5cac3;
  }
  .me-reply-item {
    margin-bottom: 8px;
    border-bottom: 1px solid #f0f0f0;
  }

  .me-view-meta {
    font-size: 12px;
    color: #969696;
  }
  a {
    cursor: pointer;
    text-decoration: none;
    transition: none 86ms ease-out;
  }
  .me-view-comment-tool {
    font-size: 13px;
    color: #a6a6a6;
    padding-right: 14px;
  }

  .me-reply-user {
    color: #78b6f7;
  }
  .me-view-info {
    display: inline-block;
    vertical-align: middle;
    margin-left: 8px;
  }
  .me-view-nickname {
    font-size: 14px;
  }


</style>
