<template>
  <button class="insertButton" type="button" @click="insertType">新增</button>
  <Popover v-if="is_show">
    <div class="head">修改分类名称</div>
    <div class="body">
      <div>分类名称：<input v-model="type.typeName"/></div>
    </div>
    <div class="bottom">
      <button style="background-color: #909399;" @click="cancel">取消</button>
      <button style="background-color: #0171CE;" @click="submit">确定</button>
    </div>
  </Popover>

  <table>
    <thead>
    <tr>
      <td>分类ID</td>
      <td>分类名称</td>
      <td>分类创建时间</td>
      <td style="width: 200px">操作</td>
    </tr>
    </thead>
    <tbody>
    <tr v-for="(item,index) in types" :key="index">
      <td>{{ item.typeId }}</td>
      <td>{{ item.typeName }}</td>
      <td>{{ item.typeCreateTime }}</td>
      <td>
        <button class="reviseButton" type="button" @click="reviseType(item.typeId)">修改</button>
        <button class="deleteButton" type="button" @click="deleteType(item.typeId)">删除</button>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";
import Popover from "@/components/common/Popover";

export default {
  name: "Type",
  components: {Popover},
  data() {
    return {
      is_show: false,
      url: '',
      types: [
        {
          typeId: '',
          typeName: '',
          typeCreateTime: '',
        }
      ],
      type: {
        typeId: '',
        typeName: '',
        typeCreateTime: '',
      }
    }
  },
  created() {
    this.queryAll();
  },
  methods: {
    insertType() {
      this.is_show = true;
      this.url = 'type/insertType'
    },

    // 获取用户
    queryAll() {
      axios.get('/type/queryAllType').then(res => {
        this.types = res.data;
      }).catch(function (error) {
        console.log(error);
      });
    },
    // 修改分类
    reviseType(e) {
      axios.get('type/queryTypeById', {params: {typeId: e}}).then(res => {
        this.is_show = true;
        this.type = res.data
        this.url = 'type/updateType'
      })
    },
    // 删除用户
    deleteType(e) {
      console.log(e)
      axios.delete('type/deleteById', {params: {typeId: e}}).then(res => {
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
      params.append("typeId", this.type.typeId);
      params.append("typeName", this.type.typeName);
      axios.post(this.url, params).then(res => {
        if (res.status === 200) {
          this.queryAll();
          this.is_show = false;
        } else {
          this.is_show = false;
          alert("修改错误！")
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