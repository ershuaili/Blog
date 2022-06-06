<template>
  <div class="myBox">
    <!--消息列表-->
    <div v-for="(item,index) in message" :key="index" class="messages">
      <div class="message">
        <!--用户头像-->
        <img alt="" class="message_img" v-bind:src="item.user.userHeadPortrait">
        <!--消息内容-->
        <div class="message_details">
          <div class="message_label">
            <span hidden="hidden">{{ item.user.userId }}</span>
            <span class="message_userName">{{ item.user.userNickname }}</span>
            <span class="message_time">{{ item.messageCreateTime }}</span>
          </div>
          <div class="message_content">{{ item.messageContent }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "Messages",
  data() {
    return {
      message: [
        {
          messageId: "",
          messageContent: "",
          messageCreateTime: "",
          user: {
            userId: "",
            userNickname: "",
            userHeadPortrait: "",
          },
        }
      ]
    }
  },
  created() {
    this.getMessage()
  },
  methods: {
    // 获取评论列表
    getMessage() {
      // 校验登录状态和用户获取用户ID
      let tokenParams = new URLSearchParams();
      tokenParams.append("token", localStorage.getItem("token"))
      axios.post('/user/checkToken', tokenParams).then(resUser => {
        // 获取留言信息
        axios.get('message/queryAllByUserId', {params: {userId: resUser.data.userId}}).then(res => {
          this.message = res.data
        })
      }).catch(function (error) {
        alert("登录信息错误,返回登录");
        router.push("/login");
        console.log(error);
      });
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

.message {
  width: 1160px;
  height: 70px;
  margin: 5px 20px;
  background: #ffffff;
  display: inline-block;
  border-radius: 10px;
  cursor: pointer;
  box-shadow: 0 1px 20px 1px rgba(0, 0, 0, 0.07);
}

.message:hover {
  box-shadow: 0 2px 27px 10px rgba(0, 0, 0, 0.2);
}

.message_img {
  width: 50px;
  height: 50px;
  border-radius: 25px;
  margin: 10px;
  float: left;
}

.message_details {
  height: 70px;
  margin-left: 70px;
}

.message_label {
  height: 20px;
}

.message_content {
  padding: 10px;
  font-size: 20px;
}

/*用户标签*/
.message_label span {
  margin: 10px;
}

.message_userName {
  width: 20px;
  font-family: "Lucida Calligraphy", cursive, serif, sans-serif;
  font-weight: bold;
}

.message_time {
  padding: 0 10px;
  color: #919191;
}

</style>