<template>
  <div>
    <div class="top">
      <span v-if="this.$route.query.identity">我 的 收 藏</span>
      <span v-else>他 的 收 藏</span>
    </div>
    <div class="display" v-for="item in displayList">
      <el-card shadow="hover" style="height: 100%;">
        <div style="font-size: 14px; font-weight: bold; color: #78b6f7; margin-bottom: 5px">{{item.title}}</div>
        <div v-html="item.content" class="content"></div>
        <span class="click" @click="displayUserInfo(item.userName)"> 来自 {{item.userName}}</span>
        <span @click="display(item.id)" style="margin-left: 10%" class="click">前往文章</span>
      </el-card>

    </div>


  </div>
</template>

<script>
  export default {
    name: "UserCollect",
    data() {
      return {
        displayList: []
      }
    },
    methods: {
      init() {
        this.$axios.get("/getCollectInfos", {
          params: {
            userName: this.$route.query.personalUsername
          }
        }).then(res => {
          if (res.data.code === 200) {
            this.displayList = res.data.data
          }
        })
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
      }
    },
    filters: {
      ellipsis(value) {
        if (!value) return "";
        if (value.length > 100) {
          return value.slice(0, 100) + "...";
        }
        return value;
      }
    },
    mounted() {
      this.init();
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

  .click {
    font-size: 10px;
    color: #78b6f7;
  }

  .click:hover {
    cursor: pointer;
  }
</style>
