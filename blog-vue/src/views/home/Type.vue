<template>
  <div class="main">
    <!-- 左侧导航 -->
    <div class="left-navigation">
      <div v-for="(item,index) in type" :key="index" class="nav-menu" @click="getCommodityByType">
        {{ item.typeName }}
      </div>
    </div>
    <!-- 商品 -->
    <div class="box">
      <CommodityCard :child="commodityList"/>
    </div>
  </div>
</template>

<script>
import CommodityCard from "@/components/home/CommodityCard";
import axios from "axios";

export default {
  name: "Type",
  components: {CommodityCard},
  data() {
    return {
      type: {
        typeId: '',
        typeName: '',
        typeCreateTime: '',
      },
      commodityList: [],
    }
  },
  created() {
    this.getAllType();
  },
  methods: {
    // 获取所有分类信息
    getAllType() {
      axios.get("type/queryAllType").then(res => {
        this.type = res.data
        // 获取初始数据
        axios.get("type/queryAllCommodityByTypeName", {params: {typeName: res.data[0].typeName}}).then(res => {
          this.commodityList = res.data.commodityList
        }).catch(function (error) {
          console.log(error);
        });
      })
    },

    // 分类获取商品信息
    getCommodityByType(e) {
      axios.get("type/queryAllCommodityByTypeName", {params: {typeName: e.target.innerText}}).then(res => {
        this.commodityList = res.data.commodityList
      }).catch(function (error) {
        console.log(error);
      });
    }
  },
}
</script>

<style scoped>
.main {
  margin: 30px auto;
  width: 1410px;
  overflow: hidden;
  z-index: -1;
}

.left-navigation {
  position: fixed;
  float: left;
  width: 200px;
}

.nav-menu {
  background: white;
  height: 70px;
  padding: 25px;
  margin-bottom: 2px;
  line-height: 20px;
  font-size: 20px;
  border-radius: 10px;
}

.box {
  float: right;
  width: 1200px;
  height: auto;
  background: white;
  padding: 30px 0;
  border-radius: 20px;
}
</style>