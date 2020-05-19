<template>
  <div>
    <el-dialog title="修改用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="selectedUser" style="text-align: left">
        <el-form-item label="姓名" :label-width="width">
          <el-input v-model="selectedUser.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" :label-width="width">
          <el-input v-model="selectedUser.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="width">
          <el-select v-model="selectedUser.sex" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="简介" :label-width="width">

          <el-input v-model="selectedUser.introduce"></el-input>
        </el-form-item>
        <!--                <el-form-item label="密码" :label-width="width" prop="password">-->
        <!--                    <el-button type="warning" @click="resetPassword(selectedUser.username)">重置密码</el-button>-->
        <!--                </el-form-item>-->
        <el-form-item label="角色分配" :label-width="width" prop="roles">
          <el-radio-group v-model="selectedUser.roleId">
            <el-radio v-for="(role,i) in roles" :key="i" :label="role.adminRole.id">{{role.adminRole.nameZh}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="edit">确 定</el-button>
      </div>
    </el-dialog>

    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>

    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="users"
        stripe
        :default-sort="{prop: 'id', order: 'ascending'}"
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          label="id"
          type="index"
          width="100">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.username}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="名字"
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.name}}</span>
          </template>
        </el-table-column>

        <el-table-column
          prop="age"
          label="年龄"
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.age}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="sex"
          label="性别"
          fit>
          <template slot-scope="scope">
            <span v-if="scope.row.sex === 1">女性</span>
            <span v-if="scope.row.sex === 0">男性</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="introduce"
          label="简介"
          show-overflow-tooltip
          fit>
          <template slot-scope="scope">
            <span>{{scope.row.introduce}}</span>
          </template>
        </el-table-column>
        <el-table-column
          label="状态"
          sortable
          width="100">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editUser(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button>取消选择</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>

  export default {
    name: 'UserProfile',
    data() {
      return {
        users: [],
        roles: [],
        dialogFormVisible: false,
        selectedUser: [],
        width: '100px',
        options: [{
          value: 0,
          label: '男性'
        }, {
          value: 1,
          label: '女性'
        }],
      }
    },
    mounted() {
      this.listUsers();
      this.listRoles()
    },
    computed: {
      tableHeight() {
        return window.innerHeight - 320
      }
    },
    methods: {
      listUsers() {
        var obj = this;
        this.$axios.get('/admin/user/list').then(resp => {
          if (resp && resp.status === 200) {
            obj.users = resp.data.data
            console.log(obj.users)
          }
        })
      },
      listRoles() {
        var obj = this;
        this.$axios.get('/admin/role/list').then(resp => {
          if (resp && resp.status === 200) {
            obj.roles = resp.data.data;
            console.log("roles")
            console.log(obj.roles)
          }
        })
      },

      editUser(user) {
        this.dialogFormVisible = true;
        this.selectedUser = user;
      },

      edit() {
        let obj = this;
        obj.dialogFormVisible = false;
        obj.$axios.post("admin/user/editUser", {
            username: obj.selectedUser.username,
            name: obj.selectedUser.name,
            age: obj.selectedUser.age,
            sex: obj.selectedUser.sex,
            introduce: obj.selectedUser.introduce,
            roleId: obj.selectedUser.roleId
          }
        ).then(res => {
          if (res.data.code === 200) {
            obj.listUsers()
            obj.$message({
              type: 'success',
              message: '修改成功!'
            });
          }
          if (res.data.code === 400) {
            obj.$message.error("出現異常")
            console.log(res.data.message)
          }
        })
      },
    }
  }
</script>

<style scoped>

</style>
