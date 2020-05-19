<template>
  <div class="infinite-list-wrapper" style="overflow:auto">
    <el-input v-model="search" placeholder="输入关键字搜索" style="float: left; width: 40%; margin-top: 20px"/>
    <el-button icon="el-icon-search" style=" margin-top: 20px" @click="searchs"></el-button>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="内容" name="essay">
        <el-card
          class="card"
          shadow="hover"
          v-infinite-scroll="load"
          infinite-scroll-disabled="disabled" v-for="card in list" v-bind:key="card.id">
          <div class="channel-item">
            <div class="likes">{{card.heat}}<br>热度</div>
            <div class="bd">
              <a @click="display(card.essay.id)"><h3 v-html="card.essay.title">{{card.essay.title}}</h3>
              </a>
              <div class="block">

                <!--            <div class="pic">-->
                <!--              <div class="pic-wrap">-->
                <!--&lt;!&ndash;                <img src="https://img1.doubanio.com/view/group_topic/small/public/p226962028.webp">&ndash;&gt;-->
                <!--              </div>-->
                <!--            </div>-->
                <p v-html="card.essay.intor">{{card.essay.intor}}
                </p></div>
              <div class="source">
                <span class="from">来自<a href="https://www.douban.com/group/638400/">{{card.essay.userName}}</a></span>
                <span class="pubtime" v-html="card.essay.songName">{{card.essay.songName}}</span>
              </div>
            </div>
          </div>


        </el-card>
        <el-pagination
          style="text-align: center"
          @current-change="handleCurrentChange"
          :page-size="size"
          layout="total,prev, pager, next, jumper"
          :total="nums"
        >
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="用户" name="user">
        <div class="info" v-for="item in userList">
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
          </el-row>


        </div>
        <el-pagination
          style="text-align: center"
          @current-change="handleUserChange"
          :page-size="size"
          layout="total,prev, pager, next, jumper"
          :total="userNums"
        >
        </el-pagination>
      </el-tab-pane>
    </el-tabs>


  </div>
</template>

<script>
  export default {
    data() {
      return {
        list: [],
        search: '',
        // loading: false,
        activeName: 'essay',
        type: 'essay',//搜索的类型
        size: 8,
        nums: 0,
        curPage: 1,
        userList: [],
        userNums: 0,
        userCurPage: 1,
      }
    },
    computed: {
      noMore() {
        return this.count >= 20
      },
      disabled() {
        return this.loading || this.noMore
      }
    },

    methods: {
      initEssay() {
        this.$axios.get("musicSea/findAllEssayWithSong", {
          params: {
            message: this.search,
            start: (this.curPage - 1) * this.size,
            size: this.size
          }
        }).then(res => {
          if (res.data.code === 200) {
            console.log(res.data.data);
            this.list = res.data.data.list;
            this.nums = res.data.data.nums
          }
          if (res.data.code === 400) {
            this.$message.error("发生未知错误")
          }
        })
      },
      initUser() {
        this.$axios.get("musicSea/findAllUserByPage", {
          params: {
            message: this.search,
            start: (this.userCurPage - 1) * this.size,
            size: this.size
          }
        }).then(res => {
          if (res.data.code === 200) {
            console.log(res.data.data);
            this.userList = res.data.data.list;
            this.userNums = res.data.data.nums
          }
          if (res.data.code === 400) {
            this.$message.error("发生未知错误")
          }
        })
      },
      searchs() {
        if (this.type === "essay") {
          this.initEssay()
        } else if (this.type === 'user') {
          this.initUser()
        }


      },
      load() {

      },
      display(id) {
        let routeData = this.$router.resolve({
          path: '/essayDisplay',
          query: {id: id}
        });
        window.open(routeData.href, '_blank');
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

      handleCurrentChange(val) {
        this.curPage = val;
        this.initEssay();
        console.log(`当前页: ${val}`);
      },
      handleUserChange(val) {
        this.userCurPage = val;
        this.initUser();
        console.log(`当前页: ${val}`);
      },

      handleClick(tab, event) {
        this.type = tab.name;
        console.log(tab, event);
      }
    },
    mounted() {
      this.initEssay();
      this.initUser();
    }
  }
</script>

<style scoped>

  .channel-item {
    margin-bottom: 20px;
    text-align: left;
  }

  div {
    display: block;
  }


  .channel-item .likes {
    float: left;
    margin-right: 19px;
    padding: 8px 0;
    width: 48px;
    line-height: 1.3;
    text-align: center;
    font-size: 13px;
    color: #ca6445;
    background: #fae9da;
  }

  .channel-item .bd {
    padding-bottom: 10px;
    border-bottom: 1px solid #e5e5e5;
  }

  .channel-item .bd, .channel-item .block, .channel-item .block p, .mod .hd, .channel-group-rec li, .channel-group-rec li .info {
    overflow: hidden;
    zoom: 1;
  }

  .channel-item h3 {
    margin: 0 0 12px 0;
    line-height: 1.3;
    font-weight: normal;
    background: 0;
  }

  .channel-item .block {
    padding: 10px;
    color: #999;
    background: #f9f9f9;
    height: 60px;
  }

  .channel-item .pic {
    position: relative;
    float: left;
    margin-right: 16px;
    width: 60px;
    height: 60px;
    overflow: hidden;
    white-space: nowrap;
  }

  .channel-item .block p {
    margin: 0;
  }

  .channel-item .source {
    margin-top: 10px;
    color: #666;
  }

  .channel-item .source .from {
    margin-right: 18px;
  }

  .channel-item .source {
    margin-top: 10px;
    color: #666;
  }

  .channel-item .pubtime {
    color: #999;
  }

  a:visited {
    color: #666699;
    text-decoration: none;
  }

  a:link {
    color: #37a;
    text-decoration: none;
  }

  a:visited {
    color: #669;
    text-decoration: none;
  }

  a {
    cursor: pointer;
  }

  user agent stylesheet
  a:-webkit-any-link {
    color: -webkit-link;
    cursor: pointer;
    text-decoration: underline;
  }

  .card {
    width: 75%;
  }

  .info {
    width: 95%;
    height: 100px;
    border-bottom: 1px rgba(12, 0, 62, 0.35);
    text-align: left;
  }
</style>
