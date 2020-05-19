<template>
  <div>
    <el-dialog title="申请专栏" :visible.sync="dialogFormVisible" style="text-align: left">
      <el-form :model="form">
        <el-form-item label="名字" :label-width="width">
          <div class="input">
            <el-input v-model="form.name"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="简介" :label-width="width">
          <div class="input">
            <el-input v-model="form.introduce"></el-input>
          </div>
        </el-form-item>
        <el-form-item label="标签" :label-width="width">
          <div class="input">
            <el-select v-model="form.label" placeholder="父标签">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.name"
                :value="item.value">
              </el-option>
            </el-select>
          </div>
        </el-form-item>
        <el-form-item label="封面照片" :label-width="width">
          <div class="input">
            <el-input v-model="form.url"></el-input>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addTopic">确 定</el-button>
      </div>
    </el-dialog>
    <div v-if="displayList.length === 0 ">
      <div class="none">
        还没有创建专栏 <span @click="dialogFormVisible = true" class="click" v-if="this.$route.query.identity">点击申请创建</span>
      </div>


    </div>

    <div v-else>
      <div class="top">
        <span v-if="this.$route.query.identity">我 的 专 栏</span>
        <span v-else>他 的 专 栏</span>
        <span style="margin-left: 75%; font-size: 12px; font-weight: normal" @click="dialogFormVisible = true"
              class="click" v-if="this.$route.query.identity && displayList.length>0">点击申请创建</span>
      </div>
      <div class="info" v-for="item in displayList">
        <el-row :gutter="20">
          <el-col :span="3">
            <el-image
              style="width: 80px; height: 80px"
              :src="'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'"/>
          </el-col>
          <el-col :span="19">
            <div style="margin-top: 5px; font-size: 14px; cursor: pointer" @click="displayTopicInfo(item.id)">
              {{item.name}}
            </div>
            <div style="margin-top: 5px; font-size: 12px">{{item.introduce}}</div>
            <div style="margin-top: 20px; font-size: 10px; color: #969696"><span>{{item.productionNum}}作品 </span> <span
              style="margin-left: 15px"> {{item.attentionNum}}人关注</span></div>
          </el-col>
          <el-col :span="2" style="margin-top: 20px;">
            <el-button v-if="item.status" @click="cancelAttention(item.id)" style="width: 80px; height: 40px">已 关 注
            </el-button>
            <el-button v-else @click="attention(item.id)" style="width: 80px; height: 40px">关 注</el-button>
          </el-col>
        </el-row>


      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: "UserTopic",
    data() {
      return {
        displayList: [],
        form: {
          name: " ",
          url: " ",
          introduce: " ",
          label: " ",
        },
        dialogFormVisible: false,
        width: '80px',
        options: []
      }
    },
    methods: {
      init() {
        let obj = this;
        obj.$axios.get("getTopics", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.displayList = res.data.data
          }
        })
      },
      addTopic() {
        let obj = this;
        obj.$axios.post("addTopic", {
          name: obj.form.name,
          url: obj.form.url,
          introduce: obj.form.introduce,
          label: obj.form.label,
        }).then(res => {
          if (res.data.code === 200) {
            obj.$message({
              type: 'success',
              message: '申请成功，会在3个工作日审核，审核通过后会通知您!'
            });
            obj.dialogFormVisible = false;
            obj.form = ""
          }
          if (res.data.code === 400) {
            obj.$message.error("出現異常")
            console.log(res.data.message)
          }
        })
      },
      attention(id) {
        this.$axios.get("collectTopic", {
          params: {
            id: id
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("成功关注");
            this.displayList.forEach(item => {
              if (item.id === id) {
                item.status = true
              }
            })
          }
        })
      },
      cancelAttention(id) {
        this.$axios.get("cancelCollectTopic", {
          params: {
            id: id
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("已取消关注");
            this.displayList.forEach(item => {
              if (item.id === id) {
                item.status = false
              }
            })
          }
        })
      },
      displayTopicInfo(id) {
        let routeData = this.$router.resolve({
          path: '/topic',
          query: {id: id}
        });
        window.open(routeData.href, '_blank');
      }
    },
    mounted() {
      this.init()
    }
  }
</script>

<style scoped>
  .top {
    width: 100%;
    height: 50px;
    line-height: 50px;
    font-family: "PingFang SC";
    text-align: left;
    padding-left: 3%;
    font-size: 20px;
    font-weight: bold;
  }

  .click {
    color: #78b6f7;
  }

  .click:hover {
    cursor: pointer;
  }

  .none {
    width: 100%;
    height: 200px;
    text-align: center;
    font-size: 16px;
    line-height: 200px;
  }

  .info {
    width: 95%;
    height: 100px;
    border-bottom: 1px rgba(12, 0, 62, 0.35);
    text-align: left;
  }

  .input {
    width: 400px;
  }
</style>