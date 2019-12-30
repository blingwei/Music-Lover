<template>
  <div>
    <el-dialog
      title="评论"
      width="30%"
      :visible.sync="dialogVisible"
    >
      <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        v-model="textarea">
      </el-input>
      <span slot="footer" class="dialog-footer">
    <el-button @click="cleanComment">取 消</el-button>
    <el-button type="primary" @click="addComment">评论</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>

  export default {
      name: "CommentItem",
      data() {
        return {
            dialogVisible: false,
            textarea:"",
            commentInfo: []
        }
      },
      methods: {
          addComment(){
              if(this.textarea == null){
                  this.$message.error("评论内容不能为空");
                  return;
              }
              this.commentInfo = this.$parent.comment;
              this.commentInfo.content = this.textarea
              this.$axios.post("/comment/addComment", this.commentInfo).then(res => {
                  this.$message.info(res.data.message)
                  this.dialogVisible = false;
                  this.cleanComment();
                  this.$parent.initComment();
              })
          },
          cleanComment(){
              this.textarea = "";
              this.$parent.reset();
              this.dialogVisible = false;
          }
      }
    }
</script>

<style>

</style>
