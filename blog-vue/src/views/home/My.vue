<template>
  <div class="myBox">
    <div class="user_box">
      <div>
        <img alt="" class="message_img" v-bind:src="user.userHeadPortrait">
      </div>
      <div class="user_info">
        <div>昵称：{{ user.userNickname }}</div>
        <div>Q Q：{{ user.userQq }}</div>
        <div>电话：{{ user.userPhone }}</div>
        <div>邮箱：{{ user.userEmail }}</div>
      </div>
    </div>
    <div class="content_box">
      <div class="con_head">
        <span @click="myRelease">我发布的</span>
        <span @click="myShip">待发货的</span>
        <span @click="mySell">我卖出的</span>
      </div>
      <CommodityCard :child="commodityList"/>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";
import CommodityCard from "@/components/home/CommodityCard";

export default {
  name: "My",
  components: {CommodityCard},
  data() {
    return {
      user: {
        userId: '',
        userNickname: '',
        userEmail: '',
        userPhone: '',
        userQq: '',
        userPassword: '',
        userHeadPortrait: '',
        userCreateTime: '',
      },
      commodityList: [],
    }
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      // 校验登录状态和用户获取用户ID
      let tokenParams = new URLSearchParams();
      tokenParams.append("token", localStorage.getItem("token"))
      axios.post('/user/checkToken', tokenParams).then(resUser => {
        axios.get('user/queryUserById', {params: {userId: resUser.data.userId}}).then(res => {
          this.user = res.data
          this.myRelease();
        })
      }).catch(function (error) {
        alert("登录信息错误,返回登录");
        router.push("/login");
        console.log(error);
      });
    },

    myRelease() {
      axios.get('commodity/queryReleaseCommodityByUserId', {params: {userId: this.user.userId}}).then(res => {
        this.commodityList = res.data;
      })
    },

    myShip(){
      axios.get('commodity/queryShipCommodityByUserId', {params: {userId: this.user.userId}}).then(res => {
        this.commodityList = res.data;
      })
    },

    mySell() {
      axios.get('commodity/querySoldCommodityByUserId',{params:{userId:this.user.userId}}).then(res=>{
        this.commodityList=res.data;
      })
    },
  }
}
</script>

<style scoped>
@import "../../assets/css/myBox.css";

.myBox {
  text-align: left;
  line-height: normal;
}

.user_box {
  width: 100%;
  height: 350px;
}

.message_img {
  width: 200px;
  height: 200px;
  border-radius: 100px;
  margin: 65px;
  display: inline-block;
  float: left;
}

.user_info {
  height: 350px;
  width: 870px;
  display: inline-block;
  float: left;
  font-size: 30px;
}

.user_info div {
  padding: 20px;
}

.content_box {
  padding-bottom: 20px;
  min-height: 400px;
  background: #F3F5F9;
}

.con_head {
  font-size: 30px;
  height: 40px;
  background: #ffffff;
}

.con_head span {
  margin: 0 140px;
  cursor: pointer;
}
</style>