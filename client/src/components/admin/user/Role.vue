<template>
  <div>
    <el-dialog
      title="修改角色信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedRole" style="text-align: left" ref="dataForm">
        <el-form-item label="角色名" label-width="120px" prop="username">
          <el-input v-model="selectedRole.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" label-width="120px" prop="name">
          <el-input v-model="selectedRole.nameZh" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="功能配置" label-width="120px" prop="perms">
          <el-checkbox-group v-model="selectedPermsIds">
            <el-checkbox v-for="(perm,i) in perms" :key="i" :label="perm.id">{{perm.desc}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="菜单配置" label-width="120px" prop="menus">
          <el-tree
            :data="menus"
            :props="props"
            show-checkbox
            :default-checked-keys="selectedMenusIds"
            node-key="id"
            ref="tree">
          </el-tree>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedRole)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>角色配置</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <role-create @onSubmit="listRoles()"></role-create>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="roles"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="adminRole.id"
          label="id"
          width="100">
        </el-table-column>
        <el-table-column
          prop="adminRole.name"
          label="角色名"
          fit>
        </el-table-column>
        <el-table-column
          prop="adminRole.nameZh"
          label="角色描述"
          fit>
        </el-table-column>
        <el-table-column
          label="状态"
          width="100">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.adminRole.enabled"
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
              type="text"
              size="small"
              @click="editRole(scope.row)">
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
  import RoleCreate from './RoleCreate'

  export default {
    name: 'UserRole',
    components: {RoleCreate},
    data() {
      return {
        dialogFormVisible: false,
        roles: [],
        perms: [],
        menus: [],
        selectedRole: [],
        selectedPermsIds: [],
        selectedMenusIds: [],
        props: {
          id: 'id',
          label: 'nameZh',
          children: 'children'
        }
      }
    },
    mounted() {
      this.listRoles();
      this.listPerms();
      this.listMenus();
    },
    computed: {
      tableHeight() {
        return window.innerHeight - 320
      }
    },
    methods: {
      listRoles() {
        var _this = this;
        this.$axios.get('/admin/role/list').then(resp => {
          if (resp && resp.status === 200) {
            _this.roles = resp.data.data
          }
        })
      },
      listPerms() {
        var _this = this;
        this.$axios.get('/admin/role/perm').then(resp => {
          if (resp && resp.status === 200) {
            _this.perms = resp.data.data
          }
        })
      },
      listMenus() {
        var _this = this;
        this.$axios.get('/admin/role/menu').then(resp => {
          if (resp && resp.status === 200) {
            _this.menus = resp.data.data
          }
        })
      },
      commitStatusChange(value, role) {
        if (role.id !== 1) {
          this.$confirm('是否更改角色状态？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$axios.put('/admin/role/status', {
              enabled: value,
              id: role.id
            }).then(resp => {
              if (resp && resp.status === 200) {
                if (value) {
                  this.$message('角色 [' + role.nameZh + '] 已启用')
                } else {
                  this.$message('角色 [' + role.nameZh + '] 已禁用')
                }
              }
            })
          }).catch(() => {
            role.enabled = !role.enabled;
            this.$message({
              type: 'info',
              message: '已取消'
            })
          })
        } else {
          role.enabled = true;
          this.$alert('无法禁用系统管理员！')
        }
      },
      editRole(role) {
        this.dialogFormVisible = true;
        this.selectedRole = role.adminRole;
        this.selectedPermsIds = role.perms;
        let menus = [];
        //如果有子选项，则不能直接把id直接加入，否则会把其下的所有子选项勾选
        for (let i = 0; i < this.menus.length; i++) {
          if (this.menus[i].children.length !== 0) {
            for (let j = 0; j < this.menus[i].children.length; j++) {
              if (role.menus.indexOf(this.menus[i].children[j].id) !== -1) {
                menus.push(this.menus[i].children[j].id)
              }
            }
          } else if (role.menus.indexOf(this.menus[i].id) !== -1) {
            menus.push(this.menus[i].id);
          }
        }
        this.selectedMenusIds = menus;
        // 判断树是否已经加载，第一次打开对话框前树不存在，会报错。所以需要设置 default-checked
        if (this.$refs.tree) {
          this.$refs.tree.setCheckedKeys(this.selectedMenusIds)
        }
      },
      onSubmit(role) {
        let obj = this;
        let menus = this.$refs.tree.getHalfCheckedKeys().concat(this.$refs.tree.getCheckedKeys());
        this.$axios.post('/admin/role/editRole', {
          adminRole: role,
          perms: obj.selectedPermsIds,
          menus: menus
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.dialogFormVisible = false;
            this.listRoles();
            this.$message.success("修改成功")
          }
        });

      }
    }
  }
</script>

<style scoped>
  .add-button {
    float: left;
    margin: 18px 0 18px 10px;
  }
</style>
