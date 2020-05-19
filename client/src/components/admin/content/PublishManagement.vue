<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>创作管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <el-row style="margin: 18px 2%;width: 95%">
      <el-col :span="6">
        <el-input v-model="input" placeholder="请输入内容"/>
      </el-col>
      <el-col :span="6">
        <el-select v-model="status" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-button @click="initPublishes">搜索</el-button>
      </el-col>

    </el-row>
    <el-card style="margin: 18px 2%;width: 95%">
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
          prop="status"
          label="状态"
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
              @click="recommendPublish(scope.row.publishId)"
            >
              推荐
            </el-button>
            <el-button
              type="text"
              size="small"
              @click="deletePublish(scope.row.publishId)"
            >
              删除
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
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button>批量分配</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
    <el-dialog
      title="推荐"
      :visible.sync="dialogVisible"
      @close="close"
      width="50%"
    >
      <div style="text-align: left">
        <div style="margin-top: 20px">
          <span>请选择推荐位</span>
          <el-select v-model="recommendId" placeholder="请选择推荐位">
            <el-option
              v-for="item in 6"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </div>
        <div style="margin-top: 20px; margin-bottom: 20px">
          <el-row style="" :gutter="20">
            <el-col :span="12">
              <el-input v-model="recommendUrl" autocomplete="off" placeholder="图片 URL"></el-input>
            </el-col>
            <el-col :span="4">
              <img-upload @onUpload="uploadImg" ref="imgUpload" style="margin-top: 5px"></img-upload>
            </el-col>
          </el-row>
        </div>
        <el-button type="primary" @click="recommend">推荐</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import ImgUpload from "../../../common/ImgUpload";

  export default {
    name: 'PublishManagement',
    components: {ImgUpload},
    data() {
      return {
        size: 8,
        input: "",
        publishes: [],
        nums: 0,
        curPage: 1,
        options: [{
          value: '-1',
          label: '全部'
        }, {
          value: '0',
          label: '待审核'
        }, {
          value: '1',
          label: '正常'
        }, {
          value: '2',
          label: '违规'
        },],
        status: '-1',


        dialogVisible: false,
        active: 0,

        recommendEsId: 0,
        recommendId: 0,
        recommendUrl: "",

      }
    },
    mounted() {
      this.initPublishes()
    },
    computed: {
      tableHeight() {
        return window.innerHeight - 320
      }
    },
    methods: {
      deletePublish(id) {
        this.$confirm('此操作将永久删除该创作, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post('/admin/content/publish/delete', {id: id}).then(resp => {
              if (resp && resp.status === 200) {
                this.initPublishes()
                this.$message.success("删除成功")
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      initPublishes() {
        let obj = this;
        obj.$axios.get('/admin/content/publishes', {
          params: {
            start: (obj.curPage - 1) * obj.size,
            size: obj.size,
            input: obj.input,
            status: obj.status
          }
        }).then(res => {
          if (res && res.status === 200) {
            obj.publishes = res.data.data.publishManageBeans;
            obj.nums = res.data.data.nums
          }
        })
      },
      handleCurrentChange(val) {
        this.curPage = val;
        this.initPublishes();
        console.log(`当前页: ${val}`);
      },
      recommendPublish(id) {
        this.dialogVisible = true;
        this.recommendEsId = id;
      },
      uploadImg() {
        this.recommendUrl = this.$refs.imgUpload.url
      },
      recommend() {
        console.log(this.recommendEsId);
        console.log(this.recommendUrl);
        console.log(this.recommendId);
        this.$confirm('确定要推荐此文章吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post("/admin/content/publish/recommend", {
                id: this.recommendId,
                essaySongId: this.recommendEsId,
                url: this.recommendUrl
              }).then(resp => {
              if (resp && resp.status === 200) {
                this.$message.success("推荐成功")
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消推荐'
          })
        })
      },
      close() {
        this.recommendId = 0;
        this.recommendEsId = 0;
        this.recommendUrl = "";
        this.dialogVisible = false;
      }
    }
  }
</script>

<style scoped>
</style>
