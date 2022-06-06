<template>
  <div class="register_from">
    <h3 class="register_title">系统注册</h3>
    <div class="register_msg">{{ $route.params.message }}</div>
    <div class="register_input">
      <input v-model="registerForm.username" placeholder="账号" type="text" @input="checkFrom()">
      <span v-if="is_username">账号已存在,请选择其他账户</span>
      <input v-model="registerForm.email" placeholder="邮箱" type="text">
      <input v-model="registerForm.qq" placeholder="QQ" type="text">
      <input v-model="registerForm.phone" placeholder="电话" type="text">
      <input v-model="registerForm.password" placeholder="密码" type="password" autoComplete="on">
      <button class="register_button" type="button" @click="register">注 册</button>
    </div>
    <a class="toLogin" @click="toLogin">已有账号点击登录！</a>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "Register",
  data() {
    return {
      registerForm: {
        username: '',
        email: '',
        qq:'',
        phone:'',
        password: '',
      },
      is_username: false,
    }
  },
  methods: {
    checkFrom() {
      this.is_username = false;
      axios.get('user/queryAll').then(res => {
        for (let i = 0; i < res.data.length; i++) {
          if (res.data[i].userNickname === this.registerForm.username) {
            this.is_username = true;
          }
        }
      })
    },

    // 用户注册表单提交
    register() {
      let params = new URLSearchParams();
      params.append("userNickname", this.registerForm.username)
      params.append("userEmail", this.registerForm.email)
      params.append("qq", this.registerForm.qq)
      params.append("phone", this.registerForm.phone)
      params.append("userPassword", this.registerForm.password)
      // 从后端获取数据
      axios.post('/user/insertUser', params)
          .then(successResponse => {
            if (successResponse.data === true) {
              // 注册成功返回登录
              alert("注册成功返回登录");
              this.$router.replace({path: '/login'})
            } else {
              alert("注册失败");
            }
          }).catch(function (error) {
        alert("注册失败");
        console.log(error);
      });
    },

    // 转跳登录页
    toLogin() {
      router.push({name: 'Login'})
    },
  }
}
</script>

<style scoped>
.register_title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #505458;
}

.register_msg {
  text-align: center;
  color: crimson;
}

.register_input {
  position: relative;
  margin: 0 auto;
  width: 95%;
}

.register_input input {
  margin-top: 30px;
  width: 100%;
  height: 45px;
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 7px 0 7px 5px;
  font-size: 20px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
  -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
  transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.register_input input:focus {
  border-color: #66afe9;
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
}

.register_input span {
  color: #F56C6C;
}

.register_button {
  width: 407px;
  height: 45px;
  font-size: 25px;
  border: 1px solid #ccc;
  border-radius: 10px;
  position: relative;
  margin: 30px auto;
  background: #409EFF;
}

.toLogin {
  margin-left: 20px;
  font-size: 15px;
  cursor: pointer;
}
</style>