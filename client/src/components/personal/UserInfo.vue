<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="8">
        <div style="">
          <img :src="userInfo.url" height="160px" width="200px"/>
          <img-upload v-if="this.$route.query.identity == 'true'" @onUpload="uploadImg" ref="imgUpload"
                      style="margin-top: 5px"></img-upload>

        </div>
      </el-col>
      <el-col :span="12">
        <div class="information">姓名: {{userInfo.name}}</div>
        <div class="information">年龄: <span v-if="userInfo.age != ' '">{{userInfo.age}}</span> <span
          v-else>未设置</span></div>
        <div class="information">性别: <span v-if="userInfo.sex != ' '">{{userInfo.sex}}</span> <span
          v-else>未设置</span></div>
        <div class="information">简介: {{userInfo.introduce}}</div>
      </el-col>
      <el-col :span="4">
        <el-button @click="editUserInfo" style="margin-top: 140px" v-if="this.$route.query.identity == 'true'">编
          辑 资 料
        </el-button>
        <div v-else>
          <el-button @click="attention" style="margin-top: 140px" v-if="!userInfo.status">关 注</el-button>
          <el-button @click="cancelAttention" style="margin-top: 140px" v-else>已 关 注</el-button>
        </div>

      </el-col>
    </el-row>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="姓名" :label-width="width">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="width">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="width">
          <el-select v-model="form.sex" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="简介" :label-width="width">
          <el-input v-model="form.introduce"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="edit">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import ImgUpload from "../../common/ImgUpload";

  export default {
    name: "UserInfo",
    components: {ImgUpload},
    data() {
      return {
        userInfo: {},
        form: {
          name: " ",
          sex: " ",
          age: " ",
          introduce: " "
        },
        options: [{
          value: '0',
          label: '男性'
        }, {
          value: '1',
          label: '女性'
        }],
        dialogFormVisible: false,
        width: '40px'
      }
    },
    methods: {
      init() {
        this.$axios.get("getUserInfo", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.userInfo = res.data.data;
            console.info("url");
            console.info(res.data.data)
            console.info(this.userInfo)
          }
        })
      },
      editUserInfo() {
        let obj = this;
        obj.dialogFormVisible = true;
        obj.form.name = obj.userInfo.name;
        obj.form.age = obj.userInfo.age;
        obj.form.sex = obj.userInfo.sex;
        obj.form.introduce = obj.userInfo.introduce;
      },
      edit() {
        let obj = this;
        obj.dialogFormVisible = false;
        console.log(obj.form);
        obj.$axios.post("editUserInfo", {
            name: obj.form.name,
            age: obj.form.age,
            sex: obj.form.sex,
            introduce: obj.form.introduce
          }
        ).then(res => {
          if (res.data.code === 200) {
            obj.init();
            obj.$message({
              type: 'success',
              message: '修改成功!'
            });
          }
          if (res.data.code === 400) {
            obj.$message.error("出現異常")
            console.log(res.data.message)
          }
        })
      },
      attention() {
        this.$axios.get("collectAttention", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.userInfo.status = true
          }
        })
      },
      cancelAttention() {
        this.$axios.get("cancelCollectAttention", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.userInfo.status = false
          }
        })
      },

      uploadImg() {
        let params = new URLSearchParams();
        params.append('url', this.$refs.imgUpload.url);
        this.$axios.post("uploadUserImage", params).then(res => {
          if (res && res.status === 200) {
            this.userInfo.url = this.$refs.imgUpload.url;
          }
        }).catch(error => {
          this.$message({
            type: 'error',
            message: '出现异常!'
          });
          console.log(error)
        });

      }


    },
    mounted() {
      this.init()
    }
  }
</script>

<style scoped>

  .picture {

  }

  .information {
    width: 100%;
    height: 30px;
    margin-top: 15px;
    line-height: 30px;
  }
</style>
