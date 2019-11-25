
<template>
  <div>
    <el-button type="info" @click="addFlag = true, title = '增加'" style="float: right">add</el-button>
    <el-dialog :title="title"  :visible.sync="addFlag">
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="名字" prop="name">
          <el-input v-model="ruleForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input  v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input  v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-on:click="clean">取 消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </div>
    </el-dialog>
  <el-table
    :data="tableData.filter( row => !search || row.userName.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column
      label="Name"
      prop="name" >
      <template slot-scope="scope">
        <span>{{scope.row.userName}}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="Password"
      prop="password">
      <template slot-scope="scope">
        <span>{{scope.row.password}}</span>
      </template>
    </el-table-column>
    <el-table-column
      align="right">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          placeholder="输入关键字搜索" style="float: right"/>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>

    </el-table-column>
  </el-table>
  </div>
</template>


<script>
  export default {
        name: "Index",
        data() {
            var checkName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('名字不能为空'));
                }
                setTimeout(() => {
                    console.log('长度'+value.length);
                    if (value.length>8) {
                        callback(new Error('长度不能大于8'));
                    } else {
                        callback();
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                tableData: [],
                search: '',
                dialogFormVisible: false,
                ruleForm: {
                    password: '',
                    checkPass: '',
                    name: ''
                },
                rules: {
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    name: [
                        { validator: checkName, trigger: 'blur' }
                    ]
                },
                addFlag: false,
                formLabelWidth: '120px',
                recordName: '',
                title: '',

            }
        },
        mounted() {
            this.initUser()
        },
        methods: {
            handleEdit(index, row) {
                console.log(index, row);
                this.ruleForm.name = row.userName;
                this.ruleForm.password = row.password;
                this.recordName = row.userName;
                this.addFlag = true;
                this.title = "修改";
            },
            handleDelete(index, row) {
                this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post('/delete', {userName: row.userName}).then(res =>{
                        if(res.data.code === 200){
                            this.initUser();
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        }
                    }).catch(error =>{
                        console.log(error)
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });



            },
            initUser() {
                this.$axios.post('/index').then(res =>{
                    console.log(res.data);
                    let data = res.data;
                    this.tableData = data
                }).catch(error =>{
                    console.log(error)
                })

            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        console.log("sussce");
                        this.$axios.post('/updateAndAdd', {
                            recordName: this.recordName,
                            userName: this.ruleForm.name,
                            password: this.ruleForm.password
                        }).then(res => {
                            if (res.data.code === 200) {
                                this.$message({
                                    type: 'success',
                                    message: this.title+'成功!'
                                });
                                this.initUser()
                            }
                        }).catch(error => {
                            this.$message({
                                type: 'error',
                                message: '出现异常!'
                            });
                            console.log(error)
                        });
                        this.clean();
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            clean(){
                this.recordName = '';
                this.addFlag = false;
                this.ruleForm.name = '';
                this.ruleForm.password = '';
                this.recordName = '';
                this.ruleForm.checkPass = '';
            }
        }

    }
</script>

<style scoped>

</style>
