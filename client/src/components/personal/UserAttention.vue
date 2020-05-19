<template>
  <div>
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleClick" class="sideMenu">
      <el-tab-pane label="我的关注人" name="myAttention">
        <div class="info" v-for="item in myAttentionList">
          <el-row :gutter="20">
            <el-col :span="3">
              <el-image
                style="width: 80px; height: 80px"
                :src="'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'"/>
            </el-col>
            <el-col :span="19">
              <div style="margin-top: 5px; font-size: 14px; cursor: pointer" @click="displayUserInfo(item.name)">
                {{item.name}}
              </div>
              <div style="margin-top: 5px; font-size: 12px">{{item.introduce}}</div>
              <div style="margin-top: 20px; font-size: 10px; color: #969696"><span>{{item.productionNum}}作品 </span>
                <span style="margin-left: 15px"> {{item.attentionNum}}人关注</span></div>
            </el-col>
            <el-col :span="2" style="margin-top: 20px;">
              <el-button v-if="item.status" @click="cancelAttention(item.name)" style="width: 80px; height: 40px">已 关
                注
              </el-button>
              <el-button v-else @click="attention(item.name)" style="width: 80px; height: 40px">关 注</el-button>
            </el-col>
          </el-row>


        </div>

      </el-tab-pane>
      <el-tab-pane label="关注我的人" name="attentionMe">
        <div class="info" v-for="item in attentionMeList">
          <el-row :gutter="20">
            <el-col :span="3">
              <el-image
                style="width: 80px; height: 80px"
                :src="'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'"/>
            </el-col>
            <el-col :span="19">
              <div style="margin-top: 5px; font-size: 14px; cursor: pointer" @click="displayUserInfo(item.name)">
                {{item.name}}
              </div>
              <div style="margin-top: 5px; font-size: 12px">{{item.introduce}}</div>
              <div style="margin-top: 20px; font-size: 10px; color: #969696"><span>{{item.productionNum}}作品 </span>
                <span style="margin-left: 15px"> {{item.attentionNum}}人关注</span></div>
            </el-col>
            <el-col :span="2" style="margin-top: 20px;">
              <el-button v-if="item.status" @click="cancelAttention(item.name)" style="width: 80px; height: 40px">已 关
                注
              </el-button>
              <el-button v-else @click="attention(item.name)" style="width: 80px; height: 40px">关 注</el-button>
            </el-col>
          </el-row>


        </div>
      </el-tab-pane>


      <el-tab-pane label="关注的专栏" name="attentionTopic">
        <div class="info" v-for="item in attentionTopicList">
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
              <div style="margin-top: 20px; font-size: 10px; color: #969696"><span>{{item.productionNum}}作品 </span>
                <span style="margin-left: 15px"> {{item.attentionNum}}人关注</span></div>
            </el-col>
            <el-col :span="2" style="margin-top: 20px;">
              <el-button v-if="item.status" @click="cancelAttentionTopic(item.id)" style="width: 80px; height: 40px">已 关
                注
              </el-button>
              <el-button v-else @click="attentionTopic(item.id)" style="width: 80px; height: 40px">关 注</el-button>
            </el-col>
          </el-row>


        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    name: "UserAttention",
    data() {
      return {
        activeName: 'myAttention',
        myAttentionList: [],
        attentionMeList: [],
        attentionTopicList: []
      }
    },
    methods: {
      init() {
        this.getMyAttention();
        this.getAttentionMe();
        this.getAttentionTopic()
      },
      getMyAttention() {
        let obj = this;
        obj.$axios.get("getMyAttention", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.myAttentionList = res.data.data
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
      getAttentionMe() {
        let obj = this;
        obj.$axios.get("getAttentionMe", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.attentionMeList = res.data.data
          }
        })
      },
      getAttentionTopic() {
        let obj = this;
        obj.$axios.get("getAttentionTopic", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.attentionTopicList = res.data.data
          }
        })
      },
      handleClick(tab, event) {
        console.log(tab, event);
      },
      attention(name) {
        this.$axios.get("collectAttention", {
          params: {
            userName: name
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("成功关注");
            this.myAttentionList.forEach(item => {
              if (item.name === name) {
                item.status = true
              }
            });
            this.attentionMeList.forEach(item => {
              if (item.name === name) {
                item.status = true
              }
            });
          }
        })
      },
      cancelAttention(name) {
        this.$axios.get("cancelCollectAttention", {
          params: {
            userName: name
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("已取消关注");
            this.myAttentionList.forEach(item => {
              if (item.name === name) {
                item.status = false
              }
            });
            this.attentionMeList.forEach(item => {
              if (item.name === name) {
                item.status = false
              }
            });
          }
        })
      },
      attentionTopic(id) {
        this.$axios.get("collectTopic", {
          params: {
            id: id
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("成功关注");
            this.attentionTopicList.forEach(item => {
              if (item.id === id) {
                item.status = true
              }
            })
          }
        })
      },
      cancelAttentionTopic(id) {
        this.$axios.get("cancelCollectTopic", {
          params: {
            id: id
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.$message.success("已取消关注");
            this.attentionTopicList.forEach(item => {
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
      this.init();
    }
  }
</script>

<style scoped>
  .info {
    width: 95%;
    height: 100px;
    border-bottom: 1px rgba(12, 0, 62, 0.35);
    text-align: left;
  }
</style>
