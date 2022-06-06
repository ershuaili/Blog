<template>
  <div class="login_from">
    <h3 class="login_title">系统登录</h3>
    <div class="login_msg">{{ $route.params.message }}</div>
    <div class="login_input">
      <input class="userName" v-model="loginForm.username" placeholder="账号" type="text"/>
      <input class="password" v-model="loginForm.password" placeholder="密码" type="password" autoComplete="on"/>
      <button class="login_button" type="button" @click="login">登 录</button>
    </div>
    <a class="toRegister" @click="toRegister">没有账号点击注册！</a>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "CheckLogin",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
    }
  },
  methods: {
    login() {
      let params = new URLSearchParams();
      params.append("username", this.loginForm.username)
      params.append("password", this.loginForm.password)
      // 从后端获取数据
      axios.post('/login', params)
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              // 向本地存储中加入token
              localStorage.clear()
              localStorage.setItem('token', successResponse.data.token)
              // 判断是用户还是管理员
              if (successResponse.data.role === "ADMIN") {
                this.$router.push("/admin")
              } else {
                const path = this.$route.query.redirect;
                this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
              }
            } else if (successResponse.data.code === 401) {
              alert("用户名或密码错误");
            }
          }).catch(function (error) {
        alert("用户名或密码错误");
        console.log(error);
      });
    },
    toRegister() {
      router.push({
        path: "/register"
      })
    },
  }
}
</script>

<style scoped>
.login_title {
  margin: 0 auto 30px auto;
  text-align: center;
  color: #505458;
}

.login_msg {
  text-align: center;
  color: crimson;
}

.login_input {
  position: relative;
  margin: 0 auto;
  width: 95%;
}

.login_input input {
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

.login_input input:focus {
  border-color: #66afe9;
  outline: 0;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
}

.login_button {
  width: 407px;
  height: 45px;
  font-size: 25px;
  border: 1px solid #ccc;
  border-radius: 10px;
  position: relative;
  margin: 30px auto;
  background: #409EFF;
}

.toRegister {
  margin-left: 20px;
  font-size: 15px;
  cursor: pointer;
}
</style>