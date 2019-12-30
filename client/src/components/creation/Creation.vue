<template>
  <div class="content">
    <Upload class="upload" @onUpload="uploadSongUrl"></Upload>
    <el-input type="text" v-model="title" placeholder="请输入标题" maxlength="20" show-word-limit minlength="1" style="margin-bottom: 5px"> </el-input>
    <el-input type="textarea" v-model="intor" placeholder="请输入简介" maxlength="50"  show-word-limit minlength="9"></el-input>
    <Editor ref="editor"></Editor>
    <el-button type="primary" class="submit" @click="submit">上传<i class="el-icon-upload el-icon--right"></i></el-button>
  </div>
</template>

<script>
  import Editor from "./Editor";
  import Upload from "./Upload";
    export default {
        name: "Creation",
        components: {Editor, Upload},
        data() {
            return {
                title: '',
                intor: '',
                songUrl: '',
            }
        },
        methods:{
            uploadSongUrl(data){
                this.songUrl = data.message;
                console.log(data.message)
            },
            submit() {
                this.$axios.post("creation/upload", {
                    songId: this.songUrl,
                    title: this.title,
                    intor: this.intor,
                    content: this.$refs.editor.html
                }).then(res => {
                    if(res.data.code === 200){
                        this.$message.success(res.data.message)
                    }
                    if(res.data.code === 400){
                        this.$message.error(res.data.message)
                    }
                })
                // console.log("title"+ this.title)
                // console.log("intor"+ this.intor)
                // console.log("html" + this.$refs.editor.html)

            }
        }
    }
</script>

<style scoped>
  .upload {
    height: 100px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    margin-top: 50px;
    margin-bottom: 5px;
  }

  .content {
    width: 60%;
    margin-left: 20%;

  }
  .submit {
    position: fixed;
    top:120px;
    left: 80%;
  }
</style>
