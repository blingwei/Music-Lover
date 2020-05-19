<template>
  <div>
    <el-container>
      <el-header height="40px">
        <span>{{chatName}}</span> <span v-if="status">(已离线)</span>
      </el-header>
      <el-main>
        <div class="Message-wrapper" v-for="(item, index) in messages" :key="index">
          <div :class="item.role">{{item.content}}</div>
        </div>
      </el-main>
      <el-footer height="200px">
        <el-input
          type="textarea"
          placeholder="请输入内容,按Enter键入"
          v-model="textarea"
          :rows="8"
          @keyup.enter.native="sendMessage"
        >
        </el-input>

      </el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "ChatRoom",
    data() {
      return {
        textarea: '',
        chatName: '',
        status: false,
        messages: [],
      }
    },
    methods: {

      getChatMessages(chatName) {
        this.$axios.get("message/getChatMessages", {
          params: {
            chatName: chatName
          }
        }).then(res => {
          if (res && res.status === 200) {
            this.messages = res.data.data;
            this.chatName = chatName
          }
        })
      },
      sendMessage() {
        let obj = this;
        let content = this.textarea;
        if (this.textarea !== '') {
          this.$axios.post("/message/sendMessage", {
            chatName: this.chatName,
            content: this.textarea
          }).then(res => {
            if (res && res.status === 200) {
              console.log("发消息");
              console.log(content);
              let message = {
                "content": content,
                "role": 'TextMessage TextMessage-receiver'
              };
              this.messages.push(message)
            }
          });
          this.textarea = '';
        }
      },

    },
    mounted() {
      this.chatName = this.$route.query.chatName;
      this.getChatMessages(this.chatName);
      this.$root.$on("send", value => {
        let message = {
          "content": value,
          "role": 'TextMessage TextMessage-sender'
        };
        this.messages.push(message)
      })
    }
  }
</script>

<style scoped>
  .el-header {
    text-align: center;
    line-height: 40px;
    border-style: solid;
    border-color: #DCDFE6;
    border-width: 0 0 1px 0;
    margin: 0;
    padding: 0;
  }

  .el-footer {
    text-align: left;
    margin: 0;
    padding: 0;
  }

  .el-main {
    width: 100%;
    height: 400px;
    margin: 0 10px;
    padding: 0;
    overflow-scrolling: auto;
  }

  .TextMessage-sender {
    background-color: #f6f6f6;
    color: #444;
  }

  .TextMessage {
    max-width: 388px;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
    line-height: 20px;
    letter-spacing: .4px;
    margin-right: 8px;
    margin-left: 8px;
    margin-top: 8px;
    text-align: left;
    min-height: 40px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }

  .TextMessage-receiver {
    background-color: #0084ff;
    color: #fff;
    float: right;
  }

  .Message-wrapper {
    width: 100%;
    min-height: 60px;
  }


</style>