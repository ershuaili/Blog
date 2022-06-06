<template>
  <!--登录注册全局背景-->
  <div class="background"/>
  <!--登录和注的册全局组件-->
  <div class="container">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "Login"
}
</script>

<style scoped>
.background {
  background: url("../../assets/login_background.jpg");
  background-size: 100% 100%;
  width: 100%;
  height: 100%;
  top: 0;
  position: fixed;
  z-index: -1024;
}
/*登录背景框*/
.container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 160px auto;
  width: 500px;
  padding: 35px;
  background-color: rgb(249, 247, 247);
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
</style>