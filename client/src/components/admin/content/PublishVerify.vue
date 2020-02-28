<template>
    <div>
        <el-row style="margin: 18px 0px 0px 18px ">
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
                <el-breadcrumb-item>内容管理</el-breadcrumb-item>
                <el-breadcrumb-item>创作审核</el-breadcrumb-item>
            </el-breadcrumb>
        </el-row>

        <el-card style="margin: 18px 2%;width: 95%">
            <el-tabs type="border-card">
                <el-tab-pane label="创作审核">
                    <el-table
                        stripe
                        :data="publishes"
                        style="width: 100%">
                        <el-table-column
                            type="selection"
                            width="120">
                        </el-table-column>
                        <el-table-column
                            type="index"
                            label="序号"
                            width="120">
                        </el-table-column>
                        <el-table-column
                            prop="authorName"
                            label="作者"
                            fit>
                        </el-table-column>
                        <el-table-column
                            prop="publishName"
                            label="名字"
                            fit>
                        </el-table-column>
                        <el-table-column
                            fixed="right"
                            label="操作"
                            width="120">
                            <template slot-scope="scope">
                                <el-button
                                    type="text"
                                    size="small"
                                    @click="view(scope.row.publishId)"
                                >
                                    查看
                                </el-button>
                                <el-button
                                    type="text"
                                    size="small"
                                    @click="verify(scope.row.publishId, 1)"
                                >
                                    审核
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination
                        style="text-align: right"
                        @current-change="handleCurrentChange"
                        :page-size="size"
                        layout="total,prev, pager, next, jumper"
                        :total="nums"
                    >
                    </el-pagination>
                </el-tab-pane>
                <el-tab-pane label="专栏审核">
                    <el-table
                        stripe
                        :data="topics"
                        style="width: 100%">
                        <el-table-column
                            type="selection"
                            width="120">
                        </el-table-column>
                        <el-table-column
                            type="index"
                            label="序号"
                            width="120">
                        </el-table-column>
                        <el-table-column
                            prop="authorName"
                            label="作者"
                            fit>
                        </el-table-column>
                        <el-table-column
                            prop="publishName"
                            label="名字"
                            fit>
                        </el-table-column>
                        <el-table-column
                            fixed="right"
                            label="操作"
                            width="120">
                            <template slot-scope="scope">
                                <el-button
                                    type="text"
                                    size="small"
                                    @click="verify(scope.row.publishId, 2)"
                                >
                                    审核
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination
                        style="text-align: right"
                        @current-change="handleTopicChange"
                        :page-size="size"
                        layout="total,prev, pager, next, jumper"
                        :total="topicNums"
                    >
                    </el-pagination>
                </el-tab-pane>
            </el-tabs>


        </el-card>

        <PublishView  ref="view"/>
    </div>
</template>

<script>
    import PublishView from "./PublishView";

    export default {
        name: 'PublishVerify',
        components: {PublishView},
        comments: [PublishView],
        data() {
            return {
                size: 8,
                publishes: [],
                nums:0,
                curPage: 1,
                topics: [],
                topicNums: 0,
                topicCurPage: 1,
            }
        },
        methods: {
            init() {
                this.initPublishes();
                this.initTopics();
            },
            initPublishes(){
                let obj = this;
                obj.$axios.get('/admin/assessor/publishes',{
                    params: {
                        start: (obj.curPage-1)*obj.size,
                        size: obj.size
                    }
                }).then(res => {
                    if (res && res.status === 200) {
                        obj.publishes = res.data.data.publishVerifyBeans;
                        obj.nums = res.data.data.nums
                    }
                })
            },
            initTopics(){
                let obj = this;
                obj.$axios.get('/admin/assessor/topics',{
                    params: {
                        start: (obj.topicCurPage-1)*obj.size,
                        size: obj.size
                    }
                }).then(res => {
                    if (res && res.status === 200) {
                        obj.topics = res.data.data.publishVerifyBeans;
                        obj.topicNums = res.data.data.nums
                    }
                })
            },
            view(id){
                this.$root.$emit('initView', id);
            },
            verify(id, type){
                this.$confirm('请确认后操作', '审核', {
                    distinguishCancelAndClose: true,
                    confirmButtonText: '通过',
                    cancelButtonText: '异常'
                })
                    .then(() => {
                        this.$axios.post("/admin/assessor/verifyPublish", {
                            id: id,
                            status: 1,
                            type: type
                        }).then(res => {
                            if(res && res.status === 200){
                                this.$message({
                                    type: 'success',
                                    message: '审核完成'
                                });
                                this.init()
                            }
                        }).catch(res => {
                            this.$message({
                                type: 'error',
                                message: '出现异常'
                            });
                        });

                    })
                    .catch(action => {
                        if(action === 'cancel'){
                            this.$axios.post("/admin/assessor/verifyPublish", {
                                id: id,
                                status: 2,
                                type: type
                            }).then(res => {
                                if(res && res.status === 200){
                                    this.$message({
                                        type: 'success',
                                        message: '审核完成'
                                    });
                                    this.init()
                                }
                            }).catch(res => {
                                this.$message({
                                    type: 'error',
                                    message: '出现异常'
                                });
                            });
                        }
                    });
            },
            handleCurrentChange(val){
                this.curPage = val;
                this.initPublishes();
                console.log(`当前页: ${val}`);
            },
            handleTopicChange(val){
                this.topicCurPage = val;
                this.initTopics();
                console.log(`当前页: ${val}`);
            }
        },
        mounted() {
            this.init()
        }
    }
</script>

<style scoped>

</style>
