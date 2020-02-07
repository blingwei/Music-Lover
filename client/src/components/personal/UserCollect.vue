<template>
  <div>
    <div class="top">
      <span v-if="identity">我 的 收 藏</span>
      <span v-else>他 的 收 藏</span>
    </div>
    <div class="display" v-for="item in displayList">
      <el-card shadow="hover" style="height: 100%;">
        <div style="font-size: 14px; font-weight: bold; color: #78b6f7; margin-bottom: 5px">{{item.title}}</div>
        <div v-html="item.content" class="content"></div>
        <div class="click" style="font-size: 10px;  color: #78b6f7; ">来自 {{item.UserName}} <span @click="display(item.essayId)" style="margin-left: 10%" class="click">前往文章</span></div>
      </el-card>

    </div>


  </div>
</template>

<script>
    export default {
        name: "UserCollect",
        data() {
          return {
            identity: true,
            displayList: [{
              title: '第一篇',
              content: "<p>下面是狗子的衣食住行类物品：</p>\n" +
                "<p>1.衣——没有。从最开始养狗之初，我就坚信宠物并不需要穿衣服的，（而且穿衣服还要洗，还要找地方放）而且我家本就是长毛犬，衣服就此拜拜了~</p>\n" +
                "<p>2.食——原木色狗盆狗碗，这是唯一一个从最开始一直没有换过的东西。狗碗放在厨房，方便接水，狗粮就放在旁边，一切就近摆放，物品更容易使用完后即时归位。</p>\n" +
                "<p>狗狗大型用品基本就是这个蓝色盒子，狗碗，狗粮加上狗窝</p>\n" +
                "<p>蓝色盒子里的各种物品，这个玩具绳球考虑要不要极简掉<br />\n" +
                "3.住——狗窝。这个就换了好多种啊！连我家孩子都说妈妈你别再买狗窝了（对话出现在今年双11）然后我就忍住了…目前是一个灰色的极简风狗垫，自己拿旧床单做了个小被子。</p>\n" +
                "<p>家里还有个专门的地方给狗狗上厕所，以备不时之需。之前还用狗尿垫（从来没用过狗厕所，因为不好清洗，也因为我的狗狗不会抓刨尿垫）最近试着啥也不用，狗狗上完厕所直接拿水冲。</p>\n",
              UserName: '张子枫',
              essayId: "10"

            },{
              title: '第二篇',
              content: "<p>yum install –y git</p>\n" +
                "<p>git clone https://github.com/mobz/elasticsearch-head.git</p>\n" +
                "<p>安装grunt</p>\n" +
                "<p>cd elasticsearch-head</p>\n" +
                "<p>npm install -g grunt --registry=https://registry.npm.taobao.org</p>\n" +
                "<p>在elasticsearch-head目录下node_modules/grunt下如果没有grunt二进制程序，需要执行：</p>\n" +
                "<p>npm install grunt --save</p>\n" +
                "<p>修改配置 elasticsearch-head下Gruntfile.js文件</p>\n" +
                "<p>修改connect配置节点</p>\n",
              UserName: '赵今麦',
            }]
          }
        },
      methods:{
        display(id){
          this.$store.commit('setEssayId', id)
          window.sessionStorage.setItem('essayId', JSON.stringify(id))
          this.$router.push({name: "EssayWithSongDisplay"})
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
      }
    }
</script>

<style scoped>
  .top{
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
    font-size: 10px; height: 100px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .click :hover{
    cursor: pointer;
  }
</style>
