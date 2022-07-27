<template>
  <div class="nav-bar">
    <router-link v-for="(item,index) in navList" :key="index" :to="item.name" active-class="nav-menu-active" class="nav-menu">
      <img :src="require('../../assets/svg/'+item.svg)" alt="">
      {{ item.navItem }}
    </router-link>
  </div>
</template>
<script>
export default {
  name: 'UserMenu',
  data() {
    return {
      navList: [
        {name: '/type', navItem: '分类', svg: 'type.svg'},
        {name: '/label', navItem: '标签', svg: 'release.svg'},
        {name: '/message', navItem: '留言板', svg: 'messages.svg'},
        {name: '/picture', navItem: '照片墙', svg: 'my.svg'},
        {name: '/archive', navItem: '归档', svg: 'my.svg'},
        {name: '/time', navItem: '时光机', svg: 'my.svg'},
        {name: '/about', navItem: '关于我', svg: 'my.svg'},
      ],
    }
  },
}
</script>

<style scoped>
/*!* 导航栏 *!*/
.nav-bar {
  /*position: fixed;*/
  height: 50px;
  width: 100%;
  background: rgb(75, 75, 75);
  text-align: center;
}

/* 导航栏按钮 */
.nav-menu {
  text-decoration: none;
  color: rgba(255, 255, 255, 0.6);
  padding: 5px 20px;
  line-height: 50px;
  margin: 20px;
}

.nav-menu img {
  width: 15px;
  filter: invert(100%);
  margin-right: 10px;
}

/*选中显示样式*/
.nav-menu-active {
  text-decoration: none;
  background-color: rgba(255, 255, 255, 0.2);
  border-radius: 25px;
  color: #ffffff;
  line-height: 50px;
  padding: 5px 20px;
  margin: 20px;
}
</style>