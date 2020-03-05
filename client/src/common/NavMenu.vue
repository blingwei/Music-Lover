<template>
    <el-menu
        :default-active="'/index'"
        router
        mode="horizontal"
        background-color="white"
        text-color="#222"
        active-text-color="red"
        style="min-width: 1300px">
        <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
            {{ item.navItem }}
        </el-menu-item>
        <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">BlingBlingWei - Your Music Lover</span>
        <el-popover
            placement="bottom"
            width="400"
            trigger="click"
            style="position: absolute;padding-top: 15px;right: 6%;"
        >
            <el-badge :value="onReadNum" class="item" slot="reference" :max="99">
                <i class="el-icon-message" style="font-size: 30px; cursor: pointer"></i>
            </el-badge>


            <span style="text-align: center; font-size: 20px; color: #005cc5">私信</span>
            <el-divider></el-divider>
            <div style="width: 100%; height: 400px; overflow-scrolling: auto">
                <div v-for="(item, index) in messages" :key="index">
                    <el-row>
                        <el-col :span="22">
                            <div style="font-size: 20px; font-family: 'Agency FB'; font-weight: bold">{{item.sendUserName}}</div>
                            <div style="font-size: 12px">{{item.content}}</div>
                        </el-col>
                        <el-col :span="2">
                            <i class="el-icon-delete" style="font-size: 30px; cursor: pointer" @click="deleteMessage(item.id)"></i>
                        </el-col>
                    </el-row>
                    <el-divider></el-divider>
                </div>
            </div>

        </el-popover>

        <el-dropdown @command="handleCommand" style="position: absolute;padding-top: 10px;right: 1%;">
            <el-tooltip class="el-dropdown-link" effect="dark" :content="username" placement="left">
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            </el-tooltip>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="personal">个人中心</el-dropdown-item>
                <el-dropdown-item command="creation">创作中心</el-dropdown-item>
                <el-dropdown-item command="quit">退出</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </el-menu>
</template>

<script>
    export default {
        name: "NavMenu",
        data() {
            return {
                navList: [
                    {name: '/index', navItem: '首页'},
                    {name: '/musicSea', navItem: 'music sea'},
                    {name: '/', navItem: '音乐库'}
                ],
                username: this.$store.state.user.username,
                messages: [],
                onReadNum: 0
            }
        },
        methods: {
            handleCommand(command) {
                if (command === "quit") {
                    this.$store.commit('logout');
                    this.$router.go(0)//刷新页面
                } else if (command === "personal") {
                    let routeData = this.$router.resolve({
                        path: '/personal',
                        query: {
                            personalUsername: this.$store.state.user.username,
                            identity: true
                        }
                    });
                    window.open(routeData.href, '_blank');
                } else if (command === "creation") {
                    this.$router.push("/" + command);
                }

            },
            initWebSocket() {
                // 连接错误
                this.websocket.onerror = this.setErrorMessage;

                // 连接成功
                this.websocket.onopen = this.setOnopenMessage;

                // 收到消息的回调
                this.websocket.onmessage = this.setOnmessageMessage;

                // 连接关闭的回调
                this.websocket.onclose = this.setOncloseMessage;

                // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
                window.onbeforeunload = this.onbeforeunload;
            },
            setErrorMessage() {
                console.log('WebSocket连接发生错误   状态码：' + this.websocket.readyState)
            },
            setOnopenMessage() {
                console.log('WebSocket连接成功    状态码：' + this.websocket.readyState)
            },
            setOnmessageMessage(event) {
                // 根据服务器推送的消息做自己的业务处理
                this.$notify({
                    title: '通知',
                    message: event.data,
                    duration: 0
                });
                this.getMessages();
                console.log('服务端返回：' + event.data)
            },
            setOncloseMessage() {
                console.log('WebSocket连接关闭    状态码：' + this.websocket.readyState)
            },
            onbeforeunload() {
                this.closeWebSocket()
            },
            closeWebSocket() {
                this.websocket.close()
            },
            getMessages() {
                this.$axios.get("message/getMessages").then(res => {
                    if (res && res.status === 200) {
                        this.messages = res.data.data;
                        let num = 0;
                        this.messages.forEach(item => {
                            if (item.isRead === 0) {
                                num++;
                            }
                        });
                        this.onReadNum = num;
                        console.log(this.messages)
                    }
                })
            },
            deleteMessage(id){
                this.$axios.post("message/deleteMessage", {id:id}).then(res =>{
                    if (res && res.status === 200){
                        this.getMessages();
                        this.$message.success("删除成功")
                    }
                })
            }
        },
        mounted() {
            if ('WebSocket' in window) {
                this.websocket = new WebSocket('ws://localhost:8421/websocket/' + this.$store.state.user.username);
                this.initWebSocket()
            } else {
                alert('当前浏览器 Not support websocket')
            }

            this.getMessages()

        },
        beforeDestroy() {
            this.onbeforeunload()
        },

    }
</script>

<style scoped>
    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
</style>
