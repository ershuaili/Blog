<template>
  <input type="text" class="search" placeholder="搜索商品" v-on:keyup.enter="search" ref="search">
  <div class="box">
    <CommodityCard :child="commodityList"/>
    <div v-if="isFinish" class="finish">没有更多了！</div>
  </div>
</template>

<script>
import CommodityCard from "@/components/home/CommodityCard";
import axios from "axios";

export default {
  name: "Index",
  components: {CommodityCard},
  data() {
    return {
      isFinish: false,
      page: 1,
      commodityList: [],
    }
  },
  created() {
    this.getCommodity();
    this.checkWindow();
  },
  methods: {
    // 商品搜索
    search(){
      axios.get('/commodity/searchCommodityByLimit', {params: {search:this.$refs.search.value}}).then(res => {
        this.commodityList = res.data;
      })
    },
    // 请求商品基本信息，一次十五个
    getCommodity() {
      axios.get('/commodity/queryAllCommodityByLimit', {params: {page: this.page}}).then(res => {
        if (res.data.length !== 30) {
          for (let i = 0; i < res.data.length; i++) {
            this.commodityList.push(res.data[i])
          }
          this.isFinish = true
        } else {
          for (let i = 0; i < res.data.length; i++) {
            this.commodityList.push(res.data[i])
          }
          // 页面加一
          this.page++
        }
      })
    },
    // 监视窗口滚动
    checkWindow() {
      // 提升全局变量
      let _this = this;
      // 监视窗口滚动
      window.onscroll = function () {
        // 变量scrollTop是滚动条滚动时，距离顶部的距离
        const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        // 变量windowHeight是可视区的高度
        const windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
        // 变量scrollHeight是滚动条的总高度
        const scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
        // 滚动条到接近底部的条件
        if (scrollHeight - (scrollTop + windowHeight) < 500) {
          if (!_this.isFinish) {
            if (this.$refs.search.value == null){
              _this.getCommodity();
            }
          }
        }
      }
    },
  },
}
</script>

<style scoped>
.search {
  width: 200px;
  height: 35px;
  position: relative;
  margin: 0 auto;
  top:10px;
  left: 650px
}

.box {
  margin: 30px auto;
  z-index: -1;
  width: 1200px;
  height: auto;
  background: white;
  padding: 30px 0;
  border-radius: 20px;
}

.finish {
  text-align: center;
  margin-top: 50px;
}
</style>