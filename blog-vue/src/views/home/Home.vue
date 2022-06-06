<template>
  <!--全局背景-->
  <div class="background"/>
  <!--全局导航栏-->
  <UserMenu/>
  <!--主体-->
  <div class="application-main">
    <router-view/>
  </div>
</template>

<script>
import UserMenu from "@/components/home/UserMenu";

export default {
  name: "Home",
  components: {UserMenu}
}
</script>

<style scoped>
.background {
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: -1024;
  background-color: rgb(224, 231, 234);
}

.application-main {
  padding: 50px 0;
}
</style>