<template>
  <div class="myBox">
    <div>商品名称：<input v-model="form.commodityName" required="required" type="text"></div>
    <div>商品价格：
      <input v-model="form.commodityPrice" oninput="value=value.replace(/[^\d]/g, '')" required="required" type="text">
    </div>
    <div>商品分类：
      <select v-model="form.formItem.sendValue" class="select" required="required">
        <option value="">请选择商品分类</option>
        <option v-for="(item,index) in releaseType" :key="index" v-bind:value="item.typeId"
                v-text="item.typeName"></option>
      </select>
    </div>
    <div>
      <textarea v-model="form.commodityDescribe" placeholder="写点东西介绍一下您的商品吧 ( •͈ᴗ⁃͈)ᓂ- - -♡" required="required"/>
    </div>
    <div class="upload">
      <input id="filed" hidden="" type="file" @change="filePreview">
      <p @click="selectImg()">
        <span v-if="textHide">点击上传图片</span>
        <img :src="src" alt="" style="height: 200px"/>
      </p>
    </div>
    <button @click="submit">发布</button>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  data() {
    return {
      src: '',
      textHide: true,
      files: '',
      form: {
        commodityName: '',
        commodityPrice: '',
        commodityDescribe: '',
        formItem: {
          sendValue: ''
        },
      },
      releaseType: [
        {
          typeId: '',
          typeName: ''
        }
      ]
    }
  },
  created() {
    // 获取分类信息
    axios.get('type/queryAllType').then(res => {
      this.releaseType = res.data
    })
  },
  methods: {
    // 照片展示
    selectImg: function () {
      document.getElementById('filed').click()
    },
    filePreview(e) {
      let _this = this
      const files = e.target.files[0];
      if (files.size <= 1048576) {
        this.files = files
        if (!e || !window.FileReader) return  // 判断是否支持FileReader
        let reader = new FileReader()
        reader.readAsDataURL(files) // 文件转换
        reader.onloadend = function () {
          _this.src = this.result
          _this.textHide = false;
        }
      } else {
        alert('上传文件不能大于1M，请重新选择')
      }
    },

    // 商品发布
    submit() {
      console.log('商品发布')
      // 校验登录状态和用户获取用户ID
      let tokenParams = new URLSearchParams();
      tokenParams.append("token", localStorage.getItem("token"))
      axios.post('/user/checkToken', tokenParams).then(resUser => {
        // 上传照片获取照片路径
        if (this.files !== '') {
          const formFile = new FormData();
          formFile.append("file", this.files);
          axios.post('/file/upload', formFile).then(res => {
            // 发布用户ID，商品分类ID，商品照片路径，商品描述，商品价格，商品名称
            let params = new URLSearchParams();
            params.append("commodityUserId", resUser.data.userId)
            params.append("commodityTypeId", this.form.formItem.sendValue)
            params.append("commodityPicture", res.data)
            params.append("commodityDescribe", this.form.commodityDescribe)
            params.append("commodityPrice", this.form.commodityPrice)
            params.append("commodityName", this.form.commodityName)
            // 发布商品
            axios.post('commodity/insertCommodity', params).then(res => {
              if (res.data) {
                alert("商品发布成功")
                router.push('/index')
              } else {
                alert("商品发布失败，请重新尝试。")
              }
            })
          })
        } else {
          alert("请选择照片")
        }
      }).catch(function (error) {
        alert("登录信息错误,返回登录");
        router.push("/login");
        console.log(error);
      });
    },
  },
}
</script>

<style scoped>
@import "../../assets/css/myBox.css";

.myBox {
  font-size: 30px;
}

input, select {
  margin-top: 30px;
  width: 50%;
  height: 45px;
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 7px 0 7px 5px;
  font-size: 20px;
  -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
  -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
  transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

textarea {
  margin-top: 10px;
  padding: 5px;
  width: 770px;
  height: 190px;
  box-sizing: border-box;
  resize: none;
  border: 1px solid #ccc;
  border-radius: 10px;
  outline: none;
  font-size: 17px;
  font-family: 'Mulish', -apple-system, 'Noto Serif SC', "PingFang SC", "Microsoft Yahei UI", "Microsoft Yahei", sans-serif;
  background: url("../../assets/comment_bg.png") 100% 100% no-repeat;
}

button {
  width: 90px;
  height: 40px;
  font-size: 20px;
  background-color: #67C23A;
}
</style>