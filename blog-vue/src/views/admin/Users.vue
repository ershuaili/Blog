<template>
  <button class="insertButton" type="button" @click="insertUser">新增</button>
  <Popover v-if="is_show">
    <div class="head">修改角色</div>
    <div class="body">
      <div>用户昵称：<input v-model="user.userNickname"/></div>
      <div>用户电话：<input v-model="user.userPhone"/></div>
      <div>用户Q Q：<input v-model="user.userQq"/></div>
      <div>用户邮箱：<input v-model="user.userEmail"/></div>
      <div>用户权限：<input v-model="user.userRights"/></div>
    </div>
    <div class="bottom">
      <button style="background-color: #909399;" @click="cancel">取消</button>
      <button style="background-color: #0171CE;" @click="submit">确定</button>
    </div>
  </Popover>

  <table>
    <thead>
    <tr>
      <td>用户昵称</td>
      <td>用户电话</td>
      <td>用户QQ</td>
      <td>用户邮箱</td>
      <td style="width: 700px">用户头像</td>
      <td>用户权限</td>
      <td style="width: 200px">操作</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item,index) in users" :key="index">
      <td>{{ item.userNickname }}</td>
      <td>{{ item.userPhone }}</td>
      <td>{{ item.userQq }}</td>
      <td>{{ item.userEmail }}</td>
      <td>{{ item.userHeadPortrait }}</td>
      <td>{{ item.userRights }}</td>
      <td>
        <button class="reviseButton" type="button" @click="reviseUser(item.userId)">修改</button>
        <button class="deleteButton" type="button" @click="deleteUser(item.userId)">删除</button>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";
import Popover from "@/components/common/Popover";

export default {
  name: "Users",
  components: {Popover},
  data() {
    return {
      is_show: false,
      url: '',
      users: [
        {
          userId: '',
          userNickname: '',
          userEmail: '',
          userPhone: '',
          userQq: '',
          userHeadPortrait: '',
          userCreateTime: '',
          authorities: [
            {
              authority: '',
            }
          ],
        }
      ],
      user: {
        userId: '',
        userNickname: '',
        userEmail: '',
        userPhone: '',
        userQq: '',
        userHeadPortrait: '',
        userCreateTime: '',
        userRights: '',
      }
    }
  },
  created() {
    this.queryAll();
  },
  methods: {
    insertUser() {
      this.is_show = true;
      this.url = 'user/adminInsertUser'
    },

    // 获取用户
    queryAll() {
      axios.get('/user/queryAll').then(res => {
        this.users = res.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    // 修改用户
    reviseUser(e) {
      axios.get('user/queryUserById', {params: {userId: e}}).then(res => {
        this.is_show = true;
        this.user = res.data
        this.url = 'user/updateUser'
      })
    },
    // 删除用户
    deleteUser(e) {
      console.log(e)
      axios.delete('user/deleteById', {params: {userId: e}}).then(res => {
        console.log(res)
        if (res.status === 200) {
          this.queryAll();
        } else {
          alert("删除错误！")
        }
      })
    },

    // 表单提交
    submit() {
      let params = new URLSearchParams();
      params.append("userId", this.user.userId);
      params.append("userNickname", this.user.userNickname);
      params.append("userEmail", this.user.userEmail);
      params.append("userPhone", this.user.userPhone);
      params.append("userQq", this.user.userQq);
      params.append("userHeadPortrait", this.user.userHeadPortrait);
      params.append("userRights", this.user.userRights);
      if (this.user.userPhone.length !== 11) {
        alert("手机号输入错误");
        return;
      }
      axios.post(this.url, params).then(res => {
        if (res.status === 200 && res.data !== false) {
          this.queryAll();
          this.is_show = false;
        } else {
          this.is_show = false;
          alert("错误，请联系管理员！")
        }
      })
    },
    // 取消
    cancel() {
      this.is_show = !this.is_show;
    },
  },
}
</script>

<style scoped>
@import "../../assets/css/myTable.css";

.insertButton {
  margin: 10px;
}

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