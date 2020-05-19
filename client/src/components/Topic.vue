<template>
  <div>
    <div class="info">
      <el-row :gutter="5" style="margin-top: 20px">
        <el-col :span="3">
          <el-image
            style="width: 100px; height: 100px"
            :src="'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'"/>
        </el-col>
        <el-col :span="16">
          <div style="margin-top: 10px; font-size: 14px">{{topicInfo.name}}</div>
          <div style="margin-top: 5px; font-size: 12px">{{topicInfo.introduce}}</div>
          <div style="margin-top: 20px; font-size: 10px; color: #969696"><span>{{topicInfo.productionNum}}作品 </span>
          </div>
        </el-col>
        <el-col :span="5" style="margin-top: 30px;">
          <div @click="" v-if="topicInfo.identity" class="clicked">管理专栏</div>
          <div @click="cancelAttentionTopic" v-else-if="topicInfo.status" class="clicked">已关注</div>
          <div @click="attentionTopic" v-else class="clicked">关 注</div>
          <div @click="dialogVisible = true" class="clicked">投 稿</div>
        </el-col>
      </el-row>
      <el-dialog :visible.sync="dialogVisible" style="width: 60%; margin-left: 20%" @close="checkList = []">
        <el-input v-model="search" placeholder="请输入您的作品名称" style="width: 60%"></el-input>
        <div class="myPublish">
          <el-checkbox-group v-model="checkList">
            <div style="width: 60%; height: 20px"
                 v-for="(item,index) in myPublishList.filter(data => !search || data.title.toLowerCase().includes(search.toLowerCase()))"
                 :key="index">
              <el-checkbox :label="item.id">
                {{item.title}}
              </el-checkbox>
            </div>
          </el-checkbox-group>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="contribute">投 稿</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="topics">

      <div class="display" v-for="item in publishList" v-if="publishList.length>0">
        <el-card shadow="hover" style="height: 100%;">
          <div style="font-size: 14px; font-weight: bold; color: #78b6f7; margin-bottom: 5px">{{item.title}}</div>
          <div v-html="item.content" class="content"></div>
          <span class="click" @click="displayUserInfo(item.userName)"> 来自 {{item.userName}}</span>
          <span @click="display(item.id)" style="margin-left: 10%" class="click">前往文章</span>
        </el-card>
      </div>
      <div style="height: 200px; font-size: 20px; line-height: 200px; text-align: center; color: #909399" v-else>
        该专栏还没有任何作品
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Topic",
    data() {
      return {
        topic: this.$route.query.id,
        topicInfo: {
          name: "",
          introduce: "",
          status: false,  //是否被自己关注
          identity: false, //是否是自己的专栏
          productionNum: 0
        },
        publishList: [],
        dialogVisible: false,
        search: '',
        myPublishList: [],
        checkList: []
      }
    },
    methods: {
      init() {
        let obj = this;
        obj.$axios.get("getTopicInfo", {
          params: {
            topicId: this.$route.query.id
          }
        }).then(res => {
          if (res.data.code === 200) {
            obj.topicInfo = res.data.data.topicInfo;
            obj.publishList = res.data.data.publishList;

          }
        })
      },
      displayUserInfo(userName) {
        let identity = this.$route.query.personalUsername === userName;
        let routeData = this.$router.resolve({
          path: '/personal',
          query: {
            personalUsername: userName,
            identity: identity
          }
        });
        window.open(routeData.href, '_blank');
      },
      display(id) {
        let routeData = this.$router.resolve({
          path: '/essayDisplay',
          query: {id: id}
        });
        window.open(routeData.href, '_blank');
      },
      getPublish() {
        this.$axios.get("/getPublicInfos", {
          params: {
            userName: this.$store.state.user.username
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.myPublishList = res.data.data
          }
        })
      },
      contribute() {
        let obj = this;
        if (obj.checkList.length === 0) {
          obj.$message.warning("请选择需要投稿的作品");
        } else {
          obj.$axios.post("contributeForTopic", {
            topicId: obj.topic,
            publishIdList: obj.checkList
          }).then(res => {
            if (res.data.code === 200) {
              obj.$message.success("您的投稿已通知该专栏的作者，作者审核后将会通着您")
              obj.dialogVisible = false;
              this.checkList = [];
            } else {
              obj.$message.error("发生未知错误")
            }
          })
        }
      },
      attentionTopic() {
        this.$axios.get("collectTopic", {
          params: {
            id: this.topic
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("成功关注");
            this.topicInfo.status = true
          }
        })
      },
      cancelAttentionTopic() {
        this.$axios.get("cancelCollectTopic", {
          params: {
            id: this.topic
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("已取消关注");
            this.attentionTopicList.forEach(item => {
              if (item.id === id) {
                this.topicInfo.status = false
              }
            })
          }
        })
      },
    },
    mounted() {
      this.init();
      this.getPublish();
    }
  }
</script>

<style scoped>

  .info {
    width: 60%;
    height: 100px;
    border-bottom: 1px rgba(12, 0, 62, 0.35);
    text-align: left;
    margin-left: 20%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }

  .topics {
    width: 60%;
    margin-top: 20px;
    margin-left: 20%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }

  .display {
    width: 100%;
    height: 180px;
    margin-bottom: 10px;
    text-align: left;
  }

  .content {
    font-size: 10px;
    height: 100px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .clicked {
    width: 75px;
    height: 30px;
    font-size: 14px;
    line-height: 30px;
    color: #fff;
    background-color: #0084ff;
    float: left;
    margin-right: 10px;
    text-align: center;
    border: 1px solid;
    border-radius: 3px;
    cursor: pointer;
  }

  .click {
    font-size: 10px;
    color: #78b6f7;
    cursor: pointer;
  }

  .el-dialog {
    position: relative;
    margin: 0 auto 50px;
    border-radius: 2px;
    -webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
    box-shadow: 0 1px 3px rgba(0, 0, 0, .3);
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 25%;
    height: 400px;
  }

  .myPublish {
    margin-top: 20px;
    height: 200px;
    width: 100%;
    overflow-x: hidden;
    overflow-y: scroll;
  }


</style>