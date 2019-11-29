<template>
  <body id="poster">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="login-container" >
      <h3 class="login_title">系统登录</h3>
      <el-form-item label="用户名" prop="name">
        <el-input v-model="ruleForm.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>

        <el-form-item label="确认密码" prop="checkPass" v-if="show">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="Login('ruleForm')">登录</el-button>
        <el-button @click="Register('ruleForm')">注册</el-button>
      </el-form-item>
    </el-form>

  </body>
</template>

<script>

    export default {
        name: "Login",
        data() {
            var checkAge = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户名不能为空'));
                }
                setTimeout(() => {
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
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                show: false,
                ruleForm: {
                    pass: '',
                    name: '',
                    checkPass: '',

                },
                rules: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    name: [
                        { validator: checkAge, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ]
                }
            };
        },
        mounted() {

        },
        methods: {
            Login(formName) {
                if(!this.show){
                  this.$refs[formName].validate((valid) => {
                          if (valid) {
                              this.$axios.post('/login', {
                                  username: this.ruleForm.name,
                                  password: this.ruleForm.pass
                              }).then(res => {
                                  if (res.data.code === 200) {
                                      this.$message({
                                          type: 'success',
                                          message: res.data.message
                                      });
                                      this.$store.commit('login', res.data.data);
                                      this.$router.push("/index");
                                  }
                                  if (res.data.code === 400) {
                                      this.$message({
                                          type: 'error',
                                          message: res.data.message
                                      });
                                  }
                              }).catch(error => {
                                  this.$message({
                                      type: 'error',
                                      message: '出现异常!'
                                  });
                                  console.log(error)
                              });
                          } else {
                              console.log('error submit!!');
                              return false;
                          }
                  });
                }else{
                    this.show = false
                }
            },
            Register(formName){
                if(this.show) {
                    this.$refs[formName].validate((valid) => {
                        if (valid) {
                            this.$axios.post('/register', {
                                username: this.ruleForm.name,
                                password: this.ruleForm.pass
                            }).then(res => {
                                console.log(res)
                                if (res.data.code === 200) {
                                    this.$message({
                                        type: 'success',
                                        message: res.data.message
                                    });
                                }
                                if (res.data.code === 400) {
                                    this.$message({
                                        type: 'error',
                                        message: res.data.message
                                    });
                                }
                            }).catch(error => {
                                this.$message({
                                    type: 'error',
                                    message: "未知错误"
                                });
                                console.log(error.data.message)
                            });
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
                    });
                }else{
                    this.show = true
                }
            }
        }
    }

</script>

<style scoped>

  #poster {
    background:url("../assets/Lover.jpg") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 16% auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
</style>
