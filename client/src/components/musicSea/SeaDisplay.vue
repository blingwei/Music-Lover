<template>
  <div class="infinite-list-wrapper" style="overflow:auto" >
    <el-input v-model="search"  placeholder="输入关键字搜索" style="float: left; width: 40%; margin-top: 20px" />
    <el-button icon="el-icon-search"  style="float: left; margin-top: 20px" @click="searchEssay"></el-button>
    <el-card
      class="card"
      shadow="hover"
      v-infinite-scroll="load"
      infinite-scroll-disabled="disabled" v-for="card in list" v-bind:key="card.id">

      <div class="channel-item">
        <div class="likes">{{card.heat}}<br>热度</div>
        <div class="bd">
          <a @click="display(card.essay.id)"><h3 v-html="card.essay.title">{{card.essay.title}}</h3></a>
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
<!--    <p v-if="loading">加载中...</p>-->
<!--    <p v-if="noMore">没有更多了</p>-->

  </div>
</template>

<script>
    export default {
        data () {
            return {
                list: [],
                search: ''
                // loading: false
            }
        },
        computed: {
            noMore () {
                return this.count >= 20
            },
            disabled () {
                return this.loading || this.noMore
            }
        },

        methods: {
            searchEssay() {
                this.$axios.get("musicSea/findAllEssayWithSong", {
                    params: {
                        message: this.search
                    }
                }).then(res => {
                    console.log(res)
                    if(res.data.code === 200){
                        this.list = res.data.data
                    }
                    if(res.data.code === 400){
                        this.$message.error("发生未知错误")
                    }
                })

            },
            load () {

            },
            display(id){
                this.$store.commit('setEssayId', id)
                window.sessionStorage.setItem('essayId', JSON.stringify(id))
                this.$router.push({name: "EssayWithSongDisplay"})

            }
        },
        mounted() {
            this.searchEssay()
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
</style>
