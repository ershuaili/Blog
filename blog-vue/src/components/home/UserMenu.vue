<template>
  <div class="nav">
    <!-- 导航栏-头部 -->
    <div class="logo">
      <span>YiYi</span>
    </div>
    <!-- 导航栏-左侧 -->
    <div class="nav_left">
      <router-link v-for="(item,index) in navList" :key="index" :to="item.name" active-class="nav-menu-active"
                   class="nav_menu" @click="getTopNav()">
        <h1>{{ item.navItem }}</h1>
      </router-link>
    </div>
  </div>

  <div class="main">
    <slot/>
  </div>
</template>

<script>
export default {
  name: "AdminMenu",
  data() {
    return {
      // 后台侧边栏列表
      navList: [
        {name: '/admin/users', navItem: '用户管理'},
        {name: '/admin/commodity', navItem: '物品管理'},
        {name: '/admin/types', navItem: '分类管理'},
        {name: '/admin/system', navItem: '系统管理'},
      ],
      // 后台顶部导航栏
      topNav: [],
      // 导航栏展示
    }
  },
  created() {
    this.getTopNav()
  },
  methods: {
    // 获取头部导航栏
    getTopNav() {
      this.topNav = []
      setTimeout(() => {
        let route = this.$route.matched[1].children;
        // 如果数组不为空
        if (route.length) {
          for (let i = 0; i < route.length; i++) {
            // 向topNav写入数据
            this.topNav.push({name: route[i].path, navItem: route[i].meta.nav})
          }
        }
      }, 10)
    }
  },
}
</script>

<style scoped>
/*!* 页面导航栏 *!*/
.nav {
  z-index: 1024;
}

/* 导航栏头部 */
.logo {
  float: left;
  position: fixed;
  width: 260px;
  height: 50px;
  top: 0;
  margin-top: 0;
  background: #357CA5;
  font-size: 40px;
  text-align: center;
}

/* 导航栏左侧 */
.nav_left {
  float: left;
  position: fixed;
  width: 260px;
  height: 100%;
  top: 0;
  margin-top: 50px;
  background: rgba(84, 92, 100);
}

.nav_menu {
  height: 50px;
  margin-top: 10px;
  overflow: hidden;
  display: block;
  color: rgba(255, 255, 255, 0.6);
}

.nav-menu-active {
  height: 50px;
  margin-top: 10px;
  overflow: hidden;
  display: block;
  background-color: #1990FF;
}

.nav_menu img {
  display: inline-block;
  float: left;
  padding-top: 15px;
  padding-left: 10px;
  width: 20px;
}

.nav_menu h1 {
  display: inline-block;
  float: left;
  font-size: 20px;
  padding-left: 10px;
}

.main {
  margin-left: 260px;
  padding-top: 0;
}
</style>