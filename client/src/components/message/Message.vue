<template>
  <div>
    <el-card>
      <el-container
        style="width: 60%; height: 600px; margin-left: 20%; margin-top: 5px; box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
        <el-aside width="30%">
          <el-autocomplete
            v-model="input"
            :fetch-suggestions="querySearchAsync"
            @select="handleSelect"
            placeholder="请输入联系人"
            clearable
            :trigger-on-focus="false"
          ></el-autocomplete>
          <el-divider></el-divider>
          <div style="width: 100%; height: 400px; overflow-scrolling: auto">
            <div v-for="(item, index) in contacts" :key="index">
              <el-row>
                <el-col :span="22">
                  <div
                    style="font-size: 14px; font-family: 'Agency FB'; font-weight: bold; text-align: left; cursor: pointer; margin-left: 5%">
                    <span @click="$refs.chatRoom.getChatMessages(item.sendUserName)">{{item.sendUserName}}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <i class="el-icon-delete" style="font-size: 20px; cursor: pointer"
                     @click="deleteContact(item.sendUserId, item.receiveUserId)"></i>
                </el-col>
              </el-row>
              <el-divider></el-divider>
            </div>
          </div>

        </el-aside>
        <el-main>
          <ChatRoom ref="chatRoom"></ChatRoom>
        </el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>
  import ChatRoom from "./ChatRoom";

  export default {
    name: "Message",
    components: {ChatRoom},
    data() {
      return {
        input: "",
        contacts: [] //联系人
      }
    },
    methods: {
      init() {
        this.getMyContacts();
      },

      //queryString 为在框中输入的值
      //cb回调函数,将处理好的数据推回
      querySearchAsync(queryString, cb) {
        //调用远程接口 将返回数据封装成 [{value:xxx,key2:value2},{value:xxx,key2:value2}]这样的形式，其中value关键字是必须的，检索框要根据该字段显示，其余的根据需求而定
        this.$axios.get("findUserByUserName", {
          params: {
            name: queryString
          }
        }).then(res => {
          if (res && res.status === 200) {
            cb(res.data.data)
          }
        })
      },

      //@select="handleSelect"  是选中某一列触发的事件,在这里item为选中字段所在的对象
      handleSelect(item) {
        this.$axios.post("message/addContact", {
          object: item.value
        }).then(res => {
          this.$refs.chatRoom.getChatMessages(item.value)
        });
      },


      getMyContacts() {
        let obj = this;
        this.$axios.get("message/getMyContacts").then(res => {
          if (res && res.status === 200) {
            obj.contacts = res.data.data;
            console.log(obj.contacts)
          }
        })
      },
      deleteContact(contactId, id) {
        this.$axios.post("message/deleteContact", {
          id: id,
          contactId: contactId
        }).then(res => {
          if (res && res.status === 200) {
            this.getMyContacts();
            this.$message.success("删除成功")
          }
        })
      },
    },
    mounted() {
      this.init()
    }
  }
</script>

<style scoped>


  .el-aside {
    /*background-color: #333;*/
    border-style: solid;
    border-color: #DCDFE6;
    border-width: 0 1px 0 0;
  }


  .el-main {
    margin: 1px;
    padding: 1px;
  }


</style>