<template>
    <div>
        <div class="top">
            <span v-if="this.$store.state.personal.identity">我 的 动 态</span>
            <span v-else>他 的 动 态</span>
        </div>

        <div class="display" v-for="item in conditionList">
            <el-card shadow="hover" style="height: 100%;">
            <div class="condition">
            {{item.operate}}{{item.type}}{{item.matterName}}
            </div>
            <div class="message" v-if="item.type === '音乐文章'|| item.type === '评论'">
                    <div style="font-size: 14px; font-weight: bold; color: #78b6f7; margin-bottom: 5px">{{item.conditionMessage.name}}</div>
                    <div v-html="item.conditionMessage.content" class="content"></div>
                    <span class="click"  @click="displayUserInfo(item.conditionMessage.userName)"> 来自 {{item.conditionMessage.userName}}</span>
                    <span @click="display(item.conditionMessage.essayId)" style="margin-left: 10%" class="click">前往文章</span>

            </div>
            <div class="message" v-if="item.type === '用户'|| item.type === '专栏'">
                    <el-row :gutter="20" style="margin-top: 20px">
                        <el-col :span="3">
                            <el-image
                                style="width: 80px; height: 80px"
                                :src="'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'"/>
                        </el-col>
                        <el-col :span="19">
                            <div style="margin-top: 5px; font-size: 14px" @click="displayUserInfo(item.name)" v-if="item.type === '用户'">{{item.conditionMessage.name}}</div>
                            <div style="margin-top: 5px; font-size: 14px" @click="displayTopicInfo(item.conditionMessage.id)" v-if="item.type === '专栏'">{{item.conditionMessage.name}}</div>
                            <div style="margin-top: 5px; font-size: 12px">{{item.conditionMessage.introduce}}</div>
                            <div style="margin-top: 20px; font-size: 10px; color: #969696"><span>{{item.conditionMessage.productionNum}}作品 </span> <span style="margin-left: 15px"> {{item.conditionMessage.attentionNum}}人关注</span></div>
                        </el-col>
                        <el-col :span="2" style="margin-top: 20px;">
                            <el-button v-if="item.conditionMessage.status" @click="cancelAttention(item.conditionMessage.name)" style="width: 80px; height: 40px">已 关 注</el-button>
                            <el-button v-else @click="attention(item.conditionMessage.name)" style="width: 80px; height: 40px">关 注</el-button>
                        </el-col>
                    </el-row>
            </div>
            </el-card>
        </div>
    </div>


</template>

<script>
    export default {
        name: "UserCondition",
        data() {
            return {
                conditionList: []
            }
        },
        methods: {
            init(){
                this.$axios.get("/getConditions",{
                    params: {
                        userName: this.$store.state.personal.personalUsername
                    }
                }).then(res =>{
                    if(res.data.code === 200){
                        this.conditionList= res.data.data
                    }
                })
            },
            display(id){
                this.$store.commit('setEssayId', id)
                window.sessionStorage.setItem('essayId', JSON.stringify(id));
                this.$router.push({name: "EssayWithSongDisplay"})
            },
            displayUserInfo(userName){
                let identity = this.$route.params.userName === userName;
                let data = {
                    personalUsername: userName,
                    identity : identity
                };
                this.$store.commit('setPersonal', data);
                this.$router.go(0)//刷新页面
            },
            attention(name) {
                this.$axios.get("collectAttention", {
                    params: {
                        userName: name
                    }
                }).then(res =>{
                    if (res.data.code === 200) {
                        this.$message.success("成功关注");
                        this.conditionList.forEach(item => {
                            if(item.conditionMessage.name === name){
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
                }).then(res =>{
                    if (res.data.code === 200) {
                        this.$message.success("已取消关注");
                        this.conditionList.forEach(item => {
                            if(item.conditionMessage.name === name){
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
                }).then(res =>{
                    if (res.data.code === 200) {
                        this.$message.success("成功关注");
                        this.conditionList.forEach(item => {
                            if(item.conditionMessage.id === id){
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
                }).then(res =>{
                    if (res.data.code === 200) {
                        this.$message.success("已取消关注");
                        this.conditionList.forEach(item => {
                            if(item.conditionMessage.id === id){
                                item.status = false
                            }
                        })
                    }
                })
            },
            displayTopicInfo(id){
                this.$store.commit('setTopicId', id);
                let routeData = this.$router.resolve({ path: '/topic' });
                window.open(routeData.href, '_blank');
            }
        },
        mounted() {
            this.init();
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

    .condition{
        width: 80%;
        height: 20px;
        font-size: 14px;
        line-height: 20px;
        color: #969696;
        border-bottom: 1px;
    }

    .content {
        font-size: 10px; height: 100px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .click{
        font-size: 10px;  color: #78b6f7;
    }
    .click:hover{
        cursor: pointer;
    }
    .message {
        width: 95%;
        height: 100px;
        border-bottom: 1px rgba(12,0,62,0.35);
        text-align: left;
    }
</style>