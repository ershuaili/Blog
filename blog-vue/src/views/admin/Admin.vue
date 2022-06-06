<template>
  <AdminNavMenu>
    <!--后台全局组件-->
    <router-view/>
  </AdminNavMenu>
</template>

<script>
import router from "@/router";
import axios from "axios";
import AdminNavMenu from "@/components/admin/AdminMenu";

export default {
  name: "Admin",
  components: {AdminNavMenu},
  // 管理员登录前校验
  beforeRouteEnter: (to, from, next) => {
    let params = new URLSearchParams();
    params.append("token", localStorage.getItem("token"))
    axios.post('/user/checkToken', params)
        .then(successResponse => {
          if (successResponse.data.userRights === "ADMIN") {
            next();
          } else {
            router.push({
              name: 'Login',
              params: {message: '没有权限请登录'}
            });
          }
        }).catch(function (error) {
      console.log(error);
      router.push({
        name: 'Login',
        params: {message: '没有权限请登录'}
      });
    });
  },
}
</script>

<style scoped>

</style>