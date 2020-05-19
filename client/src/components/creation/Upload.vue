<template>
  <el-upload
    class="upload-demo"
    ref="upload"
    action="http://localhost:8421/api/creation/uploadSong"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :file-list="fileList"
    :on-success="handleSuccess"
    :on-error="handleError"
    :auto-upload="false">
    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
    <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
    <div slot="tip" class="el-upload__tip">选择需要上传的音乐</div>
  </el-upload>
</template>

<script>
  export default {
    name: "Upload",
    data() {
      return {
        fileList: [],
        url: ''
      };
    },
    methods: {
      submitUpload() {
        this.$refs.upload.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleSuccess(response) {
        this.url = response.data;
        this.$message.success(response.message);
        this.$emit('onUpload', {message: response.data})
      },
      handleError(response) {
        this.$message.error(response.message)
      }
    }
  }
</script>

<style scoped>

</style>
