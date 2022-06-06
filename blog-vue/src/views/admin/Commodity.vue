<template>
  <Popover v-if="is_show">
    <div class="head">修改角色</div>
    <div class="body">
      <div>商品名称：<input v-model="commodity.commodityName"/></div>
      <div>商品价格：<input v-model="commodity.commodityPrice"/></div>
      <div>商品分类ID：<input v-model="commodity.commodityTypeId"/></div>
      <div>商品用户ID：<input v-model="commodity.commodityUserId"/></div>
      <div>商品详情：<input v-model="commodity.commodityDescribe"/></div>
      <div>商品状态：
        <select class="select" required="required" v-model="commodity.commodityIsFinish">
          <option v-bind:value="0">未出售</option>
          <option v-bind:value="1">待发货</option>
          <option v-bind:value="2">已售出</option>
        </select>
      </div>
    </div>
    <div class="bottom">
      <button style="background-color: #909399;" @click="cancel">取消</button>
      <button style="background-color: #0171CE;" @click="submit">确定</button>
    </div>
  </Popover>

  <table>
    <thead>
    <tr>
      <td>商品名称</td>
      <td>商品价格</td>
      <td>商品分类ID</td>
      <td>商品用户ID</td>
      <td style="width: 600px">商品详情</td>
      <td>是否售出</td>
      <td style="width: 180px">操作</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item,index) in commodityList" :key="index">
      <td>{{ item.commodityName }}</td>
      <td>{{ item.commodityPrice }}</td>
      <td>{{ item.commodityTypeId }}</td>
      <td>{{ item.commodityUserId }}</td>
      <td>{{ item.commodityDescribe }}</td>
      <td>{{ item.commodityIsFinish }}</td>
      <td>
        <button class="reviseButton" type="button" @click="reviseCommodity(item.commodityId)">修改</button>
        <button class="deleteButton" type="button" @click="deleteCommodity(item.commodityId)">删除</button>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";
import Popover from "@/components/common/Popover";

export default {
  name: "Commodity",
  components: {Popover},
  data() {
    return {
      is_show: false,
      commodityList: [
        {
          commodityName: '',
          commodityPrice: '',
          commodityTypeId: '',
          commodityUserId: '',
          commodityDescribe: '',
          commodityIsFinish: '',
          commodityPicture: '',
        }
      ],
      commodity: {
        commodityId: '',
        commodityName: '',
        commodityPrice: '',
        commodityTypeId: '',
        commodityUserId: '',
        commodityDescribe: '',
        commodityIsFinish: '',
      },
    }
  },
  created() {
    this.getCommodity();
  },
  methods: {
    // 获取商品信息
    getCommodity() {
      axios.get('/commodity/queryAllCommodity').then(res => {
        this.commodityList = res.data
      })
    },
    // 修改商品
    reviseCommodity(e) {
      this.is_show = true;
      axios.get('commodity/queryCommodityById', {params: {id: e}}).then(res => {
        this.commodity = res.data
      })
    },
    // 删除商品
    deleteCommodity(e) {
      axios.delete('commodity/deleteById', {params: {commodityId: e}}).then(res => {
        if (res.status === 200) {
          this.getCommodity();
        } else {
          alert("删除失败")
        }
      })
    },
    // 确定
    submit() {
      let params = new URLSearchParams();
      params.append("commodityId", this.commodity.commodityId);
      params.append("commodityName", this.commodity.commodityName);
      params.append("commodityPrice", this.commodity.commodityPrice);
      params.append("commodityTypeId", this.commodity.commodityTypeId);
      params.append("commodityUserId", this.commodity.commodityUserId);
      params.append("commodityDescribe", this.commodity.commodityDescribe);
      params.append("commodityIsFinish", this.commodity.commodityIsFinish);
      axios.post('commodity/updateCommodity',params).then(res=>{
        if (res.status===200){
          this.getCommodity();
          this.is_show=false;
        }else {
          alert("修改错误。")
        }
      })
    },
    // 取消
    cancel() {
      this.is_show = false;
      console.log("取消")
    }

  }
}
</script>

<style scoped>
@import "../../assets/css/myTable.css";

:deep(.content) {
  width: 600px;
  height: 600px;
  position: relative;
  margin: 100px auto;
  padding: 0;
  border-radius: 0;
}

.head {
  background: #e7e7e7;
  height: 50px;
  text-align: left;
  line-height: 50px;
  padding-left: 20px;
  font-size: 20px;
}

.body {
  font-size: 20px;
}

.bottom {
  background: #e7e7e7;
  height: 50px;
  width: 600px;
  position: absolute;
  bottom: 0;
}

.bottom button {
  width: 60px;
  height: 40px;
  margin: 5px;
  font-size: 20px;
  float: right;
}
</style>